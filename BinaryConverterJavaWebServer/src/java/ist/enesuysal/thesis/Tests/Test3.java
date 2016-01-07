package ist.enesuysal.thesis.Tests;

import ist.enesuysal.thesis.Annotation.Mandatory;
import ist.enesuysal.thesis.CentralSerializer;
import java.lang.reflect.Field;

public class Test3 {
    @Mandatory
    public String ekes2 = "affff";
    @Mandatory
    public int version=56;
    
    public boolean deneme;
    
    public boolean test=true;

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
