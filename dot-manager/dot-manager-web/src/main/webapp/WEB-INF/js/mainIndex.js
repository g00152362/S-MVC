
 $(function() {
	 //TUI.loadFrame('dashboard-map');
	 TUI.loadFrame('gateway-list');
 });

var	TUI={
		loadFrame:function(url){
			$( "#mainContext" ).load( url);		
		},
};




