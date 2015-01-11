Ext.define('demo.store.Purchases' ,{
    extend	: 'Ext.data.Store',
    
    requires: [
    	'demo.model.Purchase'
    ],
   
   	constructor: function(cfg) {
   		var me = this;
   		
   		cfg = cfg || {};
   		
   		me.callParent([Ext.apply ( {
   			autoLoad: false,
   			storeId	: 'Purchases',
   			model	: 'demo.model.Purchase', 
   			
   			proxy : {
   				type	: 'ajax',   				
   				reader 	: {
   					type : 'json',
   					root : 'purchases'
   				}
   				
   			}
   			
   		}, cfg)]);
   				
   		
   	}
    
});