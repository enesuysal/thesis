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

    public static byte[] GetMessage(String BASE64String) throws ClassNotFoundException, Exception {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] decodedBytes = decoder.decodeBuffer(BASE64String);
            Receiver r = new Receiver();
//            //Remove START_SERIALIZE
//            decodedBytes = pop(decodedBytes);
//            decodedBytes = pop(decodedBytes);
//            if (Helper.IsPrimitive()) {
//                throw new Exception("It is primative not a class");
//            }
//            //Remove OBJECT_START
//            decodedBytes = pop(decodedBytes);
//            int FieldLength;
//            byte[] FieldLengthByte = new byte[8];
//            System.arraycopy(decodedBytes, 0, FieldLengthByte, 0, FieldLengthByte.length);
//            //Remove FieldLengthByte
//            decodedBytes = pop(decodedBytes, FieldLengthByte);
//            FieldLength = CentralSerializer.convertToInt(FieldLengthByte);
//            MyField[] fields = new MyField[FieldLength];
//            for (int i = 0; i < FieldLength; i++) {
//                MyField field = new MyField();
//                String type = (Helper.GetFieldType(decodedBytes[0])); //FieldType
//                //Remove FieldType
//                decodedBytes = pop(decodedBytes);
//                byte[] FieldNameByte = new byte[8];
//                System.arraycopy(decodedBytes, 0, FieldNameByte, 0, FieldNameByte.length);
//                //Remove FieldNameByte
//                decodedBytes = pop(decodedBytes, FieldNameByte);
//                byte[] GetFieldNameByte = new byte[CentralSerializer.convertToInt(FieldNameByte)];
//                System.arraycopy(decodedBytes, 0, GetFieldNameByte, 0, GetFieldNameByte.length);
//                //Remove GetFieldNameByte
//                decodedBytes = pop(decodedBytes, GetFieldNameByte);
//                //Check if has value
//                byte HasValue = decodedBytes[0];
//                //Remove HasValue flag
//                decodedBytes = pop(decodedBytes);
//                if (HasValue == 1) {
//                    byte[] FieldValueLenghtByte = new byte[8];
//                    System.arraycopy(decodedBytes, 0, FieldValueLenghtByte, 0, FieldValueLenghtByte.length);
//                    int FieldValueLenght = CentralSerializer.convertToInt(FieldValueLenghtByte);
//                    //Remove FieldValueLenghtByte 
//                    decodedBytes = pop(decodedBytes, FieldValueLenghtByte);
//                    byte[] FieldValue = new byte[FieldValueLenght];
//                    System.arraycopy(decodedBytes, 0, FieldValue, 0, FieldValue.length);
//                    field.fieldValue = Helper.GetFieldValue(type, FieldValue);
//                    // REmove Value
//                    decodedBytes = pop(decodedBytes, FieldValue);
//                } else {
//
//                    field.fieldValue = Helper.GetFieldValue(type, null);
//                }
//                field.fieldName = CentralSerializer.convertToString(GetFieldNameByte);
//                field.fieldType = type;
//                fields[i] = field;
//            }
//            MyMethod m = new MyMethod();
//            m.myfields = fields;
//            Receiver r = new Receiver();
//            r.findMethod(m);
//
        } catch (IOException ex) {
            System.err.println("The Error Occured " + ex.getMessage());
        }
        return null;
    }

    public boolean IsPrimitiveArraySame(byte[] array1, byte[] array2) {
        return Arrays.equals(array1, array2);
    }
}
