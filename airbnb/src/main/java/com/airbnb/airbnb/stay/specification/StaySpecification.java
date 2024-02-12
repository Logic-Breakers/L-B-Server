package com.airbnb.airbnb.stay.specification;

import com.airbnb.airbnb.stay.entity.Stay;
import org.springframework.data.jpa.domain.Specification;

public class StaySpecification {

    public static Specification<Stay> equalPlaceType(Stay.PlaceType placeType) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("placeType"), placeType));
    }

    public static Specification<Stay> equalGuestFavourite(boolean guestFavourite) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("guestFavourite"), guestFavourite));
    }

    public static Specification<Stay> filterByPriceRange(Long minPrice, Long maxPrice) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("price"), minPrice, maxPrice));
    }

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
