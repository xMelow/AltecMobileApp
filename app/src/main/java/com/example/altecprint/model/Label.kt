package com.example.altecprint.model

import androidx.compose.ui.input.key.Key

data class Label(val name: String, var tspl: String) {
    val variableData: MutableMap<String, String> = mutableMapOf()
    val printerSettings: MutableMap<String, List<String>> = mutableMapOf()

    init {
        populateVariableDataMap()
        populatePrinterSettingsMap()
    }

    fun populateVariableDataMap() {
        variableData.clear()
        val regex = Regex("!([A-Za-z0-9_]+)")
        tspl.lines().forEach { line ->
            regex.findAll(line).forEach { match ->
                val variableName = match.groupValues[1]
                variableData[variableName] = ""
            }
        }
    }

    fun populatePrinterSettingsMap() {
        tspl.lines().forEach { line ->
            if (line.isEmpty()) return@forEach

            val parts = line.trim().split("\\s+".toRegex())

            val commandName: String
            val params: List<String>

            if (parts[0] == "SET" && parts.size >= 3) {
                commandName = parts[1]
                params = parts.drop(2)
            } else if (parts.size >= 2) {
                commandName = parts[0]
                params = parts.drop(1).joinToString(" ").split(",")
            } else {
                return@forEach
            }

            printerSettings[commandName] = params
        }
    }

    fun buildFinalTspl(variableData: Map<String, String>): String {
        var result = tspl

        variableData.forEach { (key, value) ->
            result = result.replace("!${key}", value)
        }
        return result
    }

    fun updateTspl(newTspl: String): Label {
        return Label(name, newTspl)
    }

}