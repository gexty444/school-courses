package w4;

import org.junit.Test;

public class DiskNonTerminatingTest {

    @Test
    public void checkDiskBug(){
        Disk d = new Disk(1001,-3);
        d.manipulate();
    }
}

