using CSharpWebServer;
using CSharpWebServer.DOM;
using CSharpWebServer.ist.enesuysal.thesis;
using CSharpWebServer.ist.enesuysal.thesis.Tests;
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
            byte test1 = 0x15;  
            int test2 = 10;
            string test3 = "Deneme";  
            bool test4 = true;
            char test5 = 'a'; 
            long test6 = 4666; 
            short test7 = 4666; 
            float test8 = 4666f;
            double test9 = 34.4;
            Byte test1_1 = 0x2;
            Int64 test2_1 = 10;
            Boolean test4_1 = false;
            Char test5_1 = 'f';
            Double test9_1 = 34.4;
            TestSerial tt = new TestSerial();
            Message msg = new Message(tt);
            //Console.WriteLine(test5);
            string msgToSend = msg.Seriliaze();
            //string msgToSend = "BQAAAAAAAAAAAAAAAgAAAAIAYQ==";
            //Console.WriteLine(msgToSend);
            ComplianceTest complience = new ComplianceTest();
            complience.GetMessage(msgToSend);
            Console.ReadLine();
        }

    }
}
