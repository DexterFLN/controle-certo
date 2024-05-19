package br.com.controle.certo.infrastructure.gateway.category;

import br.com.controle.certo.application.gateway.PutCategoryGateway;
import br.com.controle.certo.domain.entities.CategoryEntity;
import br.com.controle.certo.infrastructure.repository.impl.DbCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PutCategoryGatewayImpl implements PutCategoryGateway {

    @Autowired
    private DbCategoryRepository repository;

    @Override
    public CategoryEntity updateCategoryById(Integer idCategory, CategoryEntity body) {
        repository.updateCategoryById(body.getCategoryName(), body.getCategoryDescription(), idCategory);
        return body;
    }
}
