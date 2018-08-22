package com.oppo.os.ads.admin.system.confs;

import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.RedirectToLogoutFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.AssertionThreadLocalFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CasConfs {

	@Value("${cas.admin.url}")
    private String casAdminServerUrl;

    @Value("${cas.server.url}")
    private String casServerUrl;

    @Value("${cas.local.url}")
    private String appUrl;

    @Bean
    public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> servletListenerRegistrationBean() {
        ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
        servletListenerRegistrationBean.setListener(new SingleSignOutHttpSessionListener());
        return servletListenerRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean SingleSignOutFilter() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new SingleSignOutFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("casServerUrlPrefix", casServerUrl);
        registration.setName("CAS Single Sign Out Filter");
        registration.setOrder(1);

        return registration;
    }

    @Bean
    public FilterRegistrationBean redirectToLogoutFilter() {

        RedirectToLogoutFilter redirectToLogoutFilter = new RedirectToLogoutFilter();

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(redirectToLogoutFilter);
        registration.addUrlPatterns("/logout");
        registration.addInitParameter("casServerLogoutUrl", casServerUrl + "/logout");
        registration.setName("Redirect To Logout Filter");
        registration.setOrder(2);

        return registration;
    }

    @Bean
    public FilterRegistrationBean authenticationFilter() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new AuthenticationFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("casServerLoginUrl", casServerUrl + "/login");
        registration.addInitParameter("serverName", appUrl);
        registration.setName("CAS Authentication Filter");
        registration.setOrder(3);

        return registration;
    }

    @Bean
    public FilterRegistrationBean ticketValidationFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new Cas20ProxyReceivingTicketValidationFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("casServerUrlPrefix", casServerUrl);
        registration.addInitParameter("serverName", appUrl);
        registration.addInitParameter("adminServerName", casAdminServerUrl);
        registration.setName("CAS Validation Filter");
        registration.setOrder(4);

        return registration;
    }

    @Bean
    public FilterRegistrationBean httpServletRequestWrapperFilter() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new HttpServletRequestWrapperFilter());
        registration.addUrlPatterns("/*");
        registration.setName("CAS HttpServletRequest Wrapper Filter");
        registration.setOrder(5);

        return registration;
    }

    @Bean
    public FilterRegistrationBean assertionThreadLocalFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new AssertionThreadLocalFilter());
        registration.addUrlPatterns("/*");
        registration.setName("CAS Assertion Thread Local Filter");
        registration.setOrder(6);
        registration.addInitParameter("adminServerName", casAdminServerUrl);
        return registration;
    }
	
}
