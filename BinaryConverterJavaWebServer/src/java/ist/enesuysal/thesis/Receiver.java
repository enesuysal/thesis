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
import java.lang.reflect.Type;
import java.util.List;
import sun.misc.BASE64Decoder;

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

    

    @AvaliableMethod
    public void MakeObjectA(int test) {
    }

    @AvaliableMethod
    public void MakeObjectB(Test2 test) {
    }

    @AvaliableMethod
    public void MakeObjectC(Test3 test) {
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
