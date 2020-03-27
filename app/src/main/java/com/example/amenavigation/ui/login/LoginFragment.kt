package com.example.amenavigation.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.example.amenavigation.MainActivity
import com.example.amenavigation.R
import com.example.amenavigation.ui.login.LoginViewModel.AuthenticationState.AUTHENTICATED
import com.example.amenavigation.ui.login.LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    val args by navArgs<LoginFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        bLogin.setOnClickListener {
            viewModel.authenticate()
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            viewModel.refuseAuthentication()
            if (args.isDeepLink) {
                goToHomeScreen()
            } else {
                requireActivity().finish()
            }
        }

        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            when (authenticationState) {
                AUTHENTICATED -> saveUserToPreferences()
                INVALID_AUTHENTICATION -> showErrorMessage()
            }
        })
    }

    private fun saveUserToPreferences() {
        requireActivity().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)
            .edit()
            .putBoolean("authenticated", true)
            .apply()
        requireActivity().finish()
    }

    private fun goToHomeScreen() {
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun showErrorMessage() {
        Toast.makeText(context, "Login invalido", Toast.LENGTH_SHORT).show()
    }

}
