package com.upcycle.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upcycle.Entity.Product;
import com.upcycle.Entity.Seller;
import com.upcycle.Model.ProductDTO;
import com.upcycle.Model.ProductPagedResponseDTO;
import com.upcycle.Model.ProductResponseDTO;
import com.upcycle.Model.Response;
import com.upcycle.Services.ProductService;
import com.upcycle.Services.SellerService;



@CrossOrigin
@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired ProductService productService;
	@Autowired SellerService sellerService;
	
	@PostMapping
	public ResponseEntity<?> saveProduct(ProductDTO dto) {
		System.out.println(dto);
		Product product=ProductDTO.toEntity(dto);
		Seller seller=sellerService.findById(dto.getSellerId());
		product.setSeller(seller);
		productService.addProduct(product,dto.getPic());
		return Response.success(product);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> updateProduct(@RequestBody Product product,@PathVariable("id") int id) {
		System.out.println(product);		
		productService.updateProduct(product);
		return Response.success(product);		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findProduct(@PathVariable("id")int id) {
		Product product=productService.findProductById(id);
		return Response.success(ProductResponseDTO.fromEntity(product));
	}
	
	@GetMapping
	public ResponseEntity<?> findAllProducts(Optional<Integer> sellerid) {
		List<ProductResponseDTO> result = new ArrayList<ProductResponseDTO>();
		if(sellerid.isPresent()) {
			System.out.println(sellerid);
			for(Product p : productService.findProducts(sellerid.get())) {
				result.add(ProductResponseDTO.fromEntity(p));
			}
			
		}else {
			for(Product p : productService.allProducts()) {
				result.add(ProductResponseDTO.fromEntity(p));
			}
		}
		
		return Response.success(result);
	}
	
	@GetMapping("/paginated")
	public ResponseEntity<?> paginatedProducts(int page,int pagesize) {
		List<ProductResponseDTO> result = new ArrayList<ProductResponseDTO>();
		Page<Product> data=productService.allProductsPaginated(page, pagesize);
		data.forEach(item-> {
			result.add(ProductResponseDTO.fromEntity(item));
		});
		ProductPagedResponseDTO resp=new ProductPagedResponseDTO();
		resp.setPagesize(pagesize);
		resp.setCurrent(page);
		resp.setTotal(data.getTotalElements());
		resp.setPlist(result);
		return Response.success(resp);
	}
	
	@GetMapping("/cats")
	@Query("SELECT p from Product p WHERE p.name LIKE %?1%"+"OR p.category LIKE %?1%"+"OR p.brand LIKE %?1%")
	public ResponseEntity<?> findCategoryProducts(String cat) {
		List<ProductResponseDTO> result = new ArrayList<ProductResponseDTO>();
		
		for(Product p : productService.categoryProducts(cat)) {
			result.add(ProductResponseDTO.fromEntity(p));
			System.out.println(p);
		}
		
		return Response.success(result);
	}
		
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") int id) {
		productService.deleteProduct(id);
		return Response.status(HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Product>> searchProducts(String query){
		return ResponseEntity.ok(productService.searchProducts(query));
	}
	//8080/api/products/search?query=
	
}
