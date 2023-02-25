package com.lewis.msuser.services.contracts;

import com.lewis.msuser.entities.domain.Category;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryService {
    List<Category> findAll();

    Optional<Category> findById(UUID id);
}
