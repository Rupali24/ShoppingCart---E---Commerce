package com.niit.shoppingcart.dao;

import java.util.List;

public interface OrdersDAO {
	
	public <Orders> boolean save(Orders orders);
	
	  public <Orders> boolean update(Orders orders);
	  
	  public <Orders> boolean delete(Orders orders);
	  
	  public <Orders> Orders get(String id);
	  
	  public <Orders> List<Orders> list();
	  
       
}


