package ru.bestapps.job.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import ru.bestapps.job.R
import ru.bestapps.job.databinding.FragmentRespondFirstBinding


class RespondFirst  : DialogFragment() {
    private lateinit var binding:FragmentRespondFirstBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRespondFirstBinding.inflate(inflater, container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.sendLetterEditText.visibility = View.GONE
        binding.btnSubmit.setOnClickListener {
            dismiss()
        }
        binding.addLetterTextView.setOnClickListener {
            binding.sendLetterEditText.visibility = View.VISIBLE
            binding.addLetterTextView.visibility = View.GONE
        }

    }
    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
    companion object {
        fun newInstance(): RespondFirst {
            return RespondFirst()
        }
    }
}
