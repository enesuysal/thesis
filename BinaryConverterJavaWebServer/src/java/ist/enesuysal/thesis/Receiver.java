/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.enesuysal.thesis;

import ist.enesuysal.thesis.Annotation.AvaliableMethod;
import ist.enesuysal.thesis.Annotation.Mandatory;
import ist.enesuysal.thesis.Tests.TestSerial;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import sun.misc.BASE64Decoder;

/**
 *
 * @author enesuysal
 */
public class Receiver {
    public MyField[] methods= null; 
    public Receiver() throws IllegalArgumentException, IllegalAccessException, InstantiationException, ClassNotFoundException{
//                Get ALL KNown AvaliableMethods
            
            Method[] methods = this.getClass().getDeclaredMethods();
            for (Method method : methods) {
                //Find Avaliable Methods
                if (method.isAnnotationPresent(AvaliableMethod.class)) {

                    Class myClass = method.getParameters()[1].getType();
                    Object objectInstance = myClass.newInstance();
                    Field[] fields = myClass.getDeclaredFields();
                  
                    for (Field field : fields) {
                        if (field.isAnnotationPresent(Mandatory.class)) {
                            System.out.println("Field: " + field.getName());
                            MyField myField = new MyField();
                            myField.fieldName = field.getName();
                            myField.fieldType = field.getType().toString();
                            myField.fieldValue = field.get(objectInstance);
                            myField.isMandatory =true;

                        } else {
                            System.out.println("Not Mandatory Field: " + field.getName());
                             MyField myField = new MyField();
                            myField.fieldName = field.getName();
                            myField.fieldType = field.getType().toString();
                            //myField.fieldValue = field.get(myClass);
                            myField.isMandatory =false;
                            
                        }
                    }

                }
            }
            
    }
    public static Object GetMessage(String BASE64String) throws ClassNotFoundException, Exception {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] decodedBytes = decoder.decodeBuffer(BASE64String);
            if (!Helper.IsSerializable(decodedBytes)) {
                throw new Exception("Binary is not in known format");
            }
            //Remove START_SERIALIZE
            decodedBytes = pop(decodedBytes);
            decodedBytes = pop(decodedBytes);
            if (Helper.IsPrimitive()) {
                throw new Exception("It is primative not a class");
            }
            //Remove OBJECT_START
            decodedBytes = pop(decodedBytes);
            int FieldLength;
            byte[] FieldLengthByte = new byte[8];
            System.arraycopy(decodedBytes, 0, FieldLengthByte, 0, FieldLengthByte.length);
            //Remove FieldLengthByte
            decodedBytes = pop(decodedBytes, FieldLengthByte);
            FieldLength = CentralSerializer.ByteArrayToInt(FieldLengthByte);
            MyField[] fields = new MyField[FieldLength];
            for (int i = 0; i < FieldLength; i++) {
                MyField field = new MyField();
                String type = (Helper.GetFieldType(decodedBytes[0])); //FieldType
                //Remove FieldType
                decodedBytes = pop(decodedBytes);
                byte[] FieldNameByte = new byte[8];
                System.arraycopy(decodedBytes, 0, FieldNameByte, 0, FieldNameByte.length);
                //Remove FieldNameByte
                decodedBytes = pop(decodedBytes, FieldNameByte);
                byte[] GetFieldNameByte = new byte[CentralSerializer.ByteArrayToInt(FieldNameByte)];
                System.arraycopy(decodedBytes, 0, GetFieldNameByte, 0, GetFieldNameByte.length);
                //Remove GetFieldNameByte
                decodedBytes = pop(decodedBytes, GetFieldNameByte);
                //Check if has value
                byte HasValue = decodedBytes[0];
                //Remove HasValue flag
                decodedBytes = pop(decodedBytes);
                if (HasValue == 1) {
                    byte[] FieldValueLenghtByte = new byte[8];
                    System.arraycopy(decodedBytes, 0, FieldValueLenghtByte, 0, FieldValueLenghtByte.length);
                    int FieldValueLenght = CentralSerializer.ByteArrayToInt(FieldValueLenghtByte);
                    //Remove FieldValueLenghtByte 
                    decodedBytes = pop(decodedBytes, FieldValueLenghtByte);
                    byte[] FieldValue = new byte[FieldValueLenght];
                    System.arraycopy(decodedBytes, 0, FieldValue, 0, FieldValue.length);
                    field.fieldValue = Helper.GetFieldValue(type, FieldValue);
                    // REmove Value
                    decodedBytes = pop(decodedBytes, FieldValue);
                }
                field.fieldName = CentralSerializer.ByteArrayToString(GetFieldNameByte);
                field.fieldType = type;
                fields[i] = field;
            }
           
             Receiver r = new Receiver();
             r.findMethod();
 
        } catch (IOException ex) {
            System.err.println("The Error Occured " + ex.getMessage());
        }
        return null;
    }

    @AvaliableMethod
    public void MakeObjectA(Byte[] binary, TestSerial test) {

    }

//    @AvaliableMethod
//    public void MakeObjectB(Byte[] binary, int b) {
//        TestSerial s = new TestSerial();
//    }

    public void PrintAllClassValues() {
        System.out.println("#####################");
    }

    private static byte[] push(byte[] array, byte[] push) {
        byte[] longer = new byte[array.length + push.length];
        System.arraycopy(array, 0, longer, 0, array.length);
        System.arraycopy(push, 0, longer, array.length, push.length);

        return longer;
    }

    private static byte[] pop(byte[] array, byte[] pop) {
        byte[] longer = new byte[array.length - pop.length];
        System.arraycopy(array, pop.length, longer, 0, longer.length);
        // System.arraycopy(push, 0, longer, array.length, push.length);

        return longer;
    }

    private static byte[] push(byte[] array, byte push) {
        byte[] longer = new byte[array.length + 1];
        System.arraycopy(array, 0, longer, 0, array.length);
        longer[array.length] = push;
        return longer;
    }

    private static byte[] pop(byte[] array) {
        byte[] longer = new byte[array.length - 1];
        System.arraycopy(array, 1, longer, 0, longer.length);

        return longer;
    }

    private void findMethod() {
        
    }

}
