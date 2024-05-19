package br.com.controle.certo.domain.service;

import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestCategory;
import org.springframework.stereotype.Component;

@Component
public interface PutCategoryService {
    void updateCategory(String userDocument, Integer idCategory, RequestCategory body);
}
