Ext.define('demo.model.Product' ,{
    extend	: 'Ext.data.Model',
    
    fields :[
            {
            	name:'code',
            	type:'string'
            },
            {
            	name: 'name',
            	type:'string'
            },
            {
            	name: 'price',
            	type:'string'
            },
            {
            	name: 'quantity',
            	type:'string'
            }
             
    ]
});