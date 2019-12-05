<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Freemarker 测试</title>
</head>
<body>
<#--这是 freemarker 注释，不会输出到文件中-->
<h1>${name}； ${message}</h1>
<hr>
<#assign linkman="李四"/>
联系人：${linkman}
<#assign info={"tel":"110","sex":"男"}/>
电话：${info.tel};性别：${info.sex}
<hr>
<#include "header.ftl"/>
<hr>
<#assign bool=false/>
<#if bool>
    bool为true
<#else>
    bool为false
</#if>
<hr>
<#list goodsList as fruit>
    索引：${fruit_index},
    水果：${fruit.name};
    价格：${fruit.price}<br>
</#list>
<hr>
总共${goodsList?size}条记录
${goodsList[1].name}
${goodsList?first.name}
<hr>
<#assign str="{'id':'123','text':'文本'}"/>
<#assign jsonObj=str?eval/>
id为：${jsonObj.id};text为：${jsonObj.text}<p/>
<hr>
当前日期：${today?date}<br>
<hr>
当前时间：${today?time}<br>
<hr>
当前日期+时间：${today?datetime}
<hr>
格式化显示当前日期时间：${today?string('yyyy年MM月dd日 HH:mm:ss')}
<hr>
${number}
<hr>
${number?c}<p/>
<hr>
${str!"str空值的默认显示值"}
<hr>
<#if str??>
    str变量存在
<#else >
    str变量不存在
</#if>

</body>
</html>