package by.kachanov.shop.web.rest.controller;

import by.kachanov.shop.dto.Product;
import by.kachanov.shop.dto.condition.Expression;
import by.kachanov.shop.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@Api("Products")
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation("Create product")
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public BigDecimal addProduct(@RequestBody Product product) {
        productService.saveProduct(product);
        return product.getId();
    }

    @ApiOperation("Modify product")
    @RequestMapping(value = "/{productId}", method = RequestMethod.PUT)
    @ResponseBody
    public BigDecimal modifyProduct(@PathVariable("productId") BigDecimal productId, @RequestBody Product product) {
        Product oldProduct = productService.getProduct(productId);
        BeanUtils.copyProperties(product, oldProduct, "id");
        productService.saveProduct(oldProduct);
        return oldProduct.getId();
    }

    @ApiOperation("Get product")
    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    @ResponseBody
    public Product getProduct(@PathVariable("productId") BigDecimal productId) {
        return productService.getProduct(productId);
    }

    @ApiOperation("Get products list")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public List<Product> getProducts(@RequestBody(required = false) Expression selector) {
        return productService.getProducts(selector);
    }

    @ApiOperation("Delete product")
    @RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteProduct(@PathVariable("productId") BigDecimal productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
