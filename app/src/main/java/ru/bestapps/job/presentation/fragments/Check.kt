package ru.bestapps.job.presentation.fragments

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.w3c.dom.Text
import ru.bestapps.job.R
import ru.bestapps.job.databinding.FragmentCheckBinding
import ru.bestapps.job.presentation.viewmodel.CheckViewModel

class Check : Fragment() {
    private lateinit var binding: FragmentCheckBinding
    private val viewModel by viewModel<CheckViewModel>()
    var kostil = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheckBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        viewModel.getUserEmail()
        viewModel.userEmail.observe(viewLifecycleOwner) {
            binding.emailSendTextView.text = "Отправили код на $it"
        }


        binding.acceptButton.setOnClickListener {
            if (kostil) navController.navigate(R.id.action_check_to_unregisteredMain2)
        }


        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null && s.length > 1) {
                    (requireActivity().currentFocus as? EditText)?.setText(s.substring(0, 1))
                    (requireActivity().currentFocus as? EditText)?.setSelection(1)
                }
                if (s?.length == 1) {
                    val nextEditText = getNextEditText(
                        requireActivity().currentFocus as? EditText,
                        binding.editText1, binding.editText2, binding.editText3, binding.editText4
                    )
                    nextEditText?.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {
                updateButtonColor()
            }
        }

        binding.editText1.addTextChangedListener(textWatcher)
        binding.editText2.addTextChangedListener(textWatcher)
        binding.editText3.addTextChangedListener(textWatcher)
        binding.editText4.addTextChangedListener(textWatcher)



        binding.editText1.setOnKeyListener(keyListener)
        binding.editText2.setOnKeyListener(keyListener)
        binding.editText3.setOnKeyListener(keyListener)
        binding.editText4.setOnKeyListener(keyListener)
    }

    private val keyListener = View.OnKeyListener { v, keyCode, event ->
        if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {
            val currentEditText = v as? EditText
            if (currentEditText?.text.isNullOrEmpty()) {
                val previousEditText = getPreviousEditText(currentEditText)
                previousEditText?.requestFocus()
                previousEditText?.setText("")
            }
        }
        false
    }

    private fun getNextEditText(currentEditText: EditText?, editText1: EditText?,
                                editText2: EditText?, editText3: EditText?, editText4: EditText?): EditText? {
        return when (currentEditText) {
            editText1 -> editText2
            editText2 -> editText3
            editText3 -> editText4
            else -> null
        }
    }

    private fun getPreviousEditText(currentEditText: EditText?): EditText? {
        return when (currentEditText) {
            binding.editText2 -> binding.editText1
            binding.editText3 -> binding.editText2
            binding.editText4 -> binding.editText3
            else -> null
        }
    }
    private fun updateButtonColor() {

        val isAllFilled = binding.editText1.text.isNotEmpty() &&
                binding.editText2.text.isNotEmpty() &&
                binding.editText3.text.isNotEmpty() &&
                binding.editText4.text.isNotEmpty()

        if (isAllFilled) {
            kostil = true
            binding.acceptButton.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_eight_r_light)
        } else {
            kostil = false
            binding.acceptButton.background = ContextCompat.getDrawable(requireContext(), R.drawable.button_eight_r)
        }
    }
//    private fun focusOnFirstEditTextIfEmpty() {
//        if (binding.editText1.text.isEmpty() &&
//            binding.editText2.text.isEmpty() &&
//            binding.editText3.text.isEmpty() &&
//            binding.editText4.text.isEmpty()) {
//            binding.editText1.requestFocus()
//        }
//    }
}