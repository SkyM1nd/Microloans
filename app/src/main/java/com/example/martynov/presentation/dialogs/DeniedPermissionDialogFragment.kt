package com.example.martynov.presentation.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment


class DeniedPermissionDialogFragment : DialogFragment() {
    companion object {

        fun newInstance(
        ): DeniedPermissionDialogFragment {
            return DeniedPermissionDialogFragment().apply { }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Access to contacts denied")
                .setMessage("For the application to work, you need access to contacts, you have to go into Settings -> App -> Permissions and renable that perm for the app")
                .setCancelable(true)
                .setPositiveButton("OK") { _, _ ->
                    activity?.finishAndRemoveTask()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}