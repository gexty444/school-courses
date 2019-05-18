package w4;

import org.junit.Test;

public class DiskStatementTest {

    @Test
    public void testXIf(){
        Disk d = new Disk(0,0);
        d.manipulate();
    }

    @Test
    public void testYsecondIf(){
        Disk d = new Disk(1001,-2);
        d.manipulate();
    }
}
