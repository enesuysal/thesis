/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.enesuysal.thesis.Tests;

import ist.enesuysal.thesis.Annotation.Mandatory;
import ist.enesuysal.thesis.CentralSerializer;
import java.lang.reflect.Field;

/**
 *
 * @author enesuysal
 */
public class Weather {
    
    //@Mandatory
    public String country = "Portugal";
    //@Mandatory
    public String city = "Braga";
   
       
    public byte[] Serialize() {
        byte[] arrayResult = new byte[0];
        Field[] fields = this.getClass().getDeclaredFields();
        //print field names paired with their values
        for (Field field : fields) {
            try {
                arrayResult = CentralSerializer.serializePrimitive(field.getType(), field.getName(), true, field.get(this), arrayResult);
            } catch (IllegalAccessException ex) {
                System.out.println(ex);
            }
        }
        return arrayResult;
        //System.out.println(result.toString());
    }
    
}
