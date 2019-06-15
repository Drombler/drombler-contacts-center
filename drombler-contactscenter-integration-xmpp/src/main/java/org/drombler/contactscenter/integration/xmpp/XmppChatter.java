package org.drombler.contactscenter.integration.xmpp;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class XmppChatter {

    @PostConstruct
public void setUp(){
    try {
        XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                .setUsernameAndPassword("drombler.test.mike", "drombler.test.mike")
                .setXmppDomain("jabber.hot-chilli.net")
                .setHost("jabber.hot-chilli.net")
                .setCompressionEnabled(true)
                .build();

        AbstractXMPPConnection connection = new XMPPTCPConnection(config);
        connection.connect()
                .login();
    } catch (InterruptedException | IOException | SmackException | XMPPException e) {
        e.printStackTrace();

    }
    }
}
