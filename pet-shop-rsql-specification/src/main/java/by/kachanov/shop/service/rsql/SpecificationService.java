package by.kachanov.shop.service.rsql;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationService {

    <T>Predicate buildPredicate(String query, Root<T> queryRoot, CriteriaBuilder criteriaBuilder);

    <T> Specification<T> buildSpecification(String query);

}
