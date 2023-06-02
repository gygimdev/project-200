package hello.project.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.UUID;

@Slf4j
public class MDCLoggingFilter implements Filter {

    private static final String REQUEST_ID_HEADER = "X-Request-ID";
    private static final String MDC_KEY = "request_id";
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 필터 초기화 로직
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {

            HttpServletRequest httpRequest = (HttpServletRequest) request;


            // HTTP 헤더에서 request_id 값을 읽어옴
            String requestId = httpRequest.getHeader(REQUEST_ID_HEADER);
            // HTTP 헤더에서 URI 값을 읽어옴
            String requestURI = httpRequest.getRequestURI();


            if(StringUtils.hasText(requestId)) {
                MDC.put(MDC_KEY, requestId);
            } else {
                final UUID uuid = UUID.randomUUID();
                MDC.put("request_id", uuid.toString());
            }

            // URL 패턴 필터링
            if (pathMatcher.match("/js/**", requestURI) || pathMatcher.match("/css/**", requestURI)) {
                // 로그를 출력하지 않고 종료
                chain.doFilter(request, response);
            } else {
                // request_id 할당
                log.info("Request received for URI: {}", requestURI);

                // 다음 필터 또는 서블릿 호출
                chain.doFilter(request, response);
            }

        } finally {
            MDC.clear();
        }
    }

    @Override
    public void destroy() {
    }
}
