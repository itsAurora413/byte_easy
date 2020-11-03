<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head th:include="_meta :: header">
    <title>编辑</title>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row">
        <form class="layui-form form form-horizontal" id="form-${entity?uncap_first}-edit" action="#"
              th:action="@{/${package.ModuleName}/${entity?uncap_first}/edit}"
              th:object="${r"$"}{${entity?uncap_first}}" enctype="multipart/form-data">
            <input type="hidden" name="id" th:value="${r"$"}{${entity?uncap_first}.id}"/>
            <#list table.fields as field >
                <#if (field.propertyName != "id" && field.propertyName != "createTime" && field.propertyName != "updateTime") >

                    <div class="layui-form-item">
                        <label class="layui-form-label"><span
                                    class="c-red">*</span>${field.comment?split("#")[0]}：</label>
                        <#if field.type == 'datetime'>
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input timeSpace" readonly="readonly" autocomplete="off"
                                       th:value="${r"$"}{#temporals.format(${entity?uncap_first}.${field.propertyName}, 'yyyy-MM-dd HH:mm:ss')}"
                                       name="${field.propertyName}" placeholder="请选择${field.comment?split("#")[0]}"/>
                            </div>
                        <#elseif field.type == 'tinyint' ||field.type == 'smallint' ||field.type == 'mediumint'||field.type == 'int'||field.type == 'bigint'>
                            <div class="layui-input-inline">
                                <input type="number" class="layui-input"
                                       th:value="${r"$"}{${entity?uncap_first}.${field.propertyName}}"
                                       name="${field.propertyName}" placeholder="修改${field.comment?split("#")[0]}"
                                       id="${field.propertyName}"/>
                            </div>
                        <#elseif field.type == 'float' ||field.type == 'double'||field.type == 'real'||field.type == 'decimal' >
                            <div class="layui-input-inline">
                                <input type="number" step="0.001" class="layui-input"
                                       th:value="${r"$"}{${entity?uncap_first}.${field.propertyName}}"
                                       name="${field.propertyName}" placeholder="修改${field.comment?split("#")[0]}"
                                       id="${field.propertyName}"/>
                            </div>
                        <#elseif field.type == 'text'>
                            <div class="layui-input-block">
                                <script id="editor" th:text="${r"$"}{${entity?uncap_first}.${field.propertyName}}"
                                        name="${field.propertyName}" autofocus type="text/plain" style="height: 500px">
                                </script>
                            </div>
                        <#else>
                            <div class="layui-input-inline">
                                <#if field.comment?index_of("img")!=-1>
                                    <input class="layui-input upload-url" type="text" readonly="" style="width:400px">
                                    <a href="javascript:void();" class="btn btn-primary upload-btn"><i
                                                class="Hui-iconfont"></i> 浏览文件</a>
                                    <input type="file" multiple="" style="width:100%; height:100%"
                                           th:value="${r"$"}{${entity?uncap_first}.${field.propertyName}}"
                                           name="${field.propertyName}File" id="${field.propertyName}"
                                           class="input-file valid">
                                    <br/>
                                    <img th:src="@{{path}(path=${r"$"}{${entity?uncap_first}.${field.propertyName}})}"
                                         style=" width:100xp; height:100px;"/>
                                <#else>
                                    <input type="text" class="layui-input"
                                           th:value="${r"$"}{${entity?uncap_first}.${field.propertyName}}"
                                           name="${field.propertyName}" placeholder="修改${field.comment?split("#")[0]}"
                                           id="${field.propertyName}"/>
                                </#if>

                            </div>
                        </#if>
                    </div>
                </#if>
            </#list>

            <div class="layui-form-item">
                <label class="layui-form-label">
                </label>
                <button class="layui-btn" id="subbtn" type="submit">
                    增加
                </button>
                <button class="layui-btn" id="reset" type="reset">
                    重置
                </button>
            </div>
        </form>
    </div>
</div>
<!--_footer 作为公共模版分离出去-->
<div th:replace="_footer :: footerjs"></div>
<div th:replace="_ueditor :: ueditor"></div>
<!--/_footer 作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本-->
<script>
    layui.use(['form', 'layer', 'laydate'], function () {
        $ = layui.jquery;
        var form = layui.form,
            laydate = layui.laydate,
            layer = layui.layer;
        lay('.timeSpace').each(function () {
            laydate.render({
                elem: this
                , trigger: 'click'
                , type: 'datetime'
            });
        });
    });
    $(function () {
        $("#form-${entity?uncap_first}-edit").validate({
            rules: {
        <#list table.fields as field >
        <#if (field.propertyName != "id" && field.propertyName != "createTime" && field.propertyName != "updateTime") >
        ${field.propertyName}:
        {
            required: true,
        }
    ,
        </#if>
        </#list>
    },
        onkeyup:false,
            debug
    :
        true,
            success
    :
        "valid",
            submitHandler
    :

        function (form) {
            $(form).ajaxSubmit({
                type: 'POST',
                url: "/${package.ModuleName}/${entity?uncap_first}/edit",
                success: function (data) {
                    if (data.code == "1") {
                        selfmsg('编辑成功!', 1, function () {
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.location.reload();
                            parent.layer.close(index);
                        });
                    } else {
                        selfmsg('编辑失败!');
                    }
                },
                error: function () {
                    selfmsg('编辑异常!', 5);
                }
            });
        }
    })
        ;
    });

</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>