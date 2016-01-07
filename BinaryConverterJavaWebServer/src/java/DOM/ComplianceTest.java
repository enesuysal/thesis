/*
 *  Information Systems and Computer Engineering
 *  Distributed programming in cloud computing
 *  Enes UYSAL
 *  Enes UYSAL
 *   
 *   Supervisor: José Carlos Martins Delgado
 */
package DOM;

import ist.enesuysal.thesis.Helper.Helper;
import ist.enesuysal.thesis.Receiver;
import java.io.IOException;
import java.util.Arrays;
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
            outerloop:
            for (int i = 0; i < r.knownMethods.length; i++) {

                if (r.knownMethods[i].myfields.length == 1 && Helper.CheckPrimitive(decodedBytes)) {
                    //Check Type
                    if (decodedBytes[0] == r.knownMethods[i].myfields[0]) //Create New bytearray
                    {
                        r.createPrimitive(decodedBytes);
                    }
                } else if (!Helper.CheckPrimitive(decodedBytes)) {
                    //Object 
                    //System.out.println(Arrays.toString(r.knownMethods[i].myfields));
                    boolean found = false;
                    byte[] finalArray = new byte[0];
                    String className = "";
                    
                    while (Helper.GetFieldBytes(r.knownMethods[i].myfields).length != 0) {
                        found = false;
                        byte[] fieldByte = Helper.GetFieldBytes(r.knownMethods[i].myfields);
                       
                        if (fieldByte[0] == 1) {

                            byte[] newFieldByte = Helper.pop(fieldByte);
                            if (Helper.indexOf(decodedBytes, newFieldByte) != -1) {
                                found = true;
                                className = r.knownMethods[i].methodName;
                                System.out.println("Mandatotory field found @"+ r.knownMethods[i].methodName);
                                finalArray = Helper.push(finalArray, newFieldByte);
                            } else {
                                System.out.println("Mandatotory field is not found");
                                break;
                            }
                        } else {
                            //fieldByte= Helper.pop(fieldByte);
                            byte[] newFieldByte = Helper.pop(fieldByte);
                            //System.out.println(Arrays.toString(decodedBytes));
                            //System.out.println(Arrays.toString(fieldByte));
                            if (Helper.indexOf(decodedBytes, newFieldByte) != -1) {
                                found = true;
                                className = r.knownMethods[i].methodName;
                                System.out.println("Optional field found with field name and value equlity @"+ r.knownMethods[i].methodName);
                                finalArray = Helper.push(finalArray, newFieldByte);
                            } else {

                                if (Helper.CheckOptional(decodedBytes, newFieldByte)) {
                                    System.out.println("Optional field found with field name equlity @"+ r.knownMethods[i].methodName);
                                    System.out.println(Arrays.toString(newFieldByte));
                                    className = r.knownMethods[i].methodName;
                                    finalArray = Helper.push(finalArray, newFieldByte);
                                    
                                    found = true;
                                } else {
                                    System.out.println("Optional field is not found");
                                   break;
                                }
                            }
                        }
                        r.knownMethods[i].myfields = Helper.pop(r.knownMethods[i].myfields, fieldByte);
                    }
                    if (found) {
                        System.out.println(Arrays.toString(finalArray));
                        r.createObject(className, finalArray);
                         break outerloop;
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
