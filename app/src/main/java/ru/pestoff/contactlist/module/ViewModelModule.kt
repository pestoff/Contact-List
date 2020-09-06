package ru.pestoff.contactlist.module

import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import ru.pestoff.contactlist.repository.PersonRepository
import ru.pestoff.contactlist.viewModel.ListPersonViewModel
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Provides
    @Singleton
    fun provideListPersonViewModel(repository: PersonRepository): ViewModel {
        return ListPersonViewModel(repository)
    }
}