package com.example.altecprint.model

class Label(val name: String, val tspl: List<String>) {
    var drawCommands: List<String> = listOf()

    fun getDrawTspl() {
        var clsPassed = false
        val commands = mutableListOf<String>()

        for(tsplLine in tspl) {
            if (tsplLine.contains("CLS")) {
                clsPassed = true
            }
            if (clsPassed) {
                commands.add(tsplLine)
            }
        }
        drawCommands = commands
    }

    fun getDynamicDrawElements() {

    }

}