package org.slf4j.helpers;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

/* renamed from: org.slf4j.helpers.a */
public class NOPLoggerFactory implements ILoggerFactory {
    public Logger m7507a(String str) {
        return NOPLogger.NOP_LOGGER;
    }
}
