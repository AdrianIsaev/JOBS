package ru.bestapps.job.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.bestapps.domain.models.VacancyDomain
import ru.bestapps.job.R
import ru.bestapps.job.databinding.VacancyListViewBinding
import ru.bestapps.job.presentation.viewmodel.ClientFavoritesViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


class FavoritesAdapter(private val viewModel: ClientFavoritesViewModel) :
    RecyclerView.Adapter<FavoritesAdapter.Holder>() {

    private val arrayOfVacancies = ArrayList<VacancyDomain>()


    class Holder(view: View, private val context: Context, private val viewModel: ClientFavoritesViewModel) :
        RecyclerView.ViewHolder(view) {
        private val binding = VacancyListViewBinding.bind(view)
        fun bind(vacancyDomain: VacancyDomain) = with(binding) {

            cityTextView.text = vacancyDomain.address?.town
            if (vacancyDomain.isFavorite) isFavoriteImageButton.background = ContextCompat.getDrawable(context, R.drawable.blue_heart_icon)
            else ContextCompat.getDrawable(context, R.drawable.naked_heart)
            val countOfViewers = getViewersCountText(vacancyDomain.lookingNumber)
            viewersCountTextView.text =
                "Сейчас просматривает ${getViewersCountText(vacancyDomain.lookingNumber)}"
            professionTextView.text = vacancyDomain.title
            companyTextView.text = vacancyDomain.company
            experienceTextView.text = vacancyDomain.experience?.previewText ?: ""
            dateOfPublicationTextView.text = "Опубликовано ${vacancyDomain.publishedDate?.let { parse(it) }}"
            binding.isFavoriteImageButton.setOnClickListener {

            }

        }
        private fun parse(currString: String): String{
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesAdapter.Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.vacancy_list_view, parent, false)
        return Holder(view, parent.context, viewModel)
    }

    override fun onBindViewHolder(holder: FavoritesAdapter.Holder, position: Int) {
        holder.bind(arrayOfVacancies[position])
    }

    override fun getItemCount(): Int {
        return  arrayOfVacancies.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addVacancy(vacancyDomain: VacancyDomain) {
        arrayOfVacancies.add(vacancyDomain)
        notifyDataSetChanged()
    }
}