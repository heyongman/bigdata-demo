import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyTest {
    @Test
    public void test1(){
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String now = dateFormat.format(new Date());
        System.out.println(now);
    }
}
