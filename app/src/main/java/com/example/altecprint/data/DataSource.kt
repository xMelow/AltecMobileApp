package com.example.altecprint.data

import com.example.altecprint.model.Label

object DataSource {

    val labels = listOf<Label>(
        Label("test label", "invalid tspl"),
        Label("User info label", "SIZE 103 mm,110 mm\n" +
                "GAP 3 mm,0 mm\n" +
                "REFERENCE 0,0\n" +
                "SPEED 2.0\n" +
                "DENSITY 8\n" +
                "SET RIBBON ON\n" +
                "SET PEEL OFF\n" +
                "SET CUTTER OFF\n" +
                "SET PARTIAL_CUTTER OFF\n" +
                "SET TEAR ON\n" +
                "SET REWIND OFF\n" +
                "DIRECTION 0,0\n" +
                "SHIFT 0,0\n" +
                "OFFSET 0 mm\n" +
                "\n" +
                "CLS\n" +
                "TEXT 427,47,\"0\",0,16,16,\"Hello World\"\n" +
                "BAR 33,135,1199,7\n" +
                "BOX 33,178,1232,890,6\n" +
                "QRCODE 938,951,L,14,A,0,M2,S7,\"123456789012\"\n" +
                "CIRCLE 933,590,260,12\n" +
                "TEXT 77,234,\"0\",0,16,16,\"Name:\"\n" +
                "TEXT 77,322,\"0\",0,16,16,\"Phone number:\"\n" +
                "TEXT 77,413,\"0\",0,16,16,\"Email:\"\n" +
                "TEXT 77,505,\"0\",0,16,16,\"Company:\"\n" +
                "TEXT 295,231,\"0\",0,16,16,\"flor\"\n" +
                "TEXT 509,320,\"0\",0,16,16,\"+32468294226\"\n" +
                "TEXT 295,410,\"0\",0,16,16,\"flor@altec.be\"\n" +
                "TEXT 396,502,\"0\",0,16,16,\"Altec\"\n" +
                "PRINT 1,1"),

        Label("Label with counter", "SIZE 100 mm,50 mm\n" +
                "GAP 3 mm,0 mm\n" +
                "REFERENCE 0,0\n" +
                "SPEED 2.0\n" +
                "DENSITY 8\n" +
                "SET RIBBON ON\n" +
                "SET PEEL OFF\n" +
                "SET CUTTER OFF\n" +
                "SET PARTIAL_CUTTER OFF\n" +
                "SET TEAR ON\n" +
                "SET REWIND OFF\n" +
                "DIRECTION 0,0\n" +
                "SHIFT 0,0\n" +
                "OFFSET 0 mm\n" +
                "\n" +
                "CLS\n" +
                "SET COUNTER @0 +1\n" +
                "@0=\"1\"\n" +
                "BAR 12,89,1158,5\n" +
                "BOX 12,104,1170,515,5\n" +
                "CODEPAGE 1252\n" +
                "TEXT 1073,30,\"0\",0,10,10,\"0\"+@0\n" +
                "CODEPAGE 1252\n" +
                "TEXT 26,30,\"0\",0,10,10,\"Flor Stellamans\"\n" +
                "CODEPAGE 1252\n" +
                "TEXT 26,139,\"0\",0,10,10,\"Kerkstraat 55\"\n" +
                "CODEPAGE 1252\n" +
                "TEXT 26,248,\"0\",0,12,12,\"1851 Humbeek\"\n" +
                "BARCODE 33,389,\"128M\",79,2,0,4,8,\"!104+3!0992468294226\"\n" +
                "QRCODE 867,218,L,11,A,0,M2,S7,\"flor@stellamans.be\"\n" +
                "PRINT 1,1"),

        Label("Label with complex tspl", "SIZE 80 mm,101 mm\n" +
                "BLINE 2 mm,0 mm\n" +
                "REFERENCE 0,0\n" +
                "SPEED 1\n" +
                "DENSITY 10\n" +
                "SET RIBBON ON\n" +
                "SET PEEL OFF\n" +
                "SET CUTTER OFF\n" +
                "SET PARTIAL_CUTTER OFF\n" +
                "SET TEAR ON\n" +
                "SET REWIND OFF\n" +
                "DIRECTION 0,0\n" +
                "SHIFT 0,0\n" +
                "OFFSET 0 mm\n" +
                "\n" +
                "LOOP:\n" +
                "\n" +
                "    INPUT \"Lotnummer: \", LOT\$\n" +
                "    INPUT \"Klantnaam: \", KLANT\$\n" +
                "    INPUT \"Ref. Klant: \", REF\$\n" +
                "    INPUT \"Datum: \", DATUM\$\n" +
                "    INPUT \"Aantal: \", AANTAL\$\n" +
                "    INPUT \"Kopies per lot: \", COPIES\$\n" +
                "\n" +
                "    A = VAL(AANTAL\$)\n" +
                "    C = VAL(COPIES\$)\n" +
                "\n" +
                "    FOR I = 1 TO A STEP 1\n" +
                "\n" +
                "        IF AANTAL\$ = \"1\" THEN\n" +
                "            LOTNUMMER\$ = LOT\$\n" +
                "        ELSE\n" +
                "            LOTNUMMER\$ = LOT\$ + \".\" + STR\$(I)\n" +
                "        ENDIF\n" +
                "\n" +
                "        QR\$ = \"\" + LOTNUMMER\$\n" +
                "\n" +
                "        CLS\n" +
                "        PUTBMP 32,100,\"l.BMP\"\n" +
                "        BLOCK 45,300,800,60,\"0\",0,50,60,0,2,LOTNUMMER\$\n" +
                "        BLOCK 32,550,800,50,\"0\",0,26,26,KLANT\$\n" +
                "        BLOCK 32,680,800,250,\"0\",0,26,26,0,1,1,REF\$\n" +
                "        TEXT 32,960,\"0\",0,12,14,\"DATUM IN:\"\n" +
                "        BLOCK 32,1025,800,50,\"0\",0,22,22,DATUM\$\n" +
                "        QRCODE 700,900,L,10,A,0,M2,S7,QR\$\n" +
                "\n" +
                "        PRINT 1,C\n" +
                "\n" +
                "    NEXT\n" +
                "\n" +
                "GOTO LOOP")
    )
}