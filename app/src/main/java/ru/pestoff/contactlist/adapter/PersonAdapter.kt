package ru.pestoff.contactlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.pestoff.contactlist.R
import ru.pestoff.contactlist.model.Person

class PersonAdapter(private val onClickListener: OnClickListener) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    interface OnClickListener {
        fun OnClick(person: Person)
    }

    var persons = emptyList<Person>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_view, parent, false)

        return PersonViewHolder(itemView)
    }

    override fun getItemCount() = persons.size

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
       holder.bind(persons[position])
    }

    inner class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val telephoneTextView: TextView = itemView.findViewById(R.id.item_phone)
        private val nameTextView: TextView = itemView.findViewById(R.id.item_name)
        private val heightTextView: TextView = itemView.findViewById(R.id.item_height)

        fun bind(person: Person) {

            itemView.setOnClickListener {
                onClickListener.OnClick(person)
            }

            telephoneTextView.text = person.phone
            nameTextView.text = person.name
            heightTextView.text = person.height
        }
    }


}