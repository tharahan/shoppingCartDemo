Ext.define('demo.view.reviewPanel', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.reviewPanel',
	
	id: 'reviewPanel',
	
	
	
	

	initComponent : function() {
		
		var me = this;
		
		this.store = Ext.StoreManager.get('demo.store.Purchases');

		this.columns = [ 
		
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
	             header: 'Quantity', 	            
	             flex: 1,
	             dataIndex : 'quantity'
			},
			{
	             header: 'Total', 	            
	             flex: 1,
	             dataIndex : 'total'
			}
		
		];
		
		

		this.callParent(arguments);
	}
});