package com.example.bluetoothchat.data

import android.bluetooth.BluetoothSocket
import com.example.bluetoothchat.domain.BluetoothMessage
import com.example.bluetoothchat.domain.ConnectionResult
import com.example.bluetoothchat.domain.TransferFailedException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.io.IOException

class BluetoothDataTransferService(private val socket: BluetoothSocket) {

    // this function to listen the message Incoming
    fun listenForIncomingMessages(): Flow<BluetoothMessage> {
        return flow {
            if (socket.isConnected) {
                return@flow
            }
            val buffer = ByteArray(1024)
            while (true) {
                // in here i need to keep the life and incoming listen the data
                val byteCount = try {
                    socket.inputStream.read(buffer)
                } catch (e: IOException) {
                    // this class i well be created
                    throw TransferFailedException()
                }

                emit(
                    buffer.decodeToString(endIndex = byteCount)
                        .toBluetoothMessage(isFromLocalUser = false)
                )
            }
        }.flowOn(Dispatchers.IO)
    }

    // and this function to send a message to anther device
    suspend fun sendMessage(byte: ByteArray): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                socket.outputStream.write(byte)
            } catch (e: IOException) {
                e.printStackTrace()
                return@withContext false
            }

            true
        }
    }
}