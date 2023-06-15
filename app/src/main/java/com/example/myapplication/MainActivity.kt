package com.example.myapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import io.scanbot.sdk.barcode.entity.BarcodeScanningResult
import io.scanbot.sdk.ui.barcode_scanner.view.barcode.BarcodeScannerActivity
import io.scanbot.sdk.ui.view.barcode.configuration.BarcodeScannerConfiguration

class MainActivity : AppCompatActivity() {
    private lateinit var barcodeScannerLauncher: ActivityResultLauncher<BarcodeScannerConfiguration>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        barcodeScannerLauncher = registerForActivityResult(
            BarcodeScannerActivity.ResultContract()) {
                resultEntity: BarcodeScannerActivity.Result ->
            if (resultEntity.resultOk) {
                presentResult(resultEntity.result)
            }
        }

        findViewById<Button>(R.id.start_scanner_button).setOnClickListener {
            scanBarcode()
        }
    }

    private fun scanBarcode() {
        val barcodeCameraConfiguration = BarcodeScannerConfiguration()
        barcodeCameraConfiguration.setTopBarBackgroundColor(Color.RED)
        barcodeScannerLauncher.launch(barcodeCameraConfiguration)
    }

    private fun presentResult(resultEntity: BarcodeScanningResult?) {
        val resultTextView = findViewById<TextView>(R.id.barcode_result_text_view)
        resultEntity?.let {
            val firstItem = it.barcodeItems[0]
            resultTextView.text = "${firstItem.barcodeFormat.name}: ${firstItem.text}"
        }
    }
}