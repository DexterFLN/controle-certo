package br.com.controle.certo.domain.service.CategoryService;

import br.com.controle.certo.domain.entities.CategoryEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestCategory;
import org.springframework.stereotype.Component;

@Component
public interface PostCategoryService {
    void saveCategoryUser(CategoryEntity body, String document);
}
