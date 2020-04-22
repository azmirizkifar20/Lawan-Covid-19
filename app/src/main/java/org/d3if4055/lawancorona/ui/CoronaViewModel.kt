package org.d3if4055.lawancorona.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.d3if4055.lawancorona.network.ApiCorona
import org.d3if4055.lawancorona.network.DataIndonesia
import org.d3if4055.lawancorona.network.DataProvinsi

@Suppress("SpellCheckingInspection")
class CoronaViewModel : ViewModel() {

    // buat tempat penampung data
    private val _data = MutableLiveData<DataIndonesia>()
    val data: LiveData<DataIndonesia>
        get() = _data

    private val _dataProvinsi = MutableLiveData<List<DataProvinsi>>()
    val dataProvinsi: LiveData<List<DataProvinsi>>
        get() = _dataProvinsi

    private val _response = MutableLiveData<String>()
    val response : LiveData<String>
        get() = _response

    private var job = Job()
    private val uiScope = CoroutineScope(job + Dispatchers.Main)

    init {
        _response.value = ""
        initData()
    }

    private fun initData() {
        uiScope.launch {
            try {
                val result = ApiCorona.retrofitService.showData()
                val resultProvinsi = ApiCorona.retrofitService.showDataProvinsi()

                if (resultProvinsi.isNotEmpty()) {
                    _dataProvinsi.value = resultProvinsi
                    result.map {
                        _data.value = it
                    }
                    _response.value = "Berhasil ambil data!"
                } else {
                    _response.value = "Data kosong!"
                }
            } catch (t: Throwable){
                _response.value = "Tidak ada koneksi internet!"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}