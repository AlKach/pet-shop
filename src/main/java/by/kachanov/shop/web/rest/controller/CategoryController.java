package by.kachanov.shop.web.rest.controller;

import by.kachanov.shop.dto.Category;
import by.kachanov.shop.dto.condition.Expression;
import by.kachanov.shop.service.CategoryService;
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
@Api("Categories")
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("Create category")
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<BigDecimal> addCategory(@RequestBody Category category) {
        categoryService.saveCategory(category);
        return new ResponseEntity<>(category.getId(), HttpStatus.CREATED);
    }

    @ApiOperation("Modify category")
    @RequestMapping(value = "/{categoryId}", method = RequestMethod.PUT)
    @ResponseBody
    public BigDecimal modifyCategory(@PathVariable("categoryId") BigDecimal categoryId, @RequestBody Category category) {
        Category oldCategory = categoryService.getCategory(categoryId);
        BeanUtils.copyProperties(category, oldCategory, "id");
        categoryService.saveCategory(oldCategory);
        return oldCategory.getId();
    }

    @ApiOperation("Get category")
    @RequestMapping(value = "/{categoryId}", method = RequestMethod.GET)
    @ResponseBody
    public Category getCategory(@PathVariable("categoryId") BigDecimal categoryId) {
        return categoryService.getCategory(categoryId);
    }

    @ApiOperation("Get categories list")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public List<Category> getCategories(@RequestBody(required = false) Expression selector) {
        return categoryService.getCategories(selector);
    }

    @ApiOperation("Delete category")
    @RequestMapping(value = "/{categoryId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteCategory(@PathVariable("categoryId") BigDecimal categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
