package ru.pestoff.contactlist.viewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.pestoff.contactlist.model.Person
import ru.pestoff.contactlist.repository.PersonRepository

class ListPersonViewModel(val repository: PersonRepository) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Boolean>()
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
                isError.postValue(false)
                persons.postValue(it)
                isLoading.postValue(false)
            }, {
                isError.postValue(true)
                isLoading.postValue(false)
            })
    }
}