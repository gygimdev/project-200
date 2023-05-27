package hello.project;

import hello.project.resolver.MvcHandlerExceptionResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // MVC RuntimeError 시 알맞은 errorPage 반환
    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(new MvcHandlerExceptionResolver());
    }


}
