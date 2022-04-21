package com.example.martynov

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.localbroadcastmanager.content.LocalBroadcastManager


class FragmentB : Fragment(R.layout.fragment_b) {
    companion object {

        fun newInstance(): FragmentB =
            FragmentB().apply { }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LocalBroadcastManager.getInstance(view.context).registerReceiver(
            object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
                    val nameSong =
                        intent.getStringExtra(MediaBroadcastService.EXTRA_NAME)
                    view.findViewById<TextView>(R.id.NameSongTextView).text = nameSong
                }
            }, IntentFilter(MediaBroadcastService.ACTION_MEDIA_BROADCAST)
        )

        view.findViewById<TextView>(R.id.NameSongTextView).text = MediaBroadcastService.nameSong

        val replaceButton = view.findViewById<Button>(R.id.replaceButtonFragmentMedia)
        val startButton = view.findViewById<Button>(R.id.startButtonFragmentMedia)
        val stopButton = view.findViewById<Button>(R.id.stopButtonFragmentMedia)
        val fragmentManager = activity?.supportFragmentManager

        replaceButton.setOnClickListener { replaceFragment(fragmentManager!!) }
        startButton.setOnClickListener { startService(view.context) }
        stopButton.setOnClickListener { stopService(view.context) }
    }

    private fun replaceFragment(fragmentManager: FragmentManager) {
        val transactionName = "ReplaceB"
        val fragment = FragmentC.newInstance()

        fragmentManager.beginTransaction()

            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(transactionName)
            .commit()
    }

    private fun startService(context: Context) {
        activity?.startService(Intent(context, MediaBroadcastService::class.java))
    }

    private fun stopService(context: Context) {
        activity?.stopService(Intent(context, MediaBroadcastService::class.java))
    }

}