using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace CSharpWebServer
{
    [Serializable]
    public class Test  {
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
}
