package com.frame_application

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.frame_application.adapter.RecyclerViewAdapter
import com.frame_application.fragments.EditItemDialogFragment
import com.frame_application.fragments.NewItemDialogFragment
import com.frame_application.model.Item
import com.frame_application.view_model.ViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_list.*

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.ItemClickListener {

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {
            NewItemDialogFragment().show(
                supportFragmentManager,
                NewItemDialogFragment.TAG
            )
        }

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        viewModel.allItems.observe(this) { items ->
            recyclerViewAdapter.addAll(items)
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        recyclerViewAdapter = RecyclerViewAdapter()
        recyclerViewAdapter.itemClickListener = this
        rvItemList.adapter = recyclerViewAdapter
    }

    override fun onItemClick(item: Item) {
        TODO("Not yet implemented")
    }

    override fun onItemLongClick(item: Item) {
        val bundle = Bundle()
        bundle.putSerializable("item", item)

        val editFragment = EditItemDialogFragment()
        editFragment.arguments = bundle

        editFragment.show(
            supportFragmentManager,
            EditItemDialogFragment.TAG
        )
    }

}
