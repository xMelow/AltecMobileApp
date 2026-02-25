package com.example.altecprint.model

import androidx.compose.ui.input.key.Key

data class Label(val name: String, var tspl: String) {
    val variableData: MutableMap<String, String> = mutableMapOf()

    init {
        populateVariableDataMap()
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