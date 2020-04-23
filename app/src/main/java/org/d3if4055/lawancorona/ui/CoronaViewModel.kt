package org.d3if4055.lawancorona.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.d3if4055.lawancorona.database.*
import org.d3if4055.lawancorona.repository.IndonesiaRepository
import org.d3if4055.lawancorona.repository.ProvinsiRepository

@Suppress("SpellCheckingInspection")
class CoronaViewModel(application: Application) : AndroidViewModel(application) {

    private val indonesiaDao: DataIndonesiaDao = CoronaDatabase.getInstance(application).dataIndonesiaDao
    private val provinsiDao: DataProvinsiDao = CoronaDatabase.getInstance(application).dataProvinsiDao
    private val indonesiaRepository: IndonesiaRepository = IndonesiaRepository(indonesiaDao)
    private val provinsiRepository: ProvinsiRepository = ProvinsiRepository(provinsiDao)

    // buat tempat response
    private val _dataIndo: LiveData<List<DataIndonesiaDB>>
    val dataIndo : LiveData<List<DataIndonesiaDB>>
        get() = _dataIndo

    private val _dataProv: LiveData<List<DataProvinsiDB>>
    val dataProv : LiveData<List<DataProvinsiDB>>
        get() = _dataProv

    private val _response = MutableLiveData<String>()
    val response : LiveData<String>
        get() = _response

    // handling async
    private var job = Job()
    private val uiScope = CoroutineScope(job + Dispatchers.Main)

    init {
        _response.value = ""
        uiScope.launch {
            try {
                indonesiaRepository.refreshDataIndo()
                provinsiRepository.refreshDataProv()
                _response.value = "Sinkronisasi berhasil!"

            } catch (t: Throwable){
                _response.value = "okokok"
            }
        }
        _dataIndo = indonesiaRepository.indonesia
        _dataProv = provinsiRepository.provinsi
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}