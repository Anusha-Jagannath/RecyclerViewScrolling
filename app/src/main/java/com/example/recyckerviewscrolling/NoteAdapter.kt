package com.example.recyckerviewscrolling

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyckerviewscrolling.databinding.ItemNoteBinding

class NoteAdapter(private val noteList: ArrayList<Note>) : RecyclerView.Adapter<NoteViewHolder>() {

    private lateinit var binding: ItemNoteBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        binding = ItemNoteBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        )
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentItem = noteList[position]
        holder.bind(currentItem)

    }


}


class NoteViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(note: Note) {
        binding.title.text = note.title.toString()
        binding.subtitle.text = note.id.toString()
    }
}