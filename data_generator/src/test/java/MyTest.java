import com.he.invoke.RandomNum;
import org.junit.Test;

public class MyTest {
    @Test
    public void test1(){
        for (int i = 0; i < 100; i++) {
            System.out.println(RandomNum.getRandomNum(2, 1));
        }
    }
}
