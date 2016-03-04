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
         public static Object GetValue(byte[] decodedBytes, string name, string type) {

        byte[] bytes = new byte[0];
        byte[] ValueSizebytes = new byte[8];
        bytes = push(bytes, CentralSerializer.convertToByteArray(name.Length, push(bytes, CentralSerializer.convertToByteArray(GetFieldCode(type),new byte[0]))));
        //Console.WriteLine(Arrays.toString(bytes));
       Array.Copy(decodedBytes, indexOf(decodedBytes, bytes) + 9, ValueSizebytes, 0, ValueSizebytes.Length);
       // System.out.println(Arrays.toString(ValueSizebytes));
        int ValueLenght = CentralSerializer.convertToInt(ValueSizebytes);
        byte[] Valuebytes = new byte[ValueLenght];
        int addNumber = 17;
        Array.Copy(decodedBytes, indexOf(decodedBytes, bytes) + addNumber + name.Length, Valuebytes, 0, Valuebytes.Length);
        return Helper.GetFieldValue(type.ToString(), Valuebytes);
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
                case 0x13:
                    return "System.Byte";
                case 0x02:
                case 0x11:
                    return "System.Int32";
                case 0x03:
                    return "System.String";
                case 0x04:
                case 0x10:
                    return "System.Boolean";
                case 0x05:
                case 0x12:
                    return "System.Char";
                case 0x06:
                case 0x14:
                    return "System.Long";
                case 0x07:
                case 0x15:
                    return "System.Short";
                case 0x08:
                case 0x16:
                    return "System.Single";
                case 0x09:
                case 0x17:
                    return "System.Double";
                default:
                    return "System.Object";
            }

        }
        
        public static bool isWrapperType(string clazz) {
            return getWrapperTypes().Contains(clazz);
    }

    private static List<string> getWrapperTypes() {
        List<string> ret = new List<string>();
        ret.Add("System.String");
        
        return ret;
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
                    return CentralSerializer.convertToByteArray(Convert.ToInt16(o), bytes);
                case "float":
                case "System.Single":
                    return CentralSerializer.convertToByteArray((float)o, bytes);
                case "System.Double":
                    return CentralSerializer.convertToByteArray((double)o, bytes);
            }
            return null;
        }
        public static sbyte[] toSignedByteArray(byte[] unsigned)
        {
            sbyte[] signed = new sbyte[unsigned.Length];
            Buffer.BlockCopy(unsigned, 0, signed, 0, unsigned.Length);
            return signed;
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
                    return (null == o) ? "" : Char.ConvertFromUtf32(CentralSerializer.convertToCharacter(o));
                case "System.Long":
                case "System.Int64":
                    return (null == o) ? 0 : CentralSerializer.convertToLong(o);
                case "System.Short":
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
        public static byte[] pop(byte[] array)
        {
            byte[] longer = new byte[array.Length - 1];
            Array.Copy(array, 1, longer, 0, longer.Length);
            return longer;
        }
        public static byte[] pop(byte[] array, byte[] pop)
        {
            byte[] longer = new byte[array.Length - pop.Length];
            Array.Copy(array, pop.Length, longer, 0, longer.Length);
            // System.arraycopy(push, 0, longer, array.length, push.length);

            return longer;
        }
        public static bool CheckOptional(byte[] decodedBytes, byte[] newFieldByte)
        {
            byte[] NameSize = new byte[8];
            bool found = false;
            Array.Copy(newFieldByte, 1, NameSize, 0, NameSize.Length);
            byte[] Name = new byte[CentralSerializer.convertToInt(NameSize)];
            if (indexOf(decodedBytes, NameSize) != -1)
            {
                Array.Copy(newFieldByte, 17, Name, 0, Name.Length);
                byte[] Name2 = new byte[CentralSerializer.convertToInt(NameSize)];
                Array.Copy(decodedBytes, indexOf(decodedBytes, NameSize) + 16, Name2, 0, Name.Length);
                if (Array.Equals(Name, Name2))
                {
                    found = true;
                }
            }
            return found;
        }
        public static int indexOf(byte[] outerArray, byte[] smallerArray)
        {
            for (int i = 0; i < outerArray.Length - smallerArray.Length + 1; ++i)
            {
                bool found = true;
                for (int j = 0; j < smallerArray.Length; ++j)
                {
                    if (outerArray[i + j] != smallerArray[j])
                    {
                        found = false;
                        break;
                    }
                }
                if (found)
                {
                    return i;
                }
            }
            return -1;
        }
        public static byte[] GetFieldBytes(byte[] value)
        {

            try
            {
                byte[] FieldNameLenghtbytes = new byte[8];
                byte[] FieldValueLenghtbytes = new byte[8];
                Array.Copy(value, 2, FieldNameLenghtbytes, 0, FieldNameLenghtbytes.Length);
                Array.Copy(value, 10, FieldValueLenghtbytes, 0, FieldValueLenghtbytes.Length);
                byte[] bytes = new byte[18 + CentralSerializer.convertToInt(FieldNameLenghtbytes) + CentralSerializer.convertToInt(FieldValueLenghtbytes)];
                Array.Copy(value, 0, bytes, 0, bytes.Length);
                return bytes;
            }
            catch (Exception e)
            {
                return new byte[0];
            }

        }
    }
}
