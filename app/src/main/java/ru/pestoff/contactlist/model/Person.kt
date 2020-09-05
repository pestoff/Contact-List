package ru.pestoff.contactlist.model

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize
import ru.pestoff.contactlist.database.DateConverter
import java.util.*

@Entity(tableName = "persons")
@Parcelize
data class Person(
    @PrimaryKey
    val id: String,
    val name: String,
    val phone: String,
    val height: String,
    val biography: String,
    val temperament: String,
    @Embedded
    val educationPeriod: EducationPeriod
) : Parcelable

@Parcelize
data class EducationPeriod(
    val start: Date,
    val end: Date
) : Parcelable