package demo.gunsluo.im.smack;

import junit.framework.TestCase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SmackExampleTest {

    @Test
    public void test001Join() {
        SmackExample smackExample = new SmackExample();
        try {
            smackExample.join("192.168.2.23", 5222, "meet.demo.com", true);
        } catch (Exception e) {
            TestCase.fail(e.getMessage());
        }
    }

    @Test
    public void test002Join() {
        SmackExample smackExample = new SmackExample();
        try {
            smackExample.join("192.168.2.23", 5222, "auth.meet.demo.com", true, "focus", "password3");
        } catch (Exception e) {
            TestCase.fail(e.getMessage());
        }
    }

    @Test
    public void test003Join() {
        SmackExample smackExample = new SmackExample();
        try {
            smackExample.join("192.168.2.23", 5222, "auth.meet.demo.com", false);
            TestCase.fail("failed");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Test
    public void test004Join() {
        SmackExample smackExample = new SmackExample();
        try {
            smackExample.test("192.168.2.23", 5222, "auth.meet.demo.com", true, false, "focus", "password3");
        } catch (Exception e) {
            TestCase.fail(e.getMessage());
        }
    }
}