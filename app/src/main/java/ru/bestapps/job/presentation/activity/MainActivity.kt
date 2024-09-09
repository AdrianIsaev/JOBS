package ru.bestapps.job.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.bestapps.job.databinding.ActivityMainBinding
import ru.bestapps.job.presentation.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<MainActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch(Dispatchers.IO) {
            val vacanciesList = viewModel.loadVacanciesList()
            val offersList = viewModel.loadOffersList()
            viewModel.saveLoadedVacanciesInRoom(vacanciesList)
            viewModel.saveLoadedOffersInRoom(offersList)
        }
    }

}
