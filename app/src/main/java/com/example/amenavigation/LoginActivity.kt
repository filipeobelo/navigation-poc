package com.example.amenavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.login_activity.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        findNavController(R.id.container)
            .setGraph(R.navigation.login_nav_graph, intent.extras)

        upButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}
