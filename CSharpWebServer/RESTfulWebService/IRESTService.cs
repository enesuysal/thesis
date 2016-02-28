using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace RESTfulWebService
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "IRESTService" in both code and config file together.
    [ServiceContract]
    public interface IRESTService
    {
        [OperationContract]
        [WebInvoke(Method = "GET", ResponseFormat = WebMessageFormat.Json,  
                                   BodyStyle = WebMessageBodyStyle.Bare,
                                   UriTemplate = "GetResult/{value}")]
        string GetResult(string value);
    }
}
