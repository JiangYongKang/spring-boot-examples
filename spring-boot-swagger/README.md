# 一、这个模块做什么

这个模块演示用 Springfox 给一个 POST 接口生成 Swagger 文档。依赖是 springfox-swagger2 和 springfox-swagger-ui，版本都是 2.9.2。

它是父工程的一个子模块，另外依赖 spring-boot-starter-web。没有 application.properties，也没有测试类。单独启动时，内嵌 Web 容器占用默认端口 8080。仓库总览写在根目录 README.md。

这份说明按当前源码静态整理，没有启动应用。

# 二、文档配置

配置类是 SwaggerConfiguration，包名是 spring.boot.swagger.config。类上有 @Configuration 和 @EnableSwagger2。

createRestApi 返回一个 Docket，文档类型是 SWAGGER_2。

1. 标题是“基于 Swagger 构建的 Rest API 文档”，描述是“更多请咨询服务开发者”，版本文本是 1.0。
2. 联系人、博客地址和邮箱写在 Contact 里，服务条款地址和博客地址相同。
3. 接口扫描包是 spring.boot.swagger，路径选择是全部路径。

Springfox 2.9.2 的文档页一般在 /swagger-ui.html。本模块没有另外写这个路径，也没有给页面加登录限制。

# 三、唯一的接口

接口在 TestController，包名是 spring.boot.swagger.controller。

1. 类上的路径是 /test，文档标签是“测试接口”。
2. 方法 create 映射 POST /test，操作说明是“POST请求”。
3. 查询参数 name 必填，文档里的示例值是 vincent。
4. 方法收到 name 之后没有使用它，直接返回字符串 success。

# 四、和 Spring Boot 2.7 的版本关系

Springfox 2.9.2 依赖较早的路径匹配方式。Spring Boot 2.6 起，默认的路径匹配改成了 PathPatternParser。本模块没有配置 spring.mvc.pathmatch.matching-strategy。

按这两个版本公开的兼容情况，文档组件有可能在启动时失败。这次归档没有启动验证。
