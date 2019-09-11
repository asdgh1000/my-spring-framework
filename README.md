# <img src="src/docs/asciidoc/images/spring-framework.png" width="80" height="80"> Spring Framework

## Spring Framework 5.1.x 源码阅读

### 编译
#### Spring Framework源码 在Idea中的编译阅读
* 我的编译环境

需要环境 | 版本
------------ | -------------
Java | 1.8.0_151
Gradle | 5.4.1
Idea | ULTIMATE 2018.3

* 在clone下[Spring Framework 5.1.x](https://github.com/spring-projects/spring-framework/tree/5.1.x)之后，先在目录下执行`./gradlew :spring-oxm:compileTestJava`
* 引入Spring Framework 5.1.x到Idea中
* 修改Gradle vm options 为 `-XX:MaxPermSize=2048m -Xmx2048m -XX:MaxHeapSize=2048m`
* 要注意的是如果增加模块例如增加代码中 spring-myproject模块，需要将引入的模块进行编译。具体过程如下
    * (以spring-context为例)当项目构建完之后，直接run spring-context下的test 即完成编译

###