package ru.pestoff.contactlist.service

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import ru.pestoff.contactlist.model.Person


interface PersonService {

    @GET("generated-01.json")
    fun getPersons(): Call<List<Person>>

    companion object {
        fun create(): PersonService {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://raw.githubusercontent.com/SkbkonturMobile/mobile-test-droid/master/json/")
                .build()

            return retrofit.create(PersonService::class.java)
        }
    }
}
