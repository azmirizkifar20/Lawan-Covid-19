package org.marproject.lawancovid19.work

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import org.marproject.lawancovid19.R
import org.marproject.lawancovid19.database.CoronaDatabase
import org.marproject.lawancovid19.database.DataGlobalDao
import org.marproject.lawancovid19.database.DataIndonesiaDao
import org.marproject.lawancovid19.database.DataProvinsiDao
import org.marproject.lawancovid19.repository.GlobalRepository
import org.marproject.lawancovid19.repository.IndonesiaRepository
import org.marproject.lawancovid19.repository.ProvinsiRepository
import retrofit2.HttpException

@Suppress("SpellCheckingInspection")
class RefreshDataWorker(
    appContext: Context, params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        val indonesiaDao: DataIndonesiaDao = CoronaDatabase.getInstance(applicationContext).dataIndonesiaDao
        val provinsiDao: DataProvinsiDao = CoronaDatabase.getInstance(applicationContext).dataProvinsiDao
        val globalDao: DataGlobalDao = CoronaDatabase.getInstance(applicationContext).dataGlobalDao

        // get repository
        val indonesiaRepository = IndonesiaRepository(indonesiaDao)
        val provinsiRepository = ProvinsiRepository(provinsiDao)
        val globalRepository = GlobalRepository(globalDao)

        return try {
            Log.i("testingWorker", "Worker manager is running!")
            indonesiaRepository.refreshDataIndo()
            provinsiRepository.refreshDataProv()
            globalRepository.refreshDataPositif()

            // display notif
            displayNotification()
            Result.success()
        } catch (exception: HttpException) {
            Log.i("testingWorker", "Mengulang service")
            Result.retry()
        }

    }

    private fun displayNotification() {
        val manager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("notifyData", "notifyData", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(applicationContext, "notifyData")
            .setContentTitle("Data Covid-19")
            .setContentText("Data Covid-19 telah diperbaharui")
            .setSmallIcon(R.drawable.ic_notif)

        manager.notify(1, builder.build())
    }

}