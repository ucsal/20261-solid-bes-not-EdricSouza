package br.com.ucsal.olimpiadas.Apresentation;

import java.util.*;

import br.com.ucsal.olimpiadas.Application.Service.*;
import br.com.ucsal.olimpiadas.Application.UseCase.*;
import br.com.ucsal.olimpiadas.Domain.*;
import br.com.ucsal.olimpiadas.Infrastructure.Repository.*;

public class App {

	private static ParticipanteRepository participanteRepository = new ParticipanteRepository();
	private static ProvaRepository provaRepository = new ProvaRepository();
	private static QuestaoRepository questaoRepository = new QuestaoRepository();
	private static TentativaRepository tentativaRepository = new TentativaRepository();
	private static CriarQuestaoInicalUseCase seed = new CriarQuestaoInicalUseCase(provaRepository, questaoRepository);
	private static ImprimirTabuleiroFen imprimirTabuleiroFen = new ImprimirTabuleiroFen();

	static final List<Opcao> opcoes = new ArrayList<>();

	private static final Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		seed.Execute(in);

		opcoes.add(OpcaoFactory.criar("Cadastrar participante", new CadastrarParticipanteUseCase(participanteRepository)));
		opcoes.add(OpcaoFactory.criar("Cadastrar prova", new CadastrarProvaUseCase(provaRepository)));
		opcoes.add(OpcaoFactory.criar("Cadastrar questão (A–E) em uma prova", new CadastrarQuestaoUseCase(provaRepository, questaoRepository)));
		opcoes.add(OpcaoFactory.criar("Aplicar prova (selecionar participante + prova)", new AplicarProvaUseCase(participanteRepository, provaRepository, tentativaRepository, questaoRepository, imprimirTabuleiroFen)));
		opcoes.add(OpcaoFactory.criar("Listar Tentativas", new ListarTentativasUseCase(tentativaRepository)));

		Menu.Execute(opcoes, in);
	}
}