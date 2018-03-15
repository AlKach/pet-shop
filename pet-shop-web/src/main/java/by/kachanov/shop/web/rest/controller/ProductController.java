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

import java.math.BigInteger;
import java.util.List;

@RestController
@Api("Products")
@RequestMapping("/products")
public class ProductController extends AbstractController {

    @Autowired
    private ProductService productService;

    @ApiOperation("Create product")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<BigInteger> addProduct(@RequestBody Product product) {
        productService.saveProduct(product);
        return new ResponseEntity<>(product.getId(), HttpStatus.CREATED);
    }

    @ApiOperation("Modify product")
    @RequestMapping(value = "/{productId}", method = RequestMethod.PUT)
    public BigInteger modifyProduct(@PathVariable("productId") BigInteger productId, @RequestBody Product product) {
        Product oldProduct = productService.getProduct(productId);
        BeanUtils.copyProperties(product, oldProduct, "id");
        productService.saveProduct(oldProduct);
        return oldProduct.getId();
    }

    @ApiOperation("Get product")
    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable("productId") BigInteger productId) {
        return productService.getProduct(productId);
    }

    @ApiOperation("Get products list by query")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Product> getProducts(@RequestParam(value = "q", required = false) String query) {
        return productService.getProducts(parseQuery(query));
    }

    @ApiOperation("Get products list by query AST")
    @RequestMapping(value = "/ast", method = RequestMethod.POST)
    public List<Product> getProductsAST(@RequestBody(required = false) Expression selector) {
        return productService.getProducts(selector);
    }

    @ApiOperation("Delete product")
    @RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProduct(@PathVariable("productId") BigInteger productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
