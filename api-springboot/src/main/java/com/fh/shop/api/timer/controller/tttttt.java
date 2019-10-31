package com.fh.shop.api.timer.controller;

import com.fh.shop.api.product.biz.IProductService;
import com.fh.shop.api.product.po.Product;
import com.fh.shop.api.util.EmailUtil;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

//定时器类
@Component
@EnableScheduling
public class tttttt {
    @Resource
    private IProductService productService;

@Scheduled(cron = "* * * * */10 ?")
public void getStock(){

    List<Product> list = productService.getStock();
    if(list.size() <=0){
        return;
    }
    String str ="<h1>商品不足列表</h1>";
    str+="<table border=\"1px\" cellpadding=\"0px\" cellspacing=\"0px\" style=\"text-align:center\">\n" +
            "\t<tr>\n" +
            "\t\t<td>商品名</td>\n" +
            "\t\t<td>商品价格</td>\n" +
            "\t\t<td>商品剩余数量</td>\n" +
            "\t</tr>\n" ;

    for (int i = 0; i < list.size(); i++) {
        Product product = list.get(i);
        str += "<tr>\n" +
                "\t\t<td>"+product.getProductName()+"</td>\n" +
                "\t\t<td>"+product.getPrice().toString()+"</td>\n" +
                "\t\t<td>"+product.getStock()+"</td>\n" +
                "\t</tr>";
    }
    str += "</table>";
    EmailUtil.postEmail("2394946889@qq.com",str,"王豪");


}


}
