package com.oppo.os.ads.admin.system.confs;


import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.IdleConnectionEvictor;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class HttpClient {

	@Value("${http.maxTotal}")
	private Integer maxTotal;
	
	@Value("${http.defaultMaxPerRoute}")
	private Integer defaultMaxPerRoute;
	
	@Value("${http.connectTimeout}")
	private Integer connectTimeout;

	@Value("${http.connectionRequestTimeout}")
	private Integer connectionRequestTimeout;

	@Value("${http.socketTimeout}")
	private Integer socketTimeout;

	@Value("${http.staleConnectionCheckEnabled}")
	private boolean staleConnectionCheckEnabled;
	
	/**
	 * 设置连接池的最大连接数和每个主机的并发数
	 * @return
	 */
	@Bean(name="httpClientConnectionManager")
	public PoolingHttpClientConnectionManager getpoolingHttpClientConnectionManager() {
		PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager();
		httpClientConnectionManager.setMaxTotal(maxTotal);
		httpClientConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
		return httpClientConnectionManager;
	}
	/**
	 * httpClient对象的构建器
	 * @param httpClientConnectionManager
	 * @return
	 */
	@Bean(name = "httpClientBuilder")
	public HttpClientBuilder gethttpClientBuilder(@Qualifier("httpClientConnectionManager") PoolingHttpClientConnectionManager httpClientConnectionManager) {
		 HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		 httpClientBuilder.setConnectionManager(httpClientConnectionManager);
		 return httpClientBuilder;
}
	/**
	 * 构建httpclient对象
	 * @param httpClientBuilder
	 * @return
	 */
	@Bean
    @Scope("prototype")
	public CloseableHttpClient getCloseableHttpClient(@Qualifier("httpClientBuilder")HttpClientBuilder httpClientBuilder){ 
		return httpClientBuilder.build(); 
	}
	/**
	 * 请求配置的构建器
	 * 创建连接的最长时间
	 * 从连接池获取连接的最长时间
	 * 数据传输的最长时间
	 * 提交请求前测试连接是否可用
	 * @return
	 */
	@Bean(name = "requestConfigBuilder")
	public Builder getBuilder() {
		 RequestConfig.Builder builder = RequestConfig.custom();;
		 builder.setConnectTimeout(connectTimeout)
		        .setConnectionRequestTimeout(connectionRequestTimeout)
		        .setSocketTimeout(socketTimeout)
		        .setStaleConnectionCheckEnabled(staleConnectionCheckEnabled);
		return builder;
	}
	/**
	 * 构建RequestConfig对象
	 * @param builder
	 * @return
	 */
	@Bean
	public RequestConfig getRequestConfig(@Qualifier("requestConfigBuilder")Builder builder) {
		return builder.build();
	}
	
	@Bean
	public IdleConnectionEvictor clear(@Qualifier("httpClientConnectionManager")PoolingHttpClientConnectionManager poolingHttpClientConnectionManager) {
		IdleConnectionEvictor idleConnectionEvictor =new IdleConnectionEvictor(poolingHttpClientConnectionManager, connectTimeout, null);
		return idleConnectionEvictor;
	}
	
}