package com.url.data.process.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FirstURLParsing {
    public static void main(String[] args) {
        System.out.println("웹 데이터 가져오기(테스트)");

        StringBuffer sb = readFromUrl("https://www.naver.com");
        System.out.println(sb.toString());
    }

    private static StringBuffer readFromUrl(String url_addr) {
        URL url = null;
        HttpURLConnection conn = null;

        InputStream in = null;
        InputStreamReader reader = null;
        BufferedReader br = null;

        char[] buf = new char[512];
        StringBuffer sb = new StringBuffer();

        try {
            url = new URL(url_addr);
            conn = (HttpURLConnection) url.openConnection();

            if (conn != null) {
                conn.setConnectTimeout(2000);

                conn.setRequestMethod("GET");
                conn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                conn.setUseCaches(false);

                System.out.println("request 시작 : " + url_addr);
                conn.connect();
                System.out.println("response 완료");

                int responseCode = conn.getResponseCode();
                System.out.println("response code : " + responseCode);

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    in = conn.getInputStream();
                    reader = new InputStreamReader(in, "utf-8");
                    br = new BufferedReader(reader);

                    int cnt;
                    while ((cnt = br.read(buf)) != -1) {
                        sb.append(buf, 0, cnt);
                    }
                } else {
                    System.out.println("response 실패");
                    return null;
                }

            } else {
                System.out.println("conn Null");
                return null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (conn != null) {
                conn.disconnect();
            }
            return sb;
        }
        }

}
