package com.example.unluckyyyapps.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.unluckyyyapps.databinding.ItemOnboardingSlideBinding

class OnboardingAdapter(
    private val items: List<OnboardingItem>
) : RecyclerView.Adapter<OnboardingAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemOnboardingSlideBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemOnboardingSlideBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvSlideGraphic.text = item.graphic
        holder.binding.tvSlideTitle.text = item.title
        holder.binding.tvSlideDescription.text = item.description
    }

    override fun getItemCount(): Int = items.size
}
