# Bean生命周期
## 生命周期大致顺序
```
-> 生成BeanDefinition
-> 调用 BeanFactoryPostProcessor.postProcessBeanFactory
->  bean实例化
    -> 调用构造方法
-> 属性注入
    -> 调用set方法注入属性
-> BeanPostProcessor.postProcessBeforeInitialization
-> bean初始化
    -> afterPropertiesSet
    -> init-method
-> BeanPostProcessor.postProcessAfterInitialization
```

### 1.Bean实例化

### 2.属性注入

### 3.Bean初始化
* 1.实现InitializingBean
* 2.实现afterPropertiesSet
* 3.配置init-method

## Q&A
### 1.BeanFactoryPostProcessor和BeanPostProcessor区别
这两个接口实在是很像，但是作用以及场景不同

#### 1.1.BeanFactoryPostProcessor
该接口中有一个方法：
**void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;**
实现BeanFactoryPostProcessor 接口 通过覆盖postProcessBeanFactory方法，可以在bean实例化之前修改**BeanDefinition**

* 在Spring的bean创建之前，修改bean的定义属性。
Spring允许BeanFactoryPostProcessor在容器实例化任何其他Bean之前读取配置元数据，并可以根据需要进行修改。
BeanFactoryPostProcessor是在spring容器加载了bean的beanDefinition之后，在bean实例化之前执行的。

* 可以同时配置多个BeanFactoryPostProcessor，并通过order来控制执行顺序
(eg:可以吧bean的scope从singleton改为prototype)
```java
@Override
public void postProcessBeanFactory(ConfigurableListableBeanFactory arg0)
		throws BeansException {
	System.out
			.println("BeanFactoryPostProcessor调用postProcessBeanFactory方法");
	BeanDefinition bd = arg0.getBeanDefinition("person");
	bd.getPropertyValues().addPropertyValue("phone", "110");

}
```
##### 1.1.1.常见的BeanFactoryPostProcessor实现类
* org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
* org.springframework.beans.factory.config.PropertyOverrideConfigurer
* org.springframework.beans.factory.config.CustomEditorConfigurer

#### 1.2.BeanPostProcessor
该接口有两个方法：
**Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;**
**Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;**
这两个方法，可以在spring容器实例化bean之后，在执行bean的初始化方法前后，添加一些自己的处理逻辑。
BeanPostProcessor是在spring容器加载了beanDefinition并且实例化bean之后执行的。BeanPostProcessor的执行顺序是在BeanFactoryPostProcessor之后。
##### 1.2.1.常见的BeanPostProcessor实现类
* org.springframework.context.annotation.CommonAnnotationBeanPostProcessor：支持@Resource注解的注入
* org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor：支持@Required注解的注入
* org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor：支持@Autowired注解的注入
* org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor：支持@PersistenceUnit和@PersistenceContext注解的注入
* org.springframework.context.support.ApplicationContextAwareProcessor：用来为bean注入ApplicationContext等容器对象







