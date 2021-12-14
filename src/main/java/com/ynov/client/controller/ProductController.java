package com.ynov.client.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ynov.client.model.Product;
import com.ynov.client.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public String productsPage(Model model, HttpSession session) {
		String token = (String) session.getAttribute("token");
		if (token == null) {
			return "unauthorized";
		} else {
			List<Product> products = productService.getProducts(token);
			model.addAttribute("products", products);
			return "products";
		}
	}
	
	@GetMapping("/products/{id}")
	public String productPage(@PathVariable(name = "id") Integer id, Model model, HttpSession session) {
		String token = (String) session.getAttribute("token");
		Product product = productService.getProductById(id, token);
		model.addAttribute("product", product);
		return "product";
	}
	
	@PostMapping("/products")
	public ModelAndView createNewProduct(@ModelAttribute Product product, HttpSession session) {
		String token = (String) session.getAttribute("token");
		productService.save(product, token);
		return new ModelAndView("redirect:/products");
	}
	
	@GetMapping("/newProduct")
	public String newProductPage(Model model) {
		model.addAttribute("product", new Product());
		return "newProduct";
	}
}
