package ru.pestoff.contactlist.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Observable
import ru.pestoff.contactlist.model.Person
import java.util.*

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(persons: List<Person>)

    @Query("SELECT * FROM persons")
    fun getAllPersons(): Observable<List<Person>>
}