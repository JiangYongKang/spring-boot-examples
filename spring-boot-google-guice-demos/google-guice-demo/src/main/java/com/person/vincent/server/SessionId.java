package com.person.vincent.server;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by IDEA.
 * User: vincent
 * Date: 2017/11/26
 * Comment:
 */
@BindingAnnotation
@Retention(RetentionPolicy.RUNTIME)
public @interface SessionId {
}
