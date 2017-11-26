package com.person.vincent.server;

import com.google.inject.Guice;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

/**
 * Created by IDEA.
 * User: vincent
 * Date: 2017/11/26
 * Comment:
 */
public class SessionManagerTests {

    @Inject
    private SessionManager sessionManager;

    @Before
    public void setUp() {
        Guice.createInjector(new ServerModule()).injectMembers(this);
    }

    @Test
    public void testGetSessionId() throws Exception {
        Long sessionId1 = sessionManager.getSessionIdProvider();
        Thread.sleep(1000);
        Long sessionId2 = sessionManager.getSessionIdProvider();
        Assert.assertNotEquals(sessionId1, sessionId2);
    }
}
