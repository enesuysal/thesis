using CSharpClientTestApp.CSharpWebServiceReference;
using CSharpClientTestApp.JavaWebServiceReference;
using CSharpWebServer.ist.enesuysal.thesis.Tests;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
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

      private void button1_Click(object sender, EventArgs e)
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
                TestSerial test = new TestSerial();
                CSharpWebServer.ist.enesuysal.thesis.Message msg = new CSharpWebServer.ist.enesuysal.thesis.Message(test);
                string msgToSend = msg.Seriliaze();
                switch (comboBox1.SelectedIndex)
                {
                    case 0:
                        textBox2.Text = GetJavaWebService(msgToSend);
                        
                        break;
                    case 1:
                        textBox2.Text = GetCSharpWebService(msgToSend);
                       
                        break;
                     case 2:
                        textBox2.Text = "Java RESTful WebServices";
                        GetRestMessage(msgToSend);
                        break;
                     case 3:
                        textBox2.Text = "C# RESTful WebServices";
                        GetRestMessage(msgToSend);
                        break;
                    default:
                        break;
                }
    



            }
           

        }
        //http://www.codeproject.com/Articles/255684/Create-and-Consume-RESTFul-Service-in-NET-Framewor
        public string GetRestMessage(string message)
        {

            try
            {
                string content;
               // Console.WriteLine("Enter Method:");
                string Method = "GET";//Console.ReadLine();

                //Console.WriteLine("Enter URI:");
                string uri = Console.ReadLine();

                HttpWebRequest req = WebRequest.Create(uri) as HttpWebRequest;
                req.KeepAlive = false;
                req.Method = Method.ToUpper();

                if (("POST,PUT").Split(',').Contains(Method.ToUpper()))
                {
                   
                    content = message;

                    byte[] buffer = Encoding.ASCII.GetBytes(content);
                    req.ContentLength = buffer.Length;
                    req.ContentType = "text/xml";
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
