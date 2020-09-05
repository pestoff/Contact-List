package ru.pestoff.contactlist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.pestoff.contactlist.model.Person

@Database(entities = [Person::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getPersonDao(): PersonDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase {
            INSTANCE = INSTANCE ?:
                    Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java,
                        "appDatabase")
                        .build()

            return INSTANCE!!
        }
    }
}