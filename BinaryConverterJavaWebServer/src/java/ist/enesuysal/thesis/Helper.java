/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.enesuysal.thesis;

import java.lang.reflect.Field;

/**
 *
 * @author enes
 */
public class Helper {

    public static byte[] GetFieldValue(byte type, Object o) {

        switch (type) {
            case 0x01:
                return new byte[]{(byte) o};
            case 0x02:
                return CentralSerializer.intToByteArray((int) o, null);
            case 0x03:
                return CentralSerializer.stringToByteArray((String) o, null);
            case 0x04:
                return CentralSerializer.boolToByteArray((boolean) o, null);
        }
        return null;

    }

    public static String GetFieldType(byte type) {

        switch (type) {
            case 0x01:
                return "byte";
            case 0x02:
                return "integer";
            case 0x03:
                return "string";
            case 0x04:
                return "boolean";
        }
        return null;

    }

    public static int GetFieldSize(String type) {
        switch (type) {
            case "byte":
                return 1;
            case "integer":
                return 8;
            case "string":
                return 22;
            case "boolean":
                return 1;
            default:
                return 0;
        }

    }

    public static byte GetFieldCode(Object o) {
        switch (o.toString()) {
            case "byte":
                return (byte) 0x01;
            case "integer":
                return (byte) 0x02;
            case "string":
                return (byte) 0x03;
            case "boolean":
                return (byte) 0x04;
            default:
                return (byte) 0x00;
        }

    }

    public static byte CheckHasValue(Object o, Field field) throws IllegalArgumentException, IllegalAccessException {

        Class t = field.getType();
        Object v = field.get(o);
        if (boolean.class.equals(t) && Boolean.FALSE.equals(v)) {
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

    public static boolean IsSerializable() {
        //Check START_SERIALIZE and FINISH SERIALIZE
        return true;
    }

    public static boolean IsPrimitive() {
        //Check OBJECT_START
        return false;
    }

}
