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
        public static byte[] serializePrimitive(string type, String fieldName, bool isMandatory, Object fieldValue, byte[] item)
        {
            //Append isOptional
            item = convertToByteArray(isMandatory, item);
            //FieldType
            item = convertToByteArray(Helper.GetFieldCode(type.ToString()), item);
            //append FieldNameLenght
            item = convertToByteArray(fieldName.Length, item);
            //Append ValueLenght
            item = convertToByteArray(Helper.GetFieldValueByte(type.ToString(), fieldValue, new byte[0]).Length, item);
            //Append FieldName
            item = convertToByteArray(fieldName, item);
            //Append Value
            item = Helper.GetFieldValueByte(type.ToString(), fieldValue, item);//convertToByteArray(fieldValue, item);
            return item;

        }

        public static byte[] serializePrimitive(Type type, String fieldValue, byte[] item)
        {
            return serializePrimitive(type, "", fieldValue, item);
        }
        public static byte[] serializePrimitive(Type type, byte fieldValue, byte[] item)
        {
            return serializePrimitive(type, "", fieldValue, item);
        }
        public static byte[] serializePrimitive(Type type, int fieldValue, byte[] item)
        {
            return serializePrimitive(type, "", fieldValue, item);
        }
        public static byte[] serializePrimitive(Type type, char fieldValue, byte[] item)
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
            byte[] arrayByte = new byte[1];
            arrayByte[0] = input ? (byte)1 : (byte)0;
            return Helper.push(item, arrayByte);
        }




        public static byte[] convertToByteArray(char value, byte[] item)
        {
            byte[] arrayByte = BitConverter.GetBytes(value).Reverse().ToArray();
            return Helper.push(item, arrayByte);
        }

        public static byte[] convertToByteArray(long value, byte[] item)
        {
            //REverse for Java
            byte[] arrayByte = BitConverter.GetBytes(value).Reverse().ToArray();
            return Helper.push(item, arrayByte);
        }

        public static byte[] convertToByteArray(short value, byte[] item)
        {

            byte[] arrayByte = BitConverter.GetBytes(value).Reverse().ToArray();
            return Helper.push(item, arrayByte);
        }

        public static byte[] convertToByteArray(float value, byte[] item)
        {
            byte[] arrayByte = BitConverter.GetBytes(value).Reverse().ToArray();
            return Helper.push(item, arrayByte);
        }

        public static byte[] convertToByteArray(double value, byte[] item)
        {
           byte[] bytes = new byte[8];
           bytes = BitConverter.GetBytes(value).Reverse().ToArray();
           return Helper.push(item, bytes);
        }


        public static byte convertToByte(byte[] data)
        {
            return data[0];
        }


        // ByteArray to Integer

        public static int convertToInt(byte[] bytes)
        {
            return bytes[0] << 56 | bytes[1] << 48 | bytes[2] << 40 | bytes[3] << 32 | bytes[4] << 24 | (bytes[5] & 0xFF) << 16 | (bytes[6] & 0xFF) << 8 | (bytes[7] & 0xFF);
        }

        // Boolean
        // ByteArray to Bool
        public static bool convertToBool(byte[] bytes)
        {
            bool output = Convert.ToBoolean(bytes[0]);
            return output;
        }

        // String
        // ByteArray to String
        public static String convertToString(byte[] bytes)
        {
            return System.Text.Encoding.UTF8.GetString(bytes);
        }

        public static long convertToLong(byte[] array)
        {
            long output = BitConverter.ToInt64(array.Reverse().ToArray(), 0);
            return output;
        }

        public static short convertToShort(byte[] array)
        {
            short output = BitConverter.ToInt16(array.Reverse().ToArray(), 0);
            return output;
        }

        public static char convertToCharacter(byte[] array)
        {

            Char output = BitConverter.ToChar(array.Reverse().ToArray(), 0);
            return output;
        }

        public static double convertToDouble(byte[] array)
        {
            double output = BitConverter.ToDouble(array.Reverse().ToArray(), 0);
            return output;
        }

        public static float convertToFloat(byte[] array)
        {
            float output = BitConverter.ToSingle(array.Reverse().ToArray(), 0);
            return output;
        }

    }
}
