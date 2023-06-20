package hello.project.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class TimeTraceAop {

//    @Around("execution(* hello.project.controller.*(..))")
@Around("execution(* hello.project.service.*.*(..)) || execution(* hello.project.repository.*.*(..)) || execution(* hello.project.controller.*.*(..))")
public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
    // 전처리
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {

            // AOP 가 적용된 실제 메서드 실행부분
            return joinPoint.proceed();
        } finally {
            // 후처리
            Long finish = System.currentTimeMillis();
            Long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }

    }
}
