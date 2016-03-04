/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.enesuysal.thesis.WebServices;

import DOM.ComplianceTest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author enesuysal
 */
@Path("RESTful")
public class RESTfulWebService {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RESTfulWebService
     */
    public RESTfulWebService() {
    }

    /**
     * Retrieves representation of an instance of ist.enesuysal.thesis.WebServices.RESTfulWebService
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    public String getText() {
        //TODO return proper representation object
       return "Restful WebService Working";
    }
    @POST
    @Consumes("text/plain")
    @Produces("text/plain")
    public String GetResult(String value) {
         try {
            ComplianceTest complience = new ComplianceTest();
            return complience.GetMessage(value);
        } catch (Exception ex) {
            return "Error with Message";
        }
    }
    /**
     * PUT method for updating or creating an instance of RESTfulWebService
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/plain")
    public void putText(String content) {
    }
}
