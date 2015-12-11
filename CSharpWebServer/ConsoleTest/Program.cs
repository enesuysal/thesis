using CSharpWebServer;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ConsoleTest
{
    class Program
    {
        static void Main(string[] args)
        {
            //Object Class TEst
        Test testObject = new Test(1, "Test");
        Message msg1 = new Message(testObject);
       
        //Deserialize
        Console.WriteLine( Receiver.GetMessage(msg1.Seriliaze()).GetType());
        CallWebServerClient.CallWebServerClient service = new CallWebServerClient.CallWebServerClient();
        Test newTestObject = (Test)service.GetResult(msg1.Seriliaze()); 
        // Console.WriteLine(newTestObject.getTestName());
       
         
// Test for String
        String test = "fdfdfd"; 
        Message msg2 = new Message(test);
        //Deserialize
        Console.WriteLine( Receiver.GetMessage(msg2.Seriliaze()).GetType());
        String newTestString = (String)service.GetResult(msg2.Seriliaze());
         Console.WriteLine(newTestString);
         
         
          
// Test for Int
        int testInt = 55; 
        Message msg3 = new Message(testInt);
        //Deserialize
        Console.WriteLine( Receiver.GetMessage(msg3.Seriliaze()).GetType());
        int newTestInt = (int)service.GetResult(msg3.Seriliaze());
         Console.WriteLine(newTestInt);
// Test for Bool
        Boolean testBool = true; 
        Message msg4 = new Message(testBool);
        //Deserialize
        Console.WriteLine( Receiver.GetMessage(msg4.Seriliaze()).GetType());
        Boolean newTestBool = (Boolean)service.GetResult(msg4.Seriliaze());
         Console.WriteLine(newTestBool);
         Console.ReadLine();
        }
    }
}
