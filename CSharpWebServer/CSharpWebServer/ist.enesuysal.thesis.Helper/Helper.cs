using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace CSharpWebServer.ist.enesuysal.thesis
{
    public class Helper
    {
        public static byte[] push(byte[] array, byte[] push)
        {
            byte[] longer = new byte[array.Length + push.Length];
            Array.Copy(array, 0, longer, 0, array.Length);
            Array.Copy(push, 0, longer, array.Length, push.Length);
            return longer;
        }

        public static bool CheckPrimitive(byte[] decodedBytes)
        {
            byte[] NameLenghtSize = new byte[8];
            Array.Copy(decodedBytes, 1, NameLenghtSize, 0, NameLenghtSize.Length);
            byte[] ValueLenghtSize = new byte[8];
            Array.Copy(decodedBytes, 9, ValueLenghtSize, 0, ValueLenghtSize.Length);
            return (decodedBytes.Length == (CentralSerializer.convertToInt(NameLenghtSize) + 17 + CentralSerializer.convertToInt(ValueLenghtSize)));
        }
        public static String GetFieldType(byte type)
        {

            switch (type)
            {
                case 0x01:
                    return "System.Byte";
                case 0x02:
                    return "System.Int32";
                case 0x03:
                    return "System.String";
                case 0x04:
                    return "System.Boolean";
                case 0x05:
                    return "System.Char";
                case 0x06:
                    return "System.Long";
                case 0x07:
                    return "System.Short";
                case 0x08:
                    return "System.Single";
                case 0x09:
                    return "System.Double";
                case 0x10:
                    return "class java.lang.Boolean";
                case 0x11:
                    return "class java.lang.Integer";
                case 0x12:
                    return "class java.lang.Character";
                case 0x13:
                    return "class java.lang.Byte";
                case 0x14:
                    return "class java.lang.Long";
                case 0x15:
                    return "class java.lang.Short";
                case 0x16:
                    return "class java.lang.Float";
                case 0x17:
                    return "class java.lang.Double";
                default:
                    return "class java.lang.Object";
            }

        }
        public static byte GetFieldCode(String o)
        {
            byte fieldTypeBytes = (byte)0x00;
            switch (o)
            {
                case "System.Byte":
                    fieldTypeBytes = (byte)0x01;
                    break;
                case "System.Int32":
                    fieldTypeBytes = (byte)0x02;
                    break;
                case "System.String":
                    fieldTypeBytes = (byte)0x03;
                    break;
                case "System.Boolean":
                    fieldTypeBytes = (byte)0x04;
                    break;
                case "System.Char":
                    fieldTypeBytes = (byte)0x05;
                    break;
                case "System.Long":
                case "System.Int64":
                    fieldTypeBytes = (byte)0x06;
                    break;
                case "System.Int16":
                case "System.Short":
                    fieldTypeBytes = (byte)0x07;
                    break;
                case "float":
                case "System.Single":
                    fieldTypeBytes = (byte)0x08;
                    break;
                case "double":
                case "System.Double":
                    fieldTypeBytes = (byte)0x09;
                    break;
                case "java.lang.Boolean":
                    fieldTypeBytes = (byte)0x10;
                    break;
                case "java.lang.Integer":
                    fieldTypeBytes = (byte)0x11;
                    break;
                case "java.lang.Character":
                    fieldTypeBytes = (byte)0x12;
                    break;
                case "java.lang.Byte":
                    fieldTypeBytes = (byte)0x13;
                    break;
                case "java.lang.Long":
                    fieldTypeBytes = (byte)0x14;
                    break;
                case "java.lang.Short":
                    fieldTypeBytes = (byte)0x15;
                    break;
                case "java.lang.Float":
                    fieldTypeBytes = (byte)0x16;
                    break;
                case "java.lang.Double":
                    fieldTypeBytes = (byte)0x17;
                    break;
            }
            return fieldTypeBytes;

        }
        
        public static byte[] GetFieldValueByte(string type, object o, byte[] bytes)
        {
            switch (type)
            {
                case "System.Byte":
                    return CentralSerializer.convertToByteArray((byte)o, bytes);
              
                case "System.Int32":
                    return CentralSerializer.convertToByteArray((int)o, bytes);
                case "System.String":
                    return CentralSerializer.convertToByteArray((String)o, bytes);
                case "boolean":
                case "System.Boolean":
                    return CentralSerializer.convertToByteArray((bool)o, bytes);
                case "char":
                case "System.Char":
                    return CentralSerializer.convertToByteArray((char)o, bytes);
                case "long":
                case "System.Long":
                case "System.Int64":
                    return CentralSerializer.convertToByteArray((long)o, bytes);
                case "short":
                case "System.Int16":
                case "System.Short":
                    return CentralSerializer.convertToByteArray((short)o, bytes);
                case "float":
                case "System.Single":
                    return CentralSerializer.convertToByteArray((float)o, bytes);
                case "System.Double":
                    return CentralSerializer.convertToByteArray((double)o, bytes);
            }
            return null;
        }

        public static Object GetFieldValue(String type, byte[] o)
        {

            switch (type)
            {
                case "System.Byte":
                    return (null == o) ? 0 : CentralSerializer.convertToByte(o);
                case "System.Int32":
                    return (null == o) ? 0 : CentralSerializer.convertToInt(o);
               case "System.String":
                    return (null == o) ? null : CentralSerializer.convertToString(o);
                case "boolean":
                case "System.Boolean":
                    return (null == o) ? false : CentralSerializer.convertToBool(o);
                case "char":
                case "System.Char":
                    return (null == o) ? 0 : CentralSerializer.convertToCharacter(o);
                case "long":
                case "System.Int64":
                    return (null == o) ? 0 : CentralSerializer.convertToLong(o);
                case "short":
                case "System.Int16":
                    return (null == o) ? 0 : CentralSerializer.convertToShort(o);
                case "float":
                case "System.Single":
                    return (null == o) ? 0 : CentralSerializer.convertToFloat(o);
                case "double":
                case "System.Double":
                    return (null == o) ? 0 : CentralSerializer.convertToDouble(o);
            }
            return null;

        }
    }
}
