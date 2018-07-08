package com.yanda.entity.generated;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_product_info")
public class ProductInfo implements Serializable {
    /**
     * 会员卡类型id
     */
    @Id
    private Integer id;

    /**
     * 类型名称
     */
    private String name;

    /**
     * 计算方式  1-年 2-月 3-天
     */
    @Column(name = "time_unit")
    private Integer timeUnit;

    /**
     * 计数数量
     */
    @Column(name = "time_num")
    private Integer timeNum;

    @Column(name = "current_price")
    private BigDecimal currentPrice;

    @Column(name = "old_price")
    private BigDecimal oldPrice;

    /**
     * 商品类型
     */
    private String product;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 排序号
     */
    @Column(name = "product_order")
    private Integer productOrder;

    private static final long serialVersionUID = 1L;

    public ProductInfo(Integer id, String name, Integer timeUnit, Integer timeNum, BigDecimal currentPrice, BigDecimal oldPrice, String product, Date createTime, Integer productOrder) {
        this.id = id;
        this.name = name;
        this.timeUnit = timeUnit;
        this.timeNum = timeNum;
        this.currentPrice = currentPrice;
        this.oldPrice = oldPrice;
        this.product = product;
        this.createTime = createTime;
        this.productOrder = productOrder;
    }

    public ProductInfo() {
        super();
    }

    /**
     * 获取会员卡类型id
     *
     * @return id - 会员卡类型id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置会员卡类型id
     *
     * @param id 会员卡类型id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取类型名称
     *
     * @return name - 类型名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置类型名称
     *
     * @param name 类型名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取计算方式  1-年 2-月 3-天
     *
     * @return time_unit - 计算方式  1-年 2-月 3-天
     */
    public Integer getTimeUnit() {
        return timeUnit;
    }

    /**
     * 设置计算方式  1-年 2-月 3-天
     *
     * @param timeUnit 计算方式  1-年 2-月 3-天
     */
    public void setTimeUnit(Integer timeUnit) {
        this.timeUnit = timeUnit;
    }

    /**
     * 获取计数数量
     *
     * @return time_num - 计数数量
     */
    public Integer getTimeNum() {
        return timeNum;
    }

    /**
     * 设置计数数量
     *
     * @param timeNum 计数数量
     */
    public void setTimeNum(Integer timeNum) {
        this.timeNum = timeNum;
    }

    /**
     * @return current_price
     */
    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    /**
     * @param currentPrice
     */
    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    /**
     * @return old_price
     */
    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    /**
     * @param oldPrice
     */
    public void setOldPrice(BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
    }

    /**
     * 获取商品类型
     *
     * @return product - 商品类型
     */
    public String getProduct() {
        return product;
    }

    /**
     * 设置商品类型
     *
     * @param product 商品类型
     */
    public void setProduct(String product) {
        this.product = product == null ? null : product.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取排序号
     *
     * @return product_order - 排序号
     */
    public Integer getProductOrder() {
        return productOrder;
    }

    /**
     * 设置排序号
     *
     * @param productOrder 排序号
     */
    public void setProductOrder(Integer productOrder) {
        this.productOrder = productOrder;
    }

    public enum Col {
        id("id"),
        name("name"),
        timeUnit("time_unit"),
        timeNum("time_num"),
        currentPrice("current_price"),
        oldPrice("old_price"),
        product("product"),
        createTime("create_time"),
        productOrder("product_order");

        private final String column;

        public String value() {
            return this.column;
        }

        public String getValue() {
            return this.column;
        }

        Col(String column) {
            this.column = column;
        }

        public String desc() {
            return this.column + " DESC";
        }

        public String asc() {
            return this.column + " ASC";
        }
    }
}