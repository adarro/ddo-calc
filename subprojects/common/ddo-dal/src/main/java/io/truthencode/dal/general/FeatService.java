package io.truthencode.dal.general;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;


@ApplicationScoped
public class FeatService {

    public void validateFeat(@Valid Feat feat) {
        // your business logic here
    }
}