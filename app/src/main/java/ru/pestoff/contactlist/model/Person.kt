package ru.pestoff.contactlist.model

data class Person(
    val id: String,
    val name: String,
    val phone: String,
    val height: String,
    val biography: String,
    val temperament: String,
    val educationPeriod: EducationPeriod
)

data class EducationPeriod(
    val start: String,
    val end: String
)