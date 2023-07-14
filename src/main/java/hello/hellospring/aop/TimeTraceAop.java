package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

public class TimeTraceAop {
	
	@Around("execution(* hello.hellospring..*(..))")
	// 해당 메서드가 타겟 메서드를 감싸서 실행 전후에 추가 동작을 수행한다는 것을 나타냄
	// "execution(* hello.hellospring..*(..))"은 AspectJ 포인트킷 표현식으로, hello.hellospring 패키지와 하위 패키지에 있는 모든 메서드를 대상으로 AOP를 적용한다는 의미
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
		long start = System.currentTimeMillis();
		
		System.out.println("START: " + joinPoint.toString());
		
		try {
			return joinPoint.proceed();
		} finally {
			long finish = System.currentTimeMillis();
			long timeMs = finish - start;
			
			System.out.println("END: " + joinPoint.toString()+" " + timeMs + "ms");
		}
	}

}
