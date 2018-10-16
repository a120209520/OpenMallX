<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<head>
    <title>FreeMarker</title>
</head>
<body>
    <#--基本数据-->
    ${hello}
    <hr/>

    <#--对象-->
    标题: ${item.title} 价格: ${item.price}
    <hr/>

    <#--List-->
    <table border="1">
        <tr>
            <th>序号</th>
            <th>标题</th>
            <th>价格</th>
        </tr>
        <#list itList as item>
        <#if item_index % 2 == 0>
            <tr bgcolor="#7fff00">
                <td>${item_index}</td>
                <td>${item.title}</td>
                <td>${item.price}</td>
            </tr>
        <#else>
            <tr bgcolor="#ff69b4">
             <td>${item_index}</td>
             <td>${item.title}</td>
             <td>${item.price}</td>
            </tr>
        </#if>
        </#list>
    </table>
    <hr/>

    <#--日期类型-->
    当前时间：${curTime?datetime} <br/>
    当前日期：${curTime?date} <br/>
    自定义格式：${curTime?string("yyyy/MM/dd HH:mm:ss")}
    <hr/>

    <#--null值处理(FreeMarker不支持null值)-->
    <#--!后面是默认值-->
    null测试：${val!"null"} <br/>
    null测试：${val!} <br/>
    <#if val??>
        val不是个null
    <#else>
        val是个null
    </#if>
    <hr/>
    <#--引用ftl-->
    <#include "hello.ftl">
</body>
</html>

