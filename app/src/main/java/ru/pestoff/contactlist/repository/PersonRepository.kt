package ru.pestoff.contactlist.repository

import io.reactivex.Observable
import io.reactivex.functions.Function
import io.reactivex.functions.Function3
import ru.pestoff.contactlist.database.PersonDao
import ru.pestoff.contactlist.model.Person
import ru.pestoff.contactlist.service.PersonService
import java.time.LocalDateTime
import java.util.*

class PersonRepository(val service: PersonService, val personDao: PersonDao) {

    private var requestTime = Calendar.getInstance()

    fun updateRequestTime() {
        requestTime = Calendar.getInstance()
        requestTime.add(Calendar.MINUTE, 1)
    }

    fun getPersons(): Observable<List<Person>> {
        if (Calendar.getInstance().before(requestTime)) {

            updateRequestTime()

            return personDao.getAllPersons()
        }

        updateRequestTime()

        return Observable.combineLatest(
            service.getPersons("generated-01.json"),
            service.getPersons("generated-02.json"),
            service.getPersons("generated-03.json"),
            Function3<List<Person>, List<Person>, List<Person>, List<Person>>() { first, second, third ->
                    val result = mutableListOf<Person>()

                    result.addAll(first)
                    result.addAll(second)
                    result.addAll(third)

                    return@Function3 result
                }).map(saveToDatabase)
    }

    val saveToDatabase = Function<List<Person>, List<Person>>() {

        personDao.insertAll(it)

        return@Function it
    }
}