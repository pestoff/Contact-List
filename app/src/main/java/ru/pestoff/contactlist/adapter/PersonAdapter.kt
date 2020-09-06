package ru.pestoff.contactlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.pestoff.contactlist.R
import ru.pestoff.contactlist.model.Person

class PersonAdapter(private val onClickListener: OnClickListener) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>(), Filterable {

    interface OnClickListener {
        fun OnClick(person: Person)
    }

    var persons = emptyList<Person>()
        set(value) {
            field = value
            personsFiltered = field
            notifyDataSetChanged()
        }

    var personsFiltered = persons

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_view, parent, false)

        return PersonViewHolder(itemView)
    }

    override fun getItemCount() = personsFiltered.size

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
       holder.bind(personsFiltered[position])
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charString = p0.toString()
                if (charString.isEmpty()) {
                    personsFiltered = persons
                } else {
                    val filteredList = mutableListOf<Person>()

                    persons.forEach {
                        if (it.name.toLowerCase().contains(charString.toLowerCase()) ||
                                it.phone.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(it)
                        }
                    }

                    personsFiltered = filteredList
                }

                val result = FilterResults()
                result.values = personsFiltered
                return result
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                personsFiltered = p1?.values as List<Person>
                notifyDataSetChanged()
            }

        }
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