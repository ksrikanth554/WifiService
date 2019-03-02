package com.sritechsoftsolutions.wifiservice

import android.app.Application
import android.content.Context
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var wManager: WifiManager = getApplicationContext().getSystemService(Context.WIFI_SERVICE) as WifiManager
        var wSate: Int = wManager.wifiState
        when (wSate) {
            0 -> switch1.setChecked(false)
            1->switch1.setChecked(true)


        }
        switch1.setOnCheckedChangeListener { buttonView, isChecked ->
            wManager.setWifiEnabled(isChecked)
        }
        getWifiDevices.setOnClickListener {
            var wList=wManager.scanResults
            var tempList= mutableListOf<String>()
            for (devices in wList)
            {
                tempList.add(devices.SSID+"\n"+devices.frequency)

            }
            var adapter=ArrayAdapter<String>(this@MainActivity,R.layout.abc_list_menu_item_radio,tempList)
            lView.adapter=adapter
        }

        pairedDevices.setOnClickListener {
            var wList=wManager.configuredNetworks
            var tempList= mutableListOf<String>()
            for (devices in wList)
            {
                tempList.add(devices.SSID+"\n"+devices.status)

            }
            var adapter=ArrayAdapter<String>(this@MainActivity,R.layout.abc_list_menu_item_radio,tempList)
            lView.adapter=adapter
        }
    }
}