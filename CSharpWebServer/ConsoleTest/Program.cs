﻿using ConsoleTest.WebServiceReference;
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

            TestSerial test = new TestSerial();
            Message msg = new Message(test);
            string msgToSend = msg.Seriliaze();
            JavaWebServiceClient service = new JavaWebServiceClient();
            // Show result from server
            Console.WriteLine(" Result From Server: \n" + service.GetResult(msgToSend));
            Console.ReadLine();
        }

    }
}
