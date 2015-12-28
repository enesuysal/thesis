package ist.enesuysal.thesis.Tests;

import ist.enesuysal.thesis.Annotation.Mandatory;
import java.lang.reflect.Field;

public class Test2 {

    @Mandatory
    public String count = "";
    private boolean deneme = false;
    private String enes;

    public void printString() {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append(this.getClass().getName());
        result.append(" Object {");
        result.append(newLine);

        //determine fields declared in this class only (no fields of superclass)
        Field[] fields = this.getClass().getDeclaredFields();

        //print field names paired with their values
        for (Field field : fields) {
            result.append("  ");
            try {
                result.append(field.getName());
                result.append(": ");
                //requires access to private field:
                result.append(field.get(this));
            } catch (IllegalAccessException ex) {
                System.out.println(ex);
            }
            result.append(newLine);
        }
        result.append("}");

        System.out.println(result.toString());

    }
}
