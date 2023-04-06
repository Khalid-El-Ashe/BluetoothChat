package com.example.bluetoothchat.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import com.example.bluetoothchat.R
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bluttothchat.domain.BluetoothDevice
import com.example.bluttothchat.presentation.BluetoothUiState

@Composable
fun DeviceScreen(
    state: BluetoothUiState,
    onStartScan: () -> Unit,
    onStopScan: () -> Unit,
    onStartServer: () -> Unit,
    onDeviceClicked: (BluetoothDevice) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {

        // i need to add my list when i created
        BluetoothDeviceList(
            pairedDevice = state.pairedDevices,
            scannedDevice = state.scannedDevices,
            onclick = onDeviceClicked,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            Button(
                onClick = onStartScan, colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(
                        id = R.color.btn_color
                    )
                )
            ) {
                Text(
                    text = "Start Scan",
                    style = TextStyle(color = Color.Black)
                )
            }
            Button(onClick = onStopScan,colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(
                    id = R.color.btn_color
                )
            )) {
                Text(
                    text = "Stop Scan",
                    style = TextStyle(color = Color.Black)
                )
            }
            Button(onClick = onStartServer,colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(
                    id = R.color.btn_color
                )
            )) {
                Text(
                    text = "Start Server",
                    style = TextStyle(color = Color.Black)
                )
            }
        }
    }
}

// this function for my list items
@Composable
fun BluetoothDeviceList(
    pairedDevice: List<BluetoothDevice>,
    scannedDevice: List<BluetoothDevice>,
    onclick: (BluetoothDevice) -> Unit,
    modifier: Modifier = Modifier
) {
    // i need to make my adapter in the jetpack compose
    LazyColumn(
        modifier = modifier
    ) {
        item {
            // i need to make my custom ui for the list
            Text(
                text = "Paired Devices",
                modifier = Modifier.padding(16.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White
                )
            )
        }

        items(pairedDevice) { device ->
            Text(
                // ?: that is mean (else)
                text = device.name ?: "(No Name)",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onclick(device) }
                    .padding(16.dp)
            )
        }

        item {
            // i need to make my custom ui for the list
            Text(
                text = "Scanned Devices",
                modifier = Modifier.padding(16.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White
                )
            )
        }

        items(scannedDevice) { device ->
            Text(
                text = device.name ?: "(No Name)",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onclick(device) }
                    .padding(16.dp)
            )
        }
    }
}