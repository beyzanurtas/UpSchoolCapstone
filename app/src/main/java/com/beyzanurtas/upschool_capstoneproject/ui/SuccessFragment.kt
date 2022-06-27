package com.beyzanurtas.upschool_capstoneproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.beyzanurtas.upschool_capstoneproject.R
import com.beyzanurtas.upschool_capstoneproject.databinding.FragmentSuccessBinding

class SuccessFragment : Fragment() {
    private lateinit var successBinding: FragmentSuccessBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        successBinding=FragmentSuccessBinding.inflate(inflater,container,false)
        successBinding.continueshopButton.setOnClickListener {
            findNavController()
                .navigate(R.id.action_successFragment_to_mainActivity2)
        }
        // Inflate the layout for this fragment
        return successBinding.root
    }
}
