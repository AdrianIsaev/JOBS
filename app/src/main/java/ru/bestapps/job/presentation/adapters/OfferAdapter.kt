package ru.bestapps.job.presentation.adapters

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import ru.bestapps.domain.models.OfferDomain
import ru.bestapps.job.R
import ru.bestapps.job.databinding.RecomendedListViewBinding

class OfferAdapter(): RecyclerView.Adapter<OfferAdapter.Holder>() {
    val arrayOfRecommends = ArrayList<OfferDomain>()

    class Holder(view: View, private val context: Context): RecyclerView.ViewHolder(view){
        private val binding = RecomendedListViewBinding.bind(view)
        fun bind(offerDomain: OfferDomain) = with(binding) {
            val title: String = offerDomain.title!!.trimStart()

            val firstSpaceIndex = title.indexOf(" ")

            if (firstSpaceIndex != -1) {
                val firstWord = firstSpaceIndex.let { title.substring(0, it) }
                val remainingText = title.substring(firstSpaceIndex.plus(1))
                recommendedTextView.text = "$firstWord\n$remainingText"
            } else {
                recommendedTextView.text = title
            }
            binding.recommendedHintTextView.text = offerDomain.button?.text
            when(offerDomain.id){
                "level_up_resume"->vacancyIcon.background=ContextCompat.getDrawable(context, R.drawable.green_star_in_dark_green_circle_icon)
                "temporary_job" ->vacancyIcon.background=ContextCompat.getDrawable(context, R.drawable.green_list_on_dark_green_circle_icon)
                "near_vacancies" ->vacancyIcon.background=ContextCompat.getDrawable(context, R.drawable.blue_star_icon)
            }
            binding.recommendedCardView.setOnClickListener {
                goNet(offerDomain.link.toString(), context)
            }
        }
        private fun goNet(url: String, context: Context){
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            context.startActivity(intent)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferAdapter.Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recomended_list_view, parent, false)
        return OfferAdapter.Holder(view, parent.context)
    }
    override fun onBindViewHolder(holder: OfferAdapter.Holder, position: Int) {
        holder.bind(arrayOfRecommends[position])
    }
    override fun getItemCount(): Int {
        return arrayOfRecommends.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun addOffer(offerDomain: OfferDomain){
        arrayOfRecommends.add(offerDomain)
        notifyDataSetChanged()
    }
}