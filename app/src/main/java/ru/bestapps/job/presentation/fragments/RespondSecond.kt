package ru.bestapps.job.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import ru.bestapps.job.R
import ru.bestapps.job.databinding.FragmentRespondFirstBinding
import ru.bestapps.job.databinding.FragmentRespondSecondBinding

class RespondSecond  : DialogFragment() {
    private lateinit var binding: FragmentRespondSecondBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRespondSecondBinding.inflate(inflater, container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSubmit.setOnClickListener {
            dismiss()
        }
        val message = arguments?.getString("message")
        binding.sendLetterEditText.setText(message)
    }
    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
    companion object {
        fun newInstance(message: String): RespondSecond {
            val fragment = RespondSecond()
            val args = Bundle()
            args.putString("message", message)
            fragment.arguments = args
            return fragment
        }
    }
}
