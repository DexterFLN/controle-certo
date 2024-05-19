package br.com.controle.certo.infrastructure.gateway;

import br.com.controle.certo.application.gateway.GetCategoryGateway;
import br.com.controle.certo.domain.entities.CategoryEntity;
import br.com.controle.certo.infrastructure.repository.impl.DbCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.com.controle.certo.infrastructure.mappers.CategoryMapper.dbCategoryToCategoryEntity;

@Component
public class GetCategoryGatewayImpl implements GetCategoryGateway {

    @Autowired
    private DbCategoryRepository repository;

    @Override
    public CategoryEntity getCategoryById(String document, Integer idCategory) {
        return dbCategoryToCategoryEntity(repository.getAllCategoryById(document, idCategory));
    }

    @Override
    public List<CategoryEntity> getAllCategory(String document) {
        return dbCategoryToCategoryEntity(repository.getAllCategoryByDocument(document));
    }
}
