package com.order;

import java.util.ArrayList;
import java.util.List;

public class User {
	/*
	 * 用户
	 */
	private Integer id;

	/**
	 * 用户的订单列表
	 */
	private List<Order> orderList = new ArrayList<Order>();
	
	private String name;

	private String password;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

}
