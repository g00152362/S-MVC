<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<body>
   <!-- Content Header (Page header) -->
   <section class="content-header">
     <h3>
       <i class="glyphicon glyphicon-pencil"> </i>Modify Device Group
     </h3>
   </section>
   
      <!-- Main content -->
   <section class="content">  
	<div class="container "  style="width:100%;">
    	<div class="row">   
    		<div class="col-md-10 col-md-offset-1">
			<form id="deviceGroupEditForm"  role="form" class="form-horizontal"  method="post">    		
    		<div class="box box-primary ">
    		      <div class="box-body">
    		         <!-- form start -->
            		
     				 <div class="form-group">
		                  <label for="name" class="col-sm-2 control-label">Name:</label>
		                  <div class="col-sm-8">
		                    <input type="text" class="form-control" name="name" id="name" placeholder="Device name">
		                  </div>
		                </div>  

		                <div class="form-group">
		                  <label for="description" class="col-sm-2 control-label">Description:</label>
		                  <div class="col-sm-8">
		                    <textarea class="form-control" rows="3"name="description" id="description" placeholder="Device description">
		                    </textarea>
		                  </div>
		                </div>  
		                <!-- id field hide -->  
						<input type="hidden" name="id" id="id"  value="value">            		
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

	$.getJSON("deviceGroup/listid?id=${param.id}",
			function(data){
				if(data.status != 200){
					alert("get data fail!");
					return;
				}
				loadDate(data.data);
		}); 	
	
	 $("#deviceGroupEditForm").validate({
	        rules : {
	        	name: "required",
	        },		 
	        messages: {
	            name: "Name required",
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
		        	$.post("/deviceGroup/update",$("#deviceGroupEditForm").serialize(), function(data){
        			if(data.status == 200){
        				 TUI.loadFrame('devicegroup-list');
        			}
        			else{
        				console.log("error update the device group data!");
        			}
        		});
	        }    
	    });
	});
	
	function loadDate(data){
		var FormObject = document.forms['deviceGroupEditForm'];
		$.each(data, function(key, val) { 
			console.log(key +  val);
			//FormObject.elements["serialNumber"].value="12345";
			if(FormObject.elements[key] != null){
				FormObject.elements[key].value=val;
			}
		});
		FormObject.elements["id"]=${param.id};
		
	}

	function clearForm(){
		$('#deviceGroupEditForm').form('reset');
	}
	
	function cancelEdit(){
		TUI.loadFrame('devicegroup-list');
	}
	

</script>

</body>