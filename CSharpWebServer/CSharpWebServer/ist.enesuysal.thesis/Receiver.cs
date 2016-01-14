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
        public MyMethod[] knownMethods = null;
        public Receiver()
        {
            Type myType =(typeof(Receiver));
        // Get the public methods.
           MethodInfo[] myArrayMethodInfo = myType.GetMethods();
   
            foreach (MethodInfo m in myArrayMethodInfo)
            {
                 
                object[] attributes = m.GetCustomAttributes(typeof(AvaliableMethod), true);
              if(attributes.Length==1)
                {
                    ParameterInfo o = m.GetParameters()[0];
                  Console.WriteLine( o.ParameterType
                      );
                   
                }
            }
          
        }

    [AvaliableMethod]
    public void MakeObjectA(int test) {

    }

    [AvaliableMethod]
    public void MakeObjectA(byte test) {

    }

    [AvaliableMethod]
    public void MakeObjectA(char test) {

    }

     [AvaliableMethod]
    public void MakeObjectA(long test) {

    }

     [AvaliableMethod]
    public void MakeObjectA(short test) {

    }

     [AvaliableMethod]
    public void MakeObjectA(float test) {

    }

     [AvaliableMethod]
    public void MakeObjectA(double test) {

    }

     [AvaliableMethod]
    public void MakeObjectA(bool test) {
    }

    [AvaliableMethod]
    public void MakeObjectB(Byte test) {
    }

    [AvaliableMethod]
    public void MakeObjectB(String test) {
    }

     [AvaliableMethod]
    public void MakeObjectB(int test) {
    }

     [AvaliableMethod]
    public void MakeObjectB(Boolean test) {
    }

     [AvaliableMethod]
    public void MakeObjectB(char test) {
    }

     [AvaliableMethod]
    public void MakeObjectB(short test) {
    }

    [AvaliableMethod]
    public void MakeObjectB(long test) {
    }

     [AvaliableMethod]
    public void MakeObjectB(double test) {
    }

     [AvaliableMethod]
    public void MakeObjectB(float test) {
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
