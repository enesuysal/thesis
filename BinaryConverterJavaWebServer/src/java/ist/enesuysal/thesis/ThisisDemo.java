/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.enesuysal.thesis;

import ist.enesuysal.thesis.Receiver;
import ist.enesuysal.thesis.Message;
import ist.enesuysal.thesis.Tests.TestSerial;

import java.util.Arrays;

/**
 *
 * @author enesuysal
 */
public class ThisisDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, Exception  {
         
        Message msg = new Message(new TestSerial());
        String msgToSend =  msg.Seriliaze();
       Receiver.GetMessage(msgToSend);
        //System.err.println(Arrays.toString());
//        Object o = CentralSerializer.ByteArrayToObject(msg.DeSeriliaze(msgToSend));
        
    }
    
   
    
}
