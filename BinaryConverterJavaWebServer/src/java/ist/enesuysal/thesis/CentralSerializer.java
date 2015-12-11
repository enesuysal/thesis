/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.enesuysal.thesis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;

/**
 *
 * @author enesuysal
 */
public class CentralSerializer {
    
    /*
    *
    *
    *Methods Convert  Object(Int,Bool,String,Class) to ByteArray
    *
    */
    // Primitive Int to ByteArray
    public  static  byte[] intToByteArray(int intValue, byte[] arrayByte){
          arrayByte = new byte[8];
	  arrayByte[0] = (byte)(intValue >> 56);
	  arrayByte[1] = (byte)(intValue >> 48);
	  arrayByte[2] = (byte)(intValue >> 40);
	  arrayByte[3] = (byte)(intValue >> 32);
	  arrayByte[4] = (byte) (intValue >> 24);
	  arrayByte[5] = (byte) (intValue >> 16);
	  arrayByte[6] = (byte) (intValue >> 8);
	  arrayByte[7] = (byte) (intValue /*>> 0*/);

	  return arrayByte;
    }
    
    // Primitive Bool to ByteArray
    public static byte[] boolToByteArray(Boolean input, byte[] arrayByte){
        arrayByte = new byte[1];
        arrayByte[0] = input ? (byte)1 : (byte)0;
        return arrayByte;
    }
    
    // Primitive String to ByteArray
    public static byte[] stringToByteArray(String input, byte[] arrayByte)
     {
    	 arrayByte = input.getBytes(Charset.forName("UTF-8"));
         return arrayByte;
     }
    // Serilizable object to ByteArray
    public static byte[] objectToByteArray( Object o,byte[] arrayByte ) throws IllegalArgumentException, IllegalAccessException 
    {
        ObjectSerializer serializer = new ObjectSerializer(o);
        arrayByte = serializer.Serialize();
        return  arrayByte; 
    }
    
    /*
    *
    *
    *Methods Convert ByteArrat to Object (Int,Bool,String,Class)
    *
    */
    // ByteArray to Integer
    public static int ByteArrayToInt(byte[] bytes) {
        return bytes[0] << 56 | (bytes[1] & 0xFF) << 48 |  (bytes[2] & 0xFF) << 40| (bytes[3] & 0xFF) << 32 | (bytes[4] & 0xFF) << 24 | (bytes[5] & 0xFF) << 16 | (bytes[6] & 0xFF) << 8 | (bytes[7] & 0xFF);
    }
    
    // Boolean
    // ByteArray to Bool
    public static boolean ByteArrayToBool(byte[] bytes)
    {
   	 
        boolean output = (bytes[0]!=0);
        return output;
    }
    
    // String
    // ByteArray to String
   public static String ByteArrayToString(byte[] bytes)
   {
  	 return new String(bytes);
   } 
   
   public static Object ByteArrayToObject( byte [] bytes )
   {
        ObjectSerializer serializer = new ObjectSerializer(bytes);       
        return serializer.DeSerialize(); 
   }
}
