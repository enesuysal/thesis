using CSharpWebServer.ist.enesuysal.thesis;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace CSharpWebServer.DOM
{
    public class ComplianceTest
    {
        public void GetMessage(string BASE64String)
        {
            try
            {
                byte[] decodedBytes = Convert.FromBase64String(BASE64String);
                Receiver r = new Receiver();
               


            }
            catch (Exception ex)
            {
                throw ex;
            }
        }
    }
}
