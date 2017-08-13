<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="nodeList" title="接入点列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/node/list',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
			<th data-options="field:'id',width:80">ID</th>        	
        	<th data-options="field:'esn',width:150">接入号</th>
            <th data-options="field:'name',width:150">接入名称</th>
            <th data-options="field:'position',width:200">安装位置</th>
            <th data-options="field:'sensorItem',width:300,formatter:TAOTAO.formatSensorCap">检测能力</th>
            <th data-options="field:'created',width:130,align:'center',formatter:TAOTAO.formatDateTime">创建日期</th>
        </tr>
    </thead>
</table>

<script>

    function getSelectionsIds(){
    	var gatewayList = $("#nodeList");
    	var sels = gatewayList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	
    	return ids;
    }
    
    var toolbar = [{
        text:'新增',
        iconCls:'icon-add',
        handler:function(){
        	$(".tree-title:contains('接入点录入')").parent().click();
        }
    },{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一个设备才能编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一个设备!');
        		return ;
        	}
        	
        	$("#nodeEditWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#nodeList").datagrid("getSelections")[0];
        	//		data.priceView = TAOTAO.formatPrice(data.price);
        			$("#nodeEditForm").form("load",data);
        			
         			
        			TAOTAO.init({
        				fun:function(node){
      //  					TAOTAO.changeItemParam(node, "itemeEditForm");
        				}
        			});
        		}
        	}).window("open");
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中设备!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除ID为 '+ids+' 的设备吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("/rest/node/delete",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','删除设备成功!',undefined,function(){
            					$("#nodeList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    }];
</script>