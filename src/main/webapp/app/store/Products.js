Ext.define('demo.store.Products' ,{
    extend	: 'Ext.data.Store',
    
    requires: [
    	'demo.model.Product'
    ],
   
   	constructor: function(cfg) {
   		var me = this;
   		
   		cfg = cfg || {};
   		
   		me.callParent([Ext.apply ( {
   			autoLoad: true,
   			storeId	: 'products',
   			model	: 'demo.model.Product', 
   			
   			proxy : {
   				type	: 'ajax',
   				//url		: 'data/products.json',
   				url		: 'shopping/list.action',
   				reader 	: {
   					type : 'json',
   					root : 'products'
   				}
   				
   			}
   			
   		}, cfg)]);
   				
   		
   	}
    
});