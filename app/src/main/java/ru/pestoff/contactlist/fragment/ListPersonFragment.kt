package ru.pestoff.contactlist.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.pestoff.contactlist.R
import ru.pestoff.contactlist.adapter.PersonAdapter
import ru.pestoff.contactlist.model.Person
import ru.pestoff.contactlist.repository.PersonRepository
import ru.pestoff.contactlist.service.PersonService
import ru.pestoff.contactlist.viewModel.ListPersonViewModel
import java.util.concurrent.ScheduledThreadPoolExecutor

class ListPersonFragment : Fragment() {

    private lateinit var personAdapter: PersonAdapter

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

        initViewModel()
        initRecyclerView(view)

        return view
    }

    private fun initViewModel() {
        val viewModel = ListPersonViewModel(PersonRepository(PersonService.create()))

        viewModel.loadPerson()

        viewModel.persons.observe(viewLifecycleOwner, Observer {
            personAdapter.persons = it!!
        })
    }

    private fun initRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(activity)

        personAdapter = PersonAdapter()
        recyclerView.adapter = personAdapter
    }
}