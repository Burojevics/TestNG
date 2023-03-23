package Class01;

import org.testng.annotations.Test;
import org.testng.annotations.TestInstance;

public class Priority<priority> {

    @Test(priority = 3)
    public void Atest(){
        System.out.println("I am a TestA");
    }

    @Test(priority = 1)
    public void Btest(){
        System.out.println("I am a TestB");
    }

    @Test(priority = 2)
    public void Ctest(){
        System.out.println("I am a TestC");
    }
}
