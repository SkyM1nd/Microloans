package com.example.martynov

import android.Manifest.permission.READ_CONTACTS
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Button
import android.widget.TextView
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

    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    private var task: Future<Any>? = null

    private val singlePermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            view?.findViewById<TextView>(R.id.permissionTextView)?.text = "Полученно"
        } else {
            view?.findViewById<TextView>(R.id.permissionTextView)?.text = "Не полученно"
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.permissionTextView).text = "Не запрошенно"

        val replaceButton = view.findViewById<Button>(R.id.replaceButtonFragmentC)
        val fragmentManager = activity?.supportFragmentManager
        val requestPermissionButton =
            view.findViewById<Button>(R.id.requestPermissionButtonFragmentC)
        val requestDataButton = view.findViewById<Button>(R.id.requestDataButtonFragmentC)

        replaceButton.setOnClickListener { replaceFragment(fragmentManager!!) }
        requestPermissionButton.setOnClickListener { checkPermission(view) }
        requestDataButton.setOnClickListener { requestData(view) }
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
            singlePermission.launch(READ_CONTACTS)
        }
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
                view.findViewById<TextView>(R.id.contactTextView).text = result
            }

        }
    }

    private fun retrieveImageNames(): String {
        val uri = ContactsContract.Contacts.CONTENT_URI

        val cursor = context?.contentResolver?.query(
            uri,
            null,
            null,
            null,
            null

        )
        val names = mutableListOf<String>()

        cursor?.use {
            val index = cursor.getColumnIndex("display_name")

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