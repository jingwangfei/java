package com.order;

public class OrderDAOImpl extends BaseDAOImpl<Order> {
	
	public static void main(String[] args) {
		Order order = new Order();
		order.setId("11");
		order.setTotal(122d);
		
		new OrderDAOImpl().list(order);
		
	}

}
