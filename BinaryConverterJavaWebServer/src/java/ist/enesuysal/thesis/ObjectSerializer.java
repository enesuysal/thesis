/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.enesuysal.thesis;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 *
 * @author enes
 */
public class ObjectSerializer {
    Object obj;
    public ObjectSerializer(Object o){
        this.obj=o;
    }
    
    public byte[] Serialize() throws IllegalArgumentException, IllegalAccessException{
        byte[] byteArray = new byte[0];
        byte[] START_SERIALIZE = new byte[]{(byte) 0xAC, (byte) 0xAE};// Specifies that this is a serialization 
        byteArray = push(byteArray, START_SERIALIZE);
        byte[] OBJECT_START = new byte[]{(byte) 0x73};//Specifies that Object starts here.
        byteArray = push(byteArray, OBJECT_START);
        //GET Fields
        Field[] fields = obj.getClass().getFields();
        byte[] FIELD_LENGH = CentralSerializer.intToByteArray(fields.length, null);// Length of the class name
        byteArray = push(byteArray, FIELD_LENGH);
        //For each Field 
        for (Field oneField : fields) {
            Object fieldType = oneField.getType(); //Get Field Type
            byte[] FIELD_TYPE = new byte[]{GetFieldCode(fieldType)}; // Get Field Code
            byteArray = push(byteArray, FIELD_TYPE);
            byte[] FIELDNAME_LENGH = CentralSerializer.intToByteArray(oneField.getName().length(), null); //Get fieldname Length 
            byteArray = push(byteArray, FIELDNAME_LENGH);
            byte[] FIELDNAME = CentralSerializer.stringToByteArray(oneField.getName(), null); // Get FieldName
            byteArray = push(byteArray, FIELDNAME);
            byte[] FIELD_HASVALUE = new byte[]{CheckHasValue(oneField)}; // Get FieldName
            byteArray = push(byteArray, FIELD_HASVALUE);
            Object value = oneField.get(obj);
            if(value != null) // If Field has value 
            {
                //GETVALUE(Type,Value)
            }
        }
        
        
        System.out.println("Bytes are: " + Arrays.toString(byteArray));
        return byteArray;
    }

    public Object DeSerialize() {
        return null;
    }
    
    private byte GetFieldCode(Object o){
        
        return (byte) 0x49;
    }
    
    
     private byte CheckHasValue(Object o){
        
        return (byte) 0x49;
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
