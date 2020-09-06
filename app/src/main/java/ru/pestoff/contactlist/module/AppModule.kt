package ru.pestoff.contactlist.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.pestoff.contactlist.database.AppDatabase
import ru.pestoff.contactlist.database.PersonDao
import ru.pestoff.contactlist.service.PersonService
import javax.inject.Singleton

@Module
class AppModule(val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java,"appDatabase")
            .build()
    }

    @Provides
    @Singleton
    fun providePersonDao(appDatabase: AppDatabase): PersonDao {
        return appDatabase.getPersonDao()
    }

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://raw.githubusercontent.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePersonService(retrofit: Retrofit): PersonService {
        return retrofit.create(PersonService::class.java)
    }
}