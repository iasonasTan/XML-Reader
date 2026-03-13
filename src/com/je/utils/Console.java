package com.je.utils;

public final class Console {
    private static Console sInstance;
    
    public static Console get() {
        if(sInstance==null)
            sInstance = new Console();
        return sInstance;
    }
    
    private boolean mEnabled = false;
    
    private Console() {
    }
    
    public void setEnabled(boolean enb) {
        mEnabled = enb;
    }
    
    private void println(String msg, Object obj) {
        if(mEnabled) {
            IO.println("["+msg+"] "+obj);
        }
    }
    
    public void debug(Object o) {
        println("DEBUG", o);
    }
    
    public void warn(Object o) {
        println("WARNING", o);
    }
    
    public void error(Object o) {
        println("ERROR", o);
    }
}
