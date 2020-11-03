<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity4">
<head th:include="_meta :: header">
    <title>列表</title>
</head>
<body>
<div class="x-nav">
          <span class="layui-breadcrumb">
            <a href="">首页</a>
            <a href="">${table.comment}列表</a>
            <a>
              <cite>${table.comment}管理</cite></a>
          </span>
    <a class="layui-btn layui-btn-small" style="float: right;height: 30px;line-height: 30px;margin-top: 5px;" onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body ">
                    <form action="#" class="layui-form layui-col-space5" th:action="@{/${package.ModuleName}/${entity?uncap_first}}" method="post">
                        <#list table.fields as field >
                            <#if (field.propertyName != "id" && field.propertyName != "createTime" && field.propertyName != "updateTime") >
                                <#if field.type == 'datetime'>
                                    <div class="layui-inline ">
                                        <input type="text" class="layui-input timeSpace" placeholder="搜索${field.comment?split("#")[0]}" name="${field.propertyName}Space" th:value="${r"$"}{${field.propertyName}Space}" />
                                    </div>
                                <#elseif field.type == 'tinyint' ||field.type == 'smallint' ||field.type == 'mediumint'||field.type == 'int'||field.type == 'bigint'>
                                    <div class="layui-inline ">
                                    <input type="number" class="layui-input" th:value="${r"$"}{searchInfo.${field.propertyName}}" name="${field.propertyName}" placeholder="搜索${field.comment?split("#")[0]}" id="${field.propertyName}" />
                                    </div>
                                <#elseif field.type == 'float' ||field.type == 'double'||field.type == 'real'||field.type == 'decimal' >
                                    <div class="layui-inline ">
                                    <input type="number"  step="0.001" class="layui-input" th:value="${r"$"}{searchInfo.${field.propertyName}}" name="${field.propertyName}" placeholder="搜索${field.comment?split("#")[0]}" id="${field.propertyName}" />
                                    </div>
                                <#else>
                                    <div class="layui-inline ">
                                    <input type="text"  step="0.001" class="layui-input"  th:value="${r"$"}{searchInfo.${field.propertyName}}" name="${field.propertyName}" placeholder="搜索${field.comment?split("#")[0]}" id="${field.propertyName}" />
                                    </div>
                                </#if>
                            </#if>
                        </#list>
                        <div class="layui-inline">
                            <button type="submit" class="layui-btn  layui-btn-success radius"><i class="layui-icon">&#xe615;</i>
                            </button>
                        </div>
                        <div class="cl pd-5 bg-1 bk-gray " >
							<span class="l">
                                <a href="javascript:;"
                                   onclick="deleteAll('/${package.ModuleName}/${entity?uncap_first}/deleteAll')"
                                   class="layui-btn  layui-btn-danger radius"><i class="layui-icon">&#xe64d;</i> 批量删除</a>
						<a href="javascript:;"
                           onclick="layer_show('添加','/${package.ModuleName}/${entity?uncap_first}/addBefore',900,500)"
                           class="layui-btn  layui-btn-normal radius"><i class="layui-icon">&#xe600;</i> 添加</a>
							</span>
                            <span class="r wei-height" style="float: right;margin-right: 20px">共有数据：<strong
                                        th:text="${r'${pageInfo.total}'}"></strong> 条</span>
                        </div>
                        <div class="layui-card-body" style="text-align: center">
                            <table class="layui-table layui-form">
                                <thead>
                                <tr class="text-c">
                                    <th width="25"><input type="checkbox" name="" value="" lay-filter="checkall" lay-skin="primary"/></th>
                                    <th width="25">序号</th>
                                    <#list table.fields as field >
                                        <th width="40">${field.comment?split("#")[0]}</th>
                                    </#list>
                                    <th width="100">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr class="text-c" th:if="${r"${pageInfo.list.size()"} == 0}">
                                    <td colspan="15"><strong>暂无数据</strong></td>
                                </tr>
                                <tr class="text-c" th:each="${entity?uncap_first},count:${r"${pageInfo.list}"}">
                                    <td><input type="checkbox" value="1" th:value="${r"${"}${entity?uncap_first}.id}"
                                               name="id" lay-skin="primary"/></td>
                                    <td th:text="${r"${count.count}"}"></td>
                                    <#list table.fields as field >
                                        <#if field.type == 'datetime' || field.type == 'timestamp'>
                                            <td th:text="${r"$"}{#temporals.format(${entity?uncap_first}.${field.propertyName}, 'yyyy-MM-dd HH:mm:ss')}"></td>
                                        <#else>
                                            <#if field.comment?index_of("img")!=-1>
                                                <td><img style="width:50px;height: 50px;" th:src="@{{path}(path=${r"$"}{${entity?uncap_first}.${field.propertyName}})}" /></td>
                                            <#else>
                                                <td th:text="${r"$"}{${entity?uncap_first}.${field.propertyName}}"></td>
                                            </#if>
                                        </#if>
                                    </#list>
                                    <td class="td-manage">
                                        <a title="编辑" href="javascript:;"
                                           th:onclick="'javascript:layer_show(\'编辑\',\'/${package.ModuleName}/'+'${entity?uncap_first}'+'/editBefore/'+${r"${"}${entity?uncap_first}${r".id}"}+'\',900,500)'"
                                           class="ml-5" style="text-decoration:none"><i class="layui-icon">&#xe642;</i></a>
                                        <a title="删除" href="javascript:;"
                                           th:onclick="'javascript:deleteById(\'/${package.ModuleName}/'+'${entity?uncap_first}'+'/delete/'+${r"${"}${entity?uncap_first}${r".id}"}+'\')'"
                                           class="ml-5" style="text-decoration:none"><i class="layui-icon">&#xe640;</i></a>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div th:include="_pagination :: page"></div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!--_footer 作为公共模版分离出去-->
<div th:replace="_footer :: footerjs">
</div>
<!--/_footer 作为公共模版分离出去-->
<script>
    layui.use(['laydate','form'], function(){
        var laydate = layui.laydate;
        var form = layui.form;
        // 监听全选
        form.on('checkbox(checkall)', function(data){

            if(data.elem.checked){
                $('tbody input').prop('checked',true);
            }else{
                $('tbody input').prop('checked',false);
            }
            form.render('checkbox');
        });
        form.on('select(selectchange)', function(data){
            console.log(data.value); //得到被选中的值
            var pageNo = $('#pageNo');
            var Form = $('form')
            pageNo.val(1);
            Form.submit();
            form.render('select');
        });
        //日期时间范围选择
        laydate.render({

        });
        lay('.timeSpace').each(function(){
            laydate.render({
                elem: this
                ,type: 'datetime'
                ,range: true //或 range: '~' 来自定义分割字符
            });
        });
    });


</script>
</body>
</html>