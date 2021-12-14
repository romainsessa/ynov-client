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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ynov.client.model.Product;
import com.ynov.client.service.ProductService;

@Controller
@RequestMapping("private")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public String productsPage(Model model, HttpSession session) {
		List<Product> products = productService.getProducts();
		model.addAttribute("products", products);
		return "products";
	}
	
	@GetMapping("/products/{id}")
	public String productPage(@PathVariable(name = "id") Integer id, Model model) {
		Product product = productService.getProductById(id);
		model.addAttribute("product", product);
		return "product";
	}
	
	@PostMapping("/products")
	public ModelAndView createNewProduct(@ModelAttribute Product product) {
		productService.save(product);
		return new ModelAndView("redirect:/products");
	}
	
	@GetMapping("/newProduct")
	public String newProductPage(Model model) {
		model.addAttribute("product", new Product());
		return "newProduct";
	}
}
