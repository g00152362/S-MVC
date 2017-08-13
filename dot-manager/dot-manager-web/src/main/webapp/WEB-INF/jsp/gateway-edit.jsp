<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<body>
   <!-- Content Header (Page header) -->
   <section class="content-header">
     <h3>
       Modify Device
     </h3>
   </section>
   
      <!-- Main content -->
   <section class="content">  
	<div class="container "  style="width:100%;">
    	<div class="row">   
    		<div class="col-md-10 col-md-offset-1">
			<form id="gatewayEditForm"  role="form" class="form-horizontal"  method="post">    		
    		<div class="box box-primary ">
    		      <div class="box-body">
    		         <!-- form start -->
            		
		                <div class="form-group">
		                  <label for="esn" class="col-sm-2 control-label">Esn:</label>
		                  <div class="col-sm-8">
		                    <input type="text" class="form-control " name="serialNumber" id="serialNumber" readonly>
		                  </div>
		                </div>   
		                
		                <div class="form-group">
		                  <label for="devicename" class="col-sm-2 control-label">Name:</label>
		                  <div class="col-sm-8">
		                    <input type="text" class="form-control" name="deviceName" id="deviceName" placeholder="Device name">
		                  </div>
		                </div>  
		                
		                <div class="form-group">
		                  <label for="type" class="col-sm-2 control-label">Type:</label>
		                  <div class="col-sm-8">
		                    <input type="text" class="form-control" id="type" placeholder="Device model type">
		                  </div>
		                </div>  
		                
		                <div class="form-group">
		                  <label for="devicegroup" class="col-sm-2 control-label">Group:</label>
		                  <div class="col-sm-8">
		                    <input type="text" class="form-control" id="devicegroup" placeholder="Device in group name">
		                  </div>
		                </div>  
		                <div class="form-group">
		                  <label for="type" class="col-sm-2 control-label">Location:</label>
		                  <div class="col-sm-8">
		                    <input type="text" class="form-control" id="position" placeholder="Device install location">
		                  </div>
		                </div>  

		                <div class="form-group">
		                  <label for="description" class="col-sm-2 control-label">Description:</label>
		                  <div class="col-sm-8">
		                    <textarea class="form-control" rows="3" id="description" placeholder="Device description">
		                    </textarea>
		                  </div>
		                </div>  
            		
    		      </div>
    		      <!--box body -->
	              <div class="box-footer">
	              
	              	<div class="col-sm-2 col-sm-offset-3">
	                <button type="submit" class="btn btn-primary"  >Submit</button> 
	                </div>
	                
	                <div class="col-sm-2 col-sm-offset-3">           
	                <button type="button" class="btn btn-default " onclick="cancelEdit()">Cancel</button>
	                </div>
	              </div>
	              <!-- /.box-footer -->
	          </div>
	          <!-- /.box -->  
	          </form>  		
   			</div>
    		<!-- ./COL-MD-* -->
    	</div>
    </div>

   </section>


<script type="text/javascript">

$().ready(function() {
	$.getJSON("gateway/listid?esn=${param.esn}",
			function(data){
				if(data.status != 200){
					alert("get data fail!");
					return;
				}
				loadDate(data.data);
		}); 	
	
	 $("#gatewayEditForm").validate({
	        rules : {
	        	serialNumber: "required",
	            deviceName: "required",
	        },		 
	        messages: {
	        	serialNumber: "ESN required",
	            deviceName: "Name required",
	        },
	        errorPlacement : function(error, element) {
	            element.next().remove();//删除显示图标
	            element.after('<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>');
	            element.closest('.form-group').append(error);//显示错误消息提示	        	
	        },
	        //给未通过验证的元素进行处理
	        highlight : function(element) {
	            $(element).closest('.form-group').addClass('has-error has-feedback');
	        },	        
	        submitHandler :function(form){
		        	$.post("/rest/gateway/update",$("#gatewayEditForm").serialize(), function(data){
        			if(data.status == 200){
        				 TUI.loadFrame('gateway-list');
        			}
        		});
	        }    
	    });
	});
	
	function loadDate(data){
		console.log(data);
		var FormObject = document.forms['gatewayEditForm'];
		$.each(data, function(key, val) { 
			console.log(key +  val);
			//FormObject.elements["serialNumber"].value="12345";
			if(FormObject.elements[key] != null){
				FormObject.elements[key].value=val;
			}
		});
		
		
	}

	function clearForm(){
		$('#gatewayAddForm').form('reset');
	}
	
	function cancelEdit(){
		TUI.loadFrame('gateway-list');
	}
	

</script>

</body>