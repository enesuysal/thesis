using CSharpWebServer;
using CSharpWebServer.DOM;
using CSharpWebServer.ist.enesuysal.thesis;
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
            String test3 = "Deneme";
            bool test4 = true;
            char test5 = 'a';
            long test6 = 4666;
            short test7 = 12345;//Hata
            float test8 = 4666;
            double test9 = 34.4;
            Byte test1_1 = 0x2;
            Int64 test2_1 = 10;
            Boolean test4_1 = true;
            Char test5_1 = 'a';
            Double test9_1 = 34.4;
            Message msg = new Message(test9_1);
            string msgToSend = msg.Seriliaze();
            ComplianceTest complience = new ComplianceTest();
            complience.GetMessage(msgToSend);
            Console.ReadLine();
        }
    }
}
