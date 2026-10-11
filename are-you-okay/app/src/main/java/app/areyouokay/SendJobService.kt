package app.areyouokay

import android.app.job.JobInfo
import android.app.job.JobParameters
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.ComponentName
import android.content.Context

/**
 * Delivers the check-in message to every link. Uses Android's built-in JobScheduler,
 * so if the phone is offline the message waits and is sent as soon as it reconnects.
 */
class SendJobService : JobService() {
    @Volatile private var worker: Thread? = null

    override fun onStartJob(params: JobParameters): Boolean {
        if (params.jobId == HELP_JOB_ID) {
            worker = Thread { jobFinished(params, !sendHelp()) }.also { it.start() }
            return true
        }
        worker = Thread {
            val prefs = Prefs(this)
            val message = prefs.pendingMessage
            val results = prefs.pendingLinks.associateWith { CheckIn.send(it, message) }
            val done = results.filterValues { it != CheckIn.Result.RETRY }.keys
            val broken = results.filterValues { it == CheckIn.Result.BROKEN_LINK }.keys
            // Only clear what was delivered, and only for this message: a newer check-in
            // made while we were sending keeps its own list.
            val stillPending = synchronized(CheckIn) {
                if (prefs.pendingMessage == message) {
                    prefs.pendingLinks = prefs.pendingLinks - done
                    prefs.brokenLinks = prefs.brokenLinks + broken
                }
                prefs.pendingLinks
            }
            if (stillPending.isEmpty() && prefs.brokenLinks.isEmpty()) prefs.lastDeliveredMillis = System.currentTimeMillis()
            jobFinished(params, stillPending.isNotEmpty())
        }.also { it.start() }
        return true
    }

    /** Returns true when nothing is left to send. */
    private fun sendHelp(): Boolean {
        val prefs = Prefs(this)
        val message = prefs.helpMessage
        val done = prefs.helpPendingLinks.filter {
            CheckIn.send(Links.helpTarget(it), message, Links.helpHeaders(it)) != CheckIn.Result.RETRY
        }.toSet()
        return synchronized(CheckIn) {
            if (prefs.helpMessage == message) prefs.helpPendingLinks = prefs.helpPendingLinks - done
            prefs.helpPendingLinks.isEmpty()
        }
    }

    override fun onStopJob(params: JobParameters): Boolean {
        worker?.interrupt()
        return true
    }

    companion object {
        private const val JOB_ID = 1
        private const val HELP_JOB_ID = 2

        fun schedule(context: Context, help: Boolean = false) {
            val job = JobInfo.Builder(if (help) HELP_JOB_ID else JOB_ID, ComponentName(context, SendJobService::class.java))
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                // Retry an urgent alert quickly; check-ins can wait longer.
                .setBackoffCriteria(if (help) 10_000L else 30_000L, JobInfo.BACKOFF_POLICY_EXPONENTIAL)
                .setPersisted(true)
                .build()
            context.getSystemService(JobScheduler::class.java).schedule(job)
        }
    }
}
