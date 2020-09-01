package ru.pestoff.contactlist.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import ru.pestoff.contactlist.R
import ru.pestoff.contactlist.model.Person
import ru.pestoff.contactlist.service.PersonService
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledThreadPoolExecutor
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val executors = ScheduledThreadPoolExecutor(10)

        executors.execute {
            val service = PersonService.create()

            val call = service.getPersons()

            call.enqueue(object: Callback<List<Person>> {
                override fun onFailure(call: Call<List<Person>>, t: Throwable) {
                    Log.e("CONTACTLIST", t.message!!)
                }

                override fun onResponse(
                    call: Call<List<Person>>,
                    response: Response<List<Person>>
                ) {
                    val persons = response.body()!!

                    persons.forEach {
                        Log.i("CONTACTLIST",it.name)
                    }
                }
            })

        }
    }
}