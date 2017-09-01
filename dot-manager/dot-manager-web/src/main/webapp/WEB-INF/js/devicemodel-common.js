
	function validateForm(form,submitFunction)	{
	 	form.validate({
		        rules : {
		        	id: "required",
		        	os:"required",
		        	processor:"required",
		        	coreNumber: {
		        		required:true,
		        		digits:true,
	        		   },	
	        		memory: {
		        		required:true,
		        		digits:true,
	        		   },	
	        		storage: {
		        		required:true,
		        		digits:true,
	        		   },		        		   
		        },		 
		        messages: {
		        	name: "Model Name required",
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
		        submitHandler :submitFunction,
		          
		    });
		};

