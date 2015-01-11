Ext.define('demo.controller.mainController', {
	extend : 'Ext.app.Controller',

	
	refs: [
	       {
	            ref		: 'mainPanel',
	            selector: 'panel#mainPanel'
	       }
	],
	
	init : function() {
		this.control({
			'mainPanel button#reviewBtn' : {
				click : this.reviewItems
			},
			'mainPanel button#backBtn' : {
				click : this.goBack
			},
			'mainPanel button#confirmBtn' : {
				click : this.confirmPurchase
			}
			
		});
	},
	
	goBack : function(){
		 this.getMainPanel().getLayout().setActiveItem(0);
	},
	
	reviewItems : function(){
		
		console.log(this.getMainPanel())
		
		
		var grid = Ext.getCmp('productsGridPanel');
		var s = grid.getSelectionModel().getSelection();
		if(s.length == 0)
		{
			Ext.Msg.show({
				title : 'Invalid',
				msg : 'No Items Purchased',
				icon : Ext.Msg.ERROR,
				buttons : Ext.Msg.OK
			});
			return;
		}
		
		selected = [];
		Ext.each(s, function (item) {
			
			selected.push({
		        'code': item.data.code,
		        'name': item.data.name,
		        'price': item.data.price,
		        'quantity': item.data.quantity
		    });
		});
		
		var jsonData = Ext.encode(selected);
		if(window.console)
			console.log(jsonData);
		
		var hiddenForm = Ext.create('Ext.form.Panel', {
			    															    	           
			    	            height	: 0,
			    	            width	: 0,
			    	            hidden: true
			    			});
		hiddenForm.getForm().submit({
			 method	:'POST',
			 params: {data:jsonData},
			 scope: this,						        				 
	         url	:'shopping/save.action',
	         success: function (formPanel, action) {
                 var data= Ext.JSON.decode(action.response.responseText);
                 if(window.console) {
                	 console.log(data);								                    	 
                 }
                 var store = Ext.StoreManager.get('demo.store.Purchases');
                 store.loadRawData(data, false);
                 
                 
                 this.getMainPanel().down('#costlbl').setText(' Total Cost: ' + data.cost);
                 this.getMainPanel().cost = data.cost;
                 
                 this.getMainPanel().getLayout().setActiveItem(1);
             },
             failure: function (formPanel, action) {
            	 Ext.Msg.show({
						title : 'Failed',
						msg : 'Error Saving Your Order, Please Try Again',
						icon : Ext.Msg.ERROR,
						buttons : Ext.Msg.OK
				 });	
             }
		});
	},
	
	confirmPurchase : function() {   		
    		
		Ext.Ajax.request({
			 method	:'POST',						        				
	         url	:'shopping/confirm.action',
	         scope: this,	
	         success: function(response,opts) {
	        	 this.getMainPanel().getLayout().setActiveItem(2);
	        	 var data= Ext.JSON.decode(response.responseText);
	        	 
	        	 this.getMainPanel().reference = data.refCode;
	        	 this.getMainPanel().down('#finalcostlbl').setText(' Total Cost: ' + this.getMainPanel().cost);
	        	 this.getMainPanel().down('#refCodelbl').setText(' Reference Code: ' + data.refCode);
	        	 
	         },
	         failure: function(response,opts) {
	        	 Ext.Msg.show({
						title : 'Failed',
						msg : 'Error Saving Your Order, Please Try Again',
						icon : Ext.Msg.ERROR,
						buttons : Ext.Msg.OK
				 });	
	         }
		});
    		
    	
	}

	

});