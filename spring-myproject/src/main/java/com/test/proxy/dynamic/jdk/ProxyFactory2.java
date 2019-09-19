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
public class ProxyFactory2 implements InvocationHandler {

	private Object real;

	public ProxyFactory2(Object real) {
		this.real = real;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		before();
		Object invoke = method.invoke(real, args);
		after();
		return invoke;
	}

	public static void main(String[] args) {
		// 设置变量可以保存动态代理类，默认名称以 $Proxy0 格式命名
		System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

		// 1. 创建被代理的对象，UserService接口的实现类
		Calculator calculatorImpl = new Calculator.CalculatorImpl();

		// 2. 获取对应的 ClassLoader
		ClassLoader classLoader = calculatorImpl.getClass().getClassLoader();

		// 3. 获取所有接口的Class，这里的UserServiceImpl只实现了一个接口UserService，
		Class[] interfacces = calculatorImpl.getClass().getInterfaces();

		// 4. 创建一个将传给代理类的调用请求处理器，处理所有的代理对象上的方法调用
		//     这里创建的是一个自定义的日志处理器，须传入实际的执行对象 userServiceImpl
		InvocationHandler proxyFactory2 = new ProxyFactory2(calculatorImpl);

		   /*
		   5.根据上面提供的信息，创建代理对象 在这个过程中，
               a.JDK会通过根据传入的参数信息动态地在内存中创建和.class 文件等同的字节码
               b.然后根据相应的字节码转换成对应的class，
               c.然后调用newInstance()创建代理实例
		 */
		Calculator proxy = (Calculator) Proxy.newProxyInstance(classLoader, interfacces, proxyFactory2);

		proxy.add(1, 2);

		// 保存JDK动态代理生成的代理类，类名保存为 UserServiceProxy
		ProxyUtils.generateClassFile(calculatorImpl.getClass(), "calculateProxy");
	}


	private void before() {
		System.out.println("jdk dynamic proxy start");

	}

	private void after() {
		System.out.println("jdk dynamic proxy end");
	}
}
