import com.he.invoke.RandomNum;
import org.junit.Test;

public class MyTest {
    @Test
    public void test1(){
        for (int i = 0; i < 100; i++) {
            System.out.println(RandomNum.getRandomNumSE(-2, 1));
        }
    }
    @Test
    public void test2(){
        String fileName = "15616511.sudsdfs";
        String date = fileName.substring(0,fileName.lastIndexOf("."));
        System.out.println(date);
    }
}
