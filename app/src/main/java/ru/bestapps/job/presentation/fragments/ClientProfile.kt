package ru.bestapps.job.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.bestapps.job.R
import ru.bestapps.job.databinding.FragmentClientProfileBinding
import ru.bestapps.job.databinding.FragmentClientResponsesBinding

class ClientProfile : Fragment() {
    private lateinit var binding: FragmentClientProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClientProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

}