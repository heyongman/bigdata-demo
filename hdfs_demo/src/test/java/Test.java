import java.net.URLDecoder;
import java.util.regex.Pattern;

public class Test {
    @org.junit.Test
    public void myTest() {
        String str = "\\344\\273\\273\\345\\212\\241\\345\\256\\214\\346\\210\\220";
        Pattern pattern = Pattern.compile("\\\\");
        String [] strs = pattern.split(str);
        StringBuffer sb = new StringBuffer();
        for(String s : strs){
            if(s != null && s.trim().length() > 0){
                String st = Integer.toHexString(Integer.valueOf(s,8));
                sb.append("%").append(st);
            }
        }
        try {
            System.out.println(URLDecoder.decode(sb.toString(), "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
