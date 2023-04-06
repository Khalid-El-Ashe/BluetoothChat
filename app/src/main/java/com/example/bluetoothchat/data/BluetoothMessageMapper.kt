package com.example.bluetoothchat.data

import com.example.bluetoothchat.domain.BluetoothMessage

// this function to convert from ByteArray to String
fun String.toBluetoothMessage(isFromLocalUser: Boolean): BluetoothMessage {
    val name = substringBeforeLast("#")
    val message = substringAfter("#")

    return BluetoothMessage(message = message, senderName = name, isFromLocalUser = isFromLocalUser)
}

// this function to convert from String to ByteArray
fun BluetoothMessage.toByteArray(): ByteArray {
    return "$senderName#$message".encodeToByteArray()
}