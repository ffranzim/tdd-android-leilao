package br.com.alura.leilao.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LeilaoTest {

    private final String DESCRICAO_LEILAO = "PS4";
    private final double MENOR_LANCE = 14.20;
    private final double MAIOR_LANCE = 16.20;
    private final Usuario FRANZIM = new Usuario("Franzim");
    private final Usuario HUGO = new Usuario("Hugo");

    //Criar cenario de teste
    private final Leilao PS4 = new Leilao(DESCRICAO_LEILAO);

    @Test
    public void getDescricao() {

        //executar ação esperada
        String descricao = PS4.getDescricao();

        //tester resultado esperado
        assertEquals(DESCRICAO_LEILAO, descricao);

    }

    //  [nome do metodo] + [estado do teste] + [resultado esperado]
    //  [deve] + [resultado esperado] + [estado do teste]

    @Test
    public void deve_DevolverMaiorLance_QuandoApenasUmLance() {

        //Criar cenario de teste
        Leilao ps4 = new Leilao(DESCRICAO_LEILAO);
        ps4.propoe(new Lance(FRANZIM, MENOR_LANCE));

        //executar ação esperada
        double maiorLancePS4 = ps4.getMaiorLance();

        assertEquals(MENOR_LANCE, maiorLancePS4, 0.0001);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {

        //Criar cenario de teste
        Leilao ps4 = new Leilao(DESCRICAO_LEILAO);
        ps4.propoe(new Lance(FRANZIM, MENOR_LANCE));
        ps4.propoe(new Lance(HUGO, MAIOR_LANCE));

        //executar ação esperada
        double maiorLancePS4 = ps4.getMaiorLance();

        assertEquals(MAIOR_LANCE, maiorLancePS4, 0.0001);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente() {

        PS4.propoe(new Lance(HUGO, MAIOR_LANCE));
        PS4.propoe(new Lance(FRANZIM, MENOR_LANCE));

        //executar ação esperada
        double maiorLancePS4 = PS4.getMaiorLance();

        assertEquals(MAIOR_LANCE, maiorLancePS4, 0.0001);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoApenasUmLance() {

        //Criar cenario de teste
        Leilao ps4 = new Leilao(DESCRICAO_LEILAO);
        ps4.propoe(new Lance(HUGO, MENOR_LANCE));

        //executar ação esperada
        double menorLancePS4 = ps4.getMenorLance();

        assertEquals(MENOR_LANCE, menorLancePS4, 0.0001);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {

        //Criar cenario de teste
        Leilao ps4 = new Leilao(DESCRICAO_LEILAO);
        ps4.propoe(new Lance(FRANZIM, MENOR_LANCE));
        ps4.propoe(new Lance(HUGO, MAIOR_LANCE));

        //executar ação esperada
        double menorLancePS4 = ps4.getMenorLance();

        assertEquals(MENOR_LANCE, menorLancePS4, 0.0001);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente() {

        //Criar cenario de teste
        Leilao ps4 = new Leilao(DESCRICAO_LEILAO);
        ps4.propoe(new Lance(HUGO, MAIOR_LANCE));
        ps4.propoe(new Lance(FRANZIM, MENOR_LANCE));

        //executar ação esperada
        double menorLancePS4 = ps4.getMenorLance();

        assertEquals(MENOR_LANCE, menorLancePS4, 0.0001);
    }
}