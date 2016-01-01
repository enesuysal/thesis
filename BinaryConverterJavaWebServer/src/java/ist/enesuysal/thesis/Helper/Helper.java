/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.enesuysal.thesis.Helper;

import ist.enesuysal.thesis.CentralSerializer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author enes
 */
public class Helper {
    public static byte[] push(byte[] array, byte[] push) {
        byte[] longer = new byte[array.length + push.length];
        System.arraycopy(array, 0, longer, 0, array.length);
        System.arraycopy(push, 0, longer, array.length, push.length);

        return longer;
    }
    public static byte[] GetFieldValue(byte type, Object o) {

        switch (type) {
            case 0x01:
                return new byte[]{(byte) o};
            case 0x02:
                return CentralSerializer.convertToByteArray((int) o, null);
            case 0x03:
                return CentralSerializer.convertToByteArray((String) o, null);
            case 0x04:
                return CentralSerializer.convertToByteArray((boolean) o, null);
            case 0x05:
                return CentralSerializer.convertToByteArray((char) o,null);
            case 0x06:
                return CentralSerializer.convertToByteArray((long) o,null);
            case 0x07:
                return CentralSerializer.convertToByteArray((short) o,null);
            case 0x08:
                return CentralSerializer.convertToByteArray((float) o,null);
            case 0x09:
                return CentralSerializer.convertToByteArray((double) o,null);
        }
        return null;

    }

    public static Object GetFieldValue(String type, byte[] o) {

        switch (type) {
            case "byte":
                return (null == o) ? 0 : o[0];
            case "int":
                return (null == o) ? 0 : CentralSerializer.convertToInt(o);
            case "class java.lang.String":
                return (null == o) ? null : CentralSerializer.convertToString(o);
            case "boolean":
                return (null == o) ? false : CentralSerializer.convertToBool(o);
            case "char":
                return (null == o) ? 0 : CentralSerializer.convertToCharacter(o);
            case "long":
                return (null == o) ? 0 : CentralSerializer.convertToLong(o);
            case "short":
                return (null == o) ? 0 : CentralSerializer.convertToShort(o);
            case "float":
                return (null == o) ? 0 : CentralSerializer.convertToFloat(o);
            case "double":
                return (null == o) ? 0 : CentralSerializer.convertToDouble(o);
        }
        return null;

    }

    public static boolean CheckNameTypeValue() {
        return false;
    }

    public static boolean CheckNameType() {
        return false;
    }

    public static String GetFieldType(byte type) throws Exception {

        switch (type) {
            case 0x01:
                return "byte";
            case 0x02:
                return "int";
            case 0x03:
                return "class java.lang.String";
            case 0x04:
                return "boolean";
            case 0x05:
                return "char";
            case 0x06:
                return "long";
            case 0x07:
                return "short";
            case 0x08:
                return "float";
            case 0x09:
                return "double";
        }
        throw new Exception("No Primitive Type Found");
    }

    public static int GetFieldSize(String type) {
        switch (type) {
            case "byte":
                return 1;
            case "int":
                return 8;
            case "class java.lang.String":
                return 22;
            case "boolean":
                return 1;
            case "char":
                return 2;
            case "long":
                return 8;
            case "short":
                return 2;
            case "float":
                return 4;
            case "double":
                return 8;
            default:
                return 0;
        }

    }
    private static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();

    public static boolean isWrapperType(Class<?> clazz)
    {
        return WRAPPER_TYPES.contains(clazz);
    }

    private static Set<Class<?>> getWrapperTypes()
    {
        Set<Class<?>> ret = new HashSet<Class<?>>();
        ret.add(Boolean.class);
        ret.add(Character.class);
        ret.add(Byte.class);
        ret.add(Short.class);
        ret.add(Integer.class);
        ret.add(Long.class);
        ret.add(Float.class);
        ret.add(Double.class);
        ret.add(Void.class);
        return ret;
    }
     public static String GetFieldType(byte[] o) throws Exception {
         byte[] fieldNameLenghtBytes = new byte[2];
         System.arraycopy(o, 0, fieldNameLenghtBytes, 0, 2);
         return GetFieldType(fieldNameLenghtBytes[1]);
     }
    public static byte GetFieldCode(String o) {
        switch (o) {
            case "byte":
                return (byte) 0x01;
            case "int":
                return (byte) 0x02;
            case "class java.lang.String":
                return (byte) 0x03;
            case "boolean":
                return (byte) 0x04;
            case "char":
                return (byte) 0x05;
            case "long":
                return (byte) 0x06;
            case "short":
                return (byte) 0x07;
            case "float":
                return (byte) 0x08;
            case "double":
                return (byte) 0x09;
            default:
                return (byte) 0x00;
        }

    }

    public static byte CheckHasValue(Object o, Field field) throws IllegalArgumentException, IllegalAccessException {
        field.setAccessible(true);
        Class t = field.getType();
        Object v = field.get(o);

        if (t.getName().equals("boolean") && (boolean) v == false) {
            return (byte) 0X00;
        } else if (t.getName().equals("boolean") && (boolean) v == true) {
            return (byte) 0X01;
        } else if (boolean.class.equals(t) && Boolean.FALSE.equals(v)) {
            return (byte) 0X00;
        } else if (char.class.equals(t) && ((Character) v) != Character.MIN_VALUE) {
            return (byte) 0X00;
        } else if (t.isPrimitive() && ((Number) v).doubleValue() == 0) {
            return (byte) 0X00;
        } else if (!t.isPrimitive() && v == null) {
            return (byte) 0X00;
        }
        // Has no default value
        return (byte) 0x01;
    }

    public static boolean IsSerializable(byte[] byteArray) {
        //Check START_SERIALIZE and FINISH SERIALIZE
        byte[] START_SERIALIZE = new byte[]{(byte) 0xAC, (byte) 0xAE};
        byte[] FINISH_SERIALIZE = new byte[]{(byte) 0x75};
        return byteArray[0] == START_SERIALIZE[0] && byteArray[1] == START_SERIALIZE[1] && byteArray[byteArray.length - 1] == FINISH_SERIALIZE[0];
    }

    public static boolean IsPrimitive() {
        //Check OBJECT_START
        return false;
    }

    static int GetValueByteLenght(String type) {
        return 1;
    }

    public static List<Method> getMethodsAnnotatedWith(final Class<?> type, final Class<? extends Annotation> annotation) {
        final List<Method> methods = new ArrayList<Method>();
        Class<?> klass = type;
        while (klass != Object.class) { // need to iterated thought hierarchy in order to retrieve methods from above the current instance
            // iterate though the list of methods declared in the class represented by klass variable, and add those annotated with the specified annotation
            final List<Method> allMethods = new ArrayList<Method>(Arrays.asList(klass.getDeclaredMethods()));
            for (final Method method : allMethods) {
                if (method.isAnnotationPresent(annotation)) {
                    Annotation annotInstance = method.getAnnotation(annotation);
                    // TODO process annotInstance
                    methods.add(method);
                }
            }
            // move to the upper class in the hierarchy in search for more methods
            klass = klass.getSuperclass();
        }
        return methods;
    }

    public static Field[] getAnnotatedDeclaredFields(Class clazz,
            Class<? extends Annotation> annotationClass) {
        Field[] allFields = clazz.getDeclaredFields();
        List<Field> annotatedFields = new LinkedList<Field>();

        for (Field field : allFields) {
            if (field.isAnnotationPresent(annotationClass)) {
                annotatedFields.add(field);
            }
        }

        return annotatedFields.toArray(new Field[annotatedFields.size()]);
    }

}
