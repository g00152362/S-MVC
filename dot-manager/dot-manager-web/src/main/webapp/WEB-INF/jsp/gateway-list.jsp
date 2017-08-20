<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<body>

   
   <!-- Main content -->
   <section class="content"> 
   
   <!-- statistic -->

      <!-- min boxes (Stat box) -->
      <div class="row">
        <div class="box">
 			<div class="box-body no-padding">
				<table id= "table_stat" class="table table-bordered " data-align="center">
		     	  <tr class="gray ">
				    <td ><span class="font_big font_blue "id="total">&nbsp;</span>   Devices</td>
				    <td ><span class="font_big font_green " id="online">&nbsp;</span>   Online</td> 
				     <td ><span class="font_big font_red " id="offline">&nbsp;</span>   Offline</td> 
				     <td ><span class="font_big font_gray "id="unregistered">&nbsp;</span>   Unregistered</td>
				     
				  </tr>
		      </table>
		  </div>
		  </div>
      </div>
      <!-- /.row -->
 
   
   <!-- -------table list----- -->
 		<div class="row">
   		<div class="box box-primary">
   		<div class="box-body no-padding"> 
   		<!-- tool bar -->
   	    <div id="toolbar">
   	    <div class="btn-group">
	        <button id="new" class="btn btn-Default" onClick="TUI.loadFrame('gateway-add')">
	            <i class="glyphicon glyphicon-plus"></i> New
	        </button>  
	        <button id="edit" class="btn btn-Default" onClick="editGateway()">
	            <i class="glyphicon glyphicon-pencil"></i> Edit
	        </button>    	
	        
	        <button type="button" class="btn btn-Default" data-load-url="devicegroup-select" onClick="addToGroup()"  >
	            <i class="glyphicon glyphicon-import"></i> Add to Group
	        </button>  	 
	        <button id="edit" class="btn btn-Default" onClick="removeToGroup()">
	            <i class="glyphicon glyphicon-export"></i> Remove to Group
	        </button>  	 	                         
	        <button id="remove" class="btn btn-danger"  onClick="deleteGateway()">
	            <i class="glyphicon glyphicon-remove"></i> Delete
	        </button>
	        
	        </div>
    	</div>
    	
    	<!-- list table -->
	    <table id="gatewayList"
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
	           data-id-field="serialNumber"
	           data-page-list="[10, 25, 50, 100, ALL]"
	           data-show-footer="false"
	           data-side-pagination="server"
	           data-toolbar-align="left"
	           data-url="/gateway/list"
	           data-response-handler="responseHandler">
	    	</table>    	
   	  </div>
   	  </div>
   	  <!-- ./box -->
   	  </div>
   	  </div>
   </section>   

<!-- begin select modal--------- -->   
<div class="modal " id="selectModalList" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                 <h4 class="modal-title">Select Group for device</h4>
            </div>
            <div class="modal-body"><div class="te"></div></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Select</button>
            </div>
        </div>
    </div>
    </div>
<!-- end modal -->

 
<script>
//-------- begin modal script ---->
$('#selectModalList').on('show.bs.modal', function (e) {
    $(this).find('.modal-body').load("devicegroup-select");
});

// SELECT MODAL HIDE
$('#selectModalList').on('hide.bs.modal', function (e) {
  
	var select = getDeviceGroupSelections();
    // update to DB 
    	var paramJson={
            "group": select[0],
            "ids": ids
        };    

       	$.post("gateway/updateGroup",paramJson, function(data){
			if(data.status == 200){
				$('#gatewayList').bootstrapTable('getData');
				$('#gatewayList').bootstrapTable('refresh');
			}
		});    
    
  //  $('#groupName').val(select[0]);
});


