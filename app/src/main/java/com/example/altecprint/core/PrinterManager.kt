package com.example.altecprint.core

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.Socket

class PrinterManager {
    var printerIpOrHostname = "192.168.1.123"
    var printerPort = 9100

    suspend fun printLabel(finalTspl: String?, labelAmount: Int?) {
        var tspl = finalTspl

        tspl += "\nPRINT 1,$labelAmount"
        tspl += "\r\n";

        sendData(tspl)
    }

    suspend fun sendBasData(data: String) {
        var dataToSend = data
        dataToSend += "\r\n";
        sendData(dataToSend)
    }

    suspend fun sendData(data: String): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            Socket(printerIpOrHostname, printerPort).use { socket ->
                val data = data.toByteArray(Charsets.US_ASCII)
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

    fun connectToPrinter(ipOrHostname: String, port: Int) {
        printerIpOrHostname = ipOrHostname
        printerPort = port
        // send ping or test the connection of the printer is found

    }
}