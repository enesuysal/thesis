package ist.enesuysal.thesis;

import ist.enesuysal.thesis.Helper.MyMethod;
import ist.enesuysal.thesis.Helper.MyField;
import ist.enesuysal.thesis.Helper.Helper;
import ist.enesuysal.thesis.Annotation.AvaliableMethod;
import ist.enesuysal.thesis.Tests.Test2;
import ist.enesuysal.thesis.Tests.Test3;
import java.lang.reflect.Field;

public class Receiver {

    public MyMethod[] knownMethods = null;

    public Receiver() throws IllegalArgumentException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        //Get ALL KNown AvaliableMethods
//        List<Method> allAvaliableMethods = Helper.getMethodsAnnotatedWith(this.getClass(), AvaliableMethod.class);
//        knownMethods = new MyMethod[allAvaliableMethods.size()];
//        for (int i = 0; i < allAvaliableMethods.size(); i++) {
//            knownMethods[i] = new MyMethod();
//            Class paramType = allAvaliableMethods.get(i).getParameters()[0].getType();
//           if(paramType.isPrimitive())
//           {
//               //CentralSerializer
//           }else{
//               
//           }            

//            Field[] fields = myClass.getDeclaredFields();
//            knownMethods[i].myfields = new MyField[fields.length];
//            knownMethods[i].methodName = myClass.getName();
//            for (int j = 0; j < fields.length; j++) {
//                if (fields[j].isAnnotationPresent(Mandatory.class)) {
//                    MyField myField = new MyField();
//                    myField.fieldName = fields[j].getName();
//                    myField.fieldType = fields[j].getType().toString();
//                    myField.fieldValue = fields[j].get(objectInstance);
//                    myField.isMandatory = true;
//                    knownMethods[i].myfields[j] = myField;
//
//                } else {
//                    MyField myField = new MyField();
//                    myField.fieldName = fields[j].getName();
//                    myField.fieldType = fields[j].getType().toString();
//                    //myField.fieldValue = field.get(myClass);
//                    myField.isMandatory = false;
//                    knownMethods[i].myfields[j] = myField;
//
//                }
        //    }
        //}
    }

    public void createObject(byte[] bytes) throws Exception {
        //Deserialize and Print
        String type = (Helper.GetFieldType(bytes[1])); //FieldType
        int FieldNameLength = 0;
        byte[] FieldNameLengthByte = new byte[8];
        System.arraycopy(bytes, 2, FieldNameLengthByte, 0, FieldNameLengthByte.length);
        String fieldName = "";
        byte[] FieldNameByte = new byte[FieldNameLength];
        System.arraycopy(bytes, 10, FieldNameByte, 0, FieldNameByte.length);
        if (FieldNameLength > 0) {
            fieldName = CentralSerializer.convertToString(bytes);
        }
        //Field hasValue
        byte[] FieldValueByte = new byte[bytes.length - (10 + FieldNameLength)];
        System.arraycopy(bytes, 10 + FieldNameLength, FieldValueByte, 0, FieldValueByte.length);
         System.out.println("Primitive Value " + Helper.GetFieldValue(type, FieldValueByte));
        PrintObject(Helper.GetFieldValue(type, FieldValueByte));
    }

    public void PrintObject(Object o) {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append(o.getClass().getName());
        result.append(" Object {");
        result.append(newLine);

        //determine fields declared in this class only (no fields of superclass)
        Field[] fields = o.getClass().getDeclaredFields();

        //print field names paired with their values
        for (Field field : fields) {
            result.append("  ");
            try {
                result.append(field.getName());
                result.append(": ");
                //requires access to private field:
                result.append(field.get(o));
            } catch (IllegalAccessException ex) {
                //System.out.println(ex);
            }
            result.append(newLine);
        }
        result.append("}");

        System.out.println(result.toString());
    }

    @AvaliableMethod
    public void MakeObjectA(int test) {

    }

    @AvaliableMethod
    public void MakeObjectB(boolean test) {
    }

    @AvaliableMethod
    public void MakeObjectC(long test) {
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
