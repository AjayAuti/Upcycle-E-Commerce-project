package com.upcycle.Services;

import java.util.List;

import com.upcycle.Entity.Customer;
import com.upcycle.Entity.Order;


public interface OrderService {

	Order saveOrder(Order order);
	List<Order> getAllOrders();
	List<Order> getCustomerOrders(Customer customer);
	Order findById(int id);
}
