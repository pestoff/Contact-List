package ru.pestoff.contactlist.module

import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.pestoff.contactlist.annotation.ViewModelKey
import ru.pestoff.contactlist.viewModel.ListPersonViewModel

@Module
class ViewModelModule {

    @IntoMap
    @Provides
    @ViewModelKey(ListPersonViewModel::class)
    fun provideListPersonViewModel(listPersonViewModel: ListPersonViewModel): ViewModel {
        return listPersonViewModel
    }
}