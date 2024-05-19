package br.com.controle.certo.domain.service.CategoryService;

import br.com.controle.certo.infrastructure.entrypoint.model.response.ResponseCategory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GetCategoryService {

    ResponseCategory getCategoryById();
    List<ResponseCategory> getAllCategory(String userDocument);
}
