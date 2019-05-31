package br.com.alura.leilao.model;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import br.com.alura.leilao.exception.LanceMenorQueUltimoLanceException;
import br.com.alura.leilao.exception.LanceSeguidoDoMesmoUsuarioException;
import br.com.alura.leilao.exception.UsuarioJaDeuCincoLancesException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.CombinableMatcher.both;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
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
//        assertEquals(DESCRICAO_LEILAO, descricao);
        assertThat(descricao, is(equalTo(DESCRICAO_LEILAO)));

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

//        assertEquals(MENOR_LANCE, maiorLancePS4, DELTA);
        assertThat(maiorLancePS4, closeTo(MENOR_LANCE, DELTA));
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

    @Test(expected = LanceMenorQueUltimoLanceException.class)
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente() {

        PS4.propoe(new Lance(HUGO, MAIOR_LANCE));
        PS4.propoe(new Lance(FRANZIM, MENOR_LANCE));

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

    @Test(expected = LanceMenorQueUltimoLanceException.class)
    public void deve_DevolverOMenorIgualAoMaior_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente() {

        //Criar cenario de teste
        PS4.propoe(new Lance(HUGO, MAIOR_LANCE));

        PS4.propoe(new Lance(FRANZIM, MENOR_LANCE));

        //executar ação esperada
        double menorLancePS4 = PS4.getMenorLance();
        assertEquals(MAIOR_LANCE, menorLancePS4, DELTA);
    }

    @Test(expected = LanceMenorQueUltimoLanceException.class)
    public void deve_DevolverUmLance_QuandoRecebeTresLancesSendoUmOsSubsequentesMenoresQueOPrimeiro() {

        //Criar cenario de teste
        PS4.propoe(new Lance(HUGO, MAIOR_LANCE));
        PS4.propoe(new Lance(FRANZIM, MENOR_LANCE));
        PS4.propoe(new Lance(HUGO, 15.20));

        //executar ação esperada
        List<Lance> tresMaioresLances = PS4.tresMaioresLances();

//        assertEquals(1, tresMaioresLances.size(), DELTA);
        assertEquals(MAIOR_LANCE, tresMaioresLances.get(0).getValor(), DELTA);

        assertThat(tresMaioresLances, hasSize(equalTo(1)));
        assertThat(tresMaioresLances, hasItem(new Lance(HUGO, MAIOR_LANCE)));
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

//        assertEquals(3, tresMaioresLances.size());
        assertEquals(MAIOR_LANCE, tresMaioresLances.get(0).getValor(), DELTA);
        assertEquals(16.00, tresMaioresLances.get(1).getValor(), DELTA);
        assertEquals(15.20, tresMaioresLances.get(2).getValor(), DELTA);

        assertThat(tresMaioresLances, hasSize(equalTo(3)));
        assertThat(tresMaioresLances, hasItem(new Lance(HUGO, 16.00)));
        assertThat(tresMaioresLances, contains(
                new Lance(FRANZIM, MAIOR_LANCE),
                new Lance(HUGO, 16.00),
                new Lance(FRANZIM, 15.20)
        ));

        assertThat(tresMaioresLances, both(Matchers.<Lance>hasSize(3)).and(contains(
                new Lance(FRANZIM, MAIOR_LANCE),
                new Lance(HUGO, 16.00),
                new Lance(FRANZIM, 15.20)
        )));
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


    @Test(expected = LanceMenorQueUltimoLanceException.class)
    public void naoDeve_AdicionarLance_QuandoForMenorQueOMaiorLance() {

        PS4.propoe(new Lance(FRANZIM, MAIOR_LANCE));
        PS4.propoe(new Lance(HUGO, MAIOR_LANCE));

        //Criar cenario de teste
        int qtdLances = PS4.quantidadeLances();
        assertEquals(1, qtdLances);
    }


    @Test(expected = LanceSeguidoDoMesmoUsuarioException.class)
    public void naoDeve_AdicionarLance_QuandoForOMesmoUsuarioDoUltimoLance() {

        PS4.propoe(new Lance(FRANZIM, MENOR_LANCE));
        PS4.propoe(new Lance(new Usuario("Franzim"), MAIOR_LANCE));

        //Criar cenario de teste
        int qtdLances = PS4.quantidadeLances();
        assertEquals(1, qtdLances);
    }

    @Test(expected = UsuarioJaDeuCincoLancesException.class)
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

        PS4.propoe(new Lance(HUGO, MAIOR_LANCE));

        //Criar cenario de teste
        int qtdLances = PS4.quantidadeLances();
        assertEquals(10, qtdLances);
    }

}
