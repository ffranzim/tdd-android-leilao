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

    //  [nome do metodo] + [estado do teste] + [resultado esperado]
    //  [deve] + [resultado esperado] + [estado do teste]

    @Test
    public void deve_DevolverMaiorLance_QuandoApenasUmLance() {
        final String descricaoLeilao = "PS4";
        final double lance = 14.20;
        Usuario franzim = new Usuario("Franzim");

        //Criar cenario de teste
        Leilao ps4 = new Leilao(descricaoLeilao);
        ps4.propoe(new Lance( franzim, lance));

        //executar ação esperada
        double maiorLancePS4 = ps4.getMaiorLance();

        assertEquals(lance , maiorLancePS4, 0.0001);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        final String descricaoLeilao = "PS4";
        final double menorLance = 14.20;
        final double maiorLance = 16.20;
        Usuario franzim = new Usuario("Franzim");
        Usuario hugo = new Usuario("Hugo");

        //Criar cenario de teste
        Leilao ps4 = new Leilao(descricaoLeilao);
        ps4.propoe(new Lance(franzim, menorLance));
        ps4.propoe(new Lance(hugo, maiorLance));

        //executar ação esperada
        double maiorLancePS4 = ps4.getMaiorLance();

        assertEquals(maiorLance, maiorLancePS4, 0.0001);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente() {
        final String descricaoLeilao = "PS4";
        final double menorLance = 14.20;
        final double maiorLance = 16.20;
        Usuario franzim = new Usuario("Franzim");
        Usuario hugo = new Usuario("Hugo");

        //Criar cenario de teste
        Leilao ps4 = new Leilao(descricaoLeilao);
        ps4.propoe(new Lance(hugo, maiorLance));
        ps4.propoe(new Lance( franzim, menorLance));

        //executar ação esperada
        double maiorLancePS4 = ps4.getMaiorLance();

        assertEquals(maiorLance , maiorLancePS4, 0.0001);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoApenasUmLance() {
        final String descricaoLeilao = "PS4";
        final double lance = 14.20;

        Usuario franzim = new Usuario("Franzim");
        Usuario hugo = new Usuario("Hugo");

        //Criar cenario de teste
        Leilao ps4 = new Leilao(descricaoLeilao);
        ps4.propoe(new Lance(hugo, lance));

        //executar ação esperada
        double menorLancePS4 = ps4.getMenorLance();

        assertEquals(lance , menorLancePS4, 0.0001);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        final String descricaoLeilao = "PS4";
        final double menorLance = 14.20;
        final double maiorLance = 16.20;
        Usuario franzim = new Usuario("Franzim");
        Usuario hugo = new Usuario("Hugo");

        //Criar cenario de teste
        Leilao ps4 = new Leilao(descricaoLeilao);
        ps4.propoe(new Lance(franzim, menorLance));
        ps4.propoe(new Lance(hugo, maiorLance));

        //executar ação esperada
        double menorLancePS4 = ps4.getMenorLance();

        assertEquals(menorLance, menorLancePS4, 0.0001);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente() {
        final String descricaoLeilao = "PS4";
        final double menorLance = 14.20;
        final double maiorLance = 16.20;
        Usuario franzim = new Usuario("Franzim");
        Usuario hugo = new Usuario("Hugo");

        //Criar cenario de teste
        Leilao ps4 = new Leilao(descricaoLeilao);
        ps4.propoe(new Lance(hugo, maiorLance));
        ps4.propoe(new Lance( franzim, menorLance));

        //executar ação esperada
        double menorLancePS4 = ps4.getMenorLance();

        assertEquals(menorLance , menorLancePS4, 0.0001);
    }
}