package Class01;

import org.testng.annotations.Test;

public class EnableDisable {

    @Test(enabled = false)
    public void ATest(){
        System.out.println("I am the first test case");
    }

    @Test
    public void BTest(){
        System.out.println("I am the second test case");
    }

    @Test(enabled = false)
    public void CTest(){
        System.out.println("I am the third case");
    }

}
