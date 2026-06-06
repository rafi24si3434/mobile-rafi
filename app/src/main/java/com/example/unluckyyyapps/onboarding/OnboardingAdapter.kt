package com.example.unluckyyyapps.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.unluckyyyapps.R

class OnboardingAdapter(
    private val items: List<OnboardingItem>
) : RecyclerView.Adapter<OnboardingAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvGraphic: TextView = view.findViewById(R.id.tvSlideGraphic)
        val tvTitle: TextView = view.findViewById(R.id.tvSlideTitle)
        val tvDescription: TextView = view.findViewById(R.id.tvSlideDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_onboarding_slide,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvGraphic.text = item.graphic
        holder.tvTitle.text = item.title
        holder.tvDescription.text = item.description
    }

    override fun getItemCount(): Int = items.size
}
