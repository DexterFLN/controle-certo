package br.com.controle.certo.infrastructure.entrypoint.handler;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiValidationError {
    private Integer code;
    private String message;
}
