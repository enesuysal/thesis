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
        public static byte GetFieldCode(String o)
        {
            byte fieldTypeBytes = (byte)0x00;
            switch (o)
            {
                case "byte":
                    fieldTypeBytes = (byte)0x01;
                    break;
                case "int":
                    fieldTypeBytes = (byte)0x02;
                    break;
                case "java.lang.String":
                    fieldTypeBytes = (byte)0x03;
                    break;
                case "boolean":
                    fieldTypeBytes = (byte)0x04;
                    break;
                case "char":
                    fieldTypeBytes = (byte)0x05;
                    break;
                case "long":
                    fieldTypeBytes = (byte)0x06;
                    break;
                case "short":
                    fieldTypeBytes = (byte)0x07;
                    break;
                case "float":
                    fieldTypeBytes = (byte)0x08;
                    break;
                case "double":
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
    }
}
