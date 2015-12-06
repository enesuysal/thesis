using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace CSharpWebServer
{
    public class Receiver {
   
    public static Object GetMessage(string BASE64String ) {
           try {
               byte[] decodedBytes = Convert.FromBase64String(BASE64String);
            
            switch (decodedBytes.Length) {
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
           
            
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    
}
}
