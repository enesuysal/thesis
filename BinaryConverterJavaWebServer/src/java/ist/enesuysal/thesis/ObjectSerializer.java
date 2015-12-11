/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.enesuysal.thesis;

import java.lang.reflect.Field;
import java.util.Arrays;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author enesuysal
 *
 */
public class ObjectSerializer {

    public static byte[] ObjectSerializer(Object obj) throws IllegalArgumentException, IllegalAccessException {
        byte[] byteArray = new byte[1];
        byte[] STREAM_MAGIC = new byte[]{(byte) 0xAC, (byte) 0xAE};// Specifies that this is a serialization 
        byteArray = push(byteArray, STREAM_MAGIC);
        byte[] STREAM_VERSION = new byte[]{(byte) 0x00, (byte) 0x05};//The serialization version.
        byteArray = push(byteArray, STREAM_VERSION);
        byte[] TC_OBJECT = new byte[]{(byte) 0x73};//Specifies that this is a new Object.
        byteArray = push(byteArray, TC_OBJECT);
        byte[] TC_CLASSDESC = new byte[]{(byte) 0x72};//Specifies that this is a new class
        byteArray = push(byteArray, TC_CLASSDESC);
        byte[] CLASSNAME_LENGH = CentralSerializer.intToByteArray(obj.getClass().getName().length(), null);// Length of the class name
        byteArray = push(byteArray, CLASSNAME_LENGH);
        byte[] CLASSNAME = CentralSerializer.stringToByteArray(obj.getClass().getName(), null);// Length of the class name
        byteArray = push(byteArray, CLASSNAME);
        Field[] fields = obj.getClass().getFields();
        byte[] FIELD_LENGH = CentralSerializer.intToByteArray(fields.length, null);// Length of the class name
        byteArray = push(byteArray, FIELD_LENGH);

        for (Field oneField : fields) {
            Object fieldType = oneField.getType();
            byte[] FIELD_TYPE = new byte[]{(byte) 0x49};
            byteArray = push(byteArray, FIELD_TYPE);
            byte[] FIELDNAME_LENGH = CentralSerializer.intToByteArray(oneField.getName().length(), null);
            byteArray = push(byteArray, FIELDNAME_LENGH);
            byte[] FIELDNAME = CentralSerializer.stringToByteArray(oneField.getName(), null);
            byteArray = push(byteArray, FIELDNAME);
        }
        byte TC_ENDBLOCKDATA = (byte) 0x78;
        byteArray = push(byteArray, TC_ENDBLOCKDATA);
        byte TC_NULL = (byte) 0x70;
        byteArray = push(byteArray, TC_NULL);
        for (Field oneField : fields) {
            boolean value = (boolean) oneField.get(obj);
            byte[] FIELDVALUE = CentralSerializer.boolToByteArray(value, null);
            byteArray = push(byteArray, FIELDVALUE);
        }

        return byteArray;
    }

    public static Object ObjectSerializer(byte[] bytes) throws ClassNotFoundException, Exception {

        Object object = null;
        if (bytes.length == 0) {
            throw new Exception();
        }
        if (bytes[1] == -84 && bytes[2] == -82 && bytes[3] == 0 && bytes[4] == 5) {
            System.err.println("Success");
        }
        if (bytes[5] == 115) {
            System.err.println("It is Object");
        }
        if (bytes[6] == 114) {
            System.err.println("It is Class");
        }
        int ClassNameLength = 0;
        //byte[] ClassNameLengthBytes = new byte[]{(byte)bytes[7],(byte)bytes[8],(byte)bytes[9],(byte)bytes[10],(byte)bytes[11],(byte)bytes[12],(byte)bytes[13],(byte)bytes[14]}
        byte[] ClassNameLengthBytes = new byte[]{(byte) bytes[7], (byte) bytes[8], (byte) bytes[9], (byte) bytes[10], (byte) bytes[11], (byte) bytes[12], (byte) bytes[13], (byte) bytes[14]};
        ClassNameLength = CentralSerializer.ByteArrayToInt(ClassNameLengthBytes);
        System.err.println(ClassNameLength);
        byte[] ClassNameByte = new byte[0];
        for (int i = 0; i < ClassNameLength; i++) {
            ClassNameByte = push(ClassNameByte, bytes[15 + i]);
        }
        int FieldLength = 0;
        byte[] FieldLengthByte = new byte[0];
        for (int i = 0; i < 8; i++) {
            FieldLengthByte = push(FieldLengthByte, bytes[15 + ClassNameLength + i]);
        }
        FieldLength = CentralSerializer.ByteArrayToInt(FieldLengthByte);
        //int NumberofFields =obj.getClass().getFields().length;
        System.err.println(FieldLength);
        for (int i = 0; i < FieldLength; i++) {
           
            
        }
        String ClassName = CentralSerializer.ByteArrayToString(ClassNameByte);
        System.err.println(ClassName);
        Class c = Class.forName(ClassName);
        object = c.newInstance();
        return object;
    }

    private static byte[] push(byte[] array, byte[] push) {
        byte[] longer = new byte[array.length + push.length];
        System.arraycopy(array, 0, longer, 0, array.length);
        System.arraycopy(push, 0, longer, array.length, push.length);

        return longer;
    }

    private static byte[] push(byte[] array, byte push) {
        byte[] longer = new byte[array.length + 1];
        System.arraycopy(array, 0, longer, 0, array.length);
        longer[array.length] = push;
        return longer;
    }

}
