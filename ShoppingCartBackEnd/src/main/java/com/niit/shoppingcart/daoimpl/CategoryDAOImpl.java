package com.niit.shoppingcart.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.domain.Category;

@Repository("categoryDAO")  
@Transactional
public class CategoryDAOImpl  implements CategoryDAO{
	
	@Autowired private SessionFactory sessionFactory;
	
	
	public CategoryDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory= sessionFactory;
	}
	
	
	private Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}

	public List<Category> list() 
	{
		 return getCurrentSession().createQuery("from Category").list();
	}

	public boolean save(Category category) 
	{
		try
		{
		getCurrentSession().save(category);
		}
		catch (Exception ex) 
		{
		ex.printStackTrace();
		return true;
		}
		
		return false;
	}

	public boolean update(Category category) 
	{
		try
		{
		getCurrentSession().update(category);
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		return true;
		}
		
		return false;
	}

	public boolean delete(String id) 
	{
		try
		{
		getCurrentSession().delete(getCategoryByID(id));
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		return true;
		}
		
		return false;
	}

	public Category getCategoryByID(String id) 
	{
		
		 return  (Category) getCurrentSession().get(Category.class, id);
		
	}

	public Category getCategoryByName(String name) {
		
	
		
		Query query=  getCurrentSession().createQuery("from Category where name =?");
		query.setString(0, name);
		
		
	  return (Category)	query.uniqueResult();
		
	}

}









