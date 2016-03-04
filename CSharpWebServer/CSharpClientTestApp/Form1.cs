using CSharpClientTestApp.CSharpWebServiceReference;
using CSharpClientTestApp.JavaWebServiceReference;
using CSharpWebServer.ist.enesuysal.thesis.Tests;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace CSharpClientTestApp
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }
        /*
         * 
         * 








ExampleClass1
ExampleClass2
ExampleClass3
         */
        public CSharpWebServer.ist.enesuysal.thesis.Message GetMessage(string type)
        {
            CSharpWebServer.ist.enesuysal.thesis.Message msg=null;
            string value = textBox1.Text;
            switch (type)
            {
                case "byte":
                    byte testByte;
                    if (!byte.TryParse(value, out testByte))
                    {
                        throw new Exception("Value is not in correct format");
                    }
                    msg = new CSharpWebServer.ist.enesuysal.thesis.Message(testByte);
                    break;
                case "short":
                    short testShort;
                    if (!short.TryParse(value, out testShort))
                    {
                        throw new Exception("Value is not in correct format");
                    }
                    msg = new CSharpWebServer.ist.enesuysal.thesis.Message(testShort);
                    break;
                case "int":
                    int testInt;
                    if (!int.TryParse(value, out testInt))
                    {
                        throw new Exception("Value is not in correct format");
                    }
                    msg = new CSharpWebServer.ist.enesuysal.thesis.Message(testInt);
                    break;
                case "long":
                    long testLong;
                    if (!long.TryParse(value, out testLong))
                    {
                        throw new Exception("Value is not in correct format");
                    }
                    msg = new CSharpWebServer.ist.enesuysal.thesis.Message(testLong);
                    break;
                case "float":
                    float testFloat;
                    if (!float.TryParse(value, out testFloat))
                    {
                        throw new Exception("Value is not in correct format");
                    }
                    msg = new CSharpWebServer.ist.enesuysal.thesis.Message(testFloat);
                    break;
                case "double":
                    double testDouble;
                    if (!double.TryParse(value, out testDouble))
                    {
                        throw new Exception("Value is not in correct format");
                    }
                    msg = new CSharpWebServer.ist.enesuysal.thesis.Message(testDouble);
                    break;
                case "char":
                    char testChar;
                    if (!char.TryParse(value, out testChar))
                    {
                        throw new Exception("Value is not in correct format");
                    }
                    msg = new CSharpWebServer.ist.enesuysal.thesis.Message(testChar);
                    break;
                case "boolean":
                    bool testBool;
                    if (!bool.TryParse(value, out testBool))
                    {
                        throw new Exception("Value is not in correct format");
                    }
                    msg = new CSharpWebServer.ist.enesuysal.thesis.Message(testBool);
                    break;
                case "ExampleClass1":
                    TestSerial test = new TestSerial();
                    msg = new CSharpWebServer.ist.enesuysal.thesis.Message(test);
                    break;
                case "ExampleClass2":
                    TestSerial2 test2 = new TestSerial2();
                    msg = new CSharpWebServer.ist.enesuysal.thesis.Message(test2);
                    break;
                case "ExampleClass3":
                    TestSerial3 test3 = new TestSerial3();
                    msg = new CSharpWebServer.ist.enesuysal.thesis.Message(test3);
                    break;
                default:
                    msg = new CSharpWebServer.ist.enesuysal.thesis.Message(textBox1.Text);
                    break;
            }
            return msg;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            try
            {

                bool valid = false;
                if (comboBox1.SelectedIndex == -1)
                    valid = false;
                else if (comboBox2.SelectedIndex == -1)
                    valid = false;
                else
                    valid = true;

                if (!valid)
                {
                    textBox2.Text = "Please Select Parameters";
                }
                else
                {
                    Stopwatch stopWatch = new Stopwatch();
                    stopWatch.Start();
                    
                    string msgToSend = GetMessage(comboBox2.SelectedItem.ToString()).Seriliaze();
                    switch (comboBox1.SelectedIndex)
                    {
                        case 0:
                            textBox2.Text = GetJavaWebService(msgToSend);

                            break;
                        case 1:
                            textBox2.Text = GetCSharpWebService(msgToSend);

                            break;
                        case 2:
                            textBox2.Text = GetJavaRestMessage(msgToSend);

                            break;
                        case 3:
                            textBox2.Text = GetCSharpRestMessage(msgToSend).Replace("\\u000d\\u000a", "\r\n");

                            break;
                        default:
                            break;
                    }

                    stopWatch.Stop();
                    // Get the elapsed time as a TimeSpan value.
                    TimeSpan ts = stopWatch.Elapsed;

                    // Format and display the TimeSpan value.
                    string elapsedTime = String.Format("{0:00}:{1:00}:{2:00}.{3:00}",
                        ts.Hours, ts.Minutes, ts.Seconds,
                        ts.Milliseconds / 10);
                   label5.Text = ("Calculation Time " + elapsedTime);




                }

            }
            catch (Exception t)
            {

                textBox2.Text= t.Message;
            }
        }
        public string GetCSharpRestMessage(string message)
        {
            return GetRestMessage("http://csharpserverthesis.azurewebsites.net/restservice.svc/GetResult/" + message, message, "GET");
        }
        public string GetJavaRestMessage(string message)
        {
            return GetRestMessage("http://javatomcatthesis.azurewebsites.net/JavaWebServer/webresources/RESTful", message, "POST");
        }
        //http://www.codeproject.com/Articles/255684/Create-and-Consume-RESTFul-Service-in-NET-Framewor
        public string GetRestMessage(string url, string message, string method)
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
        public string GetJavaWebService(string message)
        {
            JavaWebServiceClient service = new JavaWebServiceClient();
            // Get result from server
            return (service.GetResult(message));

        }

        public string GetCSharpWebService(string message)
        {
            WebServiceSoapClient service = new WebServiceSoapClient();
            // Get result from server
            return ((string)service.GetResult(message));

        }
    }
}
