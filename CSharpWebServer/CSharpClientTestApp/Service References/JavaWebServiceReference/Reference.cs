﻿//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.34209
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace CSharpClientTestApp.JavaWebServiceReference {
    
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ServiceModel.ServiceContractAttribute(Namespace="http://WebServices.thesis.enesuysal.ist/", ConfigurationName="JavaWebServiceReference.JavaWebService")]
    public interface JavaWebService {
        
        // CODEGEN: Generating message contract since element name name from namespace  is not marked nillable
        [System.ServiceModel.OperationContractAttribute(Action="http://WebServices.thesis.enesuysal.ist/JavaWebService/GetResultRequest", ReplyAction="http://WebServices.thesis.enesuysal.ist/JavaWebService/GetResultResponse")]
        CSharpClientTestApp.JavaWebServiceReference.GetResultResponse GetResult(CSharpClientTestApp.JavaWebServiceReference.GetResultRequest request);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://WebServices.thesis.enesuysal.ist/JavaWebService/GetResultRequest", ReplyAction="http://WebServices.thesis.enesuysal.ist/JavaWebService/GetResultResponse")]
        System.Threading.Tasks.Task<CSharpClientTestApp.JavaWebServiceReference.GetResultResponse> GetResultAsync(CSharpClientTestApp.JavaWebServiceReference.GetResultRequest request);
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
    public partial class GetResultRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Name="GetResult", Namespace="http://WebServices.thesis.enesuysal.ist/", Order=0)]
        public CSharpClientTestApp.JavaWebServiceReference.GetResultRequestBody Body;
        
        public GetResultRequest() {
        }
        
        public GetResultRequest(CSharpClientTestApp.JavaWebServiceReference.GetResultRequestBody Body) {
            this.Body = Body;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.Runtime.Serialization.DataContractAttribute(Namespace="")]
    public partial class GetResultRequestBody {
        
        [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false, Order=0)]
        public string name;
        
        public GetResultRequestBody() {
        }
        
        public GetResultRequestBody(string name) {
            this.name = name;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
    public partial class GetResultResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Name="GetResultResponse", Namespace="http://WebServices.thesis.enesuysal.ist/", Order=0)]
        public CSharpClientTestApp.JavaWebServiceReference.GetResultResponseBody Body;
        
        public GetResultResponse() {
        }
        
        public GetResultResponse(CSharpClientTestApp.JavaWebServiceReference.GetResultResponseBody Body) {
            this.Body = Body;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.Runtime.Serialization.DataContractAttribute(Namespace="")]
    public partial class GetResultResponseBody {
        
        [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false, Order=0)]
        public string @return;
        
        public GetResultResponseBody() {
        }
        
        public GetResultResponseBody(string @return) {
            this.@return = @return;
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public interface JavaWebServiceChannel : CSharpClientTestApp.JavaWebServiceReference.JavaWebService, System.ServiceModel.IClientChannel {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public partial class JavaWebServiceClient : System.ServiceModel.ClientBase<CSharpClientTestApp.JavaWebServiceReference.JavaWebService>, CSharpClientTestApp.JavaWebServiceReference.JavaWebService {
        
        public JavaWebServiceClient() {
        }
        
        public JavaWebServiceClient(string endpointConfigurationName) : 
                base(endpointConfigurationName) {
        }
        
        public JavaWebServiceClient(string endpointConfigurationName, string remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public JavaWebServiceClient(string endpointConfigurationName, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public JavaWebServiceClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(binding, remoteAddress) {
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        CSharpClientTestApp.JavaWebServiceReference.GetResultResponse CSharpClientTestApp.JavaWebServiceReference.JavaWebService.GetResult(CSharpClientTestApp.JavaWebServiceReference.GetResultRequest request) {
            return base.Channel.GetResult(request);
        }
        
        public string GetResult(string name) {
            CSharpClientTestApp.JavaWebServiceReference.GetResultRequest inValue = new CSharpClientTestApp.JavaWebServiceReference.GetResultRequest();
            inValue.Body = new CSharpClientTestApp.JavaWebServiceReference.GetResultRequestBody();
            inValue.Body.name = name;
            CSharpClientTestApp.JavaWebServiceReference.GetResultResponse retVal = ((CSharpClientTestApp.JavaWebServiceReference.JavaWebService)(this)).GetResult(inValue);
            return retVal.Body.@return;
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<CSharpClientTestApp.JavaWebServiceReference.GetResultResponse> CSharpClientTestApp.JavaWebServiceReference.JavaWebService.GetResultAsync(CSharpClientTestApp.JavaWebServiceReference.GetResultRequest request) {
            return base.Channel.GetResultAsync(request);
        }
        
        public System.Threading.Tasks.Task<CSharpClientTestApp.JavaWebServiceReference.GetResultResponse> GetResultAsync(string name) {
            CSharpClientTestApp.JavaWebServiceReference.GetResultRequest inValue = new CSharpClientTestApp.JavaWebServiceReference.GetResultRequest();
            inValue.Body = new CSharpClientTestApp.JavaWebServiceReference.GetResultRequestBody();
            inValue.Body.name = name;
            return ((CSharpClientTestApp.JavaWebServiceReference.JavaWebService)(this)).GetResultAsync(inValue);
        }
    }
}
