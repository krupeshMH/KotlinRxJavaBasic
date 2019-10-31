package com.example.rxjavabasic.di.main

import androidx.lifecycle.ViewModel
import com.example.rxjavabasic.MainViewModel
import com.example.rxjavabasic.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

}