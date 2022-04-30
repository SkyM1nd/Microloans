package com.example.martynov.fragments

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.martynov.ContactEntity
import com.example.martynov.ContactsAdapter
import com.example.martynov.R
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

open class BaseFragment : Fragment() {
    var contactsAdapter = ContactsAdapter(onClick = {
        Toast.makeText(context, "clicked on $it", Toast.LENGTH_SHORT).show()
    })

    val executor: ExecutorService = Executors.newSingleThreadExecutor()
    var task: Future<Any>? = null

    fun fillContacts(result: MutableList<ContactEntity>) {
        activity?.runOnUiThread {
            activity?.findViewById<RecyclerView>(R.id.contactsRecyclerView)?.apply {
                contactsAdapter.contacts = result
                adapter = contactsAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        task?.cancel(true)
        task = null

        executor.shutdown()
    }
}