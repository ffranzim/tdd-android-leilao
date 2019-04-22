package br.com.alura.leilao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao implements Serializable {

    private final String descricao;
    private final List<Lance> lances;
    private double maiorLance = Double.NEGATIVE_INFINITY;
    private double menorLance = Double.POSITIVE_INFINITY;


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
        double lanceValor = lance.getValor();

        lances.add(lance);
        calculaMaiorLance(lance, lanceValor);
        calculaMenorLance(lance, lanceValor);
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
        Collections.sort(lances);
        return lances.subList(0,3);
    }
}
