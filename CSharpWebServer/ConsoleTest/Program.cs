using ConsoleTest.WebServiceReference;
using CSharpWebServer;
using CSharpWebServer.DOM;
using CSharpWebServer.ist.enesuysal.thesis;
using CSharpWebServer.ist.enesuysal.thesis.Tests;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Net;
using System.Reflection;
using System.Text;

namespace ConsoleTest
{
    class Program
    {
        static void Main(string[] args)
        {
            Stopwatch stopWatch = new Stopwatch();
            stopWatch.Start();
            for (int i = 0; i < 10; i++)
            {
                Employee emp = new Employee();

                emp.name = "Test Employee " + (i + 1).ToString();
                emp.gender = (i % 2 == 0) ? 'F' : 'M';
                emp.age = Convert.ToByte(20 / 2 + i * 2);
                emp.isSingle = (i % 2 == 0) ? true : false;
                emp.weight = Convert.ToInt32(50);
                emp.height = Convert.ToDouble(186.45);
                emp.eyeColor = "Blue";
                emp.salary = (float)Convert.ToDouble(2500);
                emp.street = "CHRIS NISWANDEE SMALLSYS INC 795 E DRAGRAM TUCSON AZ 85705 USA";
                emp.houseNumber = "795";
                emp.floorNumber = "1";
                emp.city = "TUCSON";
                emp.zip = "85705";
                emp.country = "USA";
                Console.WriteLine(PrintObject(emp));
                string msg = new Message(emp).Seriliaze();
                Console.WriteLine(GetJavaRestMessage(msg));
            }
            stopWatch.Stop();
            // Get the elapsed time as a TimeSpan value.
            TimeSpan ts = stopWatch.Elapsed;

            // Format and display the TimeSpan value.
            string elapsedTime = String.Format("{0:00}:{1:00}:{2:00}.{3:00}",
                ts.Hours, ts.Minutes, ts.Seconds,
                ts.Milliseconds / 10);
            Console.WriteLine("Calculation Time " + elapsedTime);
            Console.ReadLine();
        }

        public static string PrintObject(Object o)
        {
            Type type = o.GetType(); // Get type pointer
            FieldInfo[] fields = type.GetFields(); // Obtain all fields
            StringBuilder sb = new StringBuilder();
            foreach (var field in fields) // Loop through fields
            {
                string name = field.Name; // Get string name
                object temp = field.GetValue(o); // Get value

                sb.AppendLine("TYPE: " + field.FieldType.FullName);
                sb.AppendLine("Name: " + name);
                sb.AppendLine("Value: " + temp);
            }
            return sb.ToString();
        }


        public static string GetJavaRestMessage(string message)
        {
            return GetRestMessage("http://javatomcatthesis.azurewebsites.net/JavaWebServer/webresources/RESTful", message, "POST");
        }
        //http://www.codeproject.com/Articles/255684/Create-and-Consume-RESTFul-Service-in-NET-Framewor
        public static string GetRestMessage(string url, string message, string method)
        {

            try
            {
                string content;
                // Console.WriteLine("Enter Method:");
                string Method = method;//Console.ReadLine();

                //Console.WriteLine("Enter URI:");
                string uri = url;

                HttpWebRequest req = WebRequest.Create(uri) as HttpWebRequest;
                req.KeepAlive = false;
                req.Method = Method.ToUpper();

                if (("POST,PUT").Split(',').Contains(Method.ToUpper()))
                {

                    content = message;

                    byte[] buffer = Encoding.ASCII.GetBytes(content);
                    req.ContentLength = buffer.Length;
                    req.ContentType = "text/plain";
                    Stream PostData = req.GetRequestStream();
                    PostData.Write(buffer, 0, buffer.Length);
                    PostData.Close();
                }

                HttpWebResponse resp = req.GetResponse() as HttpWebResponse;

                Encoding enc = System.Text.Encoding.GetEncoding(1252);
                StreamReader loResponseStream =
                new StreamReader(resp.GetResponseStream(), enc);

                string Response = loResponseStream.ReadToEnd();

                loResponseStream.Close();
                resp.Close();
                return Response;
            }
            catch (Exception ex)
            {
                return (ex.Message.ToString());
            }


        }

    }
}
