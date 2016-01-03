
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

public class Helper {

    public static byte[] push(byte[] array, byte[] push) {
        byte[] longer = new byte[array.length + push.length];
        System.arraycopy(array, 0, longer, 0, array.length);
        System.arraycopy(push, 0, longer, array.length, push.length);

        return longer;
    }

    
    public static byte[] GetFieldBytes(){
        byte[] bytes = new byte[0];
        return bytes;
    }
    public static Object GetFieldValue(String type, byte[] o) {

        switch (type) {
            case "byte":
            case "class java.lang.Byte":
                return (null == o) ? 0 : o[0];
            case "int":
            case "class java.lang.Integer":
                return (null == o) ? 0 : CentralSerializer.convertToInt(o);
            case "class java.lang.String":
                return (null == o) ? null : CentralSerializer.convertToString(o);
            case "boolean":
            case "class java.lang.Boolean":
                return (null == o) ? false : CentralSerializer.convertToBool(o);
            case "char":
            case "class java.lang.Character":
                return (null == o) ? 0 : CentralSerializer.convertToCharacter(o);
            case "long":
            case "class java.lang.Long":
                return (null == o) ? 0 : CentralSerializer.convertToLong(o);
            case "short":
            case "class java.lang.Short":
                return (null == o) ? 0 : CentralSerializer.convertToShort(o);
            case "float":
            case "class java.lang.Float":
                return (null == o) ? 0 : CentralSerializer.convertToFloat(o);
            case "double":
            case "class java.lang.Double":
                return (null == o) ? 0 : CentralSerializer.convertToDouble(o);
        }
        return null;

    }
    public static byte[] GetFieldValueByte(String type, Object o,byte[] bytes) {

        switch (type) {
            case "byte":
            case "class java.lang.Byte":
                return  CentralSerializer.convertToByteArray((byte)o,bytes);
            case "int":
            case "class java.lang.Integer":
                return  CentralSerializer.convertToByteArray((int)o,bytes);
            case "class java.lang.String":
                return  CentralSerializer.convertToByteArray((String)o,bytes);
            case "boolean":
            case "class java.lang.Boolean":
                return  CentralSerializer.convertToByteArray((boolean)o,bytes);
            case "char":
            case "class java.lang.Character":
                return  CentralSerializer.convertToByteArray((char)o,bytes);
            case "long":
            case "class java.lang.Long":
                return  CentralSerializer.convertToByteArray((long)o,bytes);
            case "short":
            case "class java.lang.Short":
                return  CentralSerializer.convertToByteArray((short)o,bytes);
            case "float":
            case "class java.lang.Float":
                return  CentralSerializer.convertToByteArray((float)o,bytes);
            case "double":
            case "class java.lang.Double":
                return  CentralSerializer.convertToByteArray((double)o,bytes);
        }
        return null;

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
            case 0x10:
                return "class java.lang.Boolean";
        }
        throw new Exception("No Primitive or Wrapper Type Found");
    }

 
    private static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();

    public static boolean isWrapperType(Class<?> clazz) {
        return WRAPPER_TYPES.contains(clazz);
    }

    private static Set<Class<?>> getWrapperTypes() {
        Set<Class<?>> ret = new HashSet<Class<?>>();
        ret.add(Boolean.class);
        ret.add(Character.class);
        ret.add(Byte.class);
        ret.add(Short.class);
        ret.add(Integer.class);
        ret.add(Long.class);
        ret.add(Float.class);
        ret.add(Double.class);
        ret.add(String.class);
        return ret;
    }

    public static String GetFieldType(byte[] o) throws Exception {
        byte[] fieldNameLenghtBytes = new byte[2];
        System.arraycopy(o, 0, fieldNameLenghtBytes, 0, 2);
        return GetFieldType(fieldNameLenghtBytes[1]);
    }

    public static byte[] GetFieldCode(String o) {
        byte[] fieldTypeBytes = new byte[1];
        switch (o) {
            case "byte":
                fieldTypeBytes[0] = (byte) 0x01;
                break;
            case "int":
                fieldTypeBytes[0] = (byte) 0x02;
                break;
            case "java.lang.String":
                fieldTypeBytes[0] = (byte) 0x03;
                break;
            case "boolean":
                fieldTypeBytes[0] = (byte) 0x04;
                break;
            case "char":
                fieldTypeBytes[0] = (byte) 0x05;
                break;
            case "long":
                fieldTypeBytes[0] = (byte) 0x06;
                break;
            case "short":
                fieldTypeBytes[0] = (byte) 0x07;
                break;
            case "float":
                fieldTypeBytes[0] = (byte) 0x08;
                break;
            case "double":
                fieldTypeBytes[0] = (byte) 0x09;
                break;
            case "java.lang.Boolean":
                fieldTypeBytes[0] = (byte) 0x10;
                break;
            default:
                fieldTypeBytes[0] = (byte) 0x00;

        }
        return fieldTypeBytes;

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
