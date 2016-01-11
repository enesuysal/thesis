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
            //HATALI
            //FieldType
            item = convertToByteArray(Helper.Helper.GetFieldCode(type.ToString()), item);
            //append FieldNameLenght
            item = convertToByteArray(fieldName.Length, item);
            //Append ValueLenght
            item = convertToByteArray(24,item);// Helper.Helper.GetFieldValueByte(type.ToString(), fieldValue, new byte[0])
            //Append FieldName
            item = convertToByteArray(fieldName, item);
            //Append Value
          //  item = Helper.Helper.GetFieldValueByte(type.ToString(), fieldValue, item);//convertToByteArray(fieldValue, item);
            return item;

        }

        private static byte[] convertToByteArray(string fieldName, byte[] item)
        {
            throw new NotImplementedException();
        }

        private static byte[] convertToByteArray(int p, byte[] item)
        {
            throw new NotImplementedException();
        }

        private static byte[] convertToByteArray(byte p, byte[] item)
        {
            throw new NotImplementedException();
        }
        public static byte[] serializePrimitive(Type type, byte fieldValue, byte[] item)
        {
            return serializePrimitive(type, "", fieldValue, item);
        }
      
       
       
    }
}
