using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text;

namespace CSharpWebServer.ist.enesuysal.thesis.Tests
{
    public class TestSerial
    {
        // Public primitive with default value
        public int version = 56;
        // Public primitive with no value
        public String ekes2 = "affff";
        // Public primitive with default value 
        public bool deneme;

        public bool test = false;
        public byte[] Serialize()
        {
            byte[] arrayResult = new byte[0];
            Type type = this.GetType(); // Get type pointer
            FieldInfo[] fields = type.GetFields(); // Obtain all fields
            foreach (var field in fields) // Loop through fields
            {
                string name = field.Name; // Get string name
                object temp = field.GetValue(this); // Get value
                arrayResult = CentralSerializer.serializePrimitive(field.FieldType.FullName, field.Name, true, temp, arrayResult);


            }

            return arrayResult;
            //System.out.println(result.toString());
        }
    }
}
