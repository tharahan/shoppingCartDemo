Ext.define('demo.view.mainPanel', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.mainPanel',
	
	id: 'mainPanel',

	height : 600,
	activeItem : 0,
	
	
	layout : {
		type : 'card',
		deferredRender : true
	},

	initComponent : function() {
		var me = this;

		Ext.applyIf(me, {

			items : [ {
				id : 'card-0',
				margin: '50 30',
				xtype : 'container',
				items: [
				        {
				        	xtype: 'label',
				        	cls : 'title-cls',
				        	margin: '10 30',
				        	text: 'Proceed with select one more items'
				        	
				        },
				        {
				        	xtype: 'productsGridPanel',
				        	margin: '10 30'
				        	
				        },
				        {
						    xtype: 'toolbar',
						    dock: 'bottom',
						    border: false,
						    bodyStyle: {
						        background: '#ffc',
						        padding: '10px'
						    },
						    margin: '10 30',
						    items: [
						            '->',
						            { 
						            	xtype: 'button', 
						            	html: '<i class="fa fa-sign-in fa-1x" style="color:green"> Review</i>',
						            	itemId: 'reviewBtn',
						            	border: 1,
						            	style: {
						            	    borderColor: 'black',
						            	    borderStyle: 'solid'
						            	},
						            	scope: this
						            }
						    ]
						}
				]
				
				
				
			}, 
			{
				id : 'card-1',
				xtype : 'container',
				margin: '50 30',
				listeners: {
					boxready: function(container) {
						
					}
				},
				items: [
						
				        {
				        	xtype: 'label',
				        	cls : 'title-cls',
				        	margin: '10 30',
				        	text: 'Please Review Your Order'
				        	
				        },
				        {
				        	xtype: 'reviewPanel',
				        	margin: '10 30'
				        	
				        },
				        
				        {
				        	xtype: 'label',
				        	cls : 'cost-cls',
				        	itemId: 'costlbl',				        	
				        	margin: '10 30'
				        	
				        },
				        
				        {
						    xtype: 'toolbar',
						    dock: 'bottom',
						    margin: '10 30',
						    border: false,
						    bodyStyle: {
						        background: '#ffc',
						        padding: '10px'
						    },
						    items: [
						            '->',
						            { 
						            	xtype: 'button', 
						            	html: '<i class="fa fa-arrow-left fa-1x" > Back</i>',
						            	itemId : 'backBtn',						            	
						            	border: 1,
						            	style: {
						            	    borderColor: 'black',
						            	    borderStyle: 'solid'
						            	},
						            	scope: this
						            },
						           
						            { 
						            	xtype: 'button', 
						            	html: '<i class="fa fa-check fa-1x" style="color:green"> Confirm</i>',
						            	itemId: 'confirmBtn',
						            	border: 1,
						            	style: {
						            	    borderColor: 'black',
						            	    borderStyle: 'solid'
						            	},
						            	scope: this
						            }
						    ]
						}
				       
				]
			},
			{
				id : 'card-2',
				xtype : 'container',
				layout : 'vbox',
				margin: '100 30',
				items: [
				        {
				        	xtype: 'label',
				        	cls : 'title-cls',
				        	margin: '10 30',
				        	text: 'Your Order successfully placed'				        	
				        },
				        {
				        	xtype: 'label',
				        	cls : 'cost-cls',
				        	itemId: 'finalcostlbl',				        
				        	margin: '0 30'
				        	
				        },
				        {
				        	xtype: 'label',
				        	cls : 'ref-cls',
				        	itemId: 'refCodelbl',				        
				        	margin: '0 30'
				        	
				        },
				        { 
			            	xtype: 'button', 
			            	html: '<i class="fa fa-print fa-2x" > Print</i>',
			            	itemId: 'printBtn',
			            	margin: '50 30',
			            	border: 1,
			            	style: {
			            	    borderColor: 'black',
			            	    borderStyle: 'solid'
			            	},
			            	scope: this
			            }
				        
				        
				]        
			}
			]

		});

		me.callParent(arguments);

	}

});
