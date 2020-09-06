package ru.pestoff.contactlist.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelFactory
    @Inject constructor(val listPersonViewModel: ListPersonViewModel): ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.equals(ListPersonViewModel::class.java)) {
            return listPersonViewModel as T
        } else {
            throw IllegalArgumentException(
                    "Объект " + modelClass.getSimpleName() + "не может быть создан");
        }
    }

}