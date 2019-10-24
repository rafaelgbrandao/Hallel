package com.hallel.home.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hallel.core_ui.navigation.NavigationHelper
import com.hallel.core_ui.navigation.NavigationHelper.lvStartNavigationFromClick
import com.hallel.core_ui.navigation.NavigationHelper.lvStartNavigationFromDeepLink
import com.hallel.core_ui.navigation.NavigationHelper.lvStartNavigationFromFlow
import com.hallel.core_ui.navigation.NavigationObject
import com.hallel.home.R
import com.hallel.localrepository.entity.Participant
import kotlinx.android.synthetic.main.item_participant.view.*
import kotlinx.android.synthetic.main.item_sponsor.view.*

class HomeParticipantsAdapter(private val participantList: List<Participant>) :
    RecyclerView.Adapter<HomeParticipantsAdapter.CustomHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        context = parent.context
        return CustomHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_participant, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return participantList.size
    }

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {
        val participant = participantList[position]
        Glide.with(context)
            .load(participant.image)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.participantPhoto)
        holder.participantName.text = participant.name
        holder.bind(participant)
    }

    class CustomHolder(view: View) : RecyclerView.ViewHolder(view) {

        val participantPhoto: ImageView = view.itemParticipantImgPhoto
        val participantName: TextView = view.itemParticipantTxtName

        fun bind(participant: Participant) {
            itemView.setOnClickListener {
                lvStartNavigationFromFlow.value =
                    NavigationObject(
                        screenName = HomeParticipantsAdapter::class.java.name,
                        extras = participant
                    )
            }
        }
    }
}