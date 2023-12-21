package com.airbnb.airbnb;

import com.airbnb.airbnb.entity.Stay;
import org.springframework.data.jpa.domain.Specification;

public class StaySpecification {

    public static Specification<Stay> equalBeds(Long beds) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("beds"), beds));
    }
    public static Specification<Stay> equalCountry(String country) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("country"), country));
    }

    public static Specification<Stay> equalBathrooms(Long bathrooms) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("bathrooms"), bathrooms));
    }

    public static Specification<Stay> equalPropertyType(Stay.PropertyType propertyType) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("propertyType"), propertyType));
    }
}
