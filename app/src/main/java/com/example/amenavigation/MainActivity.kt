package com.example.amenavigation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.component_bottom_bar.*
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        firstTab.setOnClickListener { navigateToDestination(it.id, "First Tab clicked") }
        secondTabNeastedGraph.setOnClickListener { navigateToDestination(it.id, "Second Tab clicked") }
        thirdTab.setOnClickListener { navigateToDestination(it.id, "Third Tab clicked") }
        fourthTab.setOnClickListener { navigateToDestination(it.id, "Fourth Tab clicked") }

        val host = container as NavHostFragment

        navController = host.navController
    }

    private fun navigateToDestination(id: Int, tabName: String) {
        Toast.makeText(this, tabName, Toast.LENGTH_SHORT).show()
        val builder = NavOptions.Builder()
            .setLaunchSingleTop(true)
        val options = builder.build()
        navController.navigate(id, null, options)
    }

}
