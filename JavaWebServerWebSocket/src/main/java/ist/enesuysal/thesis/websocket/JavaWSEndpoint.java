/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.enesuysal.thesis.websocket;

import DOM.ComplianceTest;
import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.ContainerProvider;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author enesuysal
 */
@ServerEndpoint(value="/javawsendpoint")
public class JavaWSEndpoint {
     private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());


//    @OnMessage
//    public String onMessage(String message) {
//        return null;
//    }
     @OnMessage
public void broadcastSnapshot(ByteBuffer data, Session session) throws IOException {
    System.out.println("broadcastBinary: " + data);
    String result = "";
     try {
         byte[] tooLong = data.array();
            ComplianceTest complience = new ComplianceTest();
            result =  complience.GetMessage(tooLong);
        } catch (Exception ex) {
            result = "Error with Message";
        }
    for (Session peer : peers) {
       if (peer.equals(session)) {   
            peer.getBasicRemote().sendText(result);
        }
    }

}
    
     @OnOpen
    public void onOpen (Session peer) {
        peers.add(peer);
    }

    @OnClose
    public void onClose (Session peer) {
        peers.remove(peer);
    }
    
}
