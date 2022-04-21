package com.example.martynov

import android.Manifest
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

class FragmentC : Fragment(R.layout.fragment_c) {
    companion object {

        fun newInstance(): FragmentC =
            FragmentC().apply { }

    }

    private var granted: Boolean = false

    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    private var task: Future<Any>? = null

    private val singlePermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            when {
                granted -> {
                    Toast.makeText(context, "Changed", Toast.LENGTH_LONG).show()
                }
                !shouldShowRequestPermissionRationale(READ_EXTERNAL_STORAGE) -> {
                    Toast.makeText(context, "NOT", Toast.LENGTH_LONG).show()
                }
                else -> {
                    Toast.makeText(context, "ELSE", Toast.LENGTH_LONG).show()
                }
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (granted) {
            view.findViewById<TextView>(R.id.permissionTextView).text = "Полученно"
        } else {
            view.findViewById<TextView>(R.id.permissionTextView).text = "Не полученно"
        }

        val replaceButton = view.findViewById<Button>(R.id.replaceButtonFragmentC)
        val fragmentManager = activity?.supportFragmentManager
        val requestPermissionButton =
            view.findViewById<Button>(R.id.requestPermissionButtonFragmentC)
        val requestDataButton = view.findViewById<Button>(R.id.requestDataButtonFragmentC)

        replaceButton.setOnClickListener { replaceFragment(fragmentManager!!) }
        //requestPermissionButton.setOnClickListener { checkPermission(view) }
        //requestDataButton.setOnClickListener { requestData(view) }
    }

    private fun replaceFragment(fragmentManager: FragmentManager) {
        val transactionName = "ReplaceC"
        val fragment = FragmentA.newInstance()

        fragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(transactionName)
            .commit()
    }

    private fun checkPermission(view: View) {
        if (isPermissionGranted()) {
            view.findViewById<TextView>(R.id.permissionTextView).text = "Полученно"
        } else {
            singlePermission.launch(READ_EXTERNAL_STORAGE)
        }
        val fragment = FragmentA.newInstance()
    }

    private fun isPermissionGranted(): Boolean =
        PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(
            requireContext(),
            READ_EXTERNAL_STORAGE
        )

    private fun requestData(view: View) {
        task = executor.submit<Any> {
            val result = try {
                retrieveImageNames()
            } catch (error: NullPointerException) {
                error.message
            }
            activity?.runOnUiThread {
                view.findViewById<TextView>(R.id.ResultContentTextView).text = result
            }
        }
    }

    private fun retrieveImageNames(): String {
        val uri = Uri.parse("content://media/external/images/media")

        val projection = arrayOf("_id", "_display_name", "_size")
        val selection = "mime_type=?"
        val selectionArgs = arrayOf("image/jpeg")
        val sortOrder = "date_added DESC"

        // SELECT _id, _display_name, _size
        // WHERE mime_type="image/jpeg"
        // ORDER BY date_added DESC
        val cursor = context?.contentResolver?.query(
            uri,
            projection,
            selection,
            selectionArgs,
            sortOrder
        )
        val names = mutableListOf<String>()

        cursor?.use {
            val index = cursor.getColumnIndex("_display_name")

            while (cursor.moveToNext()) {
                val name = cursor.getString(index)

                names.add(name)
            }
        }

        return names.joinToString("\n")
    }

    override fun onDestroy() {
        super.onDestroy()

        task?.cancel(true)
        task = null

        executor.shutdown()
    }
}