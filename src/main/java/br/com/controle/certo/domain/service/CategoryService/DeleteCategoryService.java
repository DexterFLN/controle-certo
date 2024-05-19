package br.com.controle.certo.domain.service.CategoryService;

import br.com.controle.certo.application.gateway.usercategory.DeleteUserCategoryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public interface DeleteCategoryService {


    void deleteCategory(String userDocument, Integer idCategory);
}
