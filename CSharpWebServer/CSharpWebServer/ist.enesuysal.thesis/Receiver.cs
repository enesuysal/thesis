using CSharpWebServer.ist.enesuysal.thesis.Annotation;
using CSharpWebServer.ist.enesuysal.thesis.Tests;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text;

namespace CSharpWebServer.ist.enesuysal.thesis
{
    public class Receiver
    {
        public List<MyMethod> knownMethods = null;
        public Receiver()
        {
            Type myType = (typeof(Receiver));
            // Get the public methods.
            MethodInfo[] myArrayMethodInfo = myType.GetMethods();
            knownMethods = new List<MyMethod>();
            for (int i = 0; i < myArrayMethodInfo.Length; i++)
            {

                object[] attributes = myArrayMethodInfo[i].GetCustomAttributes(typeof(AvaliableMethod), true);
                if (attributes.Length == 1)
                {

                    ParameterInfo o = myArrayMethodInfo[i].GetParameters()[0];
                    if (o.ParameterType.IsPrimitive || Helper.isWrapperType(o.ParameterType.ToString()))
                    {
                        knownMethods.Add(new MyMethod());
                        knownMethods[knownMethods.Count - 1].myfields = CentralSerializer.convertToByteArray(Helper.GetFieldCode(o.ParameterType.ToString()), new byte[0]);

                    }
                    else
                    {
                        Object objectInstance = Activator.CreateInstance(o.ParameterType);
                        FieldInfo[] fields = objectInstance.GetType().GetFields(); // Obtain all fields
                        knownMethods.Add(new MyMethod());
                        knownMethods[knownMethods.Count - 1].methodName = o.ParameterType.ToString();
                        byte[] fieldbytes = new byte[0];
                        knownMethods[knownMethods.Count - 1].myfields = new byte[0];
                        foreach (var field in fields) // Loop through fields
                        {
                            object[] Fattributes = field.GetCustomAttributes(typeof(Mandatory), true);
                            if (Fattributes.Length == 1)
                            {
                                fieldbytes = CentralSerializer.serializePrimitive(field.FieldType.FullName, field.Name, true, field.GetValue(objectInstance), new byte[0]);

                            }
                            else
                            {
                                fieldbytes = CentralSerializer.serializePrimitive(field.FieldType.FullName, field.Name, false, field.GetValue(objectInstance), new byte[0]);
                            }
                            knownMethods[knownMethods.Count - 1].myfields = Helper.push(knownMethods[knownMethods.Count - 1].myfields, fieldbytes);
                        }
                    }

                }
            }

        }
        public void createPrimitive(byte[] bytes)
        {
            //Deserialize and Print
            //System.out.println("ss" + Arrays.toString(bytes));
            String type = (Helper.GetFieldType(bytes[0])); //FieldType
            int FieldNameLength = 0;
            byte[] FieldNameLengthByte = new byte[8];
            Array.Copy(bytes, 1, FieldNameLengthByte, 0, FieldNameLengthByte.Length);
            String fieldName = "";
            byte[] FieldNameByte = new byte[FieldNameLength];
            Array.Copy(bytes, 9, FieldNameByte, 0, FieldNameByte.Length);
            if (FieldNameLength > 0)
            {
                fieldName = CentralSerializer.convertToString(bytes);
            }
            //Field hasValue
            byte[] FieldValueByte = new byte[bytes.Length - (17 + FieldNameLength)];
            Array.Copy(bytes, 17 + FieldNameLength, FieldValueByte, 0, FieldValueByte.Length);

            PrintObject(Helper.GetFieldValue(type, FieldValueByte), type);
        }

        public void createObject(String className, byte[] decodedBytes)
        {
            //Class c = Class.forName(className);
            //Object obj = c.newInstance();
            //Field[] methods = c.getFields();
            //for (Field f : methods) {
            //    f.set(obj, Helper.GetValue(decodedBytes, f.getName(), f.getType()));

            //}

            Type t = Type.GetType(className);
            object instance = Activator.CreateInstance(t);
            FieldInfo[] fields = t.GetFields(); // Obtain all fields
            foreach (var field in fields) // Loop through fields
            {
                string name = field.Name; // Get string name
                object temp = field.GetValue(instance); // Get value
                field.SetValue(instance, Helper.GetValue(decodedBytes, field.Name, field.FieldType.FullName)); //.set(obj, Helper.GetValue(decodedBytes, f.getName(), f.getType()));

            }
            PrintObject(instance);

        }

        public void PrintObject(Object o)
        {
            Type type = o.GetType(); // Get type pointer
            FieldInfo[] fields = type.GetFields(); // Obtain all fields
           
            foreach (var field in fields) // Loop through fields
            {
                string name = field.Name; // Get string name
                object temp = field.GetValue(o); // Get value
                Console.WriteLine("TYPE: " + field.FieldType.FullName);
                Console.WriteLine("Name: " + name);
                Console.WriteLine("Value: " + temp);
            }
        }

        public void PrintObject(Object o, string type)
        {
            Console.WriteLine("TYPE: " + type);
            Console.WriteLine("Value: " + o);
        }
        [AvaliableMethod]
        public void MakeObjectA(int test)
        {

        }

        [AvaliableMethod]
        public void MakeObjectA(byte test)
        {

        }

        [AvaliableMethod]
        public void MakeObjectA(Char test)
        {

        }

        [AvaliableMethod]
        public void MakeObjectA(long test)
        {

        }

        [AvaliableMethod]
        public void MakeObjectA(short test)
        {

        }

        [AvaliableMethod]
        public void MakeObjectA(float test)
        {

        }

        [AvaliableMethod]
        public void MakeObjectA(double test)
        {

        }

        [AvaliableMethod]
        public void MakeObjectA(bool test)
        {
        }


        [AvaliableMethod]
        public void MakeObjectB(string test)
        {
        }


        [AvaliableMethod]
        public void MakeObjectB(short test)
        {
        }


        [AvaliableMethod]
        public void MakeObjectB(double test)
        {
        }

        [AvaliableMethod]
        public void MakeObjectB(float test)
        {
        }

        [AvaliableMethod]
        public void MakeObjectC(Test1 test)
        {
        }

        [AvaliableMethod]
        public void MakeObjectC(Test2 test)
        {
        }

        [AvaliableMethod]
        public void MakeObjectC(Test3 test)
        {
        }


    }
}
