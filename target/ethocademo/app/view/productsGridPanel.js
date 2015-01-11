Ext.define('demo.view.productsGridPanel', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.productsGridPanel',
	
	id: 'productsGridPanel',
	
	
	selModel: Ext.create('Ext.selection.CheckboxModel', {
							mode: 'MULTI', 
							checkOnly: true
				}),
	
    plugins: [
        Ext.create('Ext.grid.plugin.CellEditing', {
            clicksToEdit: 1
        })
    ],
	
	

	initComponent : function() {
		
		var me = this;
		
		this.store = Ext.StoreManager.get('demo.store.Products');

		this.columns = [ 
			{
				header : 'Product Code',
				dataIndex : 'code',
				flex : 1
			}, 
			{
				header : 'Product Name',
				dataIndex : 'name',
				flex : 2
			}, 
			{
				header : 'Product Price',
				dataIndex : 'price',
				flex : 1
			},
			{
	             header: 'Enter Qty', 	            
	             flex: 1,
	             dataIndex : 'quantity',
	             editor: {
	                    xtype: 'textfield',
	                    allowBlank: true
	             }
			},
		
		];
		
		

		this.callParent(arguments);
	}
});