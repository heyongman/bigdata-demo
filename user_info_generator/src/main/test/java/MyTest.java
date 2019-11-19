import com.he.LineData;
import com.he.UserInfoGenerator;
import org.junit.Test;


public class MyTest {
    @Test
    public void test1(){
    }
    @Test
    public void test2(){
        String s = "1";
        System.out.println(Integer.parseInt(s)+1);
    }
    @Test
    public void test3(){
        for (int i = 0; i < 10; i++) {
            String data = LineData.getData();
            System.out.println(data);
        }

    }
}
