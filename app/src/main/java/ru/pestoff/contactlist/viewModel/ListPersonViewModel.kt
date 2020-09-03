package ru.pestoff.contactlist.viewModel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.pestoff.contactlist.model.Person
import ru.pestoff.contactlist.repository.PersonRepository
import java.util.concurrent.ScheduledThreadPoolExecutor

class ListPersonViewModel(val repository: PersonRepository) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val persons = MutableLiveData<List<Person>>()

    init {
        loadPerson()
    }

    fun loadPerson() {

        isLoading.postValue(true)

        repository.getPersons()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                persons.postValue(it)
                isLoading.postValue(false)
            }, {
                Log.e("CONTACTLIST", it.message!!)
            })
    }
}