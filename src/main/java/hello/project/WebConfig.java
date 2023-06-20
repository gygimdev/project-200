package hello.project;

import hello.project.aop.TimeTraceAop;
import hello.project.filter.MDCLoggingFilter;
import hello.project.resolver.MvcHandlerExceptionResolver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /** AOP 등록
     * @return
     */
//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }

    /** MDC 로깅 필터등록
     */
    @Bean
    public FilterRegistrationBean<MDCLoggingFilter> mdcLoggingFilter() {
        FilterRegistrationBean<MDCLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MDCLoggingFilter());
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    /** 예외처리 등록
     * runtime 예외 발생시 해당 에러에 알맞는 에러페이지로 매핑시켜주기 위해 사용
     * @param resolvers the list of configured resolvers to extend
     */
    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(new MvcHandlerExceptionResolver());
    }


}
