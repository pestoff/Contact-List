package ru.pestoff.contactlist.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import ru.pestoff.contactlist.R
import ru.pestoff.contactlist.model.Person
import ru.pestoff.contactlist.service.PersonService
import java.util.concurrent.ScheduledThreadPoolExecutor
import retrofit2.Callback
import retrofit2.Response
import ru.pestoff.contactlist.fragment.ListPersonFragment

class MainActivity : AppCompatActivity() {
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
}