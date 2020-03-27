package com.example.amenavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.amenavigation.ui.secondary.SecondaryFragment
import kotlinx.android.synthetic.main.secondary_activity.*

class SecondaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.secondary_activity)
        upButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        findNavController(R.id.container)
            .setGraph(R.navigation.secondary_nav_graph, intent.extras)
    }
}
