package org.team.service;

import org.springframework.stereotype.Service;


// JoinPoint 현재 이 SampleService는 AOP 중 Target이다.
@Service
public class SampleServiceImpl implements SampleService {
	
	/* (non-Javadoc)
	 * @see org.team.service.SampleService#doA()
	 */
	@Override
	public void doA(){
		
		System.out.println("doA");
		System.out.println("doA");
		System.out.println("doA");
		System.out.println("doA");
		System.out.println("doA");
		
	}

}
