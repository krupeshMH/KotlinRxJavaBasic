package com.example.rxjavabasic

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.rxjavabasic.db.User
import com.example.rxjavabasic.db.UserDAO
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class MainViewModel
    @Inject
    constructor(private val dao: UserDAO)
    : ViewModel() {
    companion object {
        private val TAG = "MainViewModel"
        const val USER_ID = "1"
    }

    init {
        Log.d(TAG, "MainViewModel: ViewModel is working...")
    }


    fun observerUserState(): Flowable<String> {
        return dao.getUserById(USER_ID).map { user -> user.userName }
    }

    fun insertUser(userName: String): Completable {
        val user = User(USER_ID, userName)
        return dao.insertUser(user)
    }


}