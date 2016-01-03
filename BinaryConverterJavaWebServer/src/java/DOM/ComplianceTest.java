/*
 *  Information Systems and Computer Engineering
 *  Distributed programming in cloud computing
 *  Enes UYSAL
 *  Enes UYSAL
 *   
 *   Supervisor: José Carlos Martins Delgado
 */
package DOM;

import ist.enesuysal.thesis.Annotation.AvaliableMethod;
import ist.enesuysal.thesis.Helper.Helper;
import ist.enesuysal.thesis.Receiver;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import sun.misc.BASE64Decoder;

/**
 *
 * @author enes
 */
public class ComplianceTest {

    public byte[] GetMessage(String BASE64String) throws ClassNotFoundException, Exception {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] decodedBytes = decoder.decodeBuffer(BASE64String);
            System.out.println(Arrays.toString(decodedBytes));
            Receiver r = new Receiver();
            List<Method> allAvaliableMethods = Helper.getMethodsAnnotatedWith(r.getClass(), AvaliableMethod.class);
            for (int i = 0; i < allAvaliableMethods.size(); i++) {
                Class paramType = allAvaliableMethods.get(i).getParameters()[0].getType();
                if (paramType.isPrimitive()) {
                    System.out.println(allAvaliableMethods.get(i).getName() + " arg is primitive");
                    //Check Type
                    if (Helper.GetFieldType(decodedBytes).equals(allAvaliableMethods.get(i).getParameters()[0].getType().toString())) {
                        System.out.println("Found");
                        //Create New bytearray
                        r.createPrimitive(decodedBytes);
                    }
                } else {
                    System.out.println(allAvaliableMethods.get(i).getName() + " arg is not primitive");
                    if (Helper.isWrapperType(paramType)) {
                        System.out.println(" Wrapper Type");
                        if (Helper.GetFieldType(decodedBytes).equals(allAvaliableMethods.get(i).getParameters()[0].getType().toString())) {
                            System.out.println("Found");
                            //Create New bytearray
                            r.createWrapper(decodedBytes);
                        }
                    }else{
                        //Object 
                        
                        
                    }

                }

            }

        } catch (IOException ex) {
            System.err.println("The Error Occured " + ex.getMessage());
        }
        return null;
    }

    public boolean IsPrimitiveArraySame(byte[] array1, byte[] array2) {
        return Arrays.equals(array1, array2);
    }
}
