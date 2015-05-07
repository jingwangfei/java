package com.order;

import java.util.Date;
import java.util.List;

import com.beanutils.User;

public class Order {
	/*
	 * 订单表
	 */

	/**
	 *  主键, 固定16位
	 */
	private String id; 
	
	/**
	 * 所属用户
	 */
	private User uId; // Integer uId;
	
	/**
	 * 订单详情项
	 */
	private List<OrderItem> items;

	/**
	 * 收货地址
	 */
	private String address;
	
	/**
	 * 总价格
	 */
	private Double total;

	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 订单状态
	 */
	private byte order_status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUId() {
		return uId;
	}

	public void setUId(User uId) {
		this.uId = uId;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public byte getOrder_status() {
		return order_status;
	}

	public void setOrder_status(byte orderStatus) {
		order_status = orderStatus;
	}

}
