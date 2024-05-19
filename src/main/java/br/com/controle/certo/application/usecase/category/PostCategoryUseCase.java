package br.com.controle.certo.application.usecase.category;

import br.com.controle.certo.infrastructure.entrypoint.model.request.RequestCategory;

public interface PostCategoryUseCase {
    void saveCategory(RequestCategory body, String userDocument);
}
