package ist.enesuysal.thesis;

import ist.enesuysal.thesis.Helper.MyMethod;
import ist.enesuysal.thesis.Helper.MyField;
import ist.enesuysal.thesis.Helper.Helper;
import ist.enesuysal.thesis.Annotation.AvaliableMethod;
import ist.enesuysal.thesis.Annotation.Mandatory;
import ist.enesuysal.thesis.Tests.Test1;
import ist.enesuysal.thesis.Tests.Test2;
import ist.enesuysal.thesis.Tests.Test3;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import sun.misc.BASE64Decoder;

public class Receiver {

    public MyMethod[] knownMethods = null;

    public Receiver() throws IllegalArgumentException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        //Get ALL KNown AvaliableMethods
        List<Method> allAvaliableMethods = Helper.getMethodsAnnotatedWith(this.getClass(), AvaliableMethod.class);
        knownMethods = new MyMethod[allAvaliableMethods.size()];
        for (int i = 0; i < allAvaliableMethods.size(); i++) {
            knownMethods[i] = new MyMethod();
            Class myClass = allAvaliableMethods.get(i).getParameters()[1].getType();
            Object objectInstance = myClass.newInstance();
            Field[] fields = myClass.getDeclaredFields();
            knownMethods[i].myfields = new MyField[fields.length];
            knownMethods[i].methodName = myClass.getName();
            for (int j = 0; j < fields.length; j++) {
                if (fields[j].isAnnotationPresent(Mandatory.class)) {
                    MyField myField = new MyField();
                    myField.fieldName = fields[j].getName();
                    myField.fieldType = fields[j].getType().toString();
                    myField.fieldValue = fields[j].get(objectInstance);
                    myField.isMandatory = true;
                    knownMethods[i].myfields[j] = myField;

                } else {
                    MyField myField = new MyField();
                    myField.fieldName = fields[j].getName();
                    myField.fieldType = fields[j].getType().toString();
                    //myField.fieldValue = field.get(myClass);
                    myField.isMandatory = false;
                    knownMethods[i].myfields[j] = myField;

                }

            }
        }

    }

    public static byte[] GetMessage(String BASE64String) throws ClassNotFoundException, Exception {
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
                } else {

                    field.fieldValue = Helper.GetFieldValue(type, null);
                }
                field.fieldName = CentralSerializer.ByteArrayToString(GetFieldNameByte);
                field.fieldType = type;
                fields[i] = field;
            }
            MyMethod m = new MyMethod();
            m.myfields = fields;
            Receiver r = new Receiver();
            r.findMethod(m);

        } catch (IOException ex) {
            System.err.println("The Error Occured " + ex.getMessage());
        }
        return null;
    }

    @AvaliableMethod
    public void MakeObjectA(Byte[] binary, Test1 test) {
    }

    @AvaliableMethod
    public void MakeObjectB(Byte[] binary, Test2 test) {
    }

    @AvaliableMethod
    public void MakeObjectC(Byte[] binary, Test3 test) {
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

    private void findMethod(MyMethod currentMethods) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        // For each field
        boolean Flag = false;
        MyField[] currentFields = currentMethods.myfields;
        for (MyMethod method : knownMethods) {
            for (MyField field : method.myfields) {

                Flag = false;
                for (MyField knownField : currentFields) {
                    if (knownField.isMandatory && field.fieldName.equals(knownField.fieldName) && field.fieldType.equals(knownField.fieldType) && field.fieldValue.equals(knownField.fieldValue)) {
                        Flag = true;
                        break;
                    }
                    if (!knownField.isMandatory && field.fieldName.equals(knownField.fieldName) && field.fieldType.equals(knownField.fieldType)) {
                        Flag = true;
                        break;
                    }
                }
                if (!Flag) {
                    break;
                }
            }
            if (Flag) {
                System.out.println("Avaliable Method Found");
                Class myClass = Class.forName(method.methodName);
                myClass.newInstance();
                break;
            }

        }
        if (!Flag) {
            System.out.println("No method matches..");

        }
    }
}
