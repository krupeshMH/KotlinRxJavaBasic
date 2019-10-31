package com.example.rxjavabasic.di

import android.app.Application
import androidx.room.Room
import com.example.rxjavabasic.db.UserDAO
import com.example.rxjavabasic.db.UserDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRoomInstance(application: Application): UserDatabase {
        return Room.databaseBuilder(
            application,
            UserDatabase::class.java, "UserDB.db"
        )
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDAO(database: UserDatabase): UserDAO {
        return database.userDao()
    }
}