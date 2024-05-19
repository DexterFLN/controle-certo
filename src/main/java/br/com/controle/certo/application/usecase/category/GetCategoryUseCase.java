package br.com.controle.certo.application.usecase.category;

import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseCategory;

public interface GetCategoryUseCase {

    ResponseCategory getCategoryById(String userDocument, Integer idCategory);
}
