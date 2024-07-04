package br.com.controle.certo.infrastructure.gateway.expense;

import br.com.controle.certo.application.gateway.expense.PostExpenseGateway;
import br.com.controle.certo.domain.entities.ExpenseEntity;
import br.com.controle.certo.infrastructure.repository.impl.DbCategoryRepository;
import br.com.controle.certo.infrastructure.repository.impl.DbExpenseRepository;
import br.com.controle.certo.infrastructure.repository.impl.DbUserRepository;
import br.com.controle.certo.infrastructure.repository.model.DbCategory;
import br.com.controle.certo.infrastructure.repository.model.DbExpense;
import br.com.controle.certo.infrastructure.repository.model.DbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostExpenseGatewayImpl implements PostExpenseGateway {

    @Autowired
    private DbExpenseRepository repository;
    @Autowired
    private DbUserRepository userRepository;
    @Autowired
    private DbCategoryRepository categoryRepository;

    @Override
    public void saveExpense(List<ExpenseEntity> body) {
        DbUser user = userRepository.getUserByDocument(body.stream().findFirst().map(m -> m.getUserEntity().getDocumentNumber()).orElseThrow());

        List<DbExpense> request = body.stream().map(m -> {
            DbCategory dbCategory = categoryRepository.findById(m.getCategoryEntity().getIdCategory()).orElseGet(null);

            return DbExpense.builder()
                    .uuidExpense(m.getUuidExpense())
                    .expenseDescription(m.getExpenseDescription())
                    .expenseValue(m.getExpenseValue())
                    .currentInstallment(m.getCurrentInstallment())
                    .totalInstallment(m.getTotalInstallment())
                    .expenseType(m.getExpenseType())
                    .dhCreate(m.getDhCreate())
                    .dbCategory(dbCategory)
                    .dbUser(user)
                    .build();
        }).toList();

        repository.saveAll(request);
    }
}
