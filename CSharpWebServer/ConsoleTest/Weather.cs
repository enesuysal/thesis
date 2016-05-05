using CSharpWebServer.ist.enesuysal.thesis;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleTest
{
    public class PersonDetail
    {

      
        public string date = "02/05/2016";
        public String name = "Alex";
        public char gender = 'M';
        public byte age = 55;
        public Boolean isSingle = true;
        public int weight = 100;
        public double height = 160.5;
        public String eyeColor = "Blue";
        public float salary = 133300;
        public String houseNumber = "55";
        public String floorNumber = "45";
        public String street = "Rua Agusta";
        public String city = "Lisbon";
        public String zip = "1170";
        public String country = "Portugal";
        public short countryCode = 100;

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
