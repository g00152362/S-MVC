var gw_list = gw_list || {};

;(function($, window, undefined){
	
	"use strict";
	
	$(document).ready(function()
	{
		//gw_list.$table = document.getElementById("gatewayList");
		gw_list.$table =  $('#gatewayList');
		gw_list.selections =[];
		gw_list.ids=[];
		gw_list.$selectModal =  $('#selectModal');
		localStorage['modal_select'] = "group_tag";
		localStorage['esn'] = "";
		localStorage['group_name'] = "";
		
		// init group tag
		//http://blog.csdn.net/qq_15096707/article/details/51746968 
		gw_list.tagsinput = $('#grouptags').tagsinput({
	         trimValue: false,
	         itemValue: 'value',
	         itemText: 'text',
	         tagClass: 'label-info ',
	         freeInput: false
	     })[0];
		
		//init list table;
        initTable();
	});
	

})(jQuery, window);

   
    function initTable() {
    	gw_list.$table.bootstrapTable({
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
             title: "Name",
             field: "deviceName",
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
             titleTooltip: "esn"
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
      	gw_list.$table.bootstrapTable('resetView', {
              height: getHeight()
          });
      });
    }
    
    function responseHandler(res) {
        $.each(res.rows, function (i, row) {
            row.state = $.inArray(row.id, gw_list.selections) !== -1;
        });
        return res;
    }   
    
    /*
     *  get selection serialNumber array
     */
    function getIdSelections() {
        return $.map(gw_list.$table.bootstrapTable('getSelections'), function (row) {
            return row.serialNumber;
        });
    }

   
    function getHeight() {
        return $(window).height() - $('h1').outerHeight(true);
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
 			return "unKonw";
 		}

	}
		
	/*format group */
	function groupFormat(value,row,index){
		if(value != "")
		{
			return "<a  style='color:#009AE7'  href=\"#\" onClick=\" groupDetailClick('"+value+"')\">"+value+"</a>";
		}
		else{
			return "<span >Unsigned </span>";
		}
	
	} 
	function groupDetailClick(name){
		localStorage['group_name'] = name;
		IotLoadFrame("devicegroup-detail.html");
	}
	

	/*format esn */
	function deviceEsnFormat(value,row,index){

		return "<a  style='color:#009AE7' href=\"#\" onClick=\"deviceDetailClick('"+value+"')\">"+value+"</a>";	
		
	} 
	
	function deviceDetailClick(esn){
		localStorage['esn'] = esn;
		IotLoadFrame("gateway-detail.html");
	}
	
/*
 * delete the device 
 */
	function deleteGateway(){
       	var ids = getIdSelections();
    	if(ids.length == 0){
    		alert("must select a item");
    		return ;
    	}
    	var param = {"ids":ids};
       	$.post("/gateway/delete",param, function(data){
			if(data.status == 200){
				gw_list.$table.bootstrapTable('remove', {
	                field: 'serialNumber',
	                values: ids
	            });
			}
		});
      	return;
    }
	
	/*
	 *  edit gateway
	 */
	
	function editGateway(){

       	var idss = getIdSelections();

    	if( idss.length != 1 ){
    		alert("must select a item");
    		return ;
    	}
    	localStorage['esn'] = idss[0];

       	IotLoadFrame("gateway-edit.html?esn="+idss[0]);
       	return;
    }
		
	
	function addToGroup(){
		gw_list.ids = getIdSelections();
    	if(gw_list.ids.length == 0){
    		alert("No item selected!");
    		return ;
    	}
    	// use modal_select to know who use select_group;
    	localStorage['modal_select']="move_group";
    	var obj = {'ids':gw_list.ids};
    	localStorage['device_select']= JSON.stringify(obj);
    	gw_list.$selectModal.modal();

	}
	
	// USELESS TO BE CONFIRM TO DELETE
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
	
	
	/*    **************************
	 *   modal select group process 
	 ******************************** */	
	// when move to group modal select finished
	function updateGroupData(name){

		var paramJson=JSON.parse(localStorage['device_select']);
		var c = $.extend({},paramJson,{"group":name});
	       	$.post("/gateway/updateGroup",c, function(data){
				if(data.status == 200){
					IotLoadFrame('gateway-list.html');
					return;
				}
			});    
	       	return;
	}
	
/*    **************************
 *   group tag 
 ******************************** */
	

	function addGroupTag(tagsinput,value,text,addfun,delfun){
		
	   var input = tagsinput.$element;
	   var tagsArray = tagsinput.itemsArray;
	   var tagsContainer = tagsinput.$container; // tag 的容器
	   
	   input.tagsinput('add', {'value' :value, 'text' :text});
   
	   // 当前新创建的标签
	   var cur = $(tagsContainer.children('span.tag:last'));
	
	   cur.on('click', function(e) {
	       e.stopPropagation();
	       tagsContainer.find('input').blur();
	       if(addfun != null){
	    	   addfun(value,text);
	       }
	   });
   
	   // 当前新建的标签的删除事件
	   cur.children('span[data-role=remove]').on('click', function(e) {
	       e.stopPropagation();
	       if (tagsinput.$element.attr('disabled')) {
	           return;
	       }
	       tagsinput.remove($(e.target).closest('.tag').data('item'));
	       if(delfun != null){
	    	   delfun(value,text);
	       }
	   });   
	}
	
	// tag click process
	function showListBygroup(v,t){

    	var param = {"name":v};
       	$.post("/gateway/listBygroupName",param, function(data){
   		
				gw_list.$table.bootstrapTable('load',data);

		});
	}

