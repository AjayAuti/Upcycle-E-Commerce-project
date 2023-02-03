package com.upcycle.Services;

import java.util.List;

import com.upcycle.Entity.Order;
import com.upcycle.Entity.OrderDetails;


public interface OrderdetailService {

	void saveOrderDetails(OrderDetails od);
	OrderDetails findById(int id);
	List<OrderDetails> findByOrder(Order order);
}
