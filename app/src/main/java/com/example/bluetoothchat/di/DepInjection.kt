package com.example.bluttothchat.appHilt

import android.content.Context
import com.example.bluttothchat.data.chat.AndroidBluetoothController
import com.example.bluttothchat.domain.BluetoothController
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // to make one instance for this functions
object DepInjection {

    // i need to inject the interface class BluetoothController
    @Provides
    @Singleton
    fun provideBluetoothController(@ApplicationContext context: Context): BluetoothController {
        return AndroidBluetoothController(context)
    }
}