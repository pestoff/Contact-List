package ru.pestoff.contactlist.service

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import ru.pestoff.contactlist.model.Person


interface PersonService {

    @GET("SkbkonturMobile/mobile-test-droid/master/json/{fileName}")
    fun getPersons(@Path("fileName") fileName: String ): Observable<List<Person>>

    companion object {
        fun create(): PersonService {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://raw.githubusercontent.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

            return retrofit.create(PersonService::class.java)
        }
    }
}
