package ru.pestoff.contactlist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.pestoff.contactlist.R
import ru.pestoff.contactlist.adapter.PersonAdapter
import ru.pestoff.contactlist.repository.PersonRepository
import ru.pestoff.contactlist.service.PersonService
import ru.pestoff.contactlist.viewModel.ListPersonViewModel

class ListPersonFragment : Fragment() {

    private lateinit var personAdapter: PersonAdapter

    private lateinit var progressBar: ProgressBar

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

        progressBar = view.findViewById(R.id.progress_bar)

        return view
    }

    private fun initViewModel() {
        val viewModel = ListPersonViewModel(PersonRepository(PersonService.create()))

        viewModel.persons.observe(viewLifecycleOwner, Observer {
            personAdapter.persons = it!!
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.INVISIBLE
        })
    }

    private fun initRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.HORIZONTAL))
        recyclerView.layoutManager = LinearLayoutManager(activity)


        personAdapter = PersonAdapter()
        recyclerView.adapter = personAdapter


    }
}