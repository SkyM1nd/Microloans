package com.example.martynov.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.martynov.R
import com.example.martynov.app.App
import com.example.martynov.presentation.dialogs.DeniedPermissionDialogFragment
import com.example.martynov.presentation.dialogs.PermissionDialogFragment
import com.example.martynov.presentation.fragments.SelectSaveFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ContactsViewModelFactory

    private lateinit var viewModel: ContactsViewModel

    private val singlePermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (!isGranted) {
            val myDialogFragment: DialogFragment =
                if (!shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {
                    DeniedPermissionDialogFragment.newInstance()
                } else {
                    PermissionDialogFragment.newInstance()
                }
            val manager = supportFragmentManager
            myDialogFragment.show(manager, "myDialog")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            (applicationContext as App).appComponent.inject(this)

            viewModel = ViewModelProvider(this, viewModelFactory)[ContactsViewModel::class.java]

            val transactionName = "Add Fragment select"
            val fragment = SelectSaveFragment.newInstance()

            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, fragment, "Fragment select")
                .addToBackStack(transactionName)
                .commit()
        }
        checkPermission()
    }

    fun checkPermission() {
        if (!isPermissionGranted()) {
            singlePermission.launch(Manifest.permission.READ_CONTACTS)
        }
    }

    private fun isPermissionGranted(): Boolean =
        PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_CONTACTS
        )

}