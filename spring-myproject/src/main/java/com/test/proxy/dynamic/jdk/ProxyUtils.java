package com.test.proxy.dynamic.jdk;

/**
 * @author ltw
 * on 2019-09-19.
 */
import sun.misc.ProxyGenerator;
import java.io.FileOutputStream;
import java.io.IOException;

public class ProxyUtils {
	/**
	 * 将根据类信息动态生成的二进制字节码保存到硬盘中，默认的是clazz目录下
	 * params: clazz 需要生成动态代理类的类
	 * proxyName: 为动态生成的代理类的名称
	 */
	public static void generateClassFile(Class clazz, String proxyName) {
		// 根据类信息和提供的代理类名称，生成字节码
		byte[] classFile = ProxyGenerator.generateProxyClass(proxyName, clazz.getInterfaces());
//		String paths = clazz.getResource(".").getPath();
		String paths = "spring-myproject/src/main/java/com/test/proxy/dynamic/proxyClass/";
		System.out.println(paths);
		FileOutputStream out = null;
		try {
			//保留到硬盘中
			out = new FileOutputStream(paths + proxyName + ".class");
			out.write(classFile);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

