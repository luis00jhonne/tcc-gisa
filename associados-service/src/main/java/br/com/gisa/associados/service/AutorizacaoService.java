package br.com.gisa.associados.service;

import br.com.gisa.associados.exceptions.NotFoundException;
import br.com.gisa.associados.model.AutorizacaoExameConsulta;

public interface AutorizacaoService {

    AutorizacaoExameConsulta autorizarExameConsulta(AutorizacaoExameConsulta autorizacaoExameConsulta);
    AutorizacaoExameConsulta consultarExameConsulta(String codigoAutorizacao) throws NotFoundException;
    void processarResultadoConsulta(String payload);
}
