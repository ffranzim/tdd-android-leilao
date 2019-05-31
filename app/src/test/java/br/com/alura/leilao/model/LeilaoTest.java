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
        try {
            PS4.propoe(new Lance(FRANZIM, MENOR_LANCE));
            fail("Era esperada uma RuntimeException.");
        } catch (RuntimeException e) {
            assertEquals("Lance menor que maior lance.", e.getMessage());
        } finally {
            //executar ação esperada
            double maiorLancePS4 = PS4.getMaiorLance();
            assertEquals(MAIOR_LANCE, maiorLancePS4, DELTA);
        }

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
    public void deve_DevolverOMenorIgualAoMaior_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente() {

        //Criar cenario de teste

        PS4.propoe(new Lance(HUGO, MAIOR_LANCE));

        try {
            PS4.propoe(new Lance(FRANZIM, MENOR_LANCE));
            fail("Era esperada uma RuntimeException.");
        } catch (RuntimeException e) {
            assertEquals("Lance menor que maior lance.", e.getMessage());
        } finally {
            //executar ação esperada
            double menorLancePS4 = PS4.getMenorLance();
            assertEquals(MAIOR_LANCE, menorLancePS4, DELTA);
        }
    }

    @Test
    public void deve_DevolverUmLance_QuandoRecebeTresLancesSendoUmOsSubsequentesMenoresQueOPrimeiro() {

        //Criar cenario de teste

        PS4.propoe(new Lance(HUGO, MAIOR_LANCE));
        try {
            PS4.propoe(new Lance(FRANZIM, MENOR_LANCE));
            PS4.propoe(new Lance(HUGO, 15.20));
            fail("Era esperada uma RuntimeException.");
        } catch (RuntimeException e) {
            assertEquals("Lance menor que maior lance.", e.getMessage());
        } finally {
            //executar ação esperada
            List<Lance> tresMaioresLances = PS4.tresMaioresLances();

            assertEquals(1, tresMaioresLances.size(), DELTA);
            assertEquals(MAIOR_LANCE, tresMaioresLances.get(0).getValor(), DELTA);
        }
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeNaoRecebeLances() {

        //Criar cenario de teste
        List<Lance> tresMaioresLances = PS4.tresMaioresLances();

        assertEquals(0, tresMaioresLances.size());
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeApenasUmRecebeLance() {

        PS4.propoe(new Lance(HUGO, MAIOR_LANCE));

        //Criar cenario de teste
        List<Lance> tresMaioresLances = PS4.tresMaioresLances();

        assertEquals(1, tresMaioresLances.size());
        assertEquals(MAIOR_LANCE, tresMaioresLances.get(0).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeExatoDoisLances() {

        PS4.propoe(new Lance(FRANZIM, MENOR_LANCE));
        PS4.propoe(new Lance(HUGO, MAIOR_LANCE));

        //Criar cenario de teste
        List<Lance> tresMaioresLances = PS4.tresMaioresLances();

        assertEquals(2, tresMaioresLances.size());
        assertEquals(MAIOR_LANCE, tresMaioresLances.get(0).getValor(), DELTA);
        assertEquals(MENOR_LANCE, tresMaioresLances.get(1).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeMaisDeTresLances() {

        PS4.propoe(new Lance(HUGO, MENOR_LANCE));
        PS4.propoe(new Lance(FRANZIM, 15.20));
        PS4.propoe(new Lance(HUGO, 16.00));
        PS4.propoe(new Lance(FRANZIM, MAIOR_LANCE));

        //Criar cenario de teste
        List<Lance> tresMaioresLances = PS4.tresMaioresLances();

        assertEquals(3, tresMaioresLances.size());
        assertEquals(MAIOR_LANCE, tresMaioresLances.get(0).getValor(), DELTA);
        assertEquals(16.00, tresMaioresLances.get(1).getValor(), DELTA);
        assertEquals(15.20, tresMaioresLances.get(2).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverValorZeroParaMaiorLance_QuandoNaotiverLance() {

        //Criar cenario de teste
        double maiorLance = PS4.getMaiorLance();

        assertEquals(0.0, maiorLance, DELTA);
    }

    @Test
    public void deve_DevolverValorZeroParaMenorLance_QuandoNaotiverLance() {

        //Criar cenario de teste
        double menorLance = PS4.getMenorLance();

        assertEquals(0.0, menorLance, DELTA);
    }


    @Test
    public void naoDeve_AdicionarLance_QuandoForMenorQueOMaiorLance() {
        PS4.propoe(new Lance(FRANZIM, MAIOR_LANCE));

        try {
            PS4.propoe(new Lance(HUGO, MAIOR_LANCE));
            fail("Era esperada uma RuntimeException.");
        } catch (RuntimeException e) {
            assertEquals("Lance menor que maior lance.", e.getMessage());
        } finally {
            //Criar cenario de teste
            int qtdLances = PS4.quantidadeLances();
            assertEquals(1, qtdLances);
        }


    }


    @Test
    public void naoDeve_AdicionarLance_QuandoForOMesmoUsuarioDoUltimoLance() {
        PS4.propoe(new Lance(FRANZIM, MENOR_LANCE));

        try {
            PS4.propoe(new Lance(new Usuario("Franzim"), MAIOR_LANCE));
            fail("Esperada uma RuntimeException");
        } catch (RuntimeException e) {
            assertEquals("Mesmo usuario de ultimo lance.", e.getMessage());
        } finally {
            //Criar cenario de teste
            int qtdLances = PS4.quantidadeLances();
            assertEquals(1, qtdLances);
        }


    }

    @Test
    public void naoDeve_AdicionarLance_QuandoUsuariojaTiverCincoLance() {
        PS4.propoe(new Lance(FRANZIM, MENOR_LANCE));
        PS4.propoe(new Lance(HUGO, 14.30));
        PS4.propoe(new Lance(FRANZIM, 14.40));
        PS4.propoe(new Lance(HUGO, 14.50));
        PS4.propoe(new Lance(FRANZIM, 14.60));
        PS4.propoe(new Lance(HUGO, 14.70));
        PS4.propoe(new Lance(FRANZIM, 14.80));
        PS4.propoe(new Lance(HUGO, 14.90));
        PS4.propoe(new Lance(FRANZIM, 15.00));
        PS4.propoe(new Lance(HUGO, 15.10));
        PS4.propoe(new Lance(FRANZIM, 15.20));

        try {
            PS4.propoe(new Lance(HUGO, MAIOR_LANCE));
            fail("Esperada uma RuntimeException");
        } catch (RuntimeException e) {
            assertEquals("Mesmo usuario de ultimo lance.", e.getMessage());
        } finally {
            //Criar cenario de teste
            int qtdLances = PS4.quantidadeLances();
            assertEquals(10, qtdLances);
        }


    }

}
