package com.example.ble2

import android.bluetooth.le.ScanResult
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup;
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView;
import kotlinx.android.synthetic.main.row_scan_result.view.*

//import kotlinx.android.synthetic.main.row_scan_result.view.*;
//import org.jetbrains.anko.layoutInflater;

class ScanResultAdapter(
        private val items: List<ScanResult>,
        private val onClickListener: ((device: ScanResult) -> Unit)
        ) : RecyclerView.Adapter<ScanResultAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(
                R.layout.row_scan_result,
                parent,
                false
            )
            return ViewHolder(view, onClickListener)
        }

        override fun getItemCount() = items.size

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = items[position]
            holder.bind(item)
        }

        class ViewHolder(
                private val view: View,
                private val onClickListener: ((device: ScanResult) -> Unit)
                ) : RecyclerView.ViewHolder(view) {

                    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
                    fun bind(result: ScanResult) {
                    view.device_name.text = result.device.name ?: "Unnamed"
                    view.mac_address.text = result.device.address
                    view.signal_strength.text = "${result.rssi} dBm"
                    view.setOnClickListener { onClickListener.invoke(result) }
                }
        }
}
