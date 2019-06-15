package org.drombler.contactscenter.integration.xmpp;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.impl.JidCreate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class XmppChatter {
    private static final Logger LOGGER = LoggerFactory.getLogger(XmppChatter.class);
    @PostConstruct
public void setUp(){
    try {
        XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                .setUsernameAndPassword("drombler.test.jenny", "drombler.test.jenny")
                .setXmppDomain("jabber.hot-chilli.net")
                .setHost("jabber.hot-chilli.net")
                .setCompressionEnabled(true)
                .build();

        AbstractXMPPConnection connection = new XMPPTCPConnection(config);
        connection.connect()
                .login();
        ChatManager chatManager = ChatManager.getInstanceFor(connection);
        EntityBareJid jid = JidCreate.entityBareFrom("drombler.test.mike@jabber.hot-chilli.net");
        chatManager.addIncomingListener((entityBareJid, message, chat) -> {
            try {
                if (entityBareJid.equals(jid)) {
                    LOGGER.info("Massage from mike received: {}", message.getBody());
                    chat.send("Echo: " + message.getBody());
                } else {
                    LOGGER.info("Unexpected massage received from '{}' : {}", entityBareJid, message.getBody());
                }
            } catch (SmackException.NotConnectedException | InterruptedException e) {
                LOGGER.error(e.getMessage(), e);
            }
        });
    } catch (InterruptedException | IOException | SmackException | XMPPException e) {
        LOGGER.error(e.getMessage(), e);
    }
    }
}
