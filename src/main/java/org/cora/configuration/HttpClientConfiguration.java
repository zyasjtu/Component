package org.cora.configuration;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.IdleConnectionEvictor;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import java.util.concurrent.TimeUnit;

/**
 * @author z673413
 */
@Configuration
@PropertySource("classpath:spring-config.properties")
public class HttpClientConfiguration {
    /**
     * 最大连接数
     */
    @Value("${http.maxTotal}")
    private Integer maxTotal;
    /**
     * 每个主机的最大并发数
     */
    @Value("${http.defaultMaxPerRoute}")
    private Integer defaultMaxPerRoute;
    /**
     * 创建连接的最长时间
     */
    @Value("${http.connectTimeout}")
    private Integer connectTimeout;
    /**
     * 从连接池中获取到连接的最长时间
     */
    @Value("${http.connectionRequestTimeout}")
    private Integer connectionRequestTimeout;
    /**
     * 数据传输的最长时间
     */
    @Value("${http.socketTimeout}")
    private Integer socketTimeout;

    @Autowired
    private PoolingHttpClientConnectionManager manager;


    @Bean
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager =
                new PoolingHttpClientConnectionManager();

        poolingHttpClientConnectionManager.setMaxTotal(maxTotal);

        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
        return poolingHttpClientConnectionManager;
    }

    /**
     * 定期清理无效连接
     *
     * @return evictor
     */
    @Bean
    public IdleConnectionEvictor idleConnectionEvictor() {
        return new IdleConnectionEvictor(manager, 1L, TimeUnit.HOURS);
    }

    /**
     * HttpClient对象，需要设置scope="prototype":多例对象
     * @return httpClient
     */
    @Bean
    @Scope("prototype")
    public CloseableHttpClient closeableHttpClient() {
        return HttpClients.custom().setConnectionManager(this.manager).build();
    }

    /**
     * 请求配置
     * @return config
     */
    @Bean
    public RequestConfig requestConfig() {
        return RequestConfig.custom().setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout).setSocketTimeout(socketTimeout).build();
    }
}
