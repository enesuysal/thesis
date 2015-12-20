/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.enesuysal.thesis;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author enesuysal
 */
@WebService(serviceName = "CallWebServer")
public class CallWebServer {

    /**
     * This is a sample web service operation
     * @param value
     * @return 
     */
    @WebMethod
    public Object  GetResult(@WebParam(name="value") String value) throws Exception
    {
 
        try {
            return Receiver.GetMessage(value);
        } catch (ClassNotFoundException ex) {
            return null;
        }
    }
}
