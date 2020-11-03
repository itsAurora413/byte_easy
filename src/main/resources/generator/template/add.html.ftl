<!DOCTYPE HTML>
<html  xmlns:th="http://www.thymeleaf.org">
<head th:include="_meta :: header">
<title>添加</title>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row">
        <form class="layui-form form form-horizontal"  id="form-${entity?uncap_first}-add" action="#" th:action="@{/${package.ModuleName}/${entity?uncap_first}/add}">
            <#list table.fields as field >
            <div class="layui-form-item">
                <label class="layui-form-label"><span class="x-red">*</span>${field.comment?split("#")[0]}：</label>
                <#if field.type == 'datetime'>
                <div class="layui-input-inline">
                    <input type="layui-input" class="layui-input timeSpace" readonly="readonly"
                           autocomplete="off" name="${field.propertyName}" placeholder="请选择${field.comment?split("#")[0]}"/>
                </div>
                <#elseif field.type == 'text'>
                <div class="layui-input-block" >
                    <script id="editor" name="${field.propertyName}"  type="text/plain"  style="height:500px;">
                    </script>
                    </div>
                    <#elseif field.type == 'tinyint' ||field.type == 'smallint' ||field.type == 'mediumint'||field.type == 'int'||field.type == 'bigint'>
                    <div class="layui-input-inline">
                        <input type="number" class="layui-input" name="${field.propertyName}"  placeholder="请填写${field.comment?split("#")[0]}" id="${field.propertyName}"/>
                        </div>
                        <#elseif field.type == 'float' ||field.type == 'double'||field.type == 'real'||field.type == 'decimal' >
                        <div class="layui-input-inline">
                        <input type="number" step="0.001" class="layui-input" name="${field.propertyName}" placeholder="请填写${field.comment?split("#")[0]}" id="${field.propertyName}"/>
                        </div>
                        <#else>
                        <div class="layui-input-inline">
                        <#if field.comment?index_of("img")!=-1>
                        <input class="layui-input upload-url" type="text"  readonly="" style="width:400px">
                        <a href="javascript:void();" class="btn btn-primary upload-btn"><i class="Hui-iconfont"></i> 浏览文件</a>
                    <input type="file" multiple="" style="width:100%; height:100%"  name="${field.propertyName}File"   id="${field.propertyName}" class="input-file valid">
                        <#else>
                        <input type="layui-input" class="layui-input" t name="${field.propertyName}"  placeholder="修改${field.comment?split("#")[0]}" id="${field.propertyName}"/>
                        </#if>
                        </div>
                        </#if>
                        </div>
                        </#list>
            
            <div class="layui-form-item">
                <label class="layui-form-label">
                </label>
                <button  class="layui-btn" id="subbtn" type="submit" >
                    增加
                </button>
                <button  class="layui-btn" id="reset" type="reset" >
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
    layui.use(['form', 'layer','laydate'],function() {
        $ = layui.jquery;
        var form = layui.form,
            laydate = layui.laydate,
            layer = layui.layer;
        lay('.timeSpace').each(function(){
            laydate.render({
                elem: this
                ,trigger: 'click'
                ,type: 'datetime'
            });
        });
    });
 $(function(){
	$("#form-${entity?uncap_first}-add").validate({
		rules:{
		    <#list table.fields as field >
            ${field.propertyName}:{
                required: true,
			},
            </#list>
		},
		onkeyup:false,
		debug: true,
		success:"valid",
		submitHandler:function(form){
				$(form).ajaxSubmit({
				type: 'POST',
				url: "/${package.ModuleName}/${entity?uncap_first}/add" ,
				success: function(data){
					if(data.code == "1"){
						selfmsg('添加成功!',1,function(){
							var index = parent.layer.getFrameIndex(window.name);
							parent.location.reload();
							parent.layer.close(index);
						});
					}else{
						selfmsg('添加失败!');
					}
				},
                error: function(){
					selfmsg('添加异常!',5);
				}
			});
		}
	});
});

</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>