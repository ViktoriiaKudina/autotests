import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainClassTest {

    MainClass mainClass = new MainClass();

    @Test
    public void testGetLocalNumber() {
        Assert.assertEquals("Local number should be 14",14, mainClass.getLocalNumber());
    }

    @Test
    public void testGetClassNumber() {
        Assert.assertTrue("The number should be over 45",mainClass.getClassNumber() > 45);
    }
}