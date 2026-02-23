package com.example.altecprint.core

import com.example.altecprint.model.Label
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.Socket

class PrinterManager {

    suspend fun printLabel(label: Label?, labelAmount: Int?) {
        var tspl: String? = label?.tspl

        tspl += "\nPRINT 1,$labelAmount"
        tspl += "\r\n"; // add new line at the end of tspl

        println(tspl)

//        sendTSPL(tspl)  commented out to save labels
    }

    suspend fun sendTSPL(tspl: String?): Result<Unit> = withContext(Dispatchers.IO) {
        val printerIP = "192.168.1.123"
//        val printerHostName = "PRN-Flor"
        val port = 9100

        try {
            Socket(printerIP, port).use { socket ->
                val data = tspl?.toByteArray(Charsets.US_ASCII)
                socket.getOutputStream().apply {
                    write(data)
                    flush()
                }
            }
            Result.success(Unit)
        } catch(e: Exception) {
            println(e)
            Result.failure(e)
        }
    }
}