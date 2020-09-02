package ru.pestoff.contactlist.repository

import ru.pestoff.contactlist.service.PersonService

class PersonRepository(val service: PersonService) {

    fun getPersons() = service.getPersons()
}