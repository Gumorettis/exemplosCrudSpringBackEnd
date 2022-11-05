package com.example.cadastro2.Model.entidades;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component //deixar o spring para criar o modelo de objeto da classe
@Getter
@Setter
public class TratamentoMensagem {
    
    private String mensagem;
    
}
