package ru.bestapps.job.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.bestapps.domain.models.VacancyDomain
import ru.bestapps.job.R
import ru.bestapps.job.databinding.FragmentClientFavoritesBinding
import ru.bestapps.job.databinding.FragmentClientSearchBinding
import ru.bestapps.job.presentation.adapters.FavoritesAdapter
import ru.bestapps.job.presentation.viewmodel.ClientFavoritesViewModel

class ClientFavorites : Fragment() {
    private lateinit var adapter: FavoritesAdapter
    private lateinit var binding: FragmentClientFavoritesBinding
    private var count = 0
    private val viewModel by viewModel<ClientFavoritesViewModel>()
    private var parentFragment: Fragment? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClientFavoritesBinding.inflate(inflater, container, false)

        adapter = FavoritesAdapter(viewModel)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch(Dispatchers.IO) {
            val vacanciesList = viewModel.getVacancies()
            withContext(Dispatchers.Main) {
                for (vacancy in vacanciesList) {
                    if (vacancy.isFavorite) {
                        addVacancyToRecycler(vacancy)
                        count++
                        binding.countTextView.text = countChanged(count)
                    }
                }
            }
        }




    }

    private fun countChanged(count: Int): String {
        return when {
            count % 10 == 1 && count % 100 != 11 -> "$count вакансия"
            count % 10 in 2..4 && (count % 100 !in 12..14) -> "$count вакансии"
            else -> "$count вакансий"

        }
    }

    private fun addVacancyToRecycler(vacancyDomain: VacancyDomain) {
        adapter.addVacancy(vacancyDomain)
    }
}