package by.kachanov.shop.service;

import by.kachanov.shop.service.rsql.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

public class BaseService<T> {

    @Autowired
    private SpecificationService specificationService;

    protected final Specification<T> buildSpecification(String query) {
        return specificationService.buildSpecification(query);
    }

}
