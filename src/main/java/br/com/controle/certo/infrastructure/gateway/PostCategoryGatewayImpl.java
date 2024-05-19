package br.com.controle.certo.infrastructure.gateway;

import br.com.controle.certo.application.gateway.PostCategoryGateway;
import br.com.controle.certo.domain.entities.CategoryEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestCategory;
import br.com.controle.certo.infrastructure.repository.impl.DbCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static br.com.controle.certo.infrastructure.mappers.CategoryMapper.dbCategoryToCategoryEntity;
import static br.com.controle.certo.infrastructure.mappers.CategoryMapper.requestToDbCategory;

@Component
public class PostCategoryGatewayImpl implements PostCategoryGateway {
    @Autowired
    private DbCategoryRepository repository;

    @Override
    public CategoryEntity postCategoryGateway(RequestCategory body) {
        return dbCategoryToCategoryEntity(repository.save(requestToDbCategory(body)));
    }
}
