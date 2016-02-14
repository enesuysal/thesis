using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

using WebServiceUnitTests.WebServiceReference;
using CSharpWebServer.ist.enesuysal.thesis;


namespace WebServiceUnitTests
{
    [TestClass]
    public class UnitTest1
    {
        [TestMethod]
        public void TestMethod1()
        {
            CSharpWebServer.ist.enesuysal.thesis.Tests.TestSerial tt = new TestSerial();
            Message msg = new Message(tt);
            string msgToSend = msg.Seriliaze();
            JavaWebServiceClient service = new JavaWebServiceClient();
            // Show result from server
            Console.WriteLine(" REsult From Server: " + service.GetResult(msgToSend));
            Console.ReadLine();
        }
    }
}
