package com.example.unluckyyyapps.Home

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.unluckyyyapps.R

class NewsAdapter(
    private val newsList: MutableList<News> = mutableListOf()
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivThumbnail: ImageView = view.findViewById(R.id.ivNewsThumbnail)
        val tvSourceDate: TextView = view.findViewById(R.id.tvNewsSourceDate)
        val tvTitle: TextView = view.findViewById(R.id.tvNewsTitle)
        val tvDescription: TextView = view.findViewById(R.id.tvNewsDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_news, parent, false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = newsList[position]

        holder.tvTitle.text = news.title
        holder.tvDescription.text = news.description ?: "Baca selengkapnya..."
        holder.tvSourceDate.text = "${news.sourceName} • ${formatDate(news.publishedAt)}"

        Glide.with(holder.itemView.context)
            .load(news.urlToImage)
            .transform(CenterCrop(), RoundedCorners(16))
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_menu_gallery)
            .into(holder.ivThumbnail)

        // Buka berita di browser saat diklik
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(news.url))
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = newsList.size

    fun submitList(list: List<News>) {
        newsList.clear()
        newsList.addAll(list)
        notifyDataSetChanged()
    }

    private fun formatDate(dateStr: String): String {
        return try {
            // Format: 2025-06-05T10:30:00Z -> 05 Jun 2025
            val parts = dateStr.split("T")[0].split("-")
            val months = arrayOf(
                "", "Jan", "Feb", "Mar", "Apr", "Mei", "Jun",
                "Jul", "Agu", "Sep", "Okt", "Nov", "Des"
            )
            val day = parts[2]
            val month = months[parts[1].toInt()]
            val year = parts[0]
            "$day $month $year"
        } catch (e: Exception) {
            "Baru saja"
        }
    }
}
