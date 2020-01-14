package com.hallel.home.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hallel.core.utils.Event
import com.hallel.core_ui.navigation.NavigationHelper.lvStartNavigationFromDeepLink
import com.hallel.home.R
import com.hallel.localrepository.entity.Partner
import kotlinx.android.synthetic.main.item_sponsor.view.*

class HomeSponsorAdapter(private val sponsorList: List<Partner>) :
    RecyclerView.Adapter<HomeSponsorAdapter.CustomHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        context = parent.context
        return CustomHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_sponsor, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return sponsorList.size
    }

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {
        val partner = sponsorList[position]
        Glide.with(context)
            .load(partner.image)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.sponsorImage)

        holder.bind(partner.link)
    }

    class CustomHolder(view: View) : RecyclerView.ViewHolder(view) {

        val sponsorImage: ImageView = view.itemSponsorImgLogo

        fun bind(link: String) {
            itemView.setOnClickListener {
                lvStartNavigationFromDeepLink.value = Event(link)
            }
        }
    }
}