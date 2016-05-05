using CSharpWebServer.ist.enesuysal.thesis.Annotation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;

namespace CSharpWebServer.ist.enesuysal.thesis.Tests
{
    public class Weather1
    {
        [Mandatory]
        public String country = "Portugal";
        [Mandatory]
        public String city = "Lisbon";
       
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
        }
    }
}
