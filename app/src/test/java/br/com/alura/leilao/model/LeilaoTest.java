package br.com.alura.leilao.model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LeilaoTest {

    public static final double DELTA = 0.0001;
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

        assertEquals(MENOR_LANCE, maiorLancePS4, DELTA);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {

        //Criar cenario de teste

        PS4.propoe(new Lance(FRANZIM, MENOR_LANCE));
        PS4.propoe(new Lance(HUGO, MAIOR_LANCE));

        //executar ação esperada
        double maiorLancePS4 = PS4.getMaiorLance();

        assertEquals(MAIOR_LANCE, maiorLancePS4, DELTA);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente() {

        PS4.propoe(new Lance(HUGO, MAIOR_LANCE));
        PS4.propoe(new Lance(FRANZIM, MENOR_LANCE));

        //executar ação esperada
        double maiorLancePS4 = PS4.getMaiorLance();

        assertEquals(MAIOR_LANCE, maiorLancePS4, DELTA);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoApenasUmLance() {

        //Criar cenario de teste

        PS4.propoe(new Lance(HUGO, MENOR_LANCE));

        //executar ação esperada
        double menorLancePS4 = PS4.getMenorLance();

        assertEquals(MENOR_LANCE, menorLancePS4, DELTA);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {

        //Criar cenario de teste

        PS4.propoe(new Lance(FRANZIM, MENOR_LANCE));
        PS4.propoe(new Lance(HUGO, MAIOR_LANCE));

        //executar ação esperada
        double menorLancePS4 = PS4.getMenorLance();

        assertEquals(MENOR_LANCE, menorLancePS4, DELTA);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente() {

        //Criar cenario de teste

        PS4.propoe(new Lance(HUGO, MAIOR_LANCE));
        PS4.propoe(new Lance(FRANZIM, MENOR_LANCE));

        //executar ação esperada
        double menorLancePS4 = PS4.getMenorLance();

        assertEquals(MENOR_LANCE, menorLancePS4, DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeExatoTresLances() {

        //Criar cenario de teste

        PS4.propoe(new Lance(HUGO, MAIOR_LANCE));
        PS4.propoe(new Lance(FRANZIM, MENOR_LANCE));
        PS4.propoe(new Lance(HUGO, 15.20));


        //executar ação esperada
        List<Lance> tresMaioresLances = PS4.tresMaioresLances();

        assertEquals(3, tresMaioresLances.size(), DELTA);
        assertEquals(tresMaioresLances.get(0).getValor(), MAIOR_LANCE, DELTA);
        assertEquals(tresMaioresLances.get(1).getValor(), 15.20, DELTA);
        assertEquals(tresMaioresLances.get(2).getValor(), MENOR_LANCE, DELTA);
    }
}