function getDeviceGroupSelections() {
    return $.map($('#deviceGroupSelectList').bootstrapTable('getSelections'), function (row) {
        return row.name;
    });
}
//-------- end modal script ---->

    var $table = $('#gatewayList'),
        $remove = $('#remove'),
        selections = [];
    var ids =[];
    
    function initTable() {
    	$('#gatewayList').bootstrapTable({
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
             title: "Name",//标题
             field: "deviceName",//键名
             width: 180,
             valign: 'middle',             
 //            editable: true
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
//              editable: true,              
               width: 100
           },     
           {
               field: "position",
               title: "Location",
               valign: 'middle',               
//               editable: true,                   
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


        $(window).resize(function () {
        	$('#gatewayList').bootstrapTable('resetView', {
                height: getHeight()
            });
        });
    }
    
    function getIdSelections() {
        return $.map($('#gatewayList').bootstrapTable('getSelections'), function (row) {
            return row.serialNumber;
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
        initStat();
     });
    
    function getHeight() {
        return $(window).height() - $('h1').outerHeight(true) -$('#stat').outerHeight(true) -500;
    }

	function formatStatus(value,row,index)
	{
 		if(value == 1){
 			return "<span style=\"45px;color:#5ECC49;\">\&#8226</span>"+"<span style=\";\">Online</span>";
 		}
 		else if(value == 0){
 			return "<span style=\"display:table-cell;color:#AAAAAA;font-size:30px;\">\&#8226</span>"+"<span style=\"display:table-cell;\">Offline</span>";
 		}
 		else if(value == 2){
 			return "<span style=\"display:table-cell;vertical-align:middle;height:45px;color:#AAAAAA;font-size:40px;\">\&#8226</span>"+"<span style=\"display:table-cell;vertical-align:middle;height:45px;\">unregistered</span>";
 		}	 		
 		else{
 			return "未知";
 		}
		
	}
		
	/*format group */
	function groupFormat(value,row,index){
		if(value != "")
		{
			return "<a  style='color:#009AE7' href='/group/detail?name="+value +"'>"+value+"</a>";
		}
		else{
			return "<span >Unsigned </span>";
		}
	
	} 

	/*format esn */
	function deviceEsnFormat(value,row,index){

		return "<a  style='color:#009AE7' href=\"#\" onClick=\" TUI.loadFrame('gateway-detail?esn="+value+"')\">"+value+"</a>";	
		
	} 

	function deleteGateway(){
       	var ids = getIdSelections();
    	if(ids.length == 0){
    		$().alert('close');
    		return ;
    	}
    	var param = {"ids":ids};
       	$.post("gateway/delete",param, function(data){
			if(data.status == 200){
	            $table.bootstrapTable('remove', {
	                field: 'serialNumber',
	                values: ids
	            });
			}
		});
      	return;
    }
	
	function editGateway(){
       	var ids = getIdSelections();
    	if( ids.length != 1 ){
    		$("alert").alert();
    		return ;
    	}
       	TUI.loadFrame("gateway-edit?esn="+ids[0]);
       	return;

    }
		
	function addToGroup(){
       	ids = getIdSelections();
    	if(ids.length == 0){
    		alert("No item selected!");
    		return ;
    	}		
		$('#selectModalList').modal();
		
	}
	
	function removeToGroup(){
       	ids = getIdSelections();
    	if(ids.length == 0){
    		alert("No item selected!");
    		return ;
    	}	
    	var paramJson={
                "group":null,
                "ids": ids
            };    

           	$.post("gateway/updateGroup",paramJson, function(data){
    			if(data.status == 200){
    				$('#gatewayList').bootstrapTable('getData');
    				$('#gatewayList').bootstrapTable('refresh');
    			}
    		});    
           	return;
	}
	
	// initial statistic box,get data from db
	function initStat(){
		$.post("gateway/statistic",null, function(data){
			if(data.status == 200){
	       		//disp date
	       		var tt=0;
	       		$('#online').text(data.data.online);
	       		$('#offline').text(data.data.offline);
	       		$('#unregistered').text(data.data.unregistered);
	       		$('#total').text(data.data.unregistered+data.data.offline+data.data.online);
			}
		});		
		
	}
	

</script>
</body>
