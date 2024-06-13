package br.com.controle.certo.application.usecase.category;

import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestCategory;
import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseCategory;

public interface PostCategoryUseCase {
    ResponseCategory postCategory(RequestCategory body, String userDocument);
}
