 package com.upcycle.Services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.upcycle.Dao.ProductDao;
import com.upcycle.Entity.Product;

@Service
public class ProductServiceImpl implements ProductService{
	 
	@Autowired ProductDao dao;
	
	@Autowired SellerService sellerService;
	@Override
	public void addProduct(Product p, @RequestParam("photo") MultipartFile pic) {
		
		String fileName=StringUtils.cleanPath(pic.getOriginalFilename());
		p.setPhoto(fileName);
		Product addProduct = dao.save(p);
		
		String uploadDir = "./product-images/"+addProduct.getProdid();
		
		Path uploadPath = Paths.get(uploadDir);
		
		if(!Files.exists(uploadPath)) {
			try {
				Files.createDirectories(uploadPath);
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try (InputStream inputStream = pic.getInputStream()){
			Path filePath = uploadPath.resolve(fileName);
			System.out.println(filePath.toFile().getAbsolutePath());
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING); 
			/*
			 * System.out.println(ServletUriComponentsBuilder.fromCurrentContextPath()
			 * .path("/product-images/"+addProduct.getProdid()+"/").path(pic.
			 * getOriginalFilename()).toUriString());
			 */
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		System.out.println("Photo Added successfully");
	}

	@Override
	public List<Product> findProducts(int sellerId) {
		
		return dao.findBySeller(sellerService.findById(sellerId),Sort.by(Sort.Direction.DESC,"prodid"));
	}

	@Override
	public void updateProduct(Product p) {
		Product pp=dao.getById(p.getProdid());
		p.setSeller(pp.getSeller());
		dao.save(p);
	}

	@Override
	public void deleteProduct(int prodid) {
		// TODO Auto-generated method stub
		Product p=dao.getById(prodid);
		dao.delete(p);
	}

	@Override
	public List<Product> allProducts() {
		// TODO Auto-generated method stub
		return dao.findAll(Sort.by(Sort.Direction.DESC,"prodid"));
	}

	@Override
	public Product findProductById(int prodid) {		
		return dao.getById(prodid);
	}

	@Override
	public List<Product> categoryProducts(String category) {
		return dao.findByCategory(category, Sort.by(Sort.Direction.DESC,"prodid"));
	}

	@Override
	public Page<Product> allProductsPaginated(int page,int pagesize) {
		Page<Product> prods=dao.findAll(PageRequest.of(page, pagesize,Sort.by(Direction.DESC, "prodid")));
		System.err.println(prods.getSize());
		return prods;
	}

	@Override
	public List<Product> searchProducts(String query) {
		List<Product> products = dao.searchProducts(query);
		return products;
	}
}
