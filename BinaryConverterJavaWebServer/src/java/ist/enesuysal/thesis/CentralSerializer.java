package ist.enesuysal.thesis;

import ist.enesuysal.thesis.Helper.Helper;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

public class CentralSerializer {


    public static byte[] serializePrimitive(Type type, String fieldName, boolean isOptional, Object fieldValue, byte[] item) {
        //Append isOptional
        item = convertToByteArray(isOptional, item);
        //FieldType
         item = convertToByteArray(Helper.GetFieldCode(type.getTypeName())[0], item);
        //append FieldNameLenght
        item =  convertToByteArray(fieldName.length(), item);
        //Append ValueLenght
        item = convertToByteArray(Helper.GetFieldValueByte(type.toString(),fieldValue,new byte[0]).length, item);
        //Append FieldName
        item = convertToByteArray(fieldName, item);
        //Append Value
        item = Helper.GetFieldValueByte(type.toString(),fieldValue,item);//convertToByteArray(fieldValue, item);
        return item;

    }
    
    public static byte[] serializePrimitive(Type type, String fieldName,   Object fieldValue, byte[] item) {
        
        //FieldType
         item = convertToByteArray(Helper.GetFieldCode(type.getTypeName())[0], item);
        //append FieldNameLenght
        item =  convertToByteArray(fieldName.length(), item);
        //Append ValueLenght
        item = convertToByteArray(Helper.GetFieldValueByte(type.toString(),fieldValue,new byte[0]).length, item);
        //Append FieldName
        item = convertToByteArray(fieldName, item);
        //Append Value
        item = Helper.GetFieldValueByte(type.toString(),fieldValue,item);//convertToByteArray(fieldValue, item);
        return item;

    }

    public static byte[] serializePrimitive(Type type, int fieldValue, byte[] item) {
       //FieldType
         item = convertToByteArray(Helper.GetFieldCode(type.getTypeName())[0], item);
        //append FieldNameLenght
        item =  convertToByteArray(0, item);
        //Append ValueLenght
        item = convertToByteArray(Helper.GetFieldValueByte(type.toString(),fieldValue,new byte[0]).length, item);
        //Append FieldName
        item = convertToByteArray("", item);
        //Append Value
        item = Helper.GetFieldValueByte(type.toString(),fieldValue,item);//convertToByteArray(fieldValue, item);
        return item;
    }

    
    public static byte[] serializePrimitive(Type type, boolean fieldValue, byte[] item) {
         //FieldType
         item = convertToByteArray(Helper.GetFieldCode(type.getTypeName())[0], item);
        //append FieldNameLenght
        item =  convertToByteArray(0, item);
        //Append ValueLenght
        item = convertToByteArray(Helper.GetFieldValueByte(type.toString(),fieldValue,new byte[0]).length, item);
        //Append FieldName
        item = convertToByteArray("", item);
        //Append Value
        item = convertToByteArray(fieldValue, item);
        return item;
    }
    public static byte[] serializePrimitive(Type type, Boolean fieldValue, byte[] item) {
        //FieldType
         item = convertToByteArray(Helper.GetFieldCode(type.getTypeName())[0], item);
        //append FieldNameLenght
        item =  convertToByteArray(0, item);
        //Append ValueLenght
        item = convertToByteArray(Helper.GetFieldValueByte(type.toString(),fieldValue,new byte[0]).length, item);
        //Append FieldName
        item = convertToByteArray("", item);
        //Append Value
        item = convertToByteArray(fieldValue, item);
        return item;
    }
     public static byte[] serializePrimitive(Type type, long fieldValue, byte[] item) {
        //FieldType
         item = convertToByteArray(Helper.GetFieldCode(type.getTypeName())[0], item);
        //append FieldNameLenght
        item =  convertToByteArray(0, item);
        //Append ValueLenght
        item = convertToByteArray(Helper.GetFieldValueByte(type.toString(),fieldValue,new byte[0]).length, item);
        //Append FieldName
        item = convertToByteArray("", item);
        //Append Value
        item = convertToByteArray(fieldValue, item);
        return item;
    }

