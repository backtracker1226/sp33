package org.team.web;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.team.service.SampleService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Inject
	private SampleService service;

	// 모달 파일 업로드를 위해 추가 하였습니다.
	//aop를 적용하지 않았을 경우 이 매소드의 클래스는 sampleserviceimpl임 
	@GetMapping("/ex")
	public void ex(){
		
		System.out.println(service.getClass().getName());
		service.doA();
		
	}
	
	@PostMapping("/uploadText")
	public void uploadText(@RequestParam(value="param[]") String[] param){
		System.out.println("====================");
		System.out.println("UploadText 동작 낄낄낄");
		System.out.println(Arrays.toString(param));
		System.out.println("====================");
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
}
