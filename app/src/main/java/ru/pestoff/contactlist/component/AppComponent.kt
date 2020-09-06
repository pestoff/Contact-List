package ru.pestoff.contactlist.component

import dagger.Component
import ru.pestoff.contactlist.fragment.ListPersonFragment
import ru.pestoff.contactlist.module.AppModule
import ru.pestoff.contactlist.module.ViewModelModule
import javax.inject.Singleton

@Component(modules = [AppModule::class, ViewModelModule::class])
@Singleton
interface AppComponent {
    fun inject(listPersonFragment: ListPersonFragment)
}