    public static byte[] serializePrimitive(Type type, String fieldValue, byte[] item) {
         //FieldType
         item = convertToByteArray(Helper.GetFieldCode(type.getTypeName())[0], item);
        //append FieldNameLenght
        item =  convertToByteArray(0, item);
        //Append ValueLenght
        item = convertToByteArray(Helper.GetFieldValueByte(type.toString(),fieldValue,new byte[0]).length, item);
        //Append FieldName
        item = convertToByteArray("", item);
        //Append Value
        item = convertToByteArray(fieldValue, item);
        return item;
    }

    // Primitive Int to ByteArray
    public static byte[] convertToByteArray(int intValue, byte[] item) {
        byte[] arrayByte = new byte[8];
        arrayByte[0] = (byte) (intValue >> 56);
        arrayByte[1] = (byte) (intValue >> 48);
        arrayByte[2] = (byte) (intValue >> 40);
        arrayByte[3] = (byte) (intValue >> 32);
        arrayByte[4] = (byte) (intValue >> 24);
        arrayByte[5] = (byte) (intValue >> 16);
        arrayByte[6] = (byte) (intValue >> 8);
        arrayByte[7] = (byte) (intValue /*>> 0*/);
        return Helper.push(item, arrayByte);
    }

    // Primitive Bool to ByteArray
    public static byte[] convertToByteArray(Boolean input, byte[] item) {
        byte[] arrayByte = new byte[1];
        arrayByte[0] = input ? (byte) 1 : (byte) 0;
        return Helper.push(item, arrayByte);
    }

    // Primitive String to ByteArray
    public static byte[] convertToByteArray(String input, byte[] item) {
        //if (input == null)
          //  input="";
        byte[] arrayByte = input.getBytes(Charset.forName("UTF-8"));
        return Helper.push(item, arrayByte);
    }

   

    public static byte[] convertToByteArray(char value, byte[] item) {
        byte[] bytes = new byte[2];
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.putChar(value);
        return Helper.push(item, buffer.array());
    }

    public static byte[] convertToByteArray(long value, byte[] item) {

        byte[] bytes = new byte[8];
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.putLong(value);
        return Helper.push(item, buffer.array());
    }

    public static byte[] convertToByteArray(short value, byte[] item) {

        byte[] bytes = new byte[2];
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.putShort(value);
        return Helper.push(item, buffer.array());
    }

    public static byte[] convertToByteArray(float value, byte[] item) {
        byte[] bytes = new byte[4];
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.putFloat(value);
        return Helper.push(item, buffer.array());
    }

    public static byte[] convertToByteArray(double value, byte[] item) {
        byte[] bytes = new byte[8];
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.putDouble(value);
        return Helper.push(item, buffer.array());
    }

    /*
     *
     *
     *Methods Convert ByteArrat to Object (Int,Bool,String,Class)
     *
     */
    // ByteArray to Integer
    public static int convertToInt(byte[] bytes) {
        return bytes[0] << 56 | (bytes[1] & 0xFF) << 48 | (bytes[2] & 0xFF) << 40 | (bytes[3] & 0xFF) << 32 | (bytes[4] & 0xFF) << 24 | (bytes[5] & 0xFF) << 16 | (bytes[6] & 0xFF) << 8 | (bytes[7] & 0xFF);
    }

    // Boolean
    // ByteArray to Bool
    public static boolean convertToBool(byte[] bytes) {
        return (bytes[0] != 0);
    }

    // String
    // ByteArray to String
    public static String convertToString(byte[] bytes) {
        return new String(bytes);
    }

    public static long convertToLong(byte[] array) {
        ByteBuffer buffer = ByteBuffer.wrap(array);
        return buffer.getLong();
    }

    public static short convertToShort(byte[] array) {
        ByteBuffer buffer = ByteBuffer.wrap(array);
        return buffer.getShort();
    }

    public static char convertToCharacter(byte[] array) {
        ByteBuffer buffer = ByteBuffer.wrap(array);
        return buffer.getChar();
    }

    public static double convertToDouble(byte[] array) {
        ByteBuffer buffer = ByteBuffer.wrap(array);
        return buffer.getDouble();
    }

    public static float convertToFloat(byte[] array) {
        ByteBuffer buffer = ByteBuffer.wrap(array);
        return buffer.getFloat();
    }
//    public static Object ByteArrayToObject(byte[] bytes) {
//        ObjectSerializer serializer = new ObjectSerializer(bytes);
//        try {
//            return serializer.DeSerialize();
//        } catch (Exception ex) {
//            Logger.getLogger(CentralSerializer.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
}
