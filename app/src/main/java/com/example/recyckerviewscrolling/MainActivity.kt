package com.example.recyckerviewscrolling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyckerviewscrolling.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var noteList: ArrayList<Note>
    private lateinit var manager: LinearLayoutManager
    private lateinit var adapter: NoteAdapter
    private var isScrolling: Boolean = false
    private var currentItem: Int = 0
    private var totalItems: Int = 0
    private var scrolledOutItems: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        noteList = arrayListOf()
        noteList = Utils.getData()
        setupRecyclerView()

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItem = manager.childCount
                totalItems = manager.itemCount
                scrolledOutItems = manager.findFirstVisibleItemPosition()

                if (isScrolling && (currentItem + scrolledOutItems == totalItems)) {
                    fetchData();
                    isScrolling = false;
                }

            }
        })
    }

    private fun setupRecyclerView() {
        manager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = manager
        adapter = NoteAdapter(noteList)

        binding.recyclerView.adapter = adapter
    }

    private fun fetchData() {
        binding.progressBar.visibility = View.VISIBLE
        for (i in 1..50) {
            noteList.add(Note(Math.random().toInt(), "anuj"))
        }

        adapter.notifyDataSetChanged()

        binding.progressBar.visibility = View.GONE
    }
}