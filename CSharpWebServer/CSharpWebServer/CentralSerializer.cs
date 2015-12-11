using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text;

namespace CSharpWebServer
{
    public class CentralSerializer
    {
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
            arrayByte[4] = (byte)(intValue >> 24);
            arrayByte[5] = (byte)(intValue >> 16);
            arrayByte[6] = (byte)(intValue >> 8);
            arrayByte[7] = (byte)intValue;

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
         arrayByte = Encoding.UTF8.GetBytes(input);
            return arrayByte;
     }
    // Serilizable object to ByteArray
    public static byte[] objectToByteArray( Object o,byte[] arrayByte )  
    {
        BinaryFormatter bf = new BinaryFormatter();
        using (var ms = new MemoryStream())
        {
            bf.Serialize(ms, o);
            arrayByte = ms.ToArray();
            return arrayByte;
        }
      
    }
    
    /*
    *
    *
    *Methods Convert ByteArrat to Object (Int,Bool,String,Class)
    *
    */
    // ByteArray to Integer
    public static int ByteArrayToInt(byte[] bytes) {
        return bytes[0] << 56 | bytes[1] << 48 | bytes[2] << 40 | bytes[3] << 32 | bytes[4] << 24 | (bytes[5] & 0xFF) << 16 | (bytes[6] & 0xFF) << 8 | (bytes[7] & 0xFF);
     }
    
    // Boolean
    // ByteArray to Bool
    public static bool ByteArrayToBool(byte[] bytes)
    {
   	 
      bool output = Convert.ToBoolean(bytes[0]);
            return output;
    }
    
    // String
    // ByteArray to String
   public static String ByteArrayToString(byte[] bytes)
   {
  	  return System.Text.Encoding.UTF8.GetString(bytes);
   } 
   
   public static Object ByteArrayToObject( byte [] bytes ) 
   {

       using (var memStream = new MemoryStream())
       {
           var binForm = new BinaryFormatter();
           memStream.Write(bytes, 0, bytes.Length);
           memStream.Seek(0, SeekOrigin.Begin);
           var obj = binForm.Deserialize(memStream);
           return obj;
       }
        
   }
    }
}
