package com.he;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class AddressUtils {
    public static String getAddressByIP(String ip) {

        String add = null;
        try {
            //URL U = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=114.111.166.72");
            URL U = null;
            try {
                U = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            URLConnection connection = null;
            try {
                connection = U.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            in.close();

            JSONObject jsonObject = JSONObject.fromObject(result);
            Map<String, Object> map = (Map) jsonObject;
            String code = String.valueOf(map.get("code"));//0：成功，1：失败。
            if ("0".equals(code)) {//失败
                Map<String, Object> data = (Map<String, Object>) map.get("data");

                String country = String.valueOf(data.get("country"));//国家
                String area = String.valueOf(data.get("area"));//
                String city = String.valueOf(data.get("city"));//省（自治区或直辖市）
                String region = String.valueOf(data.get("region"));//市（县）

                add = country + "-" + city + "-" + region;
                return region;

            } else  {//成功
                String data = String.valueOf(map.get("data"));//错误信息
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
