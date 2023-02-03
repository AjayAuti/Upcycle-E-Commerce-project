package com.upcycle.Entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int prodid;
	private String pname;
	private String brand;
	private String category;
	private String subcat;
	private int price;
	private String photo;
	@CreationTimestamp
	@Column(name = "created_timestamp", insertable = false, updatable = false)
	private LocalDateTime LocalDateTime;
	@ManyToOne
	@JoinColumn(name="sellerId")
	private Seller seller;
	
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
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		System.out.println("set photo");
		this.photo = photo;
	}
	public LocalDateTime getLocalDateTime() {
		return LocalDateTime;
	}
	public void setLocalDateTime(java.time.LocalDateTime LocalDateTime) {
		this.LocalDateTime = LocalDateTime;
	}
	
	public Seller getSeller() {
		return seller;
	}
	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	@Transient
	public String getProductPhotoPath() {
		if(photo == null) return null;
		System.out.println("/proudct-images/"+prodid+"/"+photo);
		return "/proudct-images/"+prodid+"/"+photo;
		
	}
	@Override
	public String toString() {
		return "Product [prodid=" + prodid + ", pname=" + pname + ", category=" + category + ", subcat=" + subcat + ", price="
				+ price + ", photo=" + photo + ", LocalDateTime=" + LocalDateTime + "]";
	}
}
