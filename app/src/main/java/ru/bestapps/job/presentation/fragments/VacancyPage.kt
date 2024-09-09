package ru.bestapps.job.presentation.fragments

import android.nfc.Tag
import android.os.Binder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.bestapps.domain.models.BackDomain
import ru.bestapps.domain.models.VacancyDomain
import ru.bestapps.job.R
import ru.bestapps.job.databinding.FragmentVacancyPageBinding
import ru.bestapps.job.presentation.adapters.BackAdapter
import ru.bestapps.job.presentation.viewmodel.VacancyPageViewModel
import kotlin.properties.Delegates

class VacancyPage : Fragment() {
    private lateinit var binding: FragmentVacancyPageBinding
    private lateinit var navController: NavController
    private lateinit var adapter: BackAdapter

    private val viewModel by viewModel<VacancyPageViewModel>()
    private var ids by Delegates.notNull<Int>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentVacancyPageBinding.inflate(inflater, container, false)

        navController = findNavController()
        adapter = BackAdapter(requireContext(), parentFragmentManager)
        binding.questionsRecyclerView.adapter = adapter
        binding.questionsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.arrowImageButton.setOnClickListener{
            navController.popBackStack()
        }

        binding.respondButton.setOnClickListener {
            val resumeDialogFragment = RespondFirst.newInstance()
            resumeDialogFragment.show(parentFragmentManager, "RespondFragment")

        }


        arguments?.let {
                bundle -> ids = bundle.getInt("ids")
        }
//            title = bundle.getString("title", "")
//            salary = bundle.getString("salary", "")
//            experience = bundle.getString("experience", "")
//            schedules = bundle.getStringArrayList("schedules")
//            appliedNumber = bundle.getString("appliedNumber")
//            lookingNumber = bundle.getString("lookingNumber")
//            company = bundle.getString("company", "")
//            town = bundle.getString("town", "")
//            house = bundle.getString("house", "")
//            street = bundle.getString("street", "")
//            description = bundle.getString("description", "")
//            isFavourite = bundle.getBoolean("isFavourite", false)
//            responsibilities = bundle.getString("responsibilities", "")
//            questions = bundle.getStringArrayList("questions")
//        }

        viewModel.viewModelScope.launch(Dispatchers.IO) {
            val currentVacancy = viewModel.getCurrentVacancy(ids)
            withContext(Dispatchers.Main){
                val backs = currentVacancy.questions
                if (backs != null) {
                    for (back in backs){
                        adapter.addBack(BackDomain(back))
                    }
                }

                binding.professionTextView.text = currentVacancy.title
                binding.salaryTextView.text = currentVacancy.salary?.full
                binding.experienceTextView.text = "Требуемый опыт: ${currentVacancy.experience?.text}"
                if (currentVacancy.schedules!=null) {
                    val formattedSchedules = currentVacancy.schedules!!.joinToString(", ") { it.replaceFirstChar { it.uppercase() } }
                    binding.employmentTextView.text = formattedSchedules
                }
                if (currentVacancy.appliedNumber == null) { binding.backButton.visibility = View.GONE }
                else { binding.backButton.text = "${currentVacancy.appliedNumber} человек уже\n откликнулось" }



                if (currentVacancy.lookingNumber == null) { binding.watchersButton.visibility = View.GONE }
                else { binding.watchersButton.text = "${currentVacancy.lookingNumber} сейчас смотрят" }


                binding.companyTextView.text = currentVacancy.company
                binding.townTextView.text = "${currentVacancy.address?.town}, ${currentVacancy.address?.street}, ${currentVacancy.address?.house}"
                binding.descriptionTextView.text = currentVacancy.description
                binding.tasksDescriptionsTextView.text = currentVacancy.responsibilities

                if (currentVacancy.isFavorite) {
                    binding.blueHeartButton.setBackgroundResource(R.drawable.blue_heart_icon)
                    binding.blueHeartButton.tag = "blue"
                }
                else {
                    binding.blueHeartButton.setBackgroundResource(R.drawable.naked_heart)
                    binding.blueHeartButton.tag = "transparent"
                }
                binding.blueHeartButton.setOnClickListener {
                    if (binding.blueHeartButton.tag == "transparent") {
                        binding.blueHeartButton.setBackgroundResource(R.drawable.blue_heart_icon)
                        binding.blueHeartButton.tag = "blue"
                        viewModel.viewModelScope.launch(Dispatchers.IO) {
                            viewModel.setFavourite(ids, true)
                        }
                    } else if (binding.blueHeartButton.tag == "blue") {
                        binding.blueHeartButton.setBackgroundResource(R.drawable.naked_heart)
                        binding.blueHeartButton.tag = "transparent"
                        viewModel.viewModelScope.launch(Dispatchers.IO) {
                            viewModel.setFavourite(ids, false)
                        }
                    }
                }
            }
        }
    }
}