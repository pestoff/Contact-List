package ru.pestoff.contactlist.fragment

import android.content.Context
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import ru.pestoff.contactlist.R
import ru.pestoff.contactlist.adapter.PersonAdapter
import ru.pestoff.contactlist.database.AppDatabase
import ru.pestoff.contactlist.model.Person
import ru.pestoff.contactlist.repository.PersonRepository
import ru.pestoff.contactlist.service.PersonService
import ru.pestoff.contactlist.viewModel.ListPersonViewModel
import java.lang.ClassCastException

class ListPersonFragment : Fragment() {

    interface FragmentInteractionListener {
        fun onItemChosen(person: Person)
    }

    private lateinit var personAdapter: PersonAdapter
    private lateinit var viewModel: ListPersonViewModel

    private lateinit var listener: FragmentInteractionListener

    private lateinit var progressBar: ProgressBar
    private lateinit var swipeContainer: SwipeRefreshLayout

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

        initViewModel(view)
        initRecyclerView(view)

        progressBar = view.findViewById(R.id.progress_bar)

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentInteractionListener) {
            listener = context
        } else {
            throw ClassCastException("$context must implement FragmentInteractionListener")
        }
    }

    private fun initViewModel(view: View) {
        viewModel = ListPersonViewModel(PersonRepository(PersonService.create(), AppDatabase.getAppDatabase(requireActivity().applicationContext).getPersonDao()))

        viewModel.persons.observe(viewLifecycleOwner, Observer {
            personAdapter.persons = it!!
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.INVISIBLE
        })

        viewModel.isError.observe(viewLifecycleOwner, Observer {
            if (it) Snackbar.make(view, R.string.connection_error, Snackbar.LENGTH_SHORT)
                .show()
        })
    }

    private fun initRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.HORIZONTAL))
        recyclerView.layoutManager = LinearLayoutManager(activity)


        personAdapter = PersonAdapter(object : PersonAdapter.OnClickListener {
            override fun OnClick(person: Person) {
                listener.onItemChosen(person)
            }
        })

        recyclerView.adapter = personAdapter

        swipeContainer = view.findViewById(R.id.swipe_container)

        swipeContainer.setOnRefreshListener {
            viewModel.loadPerson()
            swipeContainer.isRefreshing = false

        }
    }
}