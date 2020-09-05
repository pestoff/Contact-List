package ru.pestoff.contactlist.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import retrofit2.Call
import ru.pestoff.contactlist.R
import ru.pestoff.contactlist.model.Person
import ru.pestoff.contactlist.service.PersonService
import java.util.concurrent.ScheduledThreadPoolExecutor
import retrofit2.Callback
import retrofit2.Response
import ru.pestoff.contactlist.fragment.DetailsPersonFragment
import ru.pestoff.contactlist.fragment.ListPersonFragment

class MainActivity : AppCompatActivity(), ListPersonFragment.FragmentInteractionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container)

        if (fragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, ListPersonFragment())
                .commit()
        }
    }

    override fun onItemChosen(person: Person) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, DetailsPersonFragment.createInstance(person))
            .addToBackStack(null)
            .commit()
    }
}