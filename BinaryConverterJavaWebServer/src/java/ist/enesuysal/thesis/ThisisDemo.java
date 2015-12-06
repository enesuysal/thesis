/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.enesuysal.thesis;

import ist.enesuysal.thesis.Receiver;
import ist.enesuysal.thesis.Message;
import ist.enesuysal.thesis.Test;
import java.util.Arrays;

/**
 *
 * @author enesuysal
 */
public class ThisisDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        
//Object Class TEst
        Test testObject = new Test(1, "Test");
        Message msg1 = new Message(testObject);
        System.out.println(Arrays.toString(msg1.messageSerilized) + ' ' + msg1.messageSerilized.length);
        //Deserialize
        System.out.println( Receiver.GetMessage(msg1.Seriliaze()).getClass());
        Test newTestObject = (Test) Receiver.GetMessage(msg1.Seriliaze());
         System.out.println(newTestObject.getTestName());
       
         
// Test for String
        String test = "fdfdfd"; 
        Message msg2 = new Message(test);
        System.out.println(Arrays.toString(msg2.messageSerilized) + ' ' + msg2.messageSerilized.length);
        //Deserialize
        System.out.println( Receiver.GetMessage(msg2.Seriliaze()).getClass());
        String newTestString = (String) Receiver.GetMessage(msg2.Seriliaze());
         System.out.println(newTestString);
         
         
          
// Test for Int
        int testInt = 55; 
        Message msg3 = new Message(testInt);
        System.out.println(Arrays.toString(msg3.messageSerilized) + ' ' + msg3.messageSerilized.length);
        //Deserialize
        System.out.println( Receiver.GetMessage(msg3.Seriliaze()).getClass());
        int newTestInt = (int) Receiver.GetMessage(msg3.Seriliaze());
         System.out.println(newTestInt);
// Test for Bool
        Boolean testBool = true; 
        Message msg4 = new Message(testBool);
        System.out.println(Arrays.toString(msg4.messageSerilized) + ' ' + msg4.messageSerilized.length);
        //Deserialize
        System.out.println( Receiver.GetMessage(msg4.Seriliaze()).getClass());
        Boolean newTestBool = (Boolean) Receiver.GetMessage(msg4.Seriliaze());
         System.out.println(newTestBool);
        
        
    }
    
   
    
}
