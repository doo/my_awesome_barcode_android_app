package com.example.myapplication

import android.app.Application
import io.scanbot.sdk.barcode_scanner.ScanbotBarcodeScannerSDKInitializer

class ExampleApplication : Application() {
   override fun onCreate() {
       super.onCreate()

       ScanbotBarcodeScannerSDKInitializer()
           .license(this, "") // Please add a valid trial license key here. See the notes below!
           .initialize(this)
   }
}