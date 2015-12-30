package ist.enesuysal.thesis;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class CentralSerializer {

    // Primitive Int to ByteArray
    public static byte[] convertToByteArray(int intValue, byte[] arrayByte) {
        arrayByte = new byte[8];
        arrayByte[0] = (byte) (intValue >> 56);
        arrayByte[1] = (byte) (intValue >> 48);
        arrayByte[2] = (byte) (intValue >> 40);
        arrayByte[3] = (byte) (intValue >> 32);
        arrayByte[4] = (byte) (intValue >> 24);
        arrayByte[5] = (byte) (intValue >> 16);
        arrayByte[6] = (byte) (intValue >> 8);
        arrayByte[7] = (byte) (intValue /*>> 0*/);

        return arrayByte;
    }

    // Primitive Bool to ByteArray
    public static byte[] convertToByteArray(Boolean input, byte[] arrayByte) {
        arrayByte = new byte[1];
        arrayByte[0] = input ? (byte) 1 : (byte) 0;
        return arrayByte;
    }

    // Primitive String to ByteArray
    public static byte[] convertToByteArray(String input, byte[] arrayByte) {
        arrayByte = input.getBytes(Charset.forName("UTF-8"));
        return arrayByte;
    }

    // Serilizable object to ByteArray
    public static byte[] objectToByteArray(Object o, byte[] arrayByte) throws IllegalArgumentException, IllegalAccessException, Exception {
        ObjectSerializer serializer = new ObjectSerializer(o);
        arrayByte = serializer.Serialize();

        return arrayByte;
    }

    public static byte[] convertToByteArray(char value,byte[] arrayByte) {
        byte[] bytes = new byte[2];
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.putChar(value);
        return buffer.array();
    }

    public static byte[] convertToByteArray(long value,byte[] arrayByte) {

        byte[] bytes = new byte[8];
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.putLong(value);
        return buffer.array();
    }

    public static byte[] convertToByteArray(short value,byte[] arrayByte) {

        byte[] bytes = new byte[2];
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.putShort(value);
        return buffer.array();
    }

    public static byte[] convertToByteArray(float value,byte[] arrayByte) {
        byte[] bytes = new byte[4];
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.putFloat(value);
        return buffer.array();
    }

    public static byte[] convertToByteArray(double value,byte[] arrayByte) {
        byte[] bytes = new byte[8];
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.putDouble(value);
        return buffer.array();
    }

    /*
     *
     *
     *Methods Convert ByteArrat to Object (Int,Bool,String,Class)
     *
     */
    // ByteArray to Integer
    public static int ByteArrayToInt(byte[] bytes) {
        return bytes[0] << 56 | (bytes[1] & 0xFF) << 48 | (bytes[2] & 0xFF) << 40 | (bytes[3] & 0xFF) << 32 | (bytes[4] & 0xFF) << 24 | (bytes[5] & 0xFF) << 16 | (bytes[6] & 0xFF) << 8 | (bytes[7] & 0xFF);
    }

    // Boolean
    // ByteArray to Bool
    public static boolean ByteArrayToBool(byte[] bytes) {

        boolean output = (bytes[0] != 0);
        return output;
    }

    // String
    // ByteArray to String
    public static String ByteArrayToString(byte[] bytes) {
        return new String(bytes);
    }

    public static long convertToLong(byte[] array) {
        ByteBuffer buffer = ByteBuffer.wrap(array);
        return buffer.getLong();
    }

    public static short convertToShort(byte[] array) {
        ByteBuffer buffer = ByteBuffer.wrap(array);
        return buffer.getShort();
    }

    public static char convertToCharacter(byte[] array) {
        ByteBuffer buffer = ByteBuffer.wrap(array);
        return buffer.getChar();
    }

    public static double convertToDouble(byte[] array) {
        ByteBuffer buffer = ByteBuffer.wrap(array);
        return buffer.getDouble();
    }

    public static float convertToFloat(byte[] array) {
        ByteBuffer buffer = ByteBuffer.wrap(array);
        return buffer.getFloat();
    }
//    public static Object ByteArrayToObject(byte[] bytes) {
//        ObjectSerializer serializer = new ObjectSerializer(bytes);
//        try {
//            return serializer.DeSerialize();
//        } catch (Exception ex) {
//            Logger.getLogger(CentralSerializer.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
}
