package com.test.test;

import com.test.config.Appconfig;
import com.test.service.CityService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author ltw
 * on 2019-09-11.
 */
public class Test {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Appconfig.class);
		System.out.println(ac.getBean(CityService.class));
	}
}
