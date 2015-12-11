/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.enesuysal.thesis;

import java.io.Serializable;

/**
 *
 * @author enesuysal
 */
public class Test implements Serializable{
    private int TestID;

    public int getTestID() {
        return TestID;
    }

    public void setTestID(int TestID) {
        this.TestID = TestID;
    }

    public String getTestName() {
        return TestName;
    }

    public void setTestName(String TestName) {
        this.TestName = TestName;
    }
    private String TestName; 
    
   public Test(int testId,String testName){
       TestID = testId;
       TestName= testName;
   }
}
