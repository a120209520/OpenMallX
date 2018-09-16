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
        omWeb Web层工程                 打包方式:war
        omWebPortal Web层工程           打包方式:war
    }

    依赖关系：
    omCommon       无依赖
    omPojo         依赖omCommon
    omDao          依赖omPojo, omCommon
    omInterface    依赖omPojo, omCommon
    omService      依赖omDao，omInterface, omPojo, omCommon
    omWeb          依赖omInterface, omPojo, omCommon
    omWebPortal    依赖omInterface, omPojo, omCommon

    Maven安装顺序：
    omRoot
    omCommon
    omPojo
    omDao / omInterface
    omService / omWeb / omWebPortal
    

2. omRoot
    2.1 细节说明
        * pom文件中需要使用dependencyManagement，仅用来管理，不实际依赖

3. 端口
    omService                  localhost:8082/
    omWeb                      localhost:8086/
    omWebPortal                localhost:8083/
    omWebSearch                localhost:8084/
    omWebItem                  localhost:8085/
    dubbo                      :20880
    dubbo-registry(zookeeper)  localhost:2181/
    dubbo-monitor              localhost:8080/
    ngnix                      :80
    fastdfs                    192.168.25.133:22122/
    redis                      192.168.25.133:6379/
    redis集群                   192.168.25.133:7001~7006
    solr                       192.168.25.133:8080/solr
    activeMQ                   192.168.25.133:8161   (broker端口61616)

[tips]
(1) dubbo 2.6.0 不支持jdk9 
    dubbo 2.5.4 不支持jdk8 
set JAVA_HOME=C:\Program Files\Java\jre1.8.0_162
set JRE_HOME=C:\Program Files\Java\jre1.8.0_162