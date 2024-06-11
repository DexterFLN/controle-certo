package br.com.controle.certo.infrastructure.gateway.category;

import br.com.controle.certo.application.gateway.category.DeleteCategoryGateway;
import br.com.controle.certo.infrastructure.repository.impl.DbCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteCategoryGatewayImpl implements DeleteCategoryGateway {
    @Autowired
    private DbCategoryRepository repository;
    @Override
    public void deleteCategoryById(Integer idCategory) {
        repository.deleteById(idCategory);
    }
}
