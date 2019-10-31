package com.example.rxjavabasic

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.rxjavabasic.viewmodel.ViewModelProviderFactory
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var editText: EditText
    private lateinit var button: Button
    private lateinit var userName: TextView

    private val disposable = CompositeDisposable()

    private lateinit var viewModel: MainViewModel

    @Inject
    @JvmField
    var providerFactory: ViewModelProviderFactory? = null

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ui
        button = findViewById(R.id.button)
        editText = findViewById(R.id.editText)
        userName = findViewById(R.id.user_name)
        button.setOnClickListener(this)

        // init view model
        viewModel = ViewModelProviders.of(this, providerFactory).get(MainViewModel::class.java)

    }

    override fun onStart() {
        super.onStart()
        disposable.add(
            viewModel.observerUserState()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ this.userName?.text = it },
                    { error -> Log.e(TAG, "Unable to get username", error) })
        )
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button -> {
                updateDB()
            }
        }
    }

    private fun updateDB() {
        var userName = editText.text.toString()
        //viewModel?.insertUser(userName)
        disposable.add(
            viewModel.insertUser(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ button?.isEnabled = true },
                    { error -> Log.e(TAG, "Unable to update username", error) })
        )
    }

    override fun onStop() {
        super.onStop()
        // clear all the subscription
        disposable.clear()
    }
}
