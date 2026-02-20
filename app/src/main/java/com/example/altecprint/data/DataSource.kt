package com.example.altecprint.data

import com.example.altecprint.model.Label

object DataSource {

    val labels = listOf<Label>(
        Label(
            name = "invalid label",
            tspl = listOf(
                "invalid tspl"
            )
        ),

        Label(
            name = "User info label",
            tspl = listOf(
                "SIZE 103 mm,110 mm",
                "GAP 3 mm,0 mm",
                "REFERENCE 0,0",
                "SPEED 2.0",
                "DENSITY 8",
                "SET RIBBON ON",
                "SET PEEL OFF",
                "SET CUTTER OFF",
                "SET PARTIAL_CUTTER OFF",
                "SET TEAR ON",
                "SET REWIND OFF",
                "DIRECTION 0,0",
                "SHIFT 0,0",
                "OFFSET 0 mm",
                "",
                "CLS",
                "TEXT 427,47,\"0\",0,16,16,\"Hello World\"",
                "BAR 33,135,1199,7",
                "BOX 33,178,1232,890,6",
                "QRCODE 938,951,L,14,A,0,M2,S7,\"123456789012\"",
                "CIRCLE 933,590,260,12",
                "TEXT 77,234,\"0\",0,16,16,\"Name:\"",
                "TEXT 77,322,\"0\",0,16,16,\"Phone number:\"",
                "TEXT 77,413,\"0\",0,16,16,\"Email:\"",
                "TEXT 77,505,\"0\",0,16,16,\"Company:\"",
                "TEXT 295,231,\"0\",0,16,16,\"flor\"",
                "TEXT 509,320,\"0\",0,16,16,\"+32468294226\"",
                "TEXT 295,410,\"0\",0,16,16,\"flor@altec.be\"",
                "TEXT 396,502,\"0\",0,16,16,\"Altec\"",
                "PRINT 1,1"
            )
        ),

        Label(
            name = "Label with counter",
            tspl = listOf(
                "SIZE 100 mm,50 mm",
                "GAP 3 mm,0 mm",
                "REFERENCE 0,0",
                "SPEED 2.0",
                "DENSITY 8",
                "SET RIBBON ON",
                "SET PEEL OFF",
                "SET CUTTER OFF",
                "SET PARTIAL_CUTTER OFF",
                "SET TEAR ON",
                "SET REWIND OFF",
                "DIRECTION 0,0",
                "SHIFT 0,0",
                "OFFSET 0 mm",
                "",
                "CLS",
                "SET COUNTER @0 +1",
                "@0=\"1\"",
                "BAR 12,89,1158,5",
                "BOX 12,104,1170,515,5",
                "CODEPAGE 1252",
                "TEXT 1073,30,\"0\",0,10,10,\"0\"+@0",
                "CODEPAGE 1252",
                "TEXT 26,30,\"0\",0,10,10,\"Flor Stellamans\"",
                "CODEPAGE 1252",
                "TEXT 26,139,\"0\",0,10,10,\"Kerkstraat 55\"",
                "CODEPAGE 1252",
                "TEXT 26,248,\"0\",0,12,12,\"1851 Humbeek\"",
                "BARCODE 33,389,\"128M\",79,2,0,4,8,\"!104+3!0992468294226\"",
                "QRCODE 867,218,L,11,A,0,M2,S7,\"flor@stellamans.be\"",
                "PRINT 1,1"
            )
        )
    )
}