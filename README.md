# smcs
spring3+springmvc+mybatis+c3p0+shiro+jedis
项目描述
1、本项目架构：SpringMVC4.3.6 + Shiro1.2.2 + MyBatis5.1 + Jedis2.8 项目根目录/
2、目录结构：主目录分为base和twmall 其中分别有controller service dao mapper等子文件夹 
			所有与shiro有关的类都在twmall.shiro里，一般情况下不需要更改
3、配置文件：
			1、web文件：项目启动文件，基础配置包括加载SpringMVC的配置，字符集过滤器，欢迎页等
			2、spring-web：spring基础配置，配置了拼接URL和静态资源，注解等配置
			3、spring-service：事务配置文件，基于数据库的事务处理，如需要基于spring的AOP拦截可以加在这里
			4、spring-shiro：shiro权限控制核心配置文件，包括权限URL，缓存，安全管理，session等配置
			5、spring-dao：SpringMVC集成mybatis配置文件，数据库连接池各项配置， 连接池使用的是阿里的DruidDataSource
			6、spring-redis：缓存配置文件，缓存使用的jedis（redis for java client）
			7、mybatis-config：mybatis配置文件，实体别名，mapper等配置
			8、shiro_base_auth.ini：shiro动态加载权限配置文件，配置各种URL的权限（不要双击打开，使用编辑器的open with的文本打开）
4、使用细节：
			1、form表单跳转页面时统一调用baseController的ModelAndView方法
			2、ajax返回值使用JsonResponseResult方法，使用细节和以前一样，只是现在不再需要调用super.jsonResult返回json字符串，因为SpringMVC会自动把返回的对象转为json
			3、做登录、注册、授权等需要shiro授权的功能时务必保持代码统一，最好先和我说一下，因为关于登录和注册目前都有shiro工具类，不要重复写，而且这个项目的登录是以shiro为主和以前不太一样
			4、目前只加了LoginFilter登录拦截器，后续需要其他拦截器时自行添加，所有拦截器都必须继承AccessControlFilter父拦截器并且在spring-shiro配置文件中配置
			5、权限目前只有login权限，代表用户已登录，其他所有页面权限都是匿名anon代表所有用户都可访问，后续如果需要添加其他权限需要在spring-shiro配置文件中配置
			6、缓存目前只是搭建完成，具体使用后面再添加 缓存工具类RedisUtil
			7、所有JSP页面都必须写在/WEB-INF/views/jsp/下,所有FTL页面都必须写在/WEB-INF/views/ftl/下
			8、关于权限，不需要拦截的注册登录等URL都是/open/**开头 关于用户的需要登录的都是/user/**开头，后面有其他业务URL自行添加但必须在shiro_base_auth.ini中配置
			9、关于异常,需要自定义系统异常的在common.exception下自定义业务模块异常,然后在HandleGlobalException中配置,可配置返回json还是model
			10、关于日志,统一使用LogUtil工具类
			11、Controller继承BaseController 使用setSession和getSession存取session
//TODO
