package ru.bestapps.job.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import ru.bestapps.domain.models.BackDomain
import ru.bestapps.domain.models.OfferDomain
import ru.bestapps.job.R
import ru.bestapps.job.databinding.BackListViewBinding
import ru.bestapps.job.databinding.RecomendedListViewBinding
import ru.bestapps.job.presentation.fragments.RespondFirst
import ru.bestapps.job.presentation.fragments.RespondSecond

class BackAdapter(private val context: Context, private val parentFragmentManager: FragmentManager): RecyclerView.Adapter<BackAdapter.Holder>() {
    private val arrayOfBacks = ArrayList<BackDomain>()



    class Holder(view: View, private val context: Context, private val parentFragmentManager: FragmentManager): RecyclerView.ViewHolder(view){
        private val binding = BackListViewBinding.bind(view)
        fun bind(offerDomain: BackDomain) = with(binding) {
            backButton.text = offerDomain.text
            backButton.setOnClickListener{
                val resumeDialogFragment = RespondSecond.newInstance(offerDomain.text)
                resumeDialogFragment.show(parentFragmentManager, "RespondFragment")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BackAdapter.Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.back_list_view, parent, false)
        return BackAdapter.Holder(view, context, parentFragmentManager)
    }

    override fun onBindViewHolder(holder: BackAdapter.Holder, position: Int) {
        holder.bind(arrayOfBacks[position])
    }

    override fun getItemCount(): Int {
        return arrayOfBacks.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun addBack(backDomain: BackDomain){
        arrayOfBacks.add(backDomain)
        notifyDataSetChanged()
    }

}