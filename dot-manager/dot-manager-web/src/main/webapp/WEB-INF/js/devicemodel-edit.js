var dm_edit = dm_edit || {};

;(function($, window, undefined){
	
	"use strict";
	$(document).ready(function()
	{
		dm_edit.$form =  $('#deviceModelEditForm');
		dm_edit.$modelId =localStorage['Model-id'];
		
		
		$.extend(dm_edit, {

			/*
			 *  submit the edit form process
			 */
			SubmitForm:function () {
				$.post("/deviceModel/update",dm_edit.$form.serialize(), function(data){
					if(data.status == 200){
						 IotLoadFrame('devicemodel-list.html');
					}
					else{
						console.log("error update the device group data!");
					}
				});
			},	
			
			/*
			 *  cancel the edit form process
			 */
			cancelEdit:function(){
				IotLoadFrame('devicemodel-list.html');
			},
			
			loadData:function(){
				// load date form db
				var param = {'id':dm_edit.$modelId };
			    console.log(param);
				$.post("/deviceModel/listid",param,
						function(data){
							if(data.status != 200){
								alert("get data fail!");
								return;
							}
							// refresh the DOM
							var FormObject = document.forms['deviceModelEditForm'];
							$.each(data.data, function(key, val) { 
								if(FormObject.elements[key] != null){
									FormObject.elements[key].value=val;
								}
							});
					}); 				
			}
		});
	

		validateForm(dm_edit.$form,dm_edit.SubmitForm);
		dm_edit.loadData();
		

	});
	
})(jQuery, window);


	