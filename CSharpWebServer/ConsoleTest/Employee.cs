using CSharpWebServer.ist.enesuysal.thesis;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text;

namespace ConsoleTest
{
    class Employee
    {
        public String name;
        public char gender;
        public byte age;
        public Boolean isSingle;
        public int weight;
        public double height;
        public String eyeColor;
        public float salary;
        public String houseNumber;
        public String floorNumber;
        public String street;
        public String city;
        public String zip;
        public String country;
        public short countryCode;
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
