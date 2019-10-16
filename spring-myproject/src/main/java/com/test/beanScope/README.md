# Bean作用域
|    作用域 | 描述|
| -------------- | -------- |
| singleton | 该作用域将 bean 的定义的限制在每一个 Spring IoC 容器中的一个单一实例(默认)。 |
| prototype |     该作用域将单一 bean 的定义限制在任意数量的对象实例。           |
| request |  该作用域将 bean 的定义限制为 HTTP 请求。只在 web-aware Spring ApplicationContext 的上下文中有效。              |
| session |  该作用域将 bean 的定义限制为 HTTP 会话。 只在web-aware Spring ApplicationContext的上下文中有效。              |
| global-session |    该作用域将 bean 的定义限制为全局 HTTP 会话。只在 web-aware Spring ApplicationContext 的上下文中有效。            |
