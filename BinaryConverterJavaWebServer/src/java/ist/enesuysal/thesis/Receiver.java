/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.enesuysal.thesis;

import ist.enesuysal.thesis.Annotation.AvaliableMethod;
import ist.enesuysal.thesis.Annotation.Mandatory;
import ist.enesuysal.thesis.Tests.TestSerial;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import sun.misc.BASE64Decoder;

/**
 *
 * @author enesuysal
 */
public class Receiver {
   
    public static Object GetMessage(String BASE64String) throws ClassNotFoundException, Exception {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] decodedBytes = decoder.decodeBuffer(BASE64String);
            if (!Helper.IsSerializable(decodedBytes)) {
                throw new Exception("Binary is not in known format");
            }
            //Remove START_SERIALIZE
            byte[] START_SERIALIZE = new byte[]{(byte) 0xAC, (byte) 0xAE};
            decodedBytes = pop(START_SERIALIZE);
            if (Helper.IsPrimitive()) {
                throw new Exception("It is primative not a class");
            }
            //Remove OBJECT_START
            decodedBytes = pop(decodedBytes);
            // Get ALL KNown AvaliableMethods
            Receiver r = new Receiver();
            Method[] methods = r.getClass().getDeclaredMethods();
            for (Method method : methods) {
                //Find Avaliable Methods
                if (method.isAnnotationPresent(AvaliableMethod.class)) {
                    
                       System.err.println("Avaliable");
                      
                       
                        Class myClass = method.getParameters()[1].getType();
                        Field[] fields = myClass.getDeclaredFields();
                        for (Field field : fields) {
                            if (field.isAnnotationPresent(Mandatory.class)) {
                                System.out.println("Field: " + field.getName());
                            }
                        }

                }
            }
            if (r.getClass().isAnnotationPresent(AvaliableMethod.class)) {
    // process the annotation, "ac" being the instance of the object we are inspecting

            }

        } catch (IOException ex) {
            System.err.println("The Error Occured " + ex.getMessage());
        }
        return null;
    }

    
    
    @AvaliableMethod
    public void MakeObjectA(Byte[] binary, TestSerial test){
        
    }
    @AvaliableMethod
     public void MakeObjectB(Byte[] binary, int b){
        TestSerial s = new TestSerial();
    }
     
     public void PrintAllClassValues(){
         System.out.println("#####################");
     }
         private static byte[] push(byte[] array, byte[] push) {
        byte[] longer = new byte[array.length + push.length];
        System.arraycopy(array, 0, longer, 0, array.length);
        System.arraycopy(push, 0, longer, array.length, push.length);

        return longer;
    }
     private static byte[] pop(byte[] array, byte[] pop) {
        byte[] longer = new byte[array.length - pop.length];
        System.arraycopy(array, pop.length, longer, 0, longer.length);
       // System.arraycopy(push, 0, longer, array.length, push.length);

        return longer;
    }

    private static byte[] push(byte[] array, byte push) {
        byte[] longer = new byte[array.length + 1];
        System.arraycopy(array, 0, longer, 0, array.length);
        longer[array.length] = push;
        return longer;
    }
    private static byte[] pop(byte[] array) {
        byte[] longer = new byte[array.length - 1];
        System.arraycopy(array, 1, longer, 0, longer.length);
        
        return longer;
    }
    
}
