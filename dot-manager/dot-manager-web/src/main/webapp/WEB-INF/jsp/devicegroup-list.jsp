<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<body>
   <!-- Content Header (Page header) -->
   <section class="content-header">
     <h3>
       <i class="glyphicon glyphicon-list"></i> Group List
     </h3>
   </section>
   
   <!-- Main content -->
   <section class="content"> 
   	<div class="container "  style="width:100%;">
   		<div class="box box-primary">
   		<div class="box-body box-profile"> 
   		<!-- tool bar -->
   	    <div id="toolbar">
   	    <div class="btn-group">
	        <button id="new" class="btn btn-primary" onClick="TUI.loadFrame('devicegroup-add')">
	            <i class="glyphicon glyphicon-plus"></i> New
	        </button>  
	        <button id="edit" class="btn btn-Default" onClick="editGroup()">
	            <i class="glyphicon glyphicon-pencil"></i> Edit
	        </button>    	          
	        <button id="remove" class="btn btn-danger"  onClick="deleteDeviceGroup()">
	            <i class="glyphicon glyphicon-remove"></i> Delete
	        </button>
	        
	        </div>
    	</div>
    	
    	<!-- list table -->
	    <table id="deviceGroupList"
	           data-toolbar="#toolbar"
	           data-search="true"
	           data-show-refresh="true"
	           data-show-toggle="true"
	           data-show-columns="true"
	           data-show-export="true"
	           data-detail-view="false"
	           data-detail-formatter="detailFormatter"
	           data-minimum-count-columns="2"
	           data-show-pagination-switch="true"
	           data-pagination="true"
	           data-id-field="id"
	           data-page-list="[10, 25, 50, 100, ALL]"
	           data-show-footer="false"
	           data-side-pagination="server"
	           data-toolbar-align="left"
	           data-url="/deviceGroup/list"
	           data-response-handler="responseHandler">
	    	</table>    	
   	  </div>
   	  </div>
   	  <!-- ./box -->
   	</div>
   </section>   

 
<script>
    var $table = $('#deviceGroupList'),
        $remove = $('#remove'),
        selections = [];
    
    function initTable() {
        $table.bootstrapTable({
   	     dataField: "rows",
         height: getHeight(),

     columns: [
         {
             field: "ck",            	 
             checkbox: true,
             valign: 'middle' ,  
             align:'center',             

         },
         {
             title: "id",//标题
             field: "id", //键名
             visible:false,
         },         
         {
             title: "Name",//标题
             field: "name",//键名
             width: 180,
             valign: 'middle',   
             formatter: groupnameFormat,             
 //            editable: true
         },

         {
        	 title: "Online device ",
        	 field: "onlinenumber",
             width: 50,
             sortable: true,
             valign: 'middle',             
           },         
         {
        	 title: "Offline device",
             field: "offlinenumber",
             width: 50,
             valign: 'middle',             
            
          },
          {
         	 title: "Unregistered",
              field: "unregeisterednumber",
              width: 50,
              valign: 'middle',             
             
           },          
    
            {
                field: "description",
                valign: 'middle',                
                title: "Description",
                width: 200
             }      
     ]
   });



        $(window).resize(function () {
            $table.bootstrapTable('resetView', {
                height: getHeight()
            });
        });
    }
    
    function getIdSelections() {
        return $.map($table.bootstrapTable('getSelections'), function (row) {
            return row.id;
        });
    }
    function responseHandler(res) {
        $.each(res.rows, function (i, row) {
            row.state = $.inArray(row.id, selections) !== -1;
        });
        return res;
    }
    function detailFormatter(index, row) {
        var html = [];
        $.each(row, function (key, value) {
            html.push('<p><b>' + key + ':</b> ' + value + '</p>');
        });
        return html.join('');
    }
    function operateFormatter(value, row, index) {
        return [
            '<a class="like" href="javascript:void(0)" title="Like">',
            '<i class="glyphicon glyphicon-heart"></i>',
            '</a>  ',
            '<a class="remove" href="javascript:void(0)" title="Remove">',
            '<i class="glyphicon glyphicon-remove"></i>',
            '</a>'
        ].join('');
    }
    window.operateEvents = {
        'click .like': function (e, value, row, index) {
            alert('You click like action, row: ' + JSON.stringify(row));
        },
        'click .remove': function (e, value, row, index) {
            $table.bootstrapTable('remove', {
                field: 'id',
                values: [row.id]
            });
        }
    };

    
    $(function () {
        initTable();
     });
    
    function getHeight() {
        return $(window).height() - $('h1').outerHeight(true);
    }


		
	/*format group */
	function groupFormat(value,row,index){
		if(value != "Unsigned_Group")
		{
			return "<a  style='color:#009AE7' href='/group/detail?name="+value +"'>"+value+"</a>";
		}
		else{
			return "<span >"+value+"</span>";
		}
	
	} 



	function deleteDeviceGroup(){
       	var ids = getIdSelections();
       	console.log(ids.length);
    	if(ids.length == 0){
    		$().alert();
    		return ;
    	}
    	var param = {"ids":ids};
       	$.post("deviceGroup/delete",param, function(data){
			if(data.status == 200){
	            $table.bootstrapTable('remove', {
	                field: 'id',
	                values: ids
	            });
			}
		});
       	

       	return;
    }
	
	function editGroup(){
       	var ids = getIdSelections();
    	if( ids.length != 1 ){
    		$("alert").alert();
    		return ;
    	}
       	TUI.loadFrame("devicegroup-edit?id="+ids[0]);
       	return;

    }
		


</script>
</body>
