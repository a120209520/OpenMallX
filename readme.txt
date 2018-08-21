1. 工程结构
    omRoot 父工程————整合所有工程,管理jar版本     打包方式:pom
    {
        omCommon  通用工程————工具类,通用pojo等   打包方式:jar
        omManager 服务层工程————聚合工程         打包方式:pom
        {
            omDao                      打包方式:jar
            omPojo                     打包方式:jar
            omInteface                 打包方式:jar
            omService                  打包方式:war
            
        }
        omWeb Web层工程                  打包方式:war
    }

2. omRoot
    2.1 细节说明
        * pom文件中需要使用dependencyManagement，仅用来管理，不实际依赖

3. hostname记录
    omService  localhost:8080/
    omWeb      localhost:8081/
    dubbo      port=20880
    dubbo-registry(zookeeper)  localhost:2181/
    dubbo-monitor              localhost:8080/