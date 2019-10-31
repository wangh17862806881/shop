package com.fh.shop.admin.common;

public enum ProductExcelDownLoad {

    productName(1,"商品名","productName"),
    price(2,"商品价格","price"),
    productDate(3,"生产日期","productDate"),
    brandName(4,"品牌名","brandName"),



    ;


    private Integer index;

    private String parameName;

    private String filed;

    private ProductExcelDownLoad(Integer index,String parameName,String filed){
        this.index = index;
        this.parameName = parameName;
        this.filed = filed;

    }

    public Integer getIndex() {
        return index;
    }

    public String getParameName() {
        return parameName;
    }

    public String getFiled() {
        return filed;
    }
}
