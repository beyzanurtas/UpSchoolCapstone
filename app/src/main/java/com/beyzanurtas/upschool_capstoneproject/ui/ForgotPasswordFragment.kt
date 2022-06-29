package com.beyzanurtas.upschool_capstoneproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.beyzanurtas.upschool_capstoneproject.R
import com.beyzanurtas.upschool_capstoneproject.databinding.FragmentForgotPasswordBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgotPasswordFragment : Fragment() {

    private lateinit var binding: FragmentForgotPasswordBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentForgotPasswordBinding.inflate(inflater,container,false)
        binding.newpasswordSend.setOnClickListener {

            val emailAddress = binding.editTextTextEmailAddress3.text

            Firebase.auth.sendPasswordResetEmail(emailAddress.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(activity,"Email sent.",Toast.LENGTH_SHORT).show()
                    }
                }
        }
        return binding.root
    }
}