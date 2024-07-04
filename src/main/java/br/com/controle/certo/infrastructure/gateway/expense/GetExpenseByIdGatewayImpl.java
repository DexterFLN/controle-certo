package br.com.controle.certo.infrastructure.gateway.expense;

import br.com.controle.certo.infrastructure.repository.impl.DbExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetExpenseByIdGatewayImpl {
    @Autowired
    private DbExpenseRepository repository;


}
