package ist.enesuysal.thesis.Tests;

import ist.enesuysal.thesis.Annotation.Mandatory;
import ist.enesuysal.thesis.CentralSerializer;
import java.lang.reflect.Field;

public class Test1 {

    public String count = "aa";
    //@Mandatory
    public int version;
   // @Mandatory

    //@Mandatory
    //public int version;
    //@Mandatory
    //public boolean deneme;
    //public int test = 66;
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
