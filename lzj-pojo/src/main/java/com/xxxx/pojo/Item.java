package com.xxxx.pojo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    private String id;
    private String title; // 标题，字符串
    private Long sales; // 销量，数学
    private Boolean recommendation; // 是否为热门，布尔
    private Byte recoSort; // 热门排序|权重，数学
    private String city; // 所属城市，字符串
    private Long price; // 价格，数学, 单位是元
    private String rentType; // 租赁方式，整租，合租等。 字符串
    private String houseType; // 房屋面积，字符串
    private Map<String, String> info; // 房屋特性， Map集合。集合存储数据内容为： years: "建造年份", type: "房屋类型，几室几厅", level: "所在楼层", style: "装修标准", orientation: "房屋 朝向"
    private List<String> imgs; // 图片集合。字符串数组或集合

    private Date buytime; // 购买时间
    private Boolean isRented; // 是否已出租

    // 推导属性：商品图片
    public String getImg() {
        return imgs.get(0);
    }

    // 推导属性：跳转链接
    public String getLink() {
        return "/details/" + id;// 商品详情接口
    }

    // 拼接楼层、房型、面积，用于搜索
    public String getHouseTypeForSearch() {
        return info.get("level") + " | " + info.get("type") + " - " + houseType; // "楼层 | 几室几厅 - 面积"
    }

    // getter和setter方法
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getSales() {
        return sales;
    }

    public void setSales(Long sales) {
        this.sales = sales;
    }

    public Boolean getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(Boolean recommendation) {
        this.recommendation = recommendation;
    }

    public Byte getRecoSort() {
        return recoSort;
    }

    public void setRecoSort(Byte recoSort) {
        this.recoSort = recoSort;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getRentType() {
        return rentType;
    }

    public void setRentType(String rentType) {
        this.rentType = rentType;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public Map<String, String> getInfo() {
        return info;
    }

    public void setInfo(Map<String, String> info) {
        this.info = info;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public Date getBuytime() {
        return buytime;
    }

    public void setBuytime(Date buytime) {
        this.buytime = buytime;
    }

    public Boolean getIsRented() {
        return isRented;
    }

    public void setIsRented(Boolean isRented) {
        this.isRented = isRented;
    }
}
