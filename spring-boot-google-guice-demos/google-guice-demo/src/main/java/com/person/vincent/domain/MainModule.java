package com.person.vincent.domain;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.person.vincent.*;

import java.io.PrintStream;

/**
 * Created by IDEA.
 * User: vincent
 * Date: 2017/11/26
 * Comment:
 */
public class MainModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new HelloWorldModule());
    }
}
