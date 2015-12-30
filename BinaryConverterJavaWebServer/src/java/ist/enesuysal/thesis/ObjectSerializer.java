package ist.enesuysal.thesis;

import ist.enesuysal.thesis.Helper.Helper;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

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
        byte[] OBJECT_START = new byte[]{(byte) 0x73};//Specifies that Object starts here.
        byteArray = push(byteArray, OBJECT_START);
        //Check Fields
        byteArray = CheckFields(obj, byteArray);
        CheckMethods(obj,byteArray);
        byte[] OBJECT_END = new byte[]{(byte) 0x74};//Specifies that Object finishes here.
        byteArray = push(byteArray, OBJECT_END);
        byte[] FINISH_SERIALIZE = new byte[]{(byte) 0x75};// Specifies that this is a serialization 
        byteArray = push(byteArray, FINISH_SERIALIZE);
        System.out.println("Bytes are: " + Arrays.toString(byteArray));
        return byteArray;
    }

//    public Object DeSerialize() throws Exception {
//        if (!Helper.IsSerializable(byteArray)) {
//            throw new Exception("Array is not in correct format");
//        }
//        System.err.println("Last Array" + Arrays.toString(byteArray));
//        byteArray = pop(byteArray);
//        byteArray = pop(byteArray);
//        System.err.println("Last Array" + Arrays.toString(byteArray));
//        if (Helper.IsPrimitive()) {
//            throw new Exception("It is primative not a class");
//        }
//        byteArray = pop(byteArray);
//        System.err.println("Last Array" + Arrays.toString(byteArray));
//        //Create Object
//        int FieldLength = 0;
//        byte[] FieldLengthByte = new byte[8];
//        System.arraycopy(byteArray, 0, FieldLengthByte, 0, FieldLengthByte.length);
//        byteArray = pop(byteArray, FieldLengthByte);
//        System.err.println("Last Array" + Arrays.toString(byteArray));
//        FieldLength = CentralSerializer.ByteArrayToInt(FieldLengthByte);
//        int iteration = 0;
//        //FieldLength = 1;
//        for (int i = 0; i < FieldLength; i++) {
//            String type = (Helper.GetFieldType(byteArray[iteration])); //FieldType
//            System.err.println(type);
//            byteArray = pop(byteArray);
//            byte[] FieldNameByte = new byte[8];
//            System.arraycopy(byteArray, 0, FieldNameByte, 0, FieldNameByte.length);
//            byteArray = pop(byteArray, FieldNameByte);
//            System.err.println("Last Array" + Arrays.toString(byteArray));
//            byte[] GetFieldNameByte = new byte[CentralSerializer.ByteArrayToInt(FieldNameByte)];
//            System.arraycopy(byteArray, 0, GetFieldNameByte, 0, GetFieldNameByte.length);
//            System.err.println(CentralSerializer.ByteArrayToString(GetFieldNameByte));
//            byteArray = pop(byteArray, GetFieldNameByte);
//            System.err.println("Last Array" + Arrays.toString(byteArray));
//            //Check if has value
//            byte HasValue = byteArray[0];
//            byteArray = pop(byteArray);
//            System.err.println("Last Array" + Arrays.toString(byteArray));
//            if (HasValue == 1) {
//                byte[] FieldValueLenghtByte = new byte[8];
//                System.arraycopy(byteArray, 0, FieldValueLenghtByte, 0, FieldValueLenghtByte.length);
//                int FieldValueLenght = CentralSerializer.ByteArrayToInt(FieldValueLenghtByte);
//                System.err.println("Last Array" + Arrays.toString(byteArray));
//                byteArray = pop(byteArray, FieldValueLenghtByte);
//                System.err.println("Last Array" + Arrays.toString(byteArray));
//                byte[] FieldValue = new byte[FieldValueLenght];
//                System.arraycopy(byteArray, 0, FieldValue, 0, FieldValue.length);
//                byteArray = pop(byteArray, FieldValue); // REmove Value
//                System.err.println("Last Array" + Arrays.toString(byteArray));
//            }
//        }
//        return null;
//    }

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

    private byte[] CheckFields(Object obj, byte[] byteArray) throws IllegalArgumentException, IllegalAccessException {
        //GET Fields
        Field[] fields = obj.getClass().getDeclaredFields();
        byte[] FIELDS_LENGH = CentralSerializer.convertToByteArray(fields.length, null);// Length of the class name
        byteArray = push(byteArray, FIELDS_LENGH);
        System.out.println("FIELDS_LENGH: " + Arrays.toString(FIELDS_LENGH));
        //For each Field 
        for (Field oneField : fields) {
            Object fieldType = oneField.getType(); //Get Field Type
            System.out.println(fieldType.toString());
            byte[] FIELD_TYPE = new byte[]{Helper.GetFieldCode(fieldType.toString())}; // Get Field Code
            byteArray = push(byteArray, FIELD_TYPE);
            System.out.println(Arrays.toString(FIELD_TYPE));
            byte[] FIELDNAME_LENGH = CentralSerializer.convertToByteArray(oneField.getName().length(), null); //Get fieldname Length 
            byteArray = push(byteArray, FIELDNAME_LENGH);
            System.out.println("FieldName Lengh " + Arrays.toString(FIELDNAME_LENGH));
            byte[] FIELDNAME = CentralSerializer.convertToByteArray(oneField.getName(), null); // Get FieldName
            byteArray = push(byteArray, FIELDNAME);
            System.out.println("FieldName " + Arrays.toString(FIELDNAME));
            byte[] FIELD_HASVALUE = new byte[]{Helper.CheckHasValue(obj, oneField)}; // Get FieldName
            byteArray = push(byteArray, FIELD_HASVALUE);
            System.out.println("Field has value" + Arrays.toString(FIELD_HASVALUE));
            Object value = oneField.get(obj);
            if (FIELD_HASVALUE[0] != 0) // If Field has value 
            {

                byte[] FIELD_VALUE = Helper.GetFieldValue(FIELD_TYPE[0], value);
                byte[] FIELD_VALUE_LENGHT = CentralSerializer.convertToByteArray(FIELD_VALUE.length, null);
                byteArray = push(byteArray, FIELD_VALUE_LENGHT);
                byteArray = push(byteArray, FIELD_VALUE);
                System.out.println("Field  value" + Arrays.toString(FIELD_VALUE));
            }
        }

        return byteArray;
    }

    private byte[] CheckMethods(Object obj, byte[] byteArray) {
        //GET Methods
        Method[] methods = obj.getClass().getDeclaredMethods();
        byte[] METHODS_LENGH = CentralSerializer.convertToByteArray(methods.length, null);// Length of the class name
        byteArray = push(byteArray, METHODS_LENGH);
        System.out.println("METHODS_LENGH: " + Arrays.toString(METHODS_LENGH));
        //For each Field 
        for (Method oneMethod : methods) {
            String methodReturnType = oneMethod.getReturnType().toString();  
            System.err.println(oneMethod.getReturnType().toString());
            byte[] METHOD_RETURN_TYPE = new byte[]{Helper.GetFieldCode(methodReturnType)}; // Get Type Code 
            byteArray = push(byteArray, METHOD_RETURN_TYPE);
            System.out.println(Arrays.toString(METHOD_RETURN_TYPE));
            byte[] METHOD_NAME_LENGH = CentralSerializer.convertToByteArray(oneMethod.getName().length(), null); //Get fieldname Length 
            byteArray = push(byteArray, METHOD_NAME_LENGH);
            System.out.println("Method Name Lengh " + Arrays.toString(METHOD_NAME_LENGH));
            byte[] METHODNAME = CentralSerializer.convertToByteArray(oneMethod.getName(), null); // Get FieldName
            byteArray = push(byteArray, METHODNAME);
            System.out.println("MethodName " + Arrays.toString(METHODNAME));
//            byte[] METHOD_HASPARAM = new byte[]{Helper.CheckHasValue(obj, oneField)}; // Get FieldName
//            byteArray = push(byteArray, FIELD_HASVALUE);
//            System.out.println("Field has value" + Arrays.toString(FIELD_HASVALUE));
//            Object value = oneField.get(obj);
//            if (FIELD_HASVALUE[0] != 0) // If Field has value 
//            {
//
//                byte[] FIELD_VALUE = Helper.GetFieldValue(FIELD_TYPE[0], value);
//                byte[] FIELD_VALUE_LENGHT = CentralSerializer.intToByteArray(FIELD_VALUE.length, null);
//                byteArray = push(byteArray, FIELD_VALUE_LENGHT);
//                byteArray = push(byteArray, FIELD_VALUE);
//                System.out.println("Field  value" + Arrays.toString(FIELD_VALUE));
//            }
        }
        
        return null;
    }
}
