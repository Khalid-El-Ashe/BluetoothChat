package com.example.bluttothchat.domain

// i need to rename this class
typealias BluetoothDeviceDomain = BluetoothDevice

// this class for bluetooth device
data class BluetoothDevice(
    val name: String?,
    val address: String
)