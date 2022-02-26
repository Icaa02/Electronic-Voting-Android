package com.app.myvoting.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.myvoting.DetailKandidatActivity
import com.app.myvoting.retrofit.response.kandidat.ModelResult
import com.app.myvoting.R
import com.app.myvoting.databinding.ItemKandidatBinding

class KandidatAdapter(private val context: Context, private val kandidat: List<ModelResult>, private val id: Int) : RecyclerView.Adapter<KandidatAdapter.KandidatViewHolder>(){
    private var img = listOf<Int>(R.drawable.one, R.drawable.two, R.drawable.tree,)
    inner class KandidatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemKandidatBinding.bind(itemView)
        fun bindView(kandidat: ModelResult, id: Int, position: Int){
            binding.imgItemPhoto.setImageResource(img[position])
            binding.tvItemName.text = kandidat.namaKandidat
            binding.tvNoUrut.text = "Nomor Urut: ${kandidat.noUrut}"
            itemView.setOnClickListener{
                Intent(itemView.context, DetailKandidatActivity::class.java).also {
                    it.putExtra(DetailKandidatActivity.KANDIDAT_ID, kandidat.id)
                    it.putExtra(DetailKandidatActivity.NAMA_KANDIDAT, kandidat.namaKandidat)
                    it.putExtra(DetailKandidatActivity.NO_URUT, kandidat.noUrut)
                    it.putExtra(DetailKandidatActivity.VISI_MISI, kandidat.visiMisi)
                    it.putExtra("ID", id)
                    it.putExtra(DetailKandidatActivity.IMAGE, img[position])
                    itemView.context.startActivity(it)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KandidatViewHolder {
        return KandidatViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_kandidat, parent, false)
        )
    }

    override fun onBindViewHolder(holder: KandidatViewHolder, position: Int) {
        holder.bindView(kandidat[position], id, position)
    }

    override fun getItemCount(): Int = kandidat.size
}
