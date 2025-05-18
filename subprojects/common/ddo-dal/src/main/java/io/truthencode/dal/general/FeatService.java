package io.truthencode.dal.general;

import io.truthencode.ddo.dal.entity.Feat;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;


/**
 * Service for managing and validating feats in the application.
 * This service provides validation functionality for feat entities.
 */
/**
 * Service for managing and validating feats in the application.
 * Provides validation and lifecycle management for feat entities.
 */
@ApplicationScoped
public class FeatService {

    /**
         * Default constructor for FeatService.
         * Required by JPA for creating instances of the service.
         */
    public FeatService() {
        // JPA Required Default constructor
    }

    /**
     * Validates a feat.
     * @param feat The feat to validate.
     */
    public void validateFeat(@Valid Feat feat) {
        // your business logic here
    }
}
