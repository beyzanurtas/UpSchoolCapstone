package com.beyzanurtas.upschool_capstoneproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.beyzanurtas.upschool_capstoneproject.R
import com.beyzanurtas.upschool_capstoneproject.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment : Fragment() {
    private lateinit var signUpBinding: FragmentSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        signUpBinding=FragmentSignUpBinding.inflate(inflater,container,false)
        signUpBinding.haveAnAccount.setOnClickListener {
            findNavController()
                .navigate(R.id.action_signUpFragment_to_loginFragment)
        }
        return signUpBinding.root
    }

    override fun onStart() {
        super.onStart()
        signUpBinding.apply {
            signUpButton.setOnClickListener {
                signupValidation(
                    emailEdittext.text.toString(),
                    passwordEdittext.text.toString()

                )
            }
        }
    }

    private fun signupValidation(email: String, password: String) {
        val emailControl = email.trim() { it <= ' ' }
        val passwordControl = password.trim() { it <= ' ' }

        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(emailControl,passwordControl)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(activity,"Signup successful!",Toast.LENGTH_LONG).show()
                    findNavController()
                        .navigate(R.id.action_signUpFragment_to_loginFragment)
                }

            }
    }
}
