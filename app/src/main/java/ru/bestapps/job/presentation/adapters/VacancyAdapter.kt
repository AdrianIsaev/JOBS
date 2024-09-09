package ru.bestapps.job.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.bestapps.data.data.storage.models.Vacancy
import ru.bestapps.domain.models.VacancyDomain
import ru.bestapps.job.R
import ru.bestapps.job.databinding.VacancyListViewBinding
import ru.bestapps.job.presentation.viewmodel.SearchViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


class VacancyAdapter(private val viewModel: SearchViewModel, private val navController: NavController) :
    RecyclerView.Adapter<VacancyAdapter.Holder>() {

    private val arrayOfVacancies = ArrayList<VacancyDomain>()
    private var countOfVisibleItems = 3

    class Holder(view: View, private val context: Context, private val viewModel: SearchViewModel, private val navController: NavController) :
        RecyclerView.ViewHolder(view) {
        private val binding = VacancyListViewBinding.bind(view)
        fun bind(vacancyDomain: VacancyDomain) = with(binding) {
            cityTextView.text = vacancyDomain.address?.town

            if (vacancyDomain.isFavorite) isFavoriteImageButton.background = ContextCompat.getDrawable(context, R.drawable.blue_heart_icon)
            else ContextCompat.getDrawable(context, R.drawable.naked_heart)
            viewersCountTextView.text =
                "Сейчас просматривает ${getViewersCountText(vacancyDomain.lookingNumber)}"
            professionTextView.text = vacancyDomain.title
            companyTextView.text = vacancyDomain.company
            experienceTextView.text = vacancyDomain.experience?.previewText ?: ""
            dateOfPublicationTextView.text = "Опубликовано ${vacancyDomain.publishedDate?.let { parseString(it) }}"
            binding.isFavoriteImageButton.setOnClickListener {
                if (!vacancyDomain.isFavorite) {
                    binding.isFavoriteImageButton.background =
                        ContextCompat.getDrawable(context, R.drawable.blue_heart_icon)
                    vacancyDomain.isFavorite = true
                    viewModel.viewModelScope.launch(Dispatchers.IO) {
                        viewModel.setFavorite(vacancyDomain.ids, true)
                    }
                }
                else{
                    binding.isFavoriteImageButton.background =
                        ContextCompat.getDrawable(context, R.drawable.naked_heart)
                    vacancyDomain.isFavorite = false
                    viewModel.viewModelScope.launch(Dispatchers.IO) {
                        viewModel.setFavorite(vacancyDomain.ids, false)
                    }
                }
            }

            cardViewRoster.setOnClickListener { view ->
                if (view.id != binding.respondButton.id && view.id!= binding.isFavoriteImageButton.id){
                    val bundle = Bundle()
                    bundle.putInt("ids", vacancyDomain.ids)
//                    bundle.putString("title", vacancyDomain.title)
//                    bundle.putString("experience", vacancyDomain.experience?.text)
//                    bundle.putString("salary", vacancyDomain.salary?.full)
//                    bundle.putStringArrayList("schedules", vacancyDomain.schedules as java.util.ArrayList<String>?)
//                    bundle.putString("appliedNumber", vacancyDomain.appliedNumber.toString())
//                    bundle.putString("lookingNumber", vacancyDomain.lookingNumber.toString())
//                    bundle.putString("company", vacancyDomain.company)
//                    bundle.putString("town", vacancyDomain.address?.town)
//                    bundle.putString("house", vacancyDomain.address?.house)
//                    bundle.putString("street", vacancyDomain.address?.street)
//                    bundle.putString("description", vacancyDomain.description)
//                    bundle.putBoolean("isFavourite", vacancyDomain.isFavorite)
//                    bundle.putString("responsibilities", vacancyDomain.responsibilities)
//                    bundle.putStringArrayList("questions", vacancyDomain.questions as java.util.ArrayList<String>?)

                    navController.navigate(R.id.action_clientSearch_to_vacancyPage2, bundle)
                }

            }
        }


        private fun parseString(currString: String): String{
            val publishedDate = LocalDate.parse(currString)
            val formatter = DateTimeFormatter.ofPattern("d MMMM", Locale("ru"))
            val formattedDate = publishedDate.format(formatter)
            return formattedDate
        }
        private fun getViewersCountText(count: Int?): String {
            if (count != null) {
                return when {
                    count == 0 -> "0 человек"
                    count% 10 == 1 && count % 100 != 11 -> "$count человек"
                    count % 10 in 2..4 && (count % 100 !in 12..14) -> "$count человека"
                    else -> "$count человек"
                }
            }
            else
                return "0 человек"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyAdapter.Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.vacancy_list_view, parent, false)
        return Holder(view, parent.context, viewModel, navController)
    }

    override fun onBindViewHolder(holder: VacancyAdapter.Holder, position: Int) {
        holder.bind(arrayOfVacancies[position])
    }

    override fun getItemCount(): Int {
        return minOf(countOfVisibleItems, arrayOfVacancies.size)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addVacancy(vacancyDomain: VacancyDomain) {
            arrayOfVacancies.add(vacancyDomain)
            notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun loadMoreItems() {
        countOfVisibleItems++
        notifyDataSetChanged()
    }

    fun permission(): Int {
        return arrayOfVacancies.size
    }
}