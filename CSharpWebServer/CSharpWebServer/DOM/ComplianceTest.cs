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
            //try
            //{
                byte[] decodedBytes = Convert.FromBase64String(BASE64String);
                Receiver r = new Receiver();
               for (int i = 0; i < r.knownMethods.Count; i++) {

                if (r.knownMethods[i].myfields.Length == 1 && Helper.CheckPrimitive(decodedBytes)) {
                    //Check Type
                    if (decodedBytes[0] == r.knownMethods[i].myfields[0]) //Create New bytearray
                    {
                       
                        r.createPrimitive(decodedBytes);
                    }
                }
                
            }


            //}
            //catch (Exception ex)
            //{
            //    throw ex;
            //}
        }
    }
}
