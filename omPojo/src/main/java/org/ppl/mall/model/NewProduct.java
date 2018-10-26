package org.ppl.mall.model;

import java.io.Serializable;

/**
 * 首页New-Product视图
 * @author PPL
 */
public class NewProduct implements Serializable {

    private Long id;
    private String title;
    private Long price;
    private String image;
    private String catName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getImage() {
        //只取第1张图片
        return image.split(",")[0];
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
