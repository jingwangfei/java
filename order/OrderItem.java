package com.order;

public class OrderItem {
	/*
	 * 订单详 项
	 */
	private Integer id;

	/**
	 * 关联商品
	 */
	private Goods goods;

	/**
	 * 下订单时的单价
	 */
	private double price;

	/**
	 * 订单数量
	 */
	private Integer quantity;

	/**
	 * 折扣
	 */
	private double discount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

}
