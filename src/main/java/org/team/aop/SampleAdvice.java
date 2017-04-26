package org.team.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SampleAdvice {
	
	@Before("execution(* org.team.service.SampleService*.*(..))")
	public void startLog() {
		
		System.out.println("==========================");
		System.out.println("==========================");
	}
	
	

}
