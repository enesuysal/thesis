using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace CSharpWebServer.ist.enesuysal.thesis.Helper
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

        internal static object GetFieldValueByte(string p1, object fieldValue, byte[] p2)
        {
            throw new NotImplementedException();
        }
    }
}
