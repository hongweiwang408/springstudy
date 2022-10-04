# spring学习笔记

## 容器

### 一、核心概念

​	**IoC**（Inversion of control）将创建对象的过程由内部转换到外部，spring提供了**IoC容器**，用来充当“**外部**”，达到解耦的目的

​	**bean** IOC容器中创建或者管理的对象被称为**bean**

​	**DI**（Dependency Injection）建立**bean**与**bean**之间的依赖关系的过程

### 二、IOC实例教程

导入坐标

```xml
<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>5.3.23</version>
</dependency>
```

创建spring配置文件**applicationContext.xml**,创建bean

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!-- 导入spring-context的坐标 -->
	
    <bean id="bookDao" class="com.example.dao.BookDao"/>    

    <bean id="bookService" class="com.example.service.BookService"/>
</beans>
```

实例化对象

```java
public static void main(String[] args) {
		// 获取IoC容器
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//创建实例对象
		BookDao bookDao = (BookDao) ctx.getBean("bookDao");
		bookDao.say();
		
		BookService bookService=(BookService) ctx.getBean("bookService");
		bookService.say();
	}
```

### 三、DI实例教程

```xml
 <bean id="bookDao" class="com.example.dao.BookDao" />

    <bean id="bookService" class="com.example.service.BookService">
        <!-- 配置service与dao的关系 -->
        <!-- property标签表示配置当前bean属性 -->
        <!-- name 是实例对象的名称 -->
        <!-- ref就是表示绑定的bean名称 -->
        <property name="BookDaoImpl" ref="bookDao"></property>
    </bean>
```

### 四、bean配置

```xml
<bean id="id" name="name1 name2" class="class"/>
<!--name属性 别名 scope属性prototype 单例/多例 提高复用性-->
```

### 五、bean的实例化

1.spring创建bean通过无参构造器

2.静态工厂方法，创建工厂bean，并调用创建方法

3.实例工厂方法

4.FactoryBean func

```java
package com.example.service;

import org.springframework.beans.factory.FactoryBean;

public class UserServiceFactoryBean implements FactoryBean<BookService> {
//泛型表示要创建的bean的类型
    
    public BookService getObject() throws Exception {
        return new BookService();
    }

    public Class<?> getObjectType() {
        return BookService.class;
    }

}
```

### 六、bean的生命周期

```xml
<bean id="bookService" class="com.example.service.BookService"  init-method="init" destroy-method="destroy">
    <!--init-method属性为创建对象时调用方法，destroy-method为销毁对象时调用方法，只有在单例模式下，destroy-method才会生效-->
```

可以通过钩子函数在虚拟机关闭之前关闭容器

可以通过接口  InitializingBean, DisposableBean实现初始化和销毁

#### 生命周期

##### 初始化容器

- 创建对象（内存分配）

- 执行构造方法
- 执行属性注入
- 执行bean初始化方法（bean属性方法优先于接口方法）

##### 使用bean

- 执行业务操作

##### 关闭/销毁容器

- 执行bean销毁方法

### 七、依赖注入方式

#### setter注入

和DI实例一样

#### 构造器注入

 标签换为**constructor-arg**，**setter func**换为**constructor**（type 和 index属性解决参数问题）

### 八、依赖自动装配

autowire属性配置自动装装配，byType ， byName

自动装配优先级低于setter注入和构造器注入，自动装配失效

### 九、集合注入

经典白雪

### 十、数据源对象管理

1. 导入坐标

2. 创建bean 

```xml

    <bean  class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="com.mysql.cj.jdbc.Driver" />
        <property name="url" value="jdbc:mysql://localhost:3306/springtest" />
        <property name="username" value="root" />
        <property name="password" value="123456" />
    </bean>
```

### 十一、加载properties文件

开辟context命名空间

```xml
<beans xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:context="http://www.springframework.org/schema/context" xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd
        ">
    
 <context:property-placeholder location="classpath*:*.properties"/>
    
```

### 十二、容器

经典白雪



## 注解开发

### 一、注解开发定义bean
