package com.utfpr.APPFinanceiro.model.dao;

import java.util.List;

import com.utfpr.APPFinanceiro.model.entity.Balance;

public interface IBalanceDAO {

    public boolean salvar(Balance c);
    public boolean atualizar(Balance c);
    public boolean remover(Balance c);
    public List<Balance> listar();

}
