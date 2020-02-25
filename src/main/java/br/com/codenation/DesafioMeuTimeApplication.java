package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	@Desafio("incluirTime")
        @Override
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		throw new UnsupportedOperationException();
	}

	@Desafio("incluirJogador")
        @Override
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		throw new UnsupportedOperationException();
	}

	@Desafio("definirCapitao")
        @Override
	public void definirCapitao(Long idJogador) {
		throw new UnsupportedOperationException();
	}

    /**
     *
     * @param idTime
     * @return
     */
    @Desafio("buscarCapitaoDoTime")
        @Override
	public Long buscarCapitaoDoTime(Long idTime) {
		throw new UnsupportedOperationException();
	}

    /**
     *
     * @param idJogador
     * @return
     */
    @Desafio("buscarNomeJogador")
        @Override
	public String buscarNomeJogador(Long idJogador) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarNomeTime")
        @Override
	public String buscarNomeTime(Long idTime) {
		throw new UnsupportedOperationException();
	}

    /**
     *
     * @param idTime
     * @return
     */
    @Desafio("buscarJogadoresDoTime")
        @Override
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarMelhorJogadorDoTime")
        @Override
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarJogadorMaisVelho")
        @Override
	public Long buscarJogadorMaisVelho(Long idTime) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarTimes")
        @Override
	public List<Long> buscarTimes() {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarJogadorMaiorSalario")
        @Override
	public Long buscarJogadorMaiorSalario(Long idTime) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarSalarioDoJogador")
        @Override
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarTopJogadores")
        @Override
	public List<Long> buscarTopJogadores(Integer top) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarCorCamisaTimeDeFora")
        @Override
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		throw new UnsupportedOperationException();
	}

}
