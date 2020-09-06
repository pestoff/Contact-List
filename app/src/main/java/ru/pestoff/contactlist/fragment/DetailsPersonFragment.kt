package ru.pestoff.contactlist.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import ru.pestoff.contactlist.R
import ru.pestoff.contactlist.model.Person
import java.text.SimpleDateFormat
import java.util.*

class DetailsPersonFragment : Fragment() {

    private var person: Person? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.details_person_fragment, container, false)

        arguments?.let {
            person = it.getParcelable(ITEM_KEY) as Person?
        }

        initLayout(view)
        configurationToolbar(view)

        return view
    }

    fun initLayout(view: View) {
        val name = view.findViewById<TextView>(R.id.person_name)
        val phone = view.findViewById<TextView>(R.id.person_phone)
        val temperament = view.findViewById<TextView>(R.id.person_temperament)
        val educationPeriod = view.findViewById<TextView>(R.id.person_education_period)
        val biography = view.findViewById<TextView>(R.id.person_biography)

        person?.let {
            name.text = it.name
            phone.text = it.phone
            temperament.text = it.temperament

            educationPeriod.text = "${convertDate(it.educationPeriod.start)} - ${convertDate(it.educationPeriod.end)}"
            biography.text = it.biography
        }

        phone.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${phone.text}"))
            startActivity(intent)
        }
    }

    private fun configurationToolbar(view: View) {
        val appCompatActivity = activity as AppCompatActivity
        val toolbar = view.findViewById<Toolbar>(R.id.details_toolbar)
        toolbar.title = ""

        appCompatActivity.setSupportActionBar(toolbar)

        appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        appCompatActivity.supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    private fun convertDate(date: Date): String {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy")

        return dateFormat.format(date)
    }

    companion object {
        private const val ITEM_KEY = "Person"

        fun createInstance(person: Person): DetailsPersonFragment {
            return DetailsPersonFragment().apply {
                arguments = bundleOf(
                    ITEM_KEY to person
                )
            }
        }
    }
}