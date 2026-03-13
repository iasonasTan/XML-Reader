package com.je.console;

import com.je.utils.Console;

public final class ConsoleController {
    public static void setConsoleEnabled(boolean enabled) {
        Console.get().setEnabled(enabled);
    }

    private ConsoleController(){
    }
}
