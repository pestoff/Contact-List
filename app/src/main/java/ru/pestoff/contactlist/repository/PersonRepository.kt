package ru.pestoff.contactlist.repository

import io.reactivex.Single
import io.reactivex.functions.Function
import ru.pestoff.contactlist.model.Person
import ru.pestoff.contactlist.service.PersonService

class PersonRepository(val service: PersonService) {

    fun getPersons(): Single<List<Person>> {
        return service.getPersons()
            .map(func)
    }

    val func = Function<List<Person>, List<Person>>() {
        return@Function it
    }
}