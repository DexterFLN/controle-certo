package br.com.controle.certo.application.gateway.category;

import br.com.controle.certo.domain.entities.CategoryEntity;
import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestCategory;

public interface PostCategoryGateway {
    CategoryEntity postCategoryGateway(RequestCategory body);

}