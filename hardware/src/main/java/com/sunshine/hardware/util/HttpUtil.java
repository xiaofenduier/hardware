package com.sunshine.hardware.util;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtil {

    private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);


    public static String sendPost(Map<String, Object> param, String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            //装填参数
            List<NameValuePair> nvps = new ArrayList<>();
            if(!param.isEmpty()){
                for (Map.Entry<String, Object> entry : param.entrySet()) {
                    nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
                }
            }
            //设置参数到请求对象中
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            if(response.getStatusLine().getStatusCode() == 200){
                InputStream entityContent = response.getEntity().getContent();
                result = entityContent.toString();
            }
        } catch (ClientProtocolException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            // 关闭连接,释放资源
            try {
                httpClient.close();
            } catch (IOException e) {
                log.info(e.getMessage());
            }
        }
        return result;
    }
}
