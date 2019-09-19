package com.test.proxy.statics;

/**
 * 静态代理和动态代理是 Spring AOP的基础
 * 这边是静态代理的基本实现
 * 在编码时代理类已经确定，这样的问题是每创建一个代理类都需要单独编码
 *
 * @author ltw
 * on 2019-09-18.
 */
public interface Calculator {
	public int add(int a, int b);

	public static class CalculatorImpl implements Calculator {

		@Override
		public int add(int a, int b) {
			return a + b;
		}
	}

	public static class CalculatorProxy implements Calculator {

		private Calculator calculator;

		//被代理类作为构造方法参数传入代理类中，代理类和被代理类都实现 接口方法，在代理类的方法调用 被代理类方法并进行增强。
		public CalculatorProxy(Calculator calculator) {
			this.calculator = calculator;
		}

		@Override
		public int add(int a, int b) {
			System.out.println("static proxy start");
			int sum = calculator.add(a, b);
			System.out.println("static proxy end");
			return sum;
		}
	}

	public static void main(String[] args){
		Calculator proxy = new Calculator.CalculatorProxy(new Calculator.CalculatorImpl());
		proxy.add(1,2);
	}

}
