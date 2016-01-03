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
            for (int i = 0; i < r.knownMethods.length; i++) {

                if (r.knownMethods[i].myfields.length == 2) {
                    //Check Type
                    //if()
                    //Create New bytearray
                    //r.createPrimitive(decodedBytes);

//                        if (Helper.GetFieldType(decodedBytes).equals(allAvaliableMethods.get(i).getParameters()[0].getType().toString())) {
//                            System.out.println("Found");
//                            //Create New bytearray
//                          r.createWrapper(decodedBytes);
//                        }
                } else {
                    //Object 
                    System.out.println(Arrays.toString(r.knownMethods[i].myfields));
                    boolean found = false;
                    while (r.knownMethods[i].myfields.length != 0) {
                        byte[] fieldByte = Helper.GetFieldBytes(r.knownMethods[i].myfields);
                        if (fieldByte[0] == 1) {
                            System.out.println("Mandatotory");
                            if (Helper.indexOf(r.knownMethods[i].myfields, fieldByte) != -1) {
                                found=true;
                                 
                            }
                        }else{
                            System.out.println("Optional");
                            if (Helper.indexOf(r.knownMethods[i].myfields, fieldByte) != -1) {
                                found=true;
                            }else{
                                
                                 if (Helper.indexOf(r.knownMethods[i].myfields, fieldByte) != -1) 
                                found=true;
                            }
                        }
                        r.knownMethods[i].myfields = Helper.pop(r.knownMethods[i].myfields,fieldByte); 
                    }
                    System.out.println(found);
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
