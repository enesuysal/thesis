package ist.enesuysal.thesis;

import ist.enesuysal.thesis.Helper.MyMethod;
import ist.enesuysal.thesis.Helper.MyField;
import ist.enesuysal.thesis.Helper.Helper;
import ist.enesuysal.thesis.Annotation.AvaliableMethod;
import ist.enesuysal.thesis.Annotation.Mandatory;
import static ist.enesuysal.thesis.CentralSerializer.convertToByteArray;
import ist.enesuysal.thesis.Tests.Test1;
import ist.enesuysal.thesis.Tests.Test2;
import ist.enesuysal.thesis.Tests.Test3;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class Receiver {

    public MyMethod[] knownMethods = null;

    public Receiver() throws IllegalArgumentException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        //Get ALL KNown AvaliableMethods
        List<Method> allAvaliableMethods = Helper.getMethodsAnnotatedWith(this.getClass(), AvaliableMethod.class);
        knownMethods = new MyMethod[allAvaliableMethods.size()];
        for (int i = 0; i < allAvaliableMethods.size(); i++) {
            knownMethods[i] = new MyMethod();
            Class myClass = allAvaliableMethods.get(i).getParameters()[0].getType();
            if (myClass.isPrimitive() || Helper.isWrapperType(myClass)) {
                knownMethods[i].myfields = convertToByteArray(Helper.GetFieldCode(allAvaliableMethods.get(i).getParameters()[0].getType().getTypeName()), new byte[0]);
            } else {

                Object objectInstance = myClass.newInstance();
                Field[] fields = myClass.getDeclaredFields();
                knownMethods[i].methodName = myClass.getName();
                byte[] fieldbytes = new byte[0];
                knownMethods[i].myfields = new byte[0];
                for (int j = 0; j < fields.length; j++) {
                    if (fields[j].isAnnotationPresent(Mandatory.class)) {
                        fieldbytes = CentralSerializer.serializePrimitive(fields[j].getType(), fields[j].getName(), true, fields[j].get(objectInstance), new byte[0]);
                    } else {
                        fieldbytes = CentralSerializer.serializePrimitive(fields[j].getType(), fields[j].getName(), false, fields[j].get(objectInstance), new byte[0]);
                    }
                    knownMethods[i].myfields = Helper.push(knownMethods[i].myfields, fieldbytes);
                }
            }
        }
    }

    public void createPrimitive(byte[] bytes) throws Exception {
        //Deserialize and Print
        //System.out.println("ss" + Arrays.toString(bytes));
        String type = (Helper.GetFieldType(bytes[0])); //FieldType
        int FieldNameLength = 0;
        byte[] FieldNameLengthByte = new byte[8];
        System.arraycopy(bytes, 1, FieldNameLengthByte, 0, FieldNameLengthByte.length);
        String fieldName = "";
        byte[] FieldNameByte = new byte[FieldNameLength];
        System.arraycopy(bytes, 9, FieldNameByte, 0, FieldNameByte.length);
        if (FieldNameLength > 0) {
            fieldName = CentralSerializer.convertToString(bytes);
        }
        //Field hasValue
        byte[] FieldValueByte = new byte[bytes.length - (17 + FieldNameLength)];
        System.arraycopy(bytes, 17 + FieldNameLength, FieldValueByte, 0, FieldValueByte.length);
        System.out.println("Value " + Helper.GetFieldValue(type, FieldValueByte));
        PrintObject(Helper.GetFieldValue(type, FieldValueByte));
    }

    public void createWrapper(byte[] bytes) throws Exception {
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
        System.out.println("Wrapper Value " + Helper.GetFieldValue(type, FieldValueByte));
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
    public void MakeObjectA(byte test) {

    }

    @AvaliableMethod
    public void MakeObjectA(char test) {

    }

    @AvaliableMethod
    public void MakeObjectA(long test) {

    }

    @AvaliableMethod
    public void MakeObjectA(short test) {

    }

    @AvaliableMethod
    public void MakeObjectA(float test) {

    }

    @AvaliableMethod
    public void MakeObjectA(double test) {

    }

    @AvaliableMethod
    public void MakeObjectA(boolean test) {
    }

    @AvaliableMethod
    public void MakeObjectB(Byte test) {
    }
    @AvaliableMethod
    public void MakeObjectB(String test) {
    }

    @AvaliableMethod
    public void MakeObjectB(Integer test) {
    }

    @AvaliableMethod
    public void MakeObjectB(Boolean test) {
    }

    @AvaliableMethod
    public void MakeObjectB(Character test) {
    }

    @AvaliableMethod
    public void MakeObjectB(Short test) {
    }

    @AvaliableMethod
    public void MakeObjectB(Long test) {
    }

    @AvaliableMethod
    public void MakeObjectB(Double test) {
    }

    @AvaliableMethod
    public void MakeObjectB(Float test) {
    }

    @AvaliableMethod
    public void MakeObjectC(Test1 test) {
    }

}
