package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

public class TimeTraceAop {
	
	@Around("execution(* hello.hellospring..*(..))")
	// 해당 메서드가 타겟 메서드를 감싸서 실행 전후에 추가 동작을 수행한다는 것을 나타냄
	// "execution(* hello.hellospring..*(..))"은 AspectJ 포인트킷 표현식으로, 
	// hello.hellospring 패키지와 하위 패키지에 있는 모든 메서드를 대상으로 AOP를 적용한다는 의미
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
		long start = System.currentTimeMillis();
		// 타겟 메서드 실행 전에 현재 시간을 기록
		
		System.out.println("START: " + joinPoint.toString());
		// 타겟 메서드의 실행을 나타내는 시작 로그를 출력
		
		try {
			return joinPoint.proceed();
			// 타겟 메서드를 실행
			// 이 부분은 실제로 타겟 메서드를 실행하고 그 결과를 반환함
		} finally {
			long finish = System.currentTimeMillis();
			// 타겟 메서드 실행 후에 현재 시간을 기록
			long timeMs = finish - start;
			// 타겟 메서드 실행 시간을 계산
			
			System.out.println("END: " + joinPoint.toString()+" " + timeMs + "ms");
			// 타겟 메서드의 실행이 완료된 후, 실행 시간을 나타내는 로그 출력
		}
	}

}
// 이 코드는 AOP를 사용하여 타겟 메서드의 실행 시간을 측정하고 로그를 출력하는 역할을 수행함
// AspectJ의 포인트컷 표현식을 사용하여 적용 대상 메서드를 선택하며, '@Around' 어노테이션을 통해 AOP의 실행 로직을 정의
