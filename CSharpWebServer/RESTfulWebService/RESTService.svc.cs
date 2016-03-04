using CSharpWebServer.DOM;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace RESTfulWebService
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "RESTService" in code, svc and config file together.
    // NOTE: In order to launch WCF Test Client for testing this service, please select RESTService.svc or RESTService.svc.cs at the Solution Explorer and start debugging.
    public class RESTService : IRESTService
    {
        public string GetResult(string value)
        {
            WebOperationContext.Current.OutgoingResponse.ContentType = "text/plain";
            try
            {
                ComplianceTest complience = new ComplianceTest();

                return complience.GetMessage(value);
            }
            catch (Exception ex)
            {
                return "Error";
            }
        }
    }
}
