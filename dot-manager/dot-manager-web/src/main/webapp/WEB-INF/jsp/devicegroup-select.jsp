<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

  <div class="container "  style="width:100%;">
    <div class="row">  
        <div class="col-md-12">  
	   	<!-- list table -->
		    <table id="deviceGroupSelectList"
	         
	           data-search="true"

	           data-minimum-count-columns="2"
	           data-show-pagination-switch="true"
	           data-pagination="true"
	           data-id-field="id"

	           data-show-footer="false"
	           data-side-pagination="server"
	           data-toolbar-align="left"
	           data-url="/deviceGroup/list"
	           data-response-handler="responseHandler">
		    	</table>   
        </div>  		
               <!-- id field hide -->  
			<input type="hidden" name="nameSelect" id="nameSelect"  value="123">		
   	 </div>  
    </div>


  
<script>
var $table = $('#deviceGroupSelectList');
    function initTable() {
        $table.bootstrapTable({
   	     dataField: "rows",
         height: getHeight(),
         singleSelect:true,

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
         
 //            editable: true
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
    
   
    $(function () {
        initTable();
     });
    
    function getHeight() {
        return $(window).height() - $('h1').outerHeight(true);
    }


</script>

