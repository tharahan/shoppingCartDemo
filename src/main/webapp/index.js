Ext.Loader.setConfig({
	enabled: true
});

Ext.Loader.setPath('Ext.ux', 'ux');

Ext.require([
    'Ext.data.*',
    'Ext.util.*',
    'Ext.view.View'   
]);

Ext.application({

	name: 'demo',
	appFolder: 'app',
	
	
	stores: [
	             'demo.store.Products',
	             'demo.store.Purchases'	             
	],
	
	views: [
	        'mainPanel',
	        'productsGridPanel',
	        'reviewPanel'
    ],
            
    controllers:[
            'mainController'
    ],

    launch: function() {
    	
    	Ext.define('demo.global.vars', {
    	    singleton: true   	   

    	});
    	
    	
        Ext.create('Ext.panel.Panel', {
            items: [
                {
                 xtype: 'mainPanel',
                }
            ],
            renderTo: 'mainDiv'
        });
    	    
    }
});