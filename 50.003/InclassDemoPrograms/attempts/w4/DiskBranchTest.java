package w4;

import org.junit.Test;

public class DiskBranchTest {

    @Test
    public void T1(){
        Disk d = new Disk(6,5);
        d.manipulate();
    }
    @Test
    public void T2(){
        Disk d = new Disk(1001,-2);
        d.manipulate();
    }
    @Test
    public void T3(){
        Disk d = new Disk(1,4);
        d.manipulate();
    }
    @Test
    public void T4(){
        Disk d = new Disk(4,14);
        d.manipulate();
    }
}
