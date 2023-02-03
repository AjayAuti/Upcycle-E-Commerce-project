package com.upcycle.Model;

import org.springframework.beans.BeanUtils;

import com.upcycle.Entity.Product;



public class ProductResponseDTO {
	

	private String brand;
	private int prodid;
	private String pname;
	private String category;
	private String subcat;
	private int price;
	private int sellerId;
	private String sellerName;
	private String photo;
	
	
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getProdid() {
		return prodid;
	}
	public void setProdid(int prodid) {
		this.prodid = prodid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getcategory() {
		return category;
	}
	public void setcategory(String category) {
		this.category = category;
	}
	public String getSubcat() {
		return subcat;
	}
	public void setSubcat(String subcat) {
		this.subcat = subcat;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getSellerId() {
		return sellerId;
	} 
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		System.out.println("set photo");
		this.photo = photo;
	}
	
	public static ProductResponseDTO fromEntity(Product entity) {
		ProductResponseDTO dto = new ProductResponseDTO();
		dto.setSellerId(entity.getSeller().getId());
		dto.setSellerName(entity.getSeller().getName());
		BeanUtils.copyProperties(entity, dto);
		
		return dto;
	}
}
