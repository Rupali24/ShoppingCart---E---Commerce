package com.niit.shoppingcart.dao;

import java.util.List;

import com.niit.shoppingcart.domain.Cart;

public interface CartDAO 
{
	public boolean save(Cart c);
	
	public boolean update(Cart c);
	
	public List <Cart> list(String loggedInUserid);
	
	public Cart get(String id);
	
	public Object getTotalAmount(String loggedInUserid);

	public Object getCart(String loggedInUserid, String product_name);

	public int getQuantity(String loggedInUserid, String product_name);

}