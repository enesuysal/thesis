/*
 *  Information Systems and Computer Engineering
 *  Distributed programming in cloud computing
 *  Enes UYSAL
 *  Enes UYSAL
 *   
 *   Supervisor: José Carlos Martins Delgado
 */
package ist.enesuysal.thesis.WebServices;

import DOM.ComplianceTest;
import ist.enesuysal.thesis.Receiver;
import ist.enesuysal.thesis.Tests.Employee;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author enesuysal
 */
@WebService(serviceName = "JavaWebService")
public class JavaWebService {

    /**
     * This is a sample web service operation
     */
//    @WebMethod(operationName = "GetResult")
//    public String GetResult(@WebParam(name = "name") String value) {
//        try {
//            ComplianceTest complience = new ComplianceTest();
//            return complience.GetMessage(value);
//        } catch (Exception ex) {
//            return "Error with Message";
//        }
//    }
    
    @WebMethod(operationName = "GetObjectSOA")
    public String GetObjectSOA(@WebParam(name = "name") Employee value) {
        try {
            Receiver receiver = new Receiver();
            return receiver.PrintObject(value);
        } catch (Exception ex) {
            return "Error with Message";
        }
    }
}
