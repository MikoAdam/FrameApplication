package com.frame_application.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.frame_application.R
import com.frame_application.model.Item
import com.frame_application.view_model.ViewModel
import kotlinx.android.synthetic.main.dialog_item.view.*
import kotlinx.coroutines.launch

class EditItemDialogFragment : DialogFragment() {

    companion object {
        const val TAG = "EditItemDialogFragment"
    }

    private lateinit var contentView: View
    private lateinit var item: Item
    private val viewModel = ViewModelProvider(this).get(ViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = this.arguments;
        if (bundle != null) {
            item = bundle.getSerializable("item") as Item
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_item, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        contentView =
            LayoutInflater.from(context).inflate(R.layout.dialog_item, null)

        contentView.editTextItemName.setText(item.itemName)

        return AlertDialog.Builder(requireActivity())
            .setTitle("Edit an item")
            .setView(contentView)
            .setPositiveButton("Edit") { _, _ ->
                if (isValid()) {
                    val item = getItem()
                    viewModel.update(item)
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
    }

    private fun isValid(): Boolean {
        return contentView.editTextItemName.text.isNotEmpty()
    }

    private fun getItem(): Item {
        item.itemName = contentView.editTextItemName.text.toString()
        return item
    }

}