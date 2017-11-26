package com.person.vincent;

import com.google.inject.Guice;
import com.person.vincent.domain.MainModule;

/**
 * Hello world!
 */
public class Application {
    public static void main(String[] args) {
        MyApplet mainApplet = Guice.createInjector(new MainModule())
                .getInstance(MyApplet.class);
        mainApplet.run();
    }
}
