using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;


namespace CSharpWebServer.ist.enesuysal.thesis
{
    public class CentralSerializer
    {
        public static byte[] serializePrimitive(Type type, string fieldName, Object fieldValue, byte[] item)
        {

            //FieldType
            item = convertToByteArray(Helper.GetFieldCode(type.ToString()), item);
            //append FieldNameLenght
            item = convertToByteArray(fieldName.Length, item);
            //Append ValueLenght
            item = convertToByteArray(Helper.GetFieldValueByte(type.ToString(), fieldValue, new byte[0]).Length, item);// Helper.Helper.GetFieldValueByte(type.ToString(), fieldValue, new byte[0])
            //Append FieldName
            item = convertToByteArray(fieldName, item);
            //Append Value
            item = Helper.GetFieldValueByte(type.ToString(), fieldValue, item);
            return item;

        }

        public static byte[] serializePrimitive(Type type, int fieldValue, byte[] item)
        {
            return serializePrimitive(type, "", fieldValue, item);
        }

        public static byte[] serializePrimitive(Type type, bool fieldValue, byte[] item)
        {
            return serializePrimitive(type, "", fieldValue, item);
        }

        public static byte[] serializePrimitive(Type type, long fieldValue, byte[] item)
        {
            return serializePrimitive(type, "", fieldValue, item);
        }

        public static byte[] serializePrimitive(Type type, float fieldValue, byte[] item)
        {
            return serializePrimitive(type, "", fieldValue, item);
        }

        public static byte[] serializePrimitive(Type type, double fieldValue, byte[] item)
        {
            return serializePrimitive(type, "", fieldValue, item);
        }

        public static byte[] convertToByteArray(string input, byte[] item)
        {
            byte[]  arrayByte = Encoding.UTF8.GetBytes(input);
            return Helper.push(item, arrayByte);
        }

        public static byte[] convertToByteArray(byte data, byte[] item)
        {
            return Helper.push(item, new byte[] { data });
        }

        // Primitive Int to ByteArray

        public static byte[] convertToByteArray(int intValue, byte[] item)
        {
            byte[] arrayByte = new byte[8];
            arrayByte[0] = (byte)(intValue >> 56);
            arrayByte[1] = (byte)(intValue >> 48);
            arrayByte[2] = (byte)(intValue >> 40);
            arrayByte[3] = (byte)(intValue >> 32);
            arrayByte[4] = (byte)(intValue >> 24);
            arrayByte[5] = (byte)(intValue >> 16);
            arrayByte[6] = (byte)(intValue >> 8);
            arrayByte[7] = (byte)intValue;
            return Helper.push(item, arrayByte);
        }

        // Primitive Bool to ByteArray
        public static byte[] convertToByteArray(bool input, byte[] item)
        {
            throw new NotImplementedException();
        }




        public static byte[] convertToByteArray(char value, byte[] item)
        {
            throw new NotImplementedException();
        }

        public static byte[] convertToByteArray(long value, byte[] item)
        {

            throw new NotImplementedException();
        }

        public static byte[] convertToByteArray(short value, byte[] item)
        {

            throw new NotImplementedException();
        }

        public static byte[] convertToByteArray(float value, byte[] item)
        {
            throw new NotImplementedException();
        }

        public static byte[] convertToByteArray(double value, byte[] item)
        {
           byte[] bytes = new byte[8];
           bytes =  BitConverter.GetBytes(value);
           return Helper.push(item, bytes);
        }


        public static byte convertToByte(byte[] data)
        {
            return data[0];
        }


        // ByteArray to Integer

        public static int convertToInt(byte[] bytes)
        {
            throw new NotImplementedException();
        }

        // Boolean
        // ByteArray to Bool
        public static bool convertToBool(byte[] bytes)
        {
            throw new NotImplementedException();
        }

        // String
        // ByteArray to String
        public static String convertToString(byte[] bytes)
        {
            throw new NotImplementedException();
        }

        public static long convertToLong(byte[] array)
        {
            throw new NotImplementedException();
        }

        public static short convertToShort(byte[] array)
        {
            throw new NotImplementedException();
        }

        public static char convertToCharacter(byte[] array)
        {
            throw new NotImplementedException();
        }

        public static double convertToDouble(byte[] array)
        {
            throw new NotImplementedException();
        }

        public static float convertToFloat(byte[] array)
        {
            throw new NotImplementedException();
        }

    }
}
