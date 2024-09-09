package ru.bestapps.job.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.bestapps.job.R
import ru.bestapps.job.databinding.FragmentClientMessagesBinding
import ru.bestapps.job.databinding.FragmentClientResponsesBinding

class ClientMessages : Fragment() {
    private lateinit var binding: FragmentClientMessagesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClientMessagesBinding.inflate(inflater, container, false)
        return binding.root
    }

}