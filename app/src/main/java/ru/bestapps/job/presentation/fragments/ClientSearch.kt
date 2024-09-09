package ru.bestapps.job.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.bestapps.domain.models.OfferDomain
import ru.bestapps.domain.models.Text
import ru.bestapps.domain.models.VacancyDomain
import ru.bestapps.job.R
import ru.bestapps.job.databinding.FragmentClientSearchBinding
import ru.bestapps.job.presentation.adapters.OfferAdapter
import ru.bestapps.job.presentation.adapters.VacancyAdapter
import ru.bestapps.job.presentation.viewmodel.SearchViewModel

class ClientSearch : Fragment() {
    private lateinit var binding: FragmentClientSearchBinding
    private val viewModel by viewModel<SearchViewModel>()
    private lateinit var adapter: VacancyAdapter
    private lateinit var navController: NavController
    private val offerAdapter: OfferAdapter = OfferAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClientSearchBinding.inflate(inflater, container, false)
        binding.recommendedRecyclerView.adapter = offerAdapter
        binding.recommendedRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        navController = findNavController()
        adapter = VacancyAdapter(viewModel, navController)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.hiddenFilterTextView.visibility = View.GONE
        binding.blueLinesImageButton.visibility = View.GONE
        binding.stateArrowImageButton.visibility = View.GONE
        lifecycleScope.launch(Dispatchers.IO) {
            val vacancyList = viewModel.getVacancies().toMutableList()
            val vacancyListSize = vacancyList.size
            val offerList = viewModel.getOffers().toMutableList()
            withContext(Dispatchers.Main) {

                viewModel.saveFlag(Text("lock"))
                val count = vacancyList.size
                while (vacancyList.size > 0) {
                    addVacancyToRecycler(vacancyList.removeAt(0))

                }
                while (offerList.size > 0) {
                    addOfferToRecycler(offerList.removeAt(0))
                }
                binding.viewAllJobsButton.text = "Еще ${getVacancyPlural(vacancyListSize - 3)}"
                binding.viewAllJobsButton.setOnClickListener {
                    for (i in 3..count) {
                        adapter.loadMoreItems()
                    }
                    binding.viewAllJobsButton.visibility = View.GONE
                    binding.recommendedRecyclerView.visibility = View.GONE
                    binding.hiddenTextView.text = "${getVacancyPlural(vacancyListSize)}"
                    binding.headerTextView.text = ""
                    binding.hiddenFilterTextView.visibility = View.VISIBLE
                    binding.blueLinesImageButton.visibility = View.VISIBLE
                    binding.searchEditText.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.special_search_acon,
                        0,
                        0,
                        0
                    )
                    binding.stateArrowImageButton.visibility = View.VISIBLE

                    binding.stateArrowImageButton.setOnClickListener {
                        binding.viewAllJobsButton.visibility = View.VISIBLE
                        binding.recommendedRecyclerView.visibility = View.VISIBLE
                        binding.headerTextView.text = "Вакансии для вас"
                        binding.hiddenTextView.text = ""
                        binding.searchEditText.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.search,
                            0,
                            0,
                            0
                        )
                        binding.hiddenFilterTextView.visibility = View.GONE
                        binding.blueLinesImageButton.visibility = View.GONE
                        binding.stateArrowImageButton.visibility = View.GONE
                    }
                }
            }
        }
    }
    private fun getVacancyPlural(count: Int): String {
        return when (count % 100) {
            11, 12, 13, 14 -> "$count вакансий"
            else -> when (count % 10) {
                1 -> "$count вакансия"
                2, 3, 4 -> "$count вакансии"
                else -> "$count вакансий"
            }
        }
    }
    private fun addVacancyToRecycler(vacancyDomain: VacancyDomain) {
        adapter.addVacancy(vacancyDomain)
    }

    private fun addOfferToRecycler(offerDomain: OfferDomain) {
        offerAdapter.addOffer(offerDomain)
    }
}
