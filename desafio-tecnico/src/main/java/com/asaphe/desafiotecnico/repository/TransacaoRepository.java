package com.asaphe.desafiotecnico.repository;

import com.asaphe.desafiotecnico.request.TransacaoRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransacaoRepository {

    private List<TransacaoRequest> transacoes = new ArrayList<>();

    //Metodo para adicionar
    public void add(TransacaoRequest transacaoRequest) {
        transacoes.add(transacaoRequest);
    }
}
