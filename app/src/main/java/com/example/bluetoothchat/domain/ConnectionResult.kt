package com.example.bluetoothchat.domain

// this class to get the result for the connection if have error or the connection is work
sealed interface ConnectionResult{
    object ConnectionEstablish: ConnectionResult
    data class TransferSucceeded(val message: BluetoothMessage): ConnectionResult
    data class Error(val message: String): ConnectionResult
}