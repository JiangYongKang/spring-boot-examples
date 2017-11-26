package com.person.vincent;

/**
 * Created by IDEA.
 * User: vincent
 * Date: 2017/11/26
 * Comment:
 */
public class Configuration {

    public static MyApplet getMainApplet() {
        return new StringWritingApplet(new PrintStreamWriter(System.out),
                () -> "Hello world");
    }
}
