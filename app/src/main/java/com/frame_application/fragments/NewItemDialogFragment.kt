package com.frame_application.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.frame_application.R
import com.frame_application.model.Item
import com.frame_application.view_model.ViewModel
import kotlinx.android.synthetic.main.dialog_item.view.*

class NewItemDialogFragment : DialogFragment() {
    companion object {
        const val TAG = "NewItemDialogFragment"
    }

    private lateinit var contentView: View
    private val viewModel = ViewModelProvider(this).get(ViewModel::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_item, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        contentView =
            LayoutInflater.from(context).inflate(R.layout.dialog_item, null)

        return AlertDialog.Builder(requireActivity())
            .setTitle("Create an item")
            .setView(contentView)
            .setPositiveButton("Create") { _, _ ->
                if (isValid()) {
                    viewModel.insert(getItem())
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
    }

    private fun isValid(): Boolean {
        return contentView.editTextItemName.text.isNotEmpty()
    }

    private fun getItem(): Item {
        return Item(
            itemName = contentView.editTextItemName.text.toString()
        )
    }

}