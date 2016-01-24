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
            for (int i = 0; i < r.knownMethods.Count; i++)
            {

                if (r.knownMethods[i].myfields.Length == 1 && Helper.CheckPrimitive(decodedBytes))
                {
                    //Check Type
                    if (Helper.GetFieldType(decodedBytes[0]) == Helper.GetFieldType(r.knownMethods[i].myfields[0])) //Create New bytearray
                    {

                        r.createPrimitive(decodedBytes);
                    }
                }
                else if (!Helper.CheckPrimitive(decodedBytes))
                {

                    bool found = false;
                    byte[] finalArray = new byte[0];
                    String className = "";

                    while (Helper.GetFieldBytes(r.knownMethods[i].myfields).Length != 0) {
                        found = false;
                        byte[] fieldByte = Helper.GetFieldBytes(r.knownMethods[i].myfields);
                       
                        if (fieldByte[0] == 1) {

                            byte[] newFieldByte = Helper.pop(fieldByte);
                            if (Helper.indexOf(decodedBytes, newFieldByte) != -1) {
                                found = true;
                                className = r.knownMethods[i].methodName;
                               Console.WriteLine("Mandatotory field found @"+ r.knownMethods[i].methodName);
                                finalArray = Helper.push(finalArray, newFieldByte);
                            } else {
                                Console.WriteLine("Mandatotory field is not found");
                                break;
                            }
                        }else {
                            //fieldByte= Helper.pop(fieldByte);
                            byte[] newFieldByte = Helper.pop(fieldByte);
                            //System.out.println(Arrays.toString(decodedBytes));
                            //System.out.println(Arrays.toString(fieldByte));
                            if (Helper.indexOf(decodedBytes, newFieldByte) != -1) {
                                found = true;
                                className = r.knownMethods[i].methodName;
                                Console.WriteLine("Optional field found with field name and value equlity @"+ r.knownMethods[i].methodName);
                                finalArray = Helper.push(finalArray, newFieldByte);
                            } else {

                                if (Helper.CheckOptional(decodedBytes, newFieldByte)) {
                                    Console.WriteLine("Optional field found with field name equlity @"+ r.knownMethods[i].methodName);
                                   // Console.WriteLine(Array.T.toString(newFieldByte));
                                    className = r.knownMethods[i].methodName;
                                    finalArray = Helper.push(finalArray, newFieldByte);
                                    
                                    found = true;
                                } else {
                                    Console.WriteLine("Optional field is not found");
                                   break;
                                }
                            }
                        }
                        r.knownMethods[i].myfields = Helper.pop(r.knownMethods[i].myfields, fieldByte);
                    }
                    if (found) {
                       //Console.WriteLine(Arrays.toString(finalArray));
                        r.createObject(className, finalArray);
                         break;
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
