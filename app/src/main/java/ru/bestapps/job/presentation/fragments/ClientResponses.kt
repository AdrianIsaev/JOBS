package ru.bestapps.job.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.bestapps.job.R
import ru.bestapps.job.databinding.FragmentClientResponsesBinding
import ru.bestapps.job.databinding.FragmentClientSearchBinding

class ClientResponses : Fragment() {
    private lateinit var binding: FragmentClientResponsesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClientResponsesBinding.inflate(inflater, container, false)
        return binding.root
    }

}