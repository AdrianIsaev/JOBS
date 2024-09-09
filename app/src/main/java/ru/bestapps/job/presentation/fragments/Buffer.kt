package ru.bestapps.job.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.Selection.setSelection
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.bestapps.job.R
import ru.bestapps.job.databinding.FragmentBufferBinding

import ru.bestapps.job.presentation.utils.isValidEmail
import ru.bestapps.job.presentation.viewmodel.BufferViewModel

class Buffer : Fragment() {
    private lateinit var binding: FragmentBufferBinding
    private lateinit var navController: NavController
    private val viewModel by viewModel<BufferViewModel>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBufferBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        binding.buttonContinue.setOnClickListener {
            if(binding.searchJobsEditText.text.toString()
                .isValidEmail()) {
                viewModel.saveEmail(binding.searchJobsEditText.text.toString())
                navController.navigate(R.id.action_buffer2_to_check)
            }
            else {
                binding.searchJobsEditText.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.placeholder_error)
                binding.errorInfoTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
            }
        }
        binding.searchJobsEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) binding.searchJobsEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
            else binding.searchJobsEditText.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.responses,
                0,
                0,
                0
            )
        }
        binding.searchJobsEditText.apply {
            isSingleLine = true
        }

        binding.searchJobsEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.searchJobsEditText.setOnTouchListener { v, event ->
                    if (event.action == MotionEvent.ACTION_UP) {
                        val drawableEnd = 2
                        if (event.rawX >= (binding.searchJobsEditText.right - binding.searchJobsEditText.compoundDrawables[drawableEnd].bounds.width())) {
                            binding.searchJobsEditText.setText("")
                            return@setOnTouchListener true
                        }
                    }
                    false
                }
                if (s != null && s.length > 30) {
                        (requireActivity().currentFocus as? EditText)?.setText(s.substring(0, 30))
                        (requireActivity().currentFocus as? EditText)?.setSelection(24)
                    }
                if (s.isNullOrEmpty()) {
                    binding.searchJobsEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                    binding.buttonContinue.background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.button_eight_r)

                } else {
                    binding.buttonContinue.background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.button_eight_r_light)
                    binding.searchJobsEditText.setCompoundDrawablesWithIntrinsicBounds(
                        0, 0, R.drawable.edit_text_canceller, 0)
                    binding.searchJobsEditText.background = ContextCompat.getDrawable(requireContext(), R.drawable.search_field)
                    binding.errorInfoTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey_1))
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }
}
