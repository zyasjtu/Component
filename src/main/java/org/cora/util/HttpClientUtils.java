package org.cora.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author z673413
 */
public class HttpClientUtils {
    private static final Logger LOGGER = Logger.getLogger(HttpClientUtils.class);

    /**
     * 无参数get请求
     *
     * @param url 请求路径
     * @return String
     */
    public static String doGet(String url) {
        return doGet(url, null);
    }

    /**
     * 带参数get请求
     *
     * @param url   请求路径
     * @param param 请求参数
     * @return String
     */
    public static String doGet(String url, Map<String, String> param) {
        // 创建可关闭的HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 定义HttpResponse对象
        CloseableHttpResponse response = null;
        try {
            // 创建URI对象
            URIBuilder builder = new URIBuilder(url);
            if (!CollectionUtils.isEmpty(param)) {
                for (Map.Entry entry : param.entrySet()) {
                    builder.addParameter((String) entry.getKey(), (String) entry.getValue());
                }
            }
            URI uri = builder.build();
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            // 执行请求
            response = httpClient.execute(httpGet);
            // 判断返回状态码
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                // 返回响应内容
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
            return null;
        } catch (Exception e) {
            LOGGER.error("HttpClient doGet error", e);
            return null;
        } finally {
            // 关闭资源
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    LOGGER.error("Response close error", e);
                }
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                LOGGER.error("HttpClient close error", e);
            }
        }
    }

    /**
     * 无参数post请求
     *
     * @param url 请求路径
     * @return String
     */
    public static String doPost(String url) {
        return doPost(url, null);
    }

    /**
     * 带参数post请求
     *
     * @param url   请求路径
     * @param param 请求参数
     * @return String
     */
    public static String doPost(String url, Map<String, String> param) {
        // 创建可关闭的HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 定义HttpResponse对象
        CloseableHttpResponse response = null;
        try {
            // 创建http Post请求
            HttpPost httpPost = new HttpPost(url);
            if (!CollectionUtils.isEmpty(param)) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (Map.Entry entry : param.entrySet()) {
                    paramList.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
                }
                // 创建UrlEncodedFormEntity对象
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }
            // 执行请求
            response = httpClient.execute(httpPost);
            // 判断返回状态码
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                // 返回响应内容
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
            return null;
        } catch (Exception e) {
            LOGGER.error("HttpClient doPost error", e);
            return null;
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    LOGGER.error("Response close error", e);
                }
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                LOGGER.error("HttpClient close error", e);
            }
        }
    }

    /**
     * 带json参数post请求
     *
     * @param url  请求路径
     * @param json 请求参数
     * @return String
     */
    public static String doPostJson(String url, String json) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            if (StringUtils.isNotBlank(json)) {
                StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
                httpPost.setEntity(entity);
            }
            response = httpClient.execute(httpPost);
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
            return null;
        } catch (Exception e) {
            LOGGER.error("HttpClient doPostJson error", e);
            return null;
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    LOGGER.error("Response close error", e);
                }
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                LOGGER.error("HttpClient close error", e);
            }
        }
    }
}