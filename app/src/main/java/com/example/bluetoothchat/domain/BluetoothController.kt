package com.example.bluttothchat.domain

import com.example.bluetoothchat.domain.BluetoothMessage
import com.example.bluetoothchat.domain.ConnectionResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

// this class to can be scan the bluetooth devices and connect the device and start the server
interface BluetoothController {

    val isConnected: StateFlow<Boolean>

    // this element to scan devices
    val scanDevices: StateFlow<List<BluetoothDevice>>
    val pairedDevices: StateFlow<List<BluetoothDevice>>
    val errors: SharedFlow<String>

    // i need to make some function to start and stop run bluetooth
    fun startDiscovery()
    fun stopDiscovery()

    // this function to lunch the server
    fun startBluetoothServer(): Flow<ConnectionResult>

    // this functions to connection the server
    fun connectToDevice(device: BluetoothDevice): Flow<ConnectionResult>

    suspend fun trySendMessage(message: String): BluetoothMessage?

    fun closeConnection()
    fun release()
}