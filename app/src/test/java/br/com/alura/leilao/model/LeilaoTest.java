package br.com.alura.leilao.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LeilaoTest {

    @Test
    public void getDescricao() {

        final  String descricaoLeilao = "PS4";

        //Criar cenario de teste
        Leilao ps4 = new Leilao(descricaoLeilao);

        //executar ação esperada
        String descricao = ps4.getDescricao();

        //tester resultado esperado
        assertEquals(descricaoLeilao, descricao);

    }


    @Test
    public void getMaiorLance() {
        final String descricaoLeilao = "PS4";
        final double lance = 14.20;

        //Criar cenario de teste
        Leilao ps4 = new Leilao(descricaoLeilao);
        ps4.propoe(new Lance(new Usuario("Franzim"), lance ));

        //executar ação esperada
        double maiorLance = ps4.getMaiorLance();

        //tester resultado esperado
        assertEquals(lance , maiorLance, 0.0001);
    }

}