package com.example.altecprint.util

object TsplParser {

    fun parseVariableData(tspl: String): Map<String, String> {
        val regex = Regex("!([A-Za-z0-9_]+)")
        return tspl.lines()
            .flatMap { regex.findAll(it).toList() }
            .associate { it.groupValues[1] to "" }
    }

    fun parsePrinterSettings(tspl: String): Map<String, List<String>> {
        val settings = mutableMapOf<String, List<String>>()
        for (line in tspl.lines()) {
            val trimmed = line.trim()
            if (trimmed.isEmpty()) continue
            if (trimmed == "CLS") break

            val parts = trimmed.split("\\s+".toRegex())
            val commandName: String
            val params: List<String>

            if (parts[0] == "SET" && parts.size >= 3) {
                commandName = "SET ${parts[1]}"
                params = parts.drop(2)
            } else if (parts.size >= 2) {
                commandName = parts[0]
                params = parts.drop(1).joinToString(" ").split(",")
            } else {
                continue
            }
            settings[commandName] = params
        }
        return settings
    }

    fun buildFinalTspl(tspl: String, variableData: Map<String, String>): String {
        var result = tspl
        variableData.forEach { (key, value) ->
            result = result.replace("!$key", value)
        }
        return result
    }
}