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
    byte[] byteArray;

    public ObjectSerializer(Object o) {
        this.obj = o;
    }
    
     public ObjectSerializer(byte[] bytearray) {
        this.byteArray = bytearray;
    }

    public byte[] Serialize() throws IllegalArgumentException, IllegalAccessException {
        byte[] byteArray = new byte[0];
        byte[] START_SERIALIZE = new byte[]{(byte) 0xAC, (byte) 0xAE};// Specifies that this is a serialization 
        byteArray = push(byteArray, START_SERIALIZE);
        System.out.println("START_SERIALIZE: " + Arrays.toString(START_SERIALIZE));
        byte[] OBJECT_START = new byte[]{(byte) 0x73};//Specifies that Object starts here.
        byteArray = push(byteArray, OBJECT_START);
        System.out.println("OBJECT_START: " + Arrays.toString(OBJECT_START));
        //GET Fields
        Field[] fields = obj.getClass().getFields();
        byte[] FIELDS_LENGH = CentralSerializer.intToByteArray(fields.length, null);// Length of the class name
        byteArray = push(byteArray, FIELDS_LENGH);
        System.out.println("FIELDS_LENGH: " + Arrays.toString(FIELDS_LENGH));
        //For each Field 
        for (Field oneField : fields) {
            Object fieldType = oneField.getType(); //Get Field Type
            System.out.println(fieldType.toString());
            byte[] FIELD_TYPE = new byte[]{Helper.GetFieldCode(fieldType)}; // Get Field Code
            byteArray = push(byteArray, FIELD_TYPE);
            System.out.println(Arrays.toString(FIELD_TYPE));
            byte[] FIELDNAME_LENGH = CentralSerializer.intToByteArray(oneField.getName().length(), null); //Get fieldname Length 
            byteArray = push(byteArray, FIELDNAME_LENGH);
            System.out.println("FieldName Lengh " + Arrays.toString(FIELDNAME_LENGH));
            byte[] FIELDNAME = CentralSerializer.stringToByteArray(oneField.getName(), null); // Get FieldName
            byteArray = push(byteArray, FIELDNAME);
            System.out.println("FieldName " + Arrays.toString(FIELDNAME));
            byte[] FIELD_HASVALUE = new byte[]{Helper.CheckHasValue(obj, oneField)}; // Get FieldName
            byteArray = push(byteArray, FIELD_HASVALUE);
            System.out.println("Field has value" + Arrays.toString(FIELD_HASVALUE));
            Object value = oneField.get(obj);
            if (FIELD_HASVALUE != new byte[]{(byte) 0x00}) // If Field has value 
            {
                byte[] FIELD_VALUE = Helper.GetFieldValue(FIELD_TYPE[0], value);
                byteArray = push(byteArray, FIELD_VALUE);
                System.out.println("Field  value" + Arrays.toString(FIELD_VALUE));
            }
        }
        byte[] OBJECT_END = new byte[]{(byte) 0x74};//Specifies that Object finishes here.
        byteArray = push(byteArray, OBJECT_END);
        byte[] FINISH_SERIALIZE = new byte[]{(byte) 0x75};// Specifies that this is a serialization 
        byteArray = push(byteArray, FINISH_SERIALIZE);

        System.out.println("Bytes are: " + Arrays.toString(byteArray));
        return byteArray;
    }

    public Object DeSerialize() throws Exception {
        if(!Helper.IsSerializable())
            throw new Exception("Array is not in correct format");
        if(Helper.IsPrimitive())
            throw new Exception("It is primative not a class");
        //Create Object
        int FieldLength = 0;
        byte[] FieldLengthByte = new byte[0];
        for (int i = 0; i < 8; i++) {
            FieldLengthByte = push(FieldLengthByte, byteArray[3 + i]);
        }
        FieldLength = CentralSerializer.ByteArrayToInt(FieldLengthByte);
        //int NumberofFields =obj.getClass().getFields().length;
        System.err.println(FieldLength);
         int iteration = 0;
         FieldLength =1;
        for (int i = 0; i < FieldLength; i++) {
            System.err.println(Helper.GetFieldType(byteArray[11+iteration])); //FieldType
            iteration += Helper.GetFieldSize(Helper.GetFieldType(byteArray[11+iteration]));
            byte[] FieldNameByte = new byte[0];
            for (int s = 0; s < 8; s++) {
            FieldNameByte = push(FieldNameByte, byteArray[11+iteration+s]);
            }
            System.err.println("Field Name Leng" + Arrays.toString(FieldNameByte));
            iteration += 8;// FieldNameLenght
            iteration += CentralSerializer.ByteArrayToInt(FieldNameByte);// FieldNameLenght
            System.err.println("Field Name Leng" + CentralSerializer.ByteArrayToInt(FieldNameByte));
             byte[] GetFieldNameByte = new byte[0];
             for (int k = 0; k < CentralSerializer.ByteArrayToInt(FieldNameByte); k++) {
            GetFieldNameByte = push(GetFieldNameByte, byteArray[11+iteration+k]);
            }
              System.err.println("Field" + CentralSerializer.ByteArrayToString(GetFieldNameByte));
            
            
        }
        
        return null;
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
