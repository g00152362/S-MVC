var dm_add = dm_add || {};

;(function($, window, undefined){
	
	"use strict";
	$(document).ready(function()
	{
		dm_add.$form =  $('#deviceModelAddForm');

	  	initform();

	});
	
})(jQuery, window);


	function initform()	{
	 	dm_add.$form.validate({
		        rules : {
		        	name: "required",
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
		        submitHandler :function(form){
			        	$.post("/deviceModel/add",dm_add.$form.serialize(), function(data){
	        			if(data.status == 200){
	        				IotLoadFrame('devicemodel-list.html');
	        			}
	        		});
		        }    
		    });
		};


	
	function cancelAdd(){
		IotLoadFrame('devicemodel-list.html');
	}
	