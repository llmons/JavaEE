package cn.edu.xmu.javaee.productdemoaop.controller;

import cn.edu.xmu.javaee.core.model.ReturnObject;
import cn.edu.xmu.javaee.productdemoaop.controller.dto.ProductDto;
import cn.edu.xmu.javaee.productdemoaop.dao.bo.Product;
import cn.edu.xmu.javaee.productdemoaop.service.ProductService;
import cn.edu.xmu.javaee.productdemoaop.util.CloneFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品控制器
 * @author Ming Qiu
 */
@RestController /*Restful的Controller对象*/
@RequestMapping(value = "/products", produces = "application/json;charset=UTF-8")
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);


    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{id}")
    public ReturnObject getProductById(@PathVariable("id") Long id, @RequestParam(required = false, defaultValue = "auto") String type) {
        logger.debug("getProductById: id = {} " ,id);
        ReturnObject retObj = null;
        Product product = null;
        if ("manual".equals(type)){
            product = productService.findProductById_manual(id);
        } else if ("auto".equals(type)) {
            product = productService.retrieveProductByID(id, true);
        }else if ("join".equals(type)){
            product = productService.findProductById_join(id);
        }else{      // with Jpa
            product=productService.retrieveProductByID_Jpa(id,true);
        }
        ProductDto productDto = CloneFactory.copy(new ProductDto(), product);
        retObj = new ReturnObject(productDto);
        return  retObj;
    }



    @GetMapping("")
    public ReturnObject searchProductByName(@RequestParam String name, @RequestParam(required = false, defaultValue = "auto") String type) {
        ReturnObject retObj = null;
        List<Product> productList = null;
        if ("manual".equals(type)){
            productList = productService.findProductByName_manual(name);
        } else if ("auto".equals(type)){
            productList = productService.retrieveProductByName(name, true);
        }else if("join".equals(type)){
            productList = productService.findProductByName_join(name);
        }else {     // with jpa
            productList=productService.retrieveProductByName_Jpa(name,true);
        }
        List<ProductDto> data = productList.stream().map(o->CloneFactory.copy(new ProductDto(),o)).collect(Collectors.toList());
        retObj = new ReturnObject(data);
        return  retObj;
    }
}
