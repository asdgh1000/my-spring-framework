package com.test.test;

import com.test.config.Appconfig;
import com.test.service.CityService2;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author ltw
 * on 2019-09-11.
 */
public class Test {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Appconfig.class);
		CityService2 cityService2 = ac.getBean(CityService2.class);
		cityService2.queryAll();
	}
}
