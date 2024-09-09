package ru.bestapps.job.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import ru.bestapps.job.R
import ru.bestapps.job.databinding.FragmentUnregisteredMainBinding

class UnregisteredMain : Fragment() {
    private lateinit var binding: FragmentUnregisteredMainBinding
    private lateinit var button: Button
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUnregisteredMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navView = binding.bottomNavigation
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.fragmentContainerViewU) as NavHostFragment
        val navController = navHostFragment.navController

        NavigationUI.setupWithNavController(navView, navController)

     }
    fun setBottomNavigationVisibility() {
        binding.bottomNavigation.visibility = View.GONE
    }
}