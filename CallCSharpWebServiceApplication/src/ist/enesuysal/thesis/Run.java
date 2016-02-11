/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.enesuysal.thesis;

/**
 *
 * @author enesuysal
 */
public class Run {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        WebService service  = new WebService();
        WebServiceSoap port = service .getWebServiceSoap12();
        System.out.println(port.getResult("AwAAAAAAAAAAAAAABgAAAAZEZW5lbWU="));
    }
    
}
