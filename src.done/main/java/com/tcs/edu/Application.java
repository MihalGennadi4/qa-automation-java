package com.tcs.edu;

import com.tcs.edu.decoratorr.TimestampMessageDecorator;
import com.tcs.edu.printerr.ConsolePrinter;

/**
 * Main class: entry point
 */
class Application {
    public static void main(String[] args) {
        ConsolePrinter.print(TimestampMessageDecorator.decorate("Hello world!"));
    }
}