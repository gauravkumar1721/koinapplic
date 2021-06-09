package com.example.myapplication2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.myapplication2.R
import com.example.myapplication2.UserAdaptor
import com.example.myapplication2.databinding.ActivityMainBinding
import com.example.myapplication2.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val userViewModel by viewModel<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainActivityBinding: ActivityMainBinding  = DataBindingUtil.setContentView(this, R.layout.activity_main);
        userViewModel.data.observe(this, Observer {
            mainActivityBinding.recycler.adapter = UserAdaptor(it)
        })
        userViewModel.loadingState.observe(this, Observer {})
        userViewModel.toast.observe(this, {
            Toast.makeText(this,  it, Toast.LENGTH_SHORT).show()
        })

    }
}