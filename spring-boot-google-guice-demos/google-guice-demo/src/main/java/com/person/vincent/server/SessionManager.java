package com.person.vincent.server;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Created by IDEA.
 * User: vincent
 * Date: 2017/11/26
 * Comment:
 */
public class SessionManager {
    // Provider 注入
    private final Provider<Long> sessionIdProvider;

    @Inject
    public SessionManager(@SessionId Provider<Long> sessionIdProvider) {
        this.sessionIdProvider = sessionIdProvider;
    }

    public Long getSessionIdProvider() {
        return sessionIdProvider.get();
    }
}
