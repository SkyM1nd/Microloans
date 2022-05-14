package com.example.martynov.presentation.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.martynov.presentation.MainActivity

open class PermissionDialogFragment : DialogFragment() {
    companion object {

        fun newInstance(): PermissionDialogFragment {
            return PermissionDialogFragment().apply { }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Contacts not accessed")
                .setMessage("For the application to work, access to contacts is required, give permission?")
                .setCancelable(true)
                .setPositiveButton("OK") { _, _ ->
                    (activity as MainActivity).checkPermission()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}