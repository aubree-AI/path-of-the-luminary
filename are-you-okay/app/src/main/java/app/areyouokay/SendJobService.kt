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
        worker = Thread {
            val prefs = Prefs(this)
            val message = prefs.pendingMessage
            val sent = prefs.pendingLinks.filter { CheckIn.post(it, message) }.toSet()
            // Only clear what was delivered, and only for this message: a newer check-in
            // made while we were sending keeps its own list.
            val stillPending = synchronized(CheckIn) {
                if (prefs.pendingMessage == message) prefs.pendingLinks = prefs.pendingLinks - sent
                prefs.pendingLinks
            }
            if (stillPending.isEmpty()) prefs.lastDeliveredMillis = System.currentTimeMillis()
            jobFinished(params, stillPending.isNotEmpty())
        }.also { it.start() }
        return true
    }

    override fun onStopJob(params: JobParameters): Boolean {
        worker?.interrupt()
        return true
    }

    companion object {
        private const val JOB_ID = 1

        fun schedule(context: Context) {
            val job = JobInfo.Builder(JOB_ID, ComponentName(context, SendJobService::class.java))
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setBackoffCriteria(30_000L, JobInfo.BACKOFF_POLICY_EXPONENTIAL)
                .setPersisted(true)
                .build()
            context.getSystemService(JobScheduler::class.java).schedule(job)
        }
    }
}
