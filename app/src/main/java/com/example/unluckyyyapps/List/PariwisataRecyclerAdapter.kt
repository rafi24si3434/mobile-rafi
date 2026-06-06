    package com.example.unluckyyyapps.List

    import android.view.LayoutInflater
    import android.view.ViewGroup
    import androidx.recyclerview.widget.RecyclerView
    import com.bumptech.glide.Glide
    import com.example.unluckyyyapps.databinding.ItemPariwisataBinding

    class PariwisataRecyclerAdapter(
        private val wisataList: List<Pariwisata>,
        private val onClick: (Pariwisata) -> Unit
    ) : RecyclerView.Adapter<PariwisataRecyclerAdapter.ViewHolder>() {

        inner class ViewHolder(
            val binding: ItemPariwisataBinding
        ) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewHolder {

            val binding = ItemPariwisataBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            return ViewHolder(binding)
        }

        override fun onBindViewHolder(
            holder: ViewHolder,
            position: Int
        ) {

            val wisata = wisataList[position]

            with(holder.binding) {

                tvNamaWisata.text = wisata.nama
                tvDeskripsi.text = wisata.deskripsi

                Glide.with(root.context)
                    .load(wisata.gambarUrl)
                    .into(ivWisata)

                root.setOnClickListener {
                    onClick(wisata)
                }
            }
        }

        override fun getItemCount(): Int {
            return wisataList.size
        }
    }