/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.enesuysal.thesis;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.BASE64Decoder;

/**
 *
 * @author enesuysal
 */
public class Receiver {
   
    public static Object GetMessage(String BASE64String ) throws ClassNotFoundException{
          BASE64Decoder decoder = new BASE64Decoder();
           try {
            byte[] decodedBytes = decoder.decodeBuffer(BASE64String);
            
            switch (decodedBytes.length) {
                case 1: 
                    
                    return (CentralSerializer.ByteArrayToBool( decodedBytes));
               
                case 8:
                    //Convert To Integer
                    return (CentralSerializer.ByteArrayToInt(decodedBytes)); 
               
                default:
                    //try to convert object
                    try{
                     return CentralSerializer.ByteArrayToObject(decodedBytes);   
                    }catch(Exception e){
                    // It is not object convert to string
                        return CentralSerializer.ByteArrayToString(decodedBytes);
                    }
                    
                
            }
           
            
        } catch (IOException ex) {
            //Logger.getLogger(CallWebServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}
