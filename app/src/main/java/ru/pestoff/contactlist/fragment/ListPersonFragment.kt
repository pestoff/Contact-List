package ru.pestoff.contactlist.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.pestoff.contactlist.R
import ru.pestoff.contactlist.adapter.PersonAdapter
import ru.pestoff.contactlist.model.Person
import ru.pestoff.contactlist.service.PersonService
import java.util.concurrent.ScheduledThreadPoolExecutor

class ListPersonFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_person_fragment, container, false)

        initRecyclerView(view)

        return view
    }


    fun initRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(activity)

        val personAdapter = PersonAdapter()
        recyclerView.adapter = personAdapter



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
                    personAdapter.persons = response.body()!!
                }
            })

        }



    }
}