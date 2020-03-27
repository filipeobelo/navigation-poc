package com.example.amenavigation.ui.secondary

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.amenavigation.R

class SecondaryFragment : Fragment() {

    private var refusedAuthentication: Boolean = false
    private lateinit var viewModel: SecondaryViewModel
    private lateinit var navController: NavController
    val args by navArgs<SecondaryFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.secondary_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SecondaryViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        if (requireActivity().getSharedPreferences(
                getString(R.string.preference_file_key),
                Context.MODE_PRIVATE
            ).getBoolean("authenticated", false)
        ) {
            Toast.makeText(context, "Olá Usuário!", Toast.LENGTH_SHORT).show()
        } else {
            if (refusedAuthentication) {
                requireActivity().finish()
            } else {
                refusedAuthentication = true
                val directions =
                    SecondaryFragmentDirections.actionSecondaryFragmentToLoginActivity(args.isDeepLink)
                navController.navigate(directions)
            }
        }
    }
}
