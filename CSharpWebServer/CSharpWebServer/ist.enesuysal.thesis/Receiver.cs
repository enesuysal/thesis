using CSharpWebServer.ist.enesuysal.thesis.Annotation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text;

namespace CSharpWebServer.ist.enesuysal.thesis
{
    public class Receiver
    {
        public List<MyMethod>  knownMethods = null;
        public Receiver()
        {
            Type myType =(typeof(Receiver));
        // Get the public methods.
           MethodInfo[] myArrayMethodInfo = myType.GetMethods();
           knownMethods = new List<MyMethod>();
           for (int i = 0; i < myArrayMethodInfo.Length; i++)
           {

               object[] attributes = myArrayMethodInfo[i].GetCustomAttributes(typeof(AvaliableMethod), true);
              if(attributes.Length==1)
                {
                   
                    ParameterInfo o = myArrayMethodInfo[i].GetParameters()[0];
                    if (o.ParameterType.IsPrimitive)
                    {
                        knownMethods.Add(new MyMethod());
                        knownMethods[knownMethods.Count-1].myfields = CentralSerializer.convertToByteArray(Helper.GetFieldCode(o.ParameterType.ToString()), new byte[0]);

                    }
                    else
                    {

                    }
                   
                }
            }
          
        }
        public void createPrimitive(byte[] bytes)   {
        //Deserialize and Print
        //System.out.println("ss" + Arrays.toString(bytes));
        String type = (Helper.GetFieldType(bytes[0])); //FieldType
        int FieldNameLength = 0;
        byte[] FieldNameLengthByte = new byte[8];
       Array.Copy(bytes, 1, FieldNameLengthByte, 0, FieldNameLengthByte.Length);
        String fieldName = "";
        byte[] FieldNameByte = new byte[FieldNameLength];
        Array.Copy(bytes, 9, FieldNameByte, 0, FieldNameByte.Length);
        if (FieldNameLength > 0) {
            fieldName = CentralSerializer.convertToString(bytes);
        }
        //Field hasValue
        byte[] FieldValueByte = new byte[bytes.Length - (17 + FieldNameLength)];
        Array.Copy(bytes, 17 + FieldNameLength, FieldValueByte, 0, FieldValueByte.Length);
        Console.WriteLine("Value " + Helper.GetFieldValue(type, FieldValueByte));
        PrintObject(Helper.GetFieldValue(type, FieldValueByte));
    }




    public void PrintObject(Object o) {
        Console.WriteLine("Found Object");
        StringBuilder result = new StringBuilder();
        String newLine = System.Environment.NewLine;

        result.Append(o.GetType().Name);
        result.Append(" Object {");
         

        ////determine fields declared in this class only (no fields of superclass)
        //Field[] fields = o.getClass().getDeclaredFields();

        ////print field names paired with their values
        //for (Field field : fields) {
        //    result.append("  ");
        //    try {
        //        result.append(field.getName());
        //        result.append(": ");
        //        //requires access to private field:
        //        result.append(field.get(o));
        //    } catch (IllegalAccessException ex) {
        //        //System.out.println(ex);
        //    }
            result.Append(newLine);
       // }
        result.Append("}");

        Console.WriteLine(result.ToString());
    }
    [AvaliableMethod]
    public void MakeObjectA(int test) {

    }

    [AvaliableMethod]
    public void MakeObjectA(byte test)
    {

    }

    [AvaliableMethod]
    public void MakeObjectA(char test)
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
    public void MakeObjectB(Byte test)
    {
    }

    [AvaliableMethod]
    public void MakeObjectB(string test)
    {
    }

    [AvaliableMethod]
    public void MakeObjectB(int test)
    {
    }

    [AvaliableMethod]
    public void MakeObjectB(Boolean test)
    {
    }

    [AvaliableMethod]
    public void MakeObjectB(char test)
    {
    }

    [AvaliableMethod]
    public void MakeObjectB(short test)
    {
    }

    [AvaliableMethod]
    public void MakeObjectB(long test)
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

    // [AvaliableMethod]
    //public void MakeObjectC(Test1 test) {
    //}

    // [AvaliableMethod]
    //public void MakeObjectC(Test2 test) {
    //}

    // [AvaliableMethod]
    //public void MakeObjectC(Test3 test) {
    //}


    }
}
