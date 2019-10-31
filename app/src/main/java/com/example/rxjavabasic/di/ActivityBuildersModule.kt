package com.example.rxjavabasic.di

import com.example.rxjavabasic.MainActivity
import com.example.rxjavabasic.di.main.MainViewModelsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [MainViewModelsModule::class])
    abstract fun contributeMainActivity(): MainActivity
}