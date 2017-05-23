package com.niit.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.jsse.openssl.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CartDAO;
import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.domain.Cart;
import com.niit.shoppingcart.domain.Product;


@Controller
public class CartController {
	Logger log = LoggerFactory.getLogger(CartController.class);
	
	@Autowired
	private CartDAO cartDAO;

	@Autowired
	private Cart Cart;

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private HttpSession session;

	@RequestMapping(value = "/Cart", method = RequestMethod.GET)
	public String Cart(Model model) {
		log.debug("Starting of the method Cart");
		model.addAttribute("Cart", Cart);
		
		String loggedInUserid = (String) session.getAttribute("loggedInUserID");

				if(loggedInUserid!=null)
		{


		int cartSize = cartDAO.list(loggedInUserid).size();

		if (cartSize == 0) {
			model.addAttribute("errorMessage", "You do not have any products in your Cart");
		} else {
			model.addAttribute("cartList", cartDAO.list(loggedInUserid));
			model.addAttribute("totalAmount", cartDAO.getTotalAmount(loggedInUserid));
			model.addAttribute("displayCart", "true");
			model.addAttribute("cart",Cart);

		}
		
		}
		log.debug("Ending of the method Cart");
		return "/home";
	}

	
	@RequestMapping(value = "/Cart/add/{id}", method = RequestMethod.GET)
	
	public ModelAndView addToCart(@PathVariable("id") String id) 
	{
		log.debug("Starting of the method addToCart");
		
	    Product product = productDAO.get(id);
		Cart.setPrice(product.getPrice());
		Cart.setUser_name(product.getName());
		String loggedInUserid = (String) session.getAttribute("loggedInUserID");
		if (loggedInUserid == null) 
		{

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			loggedInUserid = auth.name();
		}	
		
	
	Cart setID(loggedInUserid);
	
	Cart.setStatus('N');
	
	Cart.setQuantity(1);
	Cart.setAddedDate( new Date(  System.currentTimeMillis()) );
	
	if(cartDAO.getCart(loggedInUserid, Cart.getProduct_name()) !=null)
	{
		 int presentQuantity = cartDAO.getQuantity(loggedInUserid, Cart.getProduct_name());
				 Cart.setQuantity(presentQuantity + 1);	 
		 cartDAO.update(Cart);
				 
	}
	
	{
		cartDAO.save(Cart);
		
		
	}
	cartDAO.save(Cart);


	ModelAndView mv = new ModelAndView("redirect:/home");
	mv.addObject("successMessage", " Successfuly add the product to Cart");
	log.debug("Ending of the method addToCart");
	return mv;

	}
}




