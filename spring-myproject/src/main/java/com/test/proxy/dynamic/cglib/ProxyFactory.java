package com.test.proxy.dynamic.cglib;

import com.test.proxy.statics.Calculator;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB可以传入接口也可以传入普通的类，接口使用实现的方式，普通类使用会使用继承的方式生成代理类。
 * - 由于是继承方式，如果是static方法，private方法，final方法等描述的方法是不能被代理的。
 *
 * - 做了方法访问优化，使用建立方法索引的方式避免了传统Method的方法反射调用。
 *
 * - 提供callback 和 filter设计，可以灵活的给不同的方法绑定不同的callback。编码更方便灵活。
 *
 * - CGLIB会默认代理Object中`finalize`,`equals`,`toString`,`hashCode`,`clone`等方法。比JDK代理多了`finalize`和·clone·
 *
 * @author ltw
 * on 2019-09-18.
 */
public class ProxyFactory {

	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(Calculator.class);
		enhancer.setCallback(new MethodInterceptor() {
			//类似invokerhanddler的invoke方法
			@Override
			public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
				System.out.println("cglib proxy start");
				Object invoke = methodProxy.invoke(new Calculator.CalculatorImpl(), objects);
				System.out.println("cglib proxy end");
				return invoke;
			}
		});
		Calculator proxy = (Calculator) enhancer.create();
		proxy.add(1, 2);
	}

}
