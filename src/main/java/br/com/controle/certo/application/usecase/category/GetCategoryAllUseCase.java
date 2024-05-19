package br.com.controle.certo.application.usecase.category;

import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseCategory;

import java.util.List;

public interface GetCategoryAllUseCase {
    List<ResponseCategory> getAllCategory(String userDocument);
}
