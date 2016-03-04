package ist.enesuysal.thesis.Tests;

import ist.enesuysal.thesis.CentralSerializer;
import java.lang.reflect.Field;

public class TestSerial {

    public TestSerial() {

    }
    // Public primitive with default value
    public int version=56;
    // Public primitive with no value
    public String ekes2 = "affff";
    // Public primitive with default value 
    public boolean deneme;
    
    public boolean test=false;
//    // Public primitive with default value
//    public int test;
//    // Public primitive with a value
//    public String mak = "Test";
//    // Public primitive with value
//    public boolean de = true;
//    // Private primitive with value 
//    private boolean denee = false;
//    // Private primitive with value 
//    private String enes = "TestValue";
    
    public void foo() { }

    public int bar() { return 12; }

    public String baz() { return ""; }
    public byte[] Serialize() {
        byte[] arrayResult = new byte[0];
        Field[] fields = this.getClass().getDeclaredFields();
        //print field names paired with their values
        for (Field field : fields) {
            try {
                arrayResult = CentralSerializer.serializePrimitive(field.getType(), field.getName() , field.get(this), arrayResult);
            } catch (IllegalAccessException ex) {
                System.out.println(ex);
            }
        }
        return arrayResult;
        //System.out.println(result.toString());
    }
}
