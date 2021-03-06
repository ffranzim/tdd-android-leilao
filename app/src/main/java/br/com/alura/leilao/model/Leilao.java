package br.com.alura.leilao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.alura.leilao.exception.LanceMenorQueUltimoLanceException;
import br.com.alura.leilao.exception.LanceSeguidoDoMesmoUsuarioException;
import br.com.alura.leilao.exception.UsuarioJaDeuCincoLancesException;

public class Leilao implements Serializable {


    private final String descricao;
    private final List<Lance> lances;
    private double maiorLance = 0.0;
    private double menorLance = 0.0;


    public Leilao(String descricao) {
        this.descricao = descricao;
        this.lances = new ArrayList<>();
    }

    public String getDescricao() {
        return descricao;
    }

    public double getMaiorLance() {
        return maiorLance;
    }

    public double getMenorLance() {
        return menorLance;
    }

    public void propoe(Lance lance) {

        if(maiorLance >= lance.getValor()) {
            throw new LanceMenorQueUltimoLanceException();
        }

        valida(lance);

        lances.add(lance);
        if(lances.size() == 1) {
            maiorLance = lance.getValor();
            menorLance = lance.getValor();
            Collections.sort(lances);
            return;
        }

        double lanceValor = lance.getValor();


        calculaMaiorLance(lance, lanceValor);
        calculaMenorLance(lance, lanceValor);
    }

    private void valida(Lance lance) {
        if(!lances.isEmpty()){
            Usuario usuarioLanceAtual = lance.getUsuario();

            if (lanceMenorQueMaiorLance(usuarioLanceAtual)) {
                throw new LanceMenorQueUltimoLanceException();
            }

            if (usuarioJaDeuCincoLances(lance)) {
                throw new UsuarioJaDeuCincoLancesException();
            }
        }
    }

    private boolean usuarioJaDeuCincoLances(Lance lance) {
        int numeroLancesUsuarioAtual = 0;

        for (Lance l: lances) {
            Usuario usuarioLanceLista = l.getUsuario();

            if(lance.getUsuario().equals(usuarioLanceLista)) {
                numeroLancesUsuarioAtual++;
            }

            if (numeroLancesUsuarioAtual > 4) {
                return true;
            }
        }
        return false;
    }

    private boolean lanceMenorQueMaiorLance(Usuario usuarioLanceAtual) {
        Collections.sort(lances);
        Usuario usuarioLanceMaiorLance = lances.get(0).getUsuario();

        if(usuarioLanceAtual.equals(usuarioLanceMaiorLance)) {
            throw new LanceSeguidoDoMesmoUsuarioException();
        }
        return false;
    }

    private void calculaMenorLance(Lance lance, double lanceValor) {
        if (lanceValor < menorLance) {
            menorLance = lance.getValor();
        }
    }

    private void calculaMaiorLance(Lance lance, double lanceValor) {
        if (lanceValor > maiorLance) {
            maiorLance = lance.getValor();
        }
    }

    public List<Lance> tresMaioresLances() {
        final int QTD_MAX_LANCES = 3;

        Collections.sort(lances);
        if (lances.size() > QTD_MAX_LANCES) {
            return lances.subList(0, QTD_MAX_LANCES);
        }
        return lances.subList(0, lances.size());
    }

    public int quantidadeLances() {
        return lances.size();
    }
}
