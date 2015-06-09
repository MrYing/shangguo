package com.shangguo.model.goods;

public class Goods {
    private int goodsID;               //商品编号
    private int gategoryID;            //类别编号
    private String goodsName;          //商品名称
    private int goodsOrder;            //排序
    private float originalPrice;       //原价
    private float presentPrice;        //现价
    private float orderPrice;          //预定价格
    private float amount;              //数量
    private String status;             //状态
    private int inventory;             //库存
    private String introduce;          //商品简介
    private String imageUrl;           //小图url
    private String creatTime;          //创建时间
    private String product_text;       //商品详情
    private int browseTimes;           //浏览次数
    
    public Goods(){
    	
    }
    
	public int getGoodsID() {
		return goodsID;
	}
	public void setGoodsID(int goodsID) {
		this.goodsID = goodsID;
	}
	public int getGategoryID() {
		return gategoryID;
	}
	public void setGategoryID(int gategoryID) {
		this.gategoryID = gategoryID;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getGoodsOrder() {
		return goodsOrder;
	}
	public void setGoodsOrder(int goodsOrder) {
		this.goodsOrder = goodsOrder;
	}
	public float getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(float originalPrice) {
		this.originalPrice = originalPrice;
	}
	public float getPresentPrice() {
		return presentPrice;
	}
	public void setPresentPrice(float presentPrice) {
		this.presentPrice = presentPrice;
	}
	public float getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(float orderPrice) {
		this.orderPrice = orderPrice;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getInventory() {
		return inventory;
	}
	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}
	public String getProduct_text() {
		return product_text;
	}
	public void setProduct_text(String product_text) {
		this.product_text = product_text;
	}
	public int getBrowseTimes() {
		return browseTimes;
	}
	public void setBrowseTimes(int browseTimes) {
		this.browseTimes = browseTimes;
	}
    
}
