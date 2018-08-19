1. 工程结构
    mall-root 父工程————整合所有工程,管理jar版本     打包方式:pom
    {
        mall-common 通用工程————工具类,通用pojo等   打包方式:jar
        mall-manager 服务层工程————聚合工程         打包方式:pom
        {
            mall-manager-dao                       打包方式:jar
            mall-manager-pojo                      打包方式:jar
            mall-manager-interface                 打包方式:jar
            mall-manager-service                   打包方式:jar
            mall-manager-web                       打包方式:war
        }
    }

2. mall-root
    2.1 细节说明
        * pom文件中需要使用dependencyManagement，仅用来管理，不实际依赖