package br.com.ucsal.olimpiadas.Application.UseCase;

import java.util.Scanner;

import br.com.ucsal.olimpiadas.Domain.*;
import br.com.ucsal.olimpiadas.Infrastructure.Repository.*;

public class CadastrarQuestaoUseCase implements IUseCase{

    private ProvaRepository _provaRepository;
    private QuestaoRepository _questaoRepository;

    public CadastrarQuestaoUseCase (
        ProvaRepository provaRepository,
        QuestaoRepository questaoRepository
    ) {
        _provaRepository = provaRepository;
		_questaoRepository = questaoRepository;
    }

    @Override
    public void Execute(Scanner in) {

		if (_provaRepository.isEmpty()) {
			System.out.println("não há provas cadastradas");
			return;
		}

		var provaId = _provaRepository.escolherProva(in);
		if (provaId == null)
			return;

		System.out.println("Enunciado:");
		var enunciado = in.nextLine();

		var alternativas = new String[5];
		for (int i = 0; i < 5; i++) {
			char letra = (char) ('A' + i);
			System.out.print("Alternativa " + letra + ": ");
			alternativas[i] = letra + ") " + in.nextLine();
		}

		System.out.print("Alternativa correta (A–E): ");
		char correta;
		try {
			correta = Questao.normalizar(in.nextLine().trim().charAt(0));
		} catch (Exception e) {
			System.out.println("alternativa inválida");
			return;
		}

		var q = new Questao();
		q.setProvaId(provaId);
		q.setEnunciado(enunciado);
		q.setAlternativas(alternativas);
		q.setAlternativaCorreta(correta);

		_questaoRepository.salvar(q);

		System.out.println("Questão cadastrada: " + q.getId() + " (na prova " + provaId + ")");

    }
}
