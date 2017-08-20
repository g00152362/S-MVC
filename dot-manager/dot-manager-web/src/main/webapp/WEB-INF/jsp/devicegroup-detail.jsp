<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<body>
   <!-- Content Header (Page header) -->
   <section class="content-header">
     <h3>
        <i class="glyphicon glyphicon-random"></i>   Group Profile
     </h3>
   </section>
   <!-- display group information -->
      <!-- min boxes (Stat box) -->
	<div class="container "  style="width:100%;">      
      <div class="row">
        <div class="box">
 			<div class="box-body box-profile">
				<table id= "table_stat" class="table table-bordered " data-align="center">
		     	  <tr class="gray ">
				    <td><span class="font_big font_green " id="onlinenumber">&nbsp;</span>   Online</td> 
				     <td ><span class="font_big font_red " id="offlinenumber">&nbsp;</span>   Offline</td> 
				     <td ><span class="font_big font_gray "id="unregeisterednumber">&nbsp;</span>   Unregistered</td>
				    <td ><span class="font_blue "id="description">&nbsp;</span> </td>				     
				  </tr>
		      </table>
		  </div>
		  </div>
      </div>
    <!-- -------table list----- -->
 		<div class="row">
   		<div class="box box-primary">
   		<div class="box-body no-padding"> 
  	
    	<!-- list table -->
	    <table id="gatewayListBygroupName"
	           data-toolbar="#toolbar"
	           data-search="true"
	           data-minimum-count-columns="2"
	           data-show-pagination-switch="true"
	           data-pagination="true"
	           data-id-field="serialNumber"
	           data-page-list="[10, 25, 50, 100, ALL]"
	           data-show-footer="false"
	           data-side-pagination="server"
	           data-url="/gateway/listBygroupName?name=${param.name}"	           
	           data-toolbar-align="left"
	           data-response-handler="responseHandler">

	    	</table>    	
   	  </div>
   	  </div>
   	  <!-- ./box -->
   	  </div>
 </div>  
      
      
         
<script>

function initGroupInfo(){
	$.getJSON("deviceGroup/listName?name=${param.name}",
			function(data){
				if(data.status != 200){
					alert("get data fail!");
					return;
				}
	       		$('#onlinenumber').text(data.data.onlinenumber);
	       		$('#offlinenumber').text(data.data.offlinenumber);
	       		$('#unregeisterednumber').text(data.data.unregeisterednumber);
	       		$('#description').text(data.data.description);
		}); 	
}

function initTable() {
	$('#gatewayListBygroupName').bootstrapTable({
	     dataField: "rows",
     height: getHeight(),

 columns: [
     {
         title: "Name",//标题
         field: "deviceName",//键名
         width: 180,
         valign: 'middle',             
//     editable: true
     },
     {
         field: "serialNumber",
         title: "Series Number",
         width: 180,
         formatter:'deviceEsnFormat',
         sortable: true,
         valign: 'middle',
         titleTooltip: "设备序列号"
     },
     {
         field: "status",
         title: "Status",
         width: 150,
         sortable: true,
         valign: 'middle',             
         formatter:'formatStatus'
       },         
     {
         field: "groupName",
         title: "Group",
         width: 180,
         valign: 'middle',             
         formatter:'groupFormat'               
      },
      {
          field: "type",
          title: "Model",
          valign: 'middle',              
          sortable: true,
//          editable: true,              
           width: 100
       },     
       {
           field: "position",
           title: "Location",
           valign: 'middle',               
//           editable: true,                   
              width:300
        },                    
        {
            field: "softwareVersion",
            valign: 'middle',                
            title: "Software",
            width: 60
         }      
 ]
});
	
//	$('#gatewayListBygroupName').bootstrapTable('load', "/gateway/listBygroupName?name=${param.name}");
}

$(function () {
	initGroupInfo();
	initTable();
	//load device table

 });
</script>
</body>