package org.marproject.lawancovid19

import android.app.Application
import android.os.Build
import androidx.work.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.marproject.lawancovid19.work.RefreshDataWorker
import java.util.concurrent.TimeUnit

@Suppress("SpellCheckingInspection")
class LawanCovidApplication : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        applicationScope.launch {
            setupWorker()
        }
    }

    private fun setupWorker() {
        val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setRequiresDeviceIdle(true)
            }
        }.build()

        // create task
        val task = OneTimeWorkRequest.Builder(RefreshDataWorker::class.java).setConstraints(constraints).build()
        val workManager = WorkManager.getInstance()
        workManager.enqueue(task)
    }

}