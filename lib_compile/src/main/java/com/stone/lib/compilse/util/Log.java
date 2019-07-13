package com.stone.lib.compilse.util;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;

public class Log {
    private Messager messager;

    private Log(Messager messager) {
        this.messager = messager;
    }

    public static Log newInstance(Messager messager) {
        return new Log(messager);
    }

    public void i(String msg) {
        messager.printMessage(Diagnostic.Kind.NOTE, msg);
    }

    public void e(String msg) {
        messager.printMessage(Diagnostic.Kind.ERROR, msg);
    }

}
