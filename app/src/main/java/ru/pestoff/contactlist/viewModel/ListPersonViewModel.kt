package ru.pestoff.contactlist.viewModel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.pestoff.contactlist.model.Person
import ru.pestoff.contactlist.repository.PersonRepository
import java.util.concurrent.ScheduledThreadPoolExecutor

class ListPersonViewModel(val repository: PersonRepository) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val persons = MutableLiveData<List<Person>>()

    fun loadPerson() {
        val executors = ScheduledThreadPoolExecutor(10)

        executors.execute {
            val call = repository.getPersons()

            call.enqueue(object : Callback<List<Person>> {
                override fun onFailure(call: Call<List<Person>>, t: Throwable) {
                    Log.e("CONTACTLIST", t.message!!)
                }

                override fun onResponse(
                    call: Call<List<Person>>,
                    response: Response<List<Person>>
                ) {
                    persons.postValue(response.body())
                }
            })
        }
    }
}