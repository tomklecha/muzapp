package com.tkdev.muzapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.tkdev.muzapp.R
import com.tkdev.muzapp.viewmodels.MuzViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val muzViewModel : MuzViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
        navController = navHostFragment.navController

        muzViewModel.currentUserDomain.observe(this, {
            val bundle = Bundle()
            bundle.putString("chatId", "chat_id")
            navController.setGraph(R.navigation.nav_graph, bundle)
        })

    }
}