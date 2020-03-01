package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import static java.util.Comparator.comparing;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	List<TimeDesafio> timesDesafio = new ArrayList<>();
	List<Jogador> jogadores = new ArrayList<>();

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		for(TimeDesafio t : timesDesafio) {
			if(t.getId().equals(id)) {
				throw new IdentificadorUtilizadoException("A ID informada já foi utilizada");
			}
		}
		timesDesafio.add(new TimeDesafio(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		for(Jogador j : jogadores) {
			if(j.getId().equals(id)) {
				throw new IdentificadorUtilizadoException("A ID informada já foi utilizada");
			}
		}
		buscarTime(idTime);
		jogadores.add(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		Jogador novoCapitao = buscarJogador(idJogador);
		for (Jogador j : jogadores) {
			if (j.getIdTime().equals(novoCapitao.getIdTime())) {
				j.setCapitao(false);
			}
		}
		novoCapitao.setCapitao(true);
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		buscarTime(idTime);
		for (Jogador j : jogadores) {
			if (j.getIdTime().equals(idTime) && j.getCapitao()) {
				return j.getId();
			}
		}
		throw new CapitaoNaoInformadoException("Não foi encontrado um capitão no time informado");
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		return buscarJogador(idJogador).getNome();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		return buscarTime(idTime).getNome();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		buscarTime(idTime);
		List<Long> jogadoresTime = new LinkedList<>();
		for (Jogador j : jogadores) {
			if (j.getIdTime().equals(idTime)) {
				jogadoresTime.add(j.getId());
			}
		}
		return jogadoresTime;
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		buscarTime(idTime);
		int max = -1;
		Long melhor = null;
		for (Jogador j : jogadores) {
			if (j.getIdTime().equals(idTime) && j.getNivelHabilidade() > max) {
				max = j.getNivelHabilidade();
				melhor = j.getId();
			}
		}
		return melhor;
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		buscarTime(idTime);
		Optional<Jogador> jogadorMaisVelho = jogadores.stream().
				filter(jogadores -> jogadores.getIdTime().equals(idTime)).
				min(comparing(Jogador::getDataNascimento).
						thenComparing(Jogador::getId));
		return jogadorMaisVelho.map(Jogador::getId).orElse(null);
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		List<Long> idTimes = new LinkedList<>();
		for (TimeDesafio time : timesDesafio) {
			idTimes.add(time.getId());
		}
		return idTimes;
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		buscarTime(idTime);
		Optional<Jogador> jogadorMaiorSalario = jogadores.stream().
				filter(jogadores -> jogadores.getIdTime().equals(idTime)).
				min(comparing(Jogador::getSalario).reversed().
						thenComparing(Jogador::getId));
		return jogadorMaiorSalario.map(Jogador::getId).orElse(null);
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		return buscarJogador(idJogador).getSalario();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		Jogador[] topJogadores = jogadores.toArray(new Jogador[0]);
		Arrays.sort(topJogadores, Comparator.
				comparing(Jogador::getNivelHabilidade).reversed().
				thenComparing(Jogador::getId));
		List<Long> topIds = new ArrayList<>();
		for (int i = 0; i < top && i < jogadores.size(); i++) {
			topIds.add(topJogadores[i].getId());
		}
		return topIds;
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		TimeDesafio timeCasa = buscarTime(timeDaCasa);
		TimeDesafio timeFora = buscarTime(timeDeFora);
		if (timeCasa.getCorUniformePrincipal().equals(timeFora.getCorUniformePrincipal())) {
			return timeFora.getCorUniformeSecundario();
		} else {
			return timeFora.getCorUniformePrincipal();
		}
	}

	private TimeDesafio buscarTime(Long id) {
		for(TimeDesafio t : timesDesafio) {
			if(t.getId().equals(id)) {
				return t;
			}
		}
		throw new TimeNaoEncontradoException("O time escolhido não foi encontrado");
	}

	private Jogador buscarJogador(Long id) {
		for(Jogador j : jogadores) {
			if(j.getId().equals(id)) {
				return j;
			}
		}
		throw new JogadorNaoEncontradoException("O jogador escolhido não foi encontrado");
	}

}