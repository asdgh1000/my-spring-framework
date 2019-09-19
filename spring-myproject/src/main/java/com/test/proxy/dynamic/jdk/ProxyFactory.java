package com.test.proxy.dynamic.jdk;

import com.test.proxy.statics.Calculator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 使用动态代理可以让代理类在程序运行的时候生成代理类，只需要为一类代理写一个具体的实现类就行了。
 * - JDK 动态代理只能代理有接口的类，并且是能代理接口方式，不能代理一般的类中的方法
 * <p>
 * - 提供了一个使用InvocationHandler作为参数的构造方法。在代理类中做一层包装，业务逻辑在invoke方法中实现。
 * <p>
 * - 重写了Object类的equals,hashCode,toString,它们都只是简单的调用了InvocationHandler的invoke方法，
 * 即可以对其进行特殊的操作。也就是说JDK的动态代理还可以代理上述三个方法。
 * <p>
 * - 在invoke方法中我们甚至可以不用Method.invoke方法调用实现类就返回。这种方式常常用在RPC框架中，在invoke方法发起通信调用远端的接口等。
 *
 * @author ltw
 * on 2019-09-18.
 */
public class ProxyFactory implements InvocationHandler {

	private Class<?> target;

	private Object real;

//	public ProxyFactory(Class<?> target) {
//		this.target = target;
//	}

	public Object bind(Object real) {
		this.real = real;
		return Proxy.newProxyInstance(real.getClass().getClassLoader(), real.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		before();
		Object invoke = method.invoke(real, args);
		after();
		return invoke;
	}

	public static void main(String[] args) {
		Calculator proxy = (Calculator) new ProxyFactory().bind(new Calculator.CalculatorImpl());
		proxy.add(1, 2);
	}

	private void before() {
		System.out.println("jdk dynamic proxy start");

	}

	private void after() {
		System.out.println("jdk dynamic proxy end");
	}
}
