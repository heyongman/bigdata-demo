import com.he.modules.dao.BankDao;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

public class MyTest {
    @Test
    public void test1(){
        try {
            BankDao bankDao = new BankDao();
//            three_day_big five_day_top erro_status_customer
            JSONArray five_day_top = bankDao.conHbase("three_day_big");
            System.out.println(five_day_top);
//            for (int i = 0; i < five_day_top.length(); i++) {
//                JSONObject obj = five_day_top.getJSONObject(i);
//                System.out.println(obj);
//            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @Test
    public void test2(){
        try {
            JSONObject obj = null;
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < 5; i++) {
                obj = new JSONObject();
                obj.put(i+"",i);
                jsonArray.put(obj);
            }
            System.out.println(jsonArray.length());
            System.out.println(jsonArray);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
