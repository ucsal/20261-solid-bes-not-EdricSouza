package br.com.ucsal.olimpiadas.Application.UseCase;

import java.util.Scanner;

import br.com.ucsal.olimpiadas.Application.Service.ImprimirTabuleiroFen;
import br.com.ucsal.olimpiadas.Domain.Questao;
import br.com.ucsal.olimpiadas.Domain.Resposta;
import br.com.ucsal.olimpiadas.Domain.Tentativa;
import br.com.ucsal.olimpiadas.Infrastructure.Repository.*;

public class AplicarProvaUseCase implements IUseCase {

    private ParticipanteRepository _participanteRepository;
    private ProvaRepository _provaRepository;
    private TentativaRepository _tentativaRepository;
    private QuestaoRepository _questaoRepository;
    private ImprimirTabuleiroFen _imprimirTabuleiroFen;

    public AplicarProvaUseCase(
        ParticipanteRepository participanteRepository,
        ProvaRepository provaRepository,
        TentativaRepository tentativaRepository,
        QuestaoRepository questaoRepository,
        ImprimirTabuleiroFen imprimirTabuleiroFen
        ) 
    {
        _participanteRepository = participanteRepository;
        _provaRepository = provaRepository;
        _tentativaRepository = tentativaRepository;
        _questaoRepository = questaoRepository;
        _imprimirTabuleiroFen = imprimirTabuleiroFen;
    }

    @Override
    public void Execute(Scanner in) {
        if (_participanteRepository.isEmpty()) {
			System.out.println("cadastre participantes primeiro");
			return;
		}
		if (_provaRepository.isEmpty()) {
			System.out.println("cadastre provas primeiro");
			return;
		}

		var participanteId = _participanteRepository.escolherParticipante(in);
		if (participanteId == null)
			return;

		var provaId = _provaRepository.escolherProva(in);
		if (provaId == null)
			return;

		var questoesDaProva = _questaoRepository.getQuestoes().stream().filter(q -> q.getProvaId() == provaId).toList();

		if (questoesDaProva.isEmpty()) {
			System.out.println("esta prova não possui questões cadastradas");
			return;
		}

		var tentativa = new Tentativa();
		tentativa.setParticipanteId(participanteId);
		tentativa.setProvaId(provaId);

		System.out.println("\n--- Início da Prova ---");

		for (var q : questoesDaProva) {
			System.out.println("\nQuestão #" + q.getId());
			System.out.println(q.getEnunciado());

			System.out.println("Posição inicial:");
			_imprimirTabuleiroFen.Execute(q.getFenInicial());

			for (var alt : q.getAlternativas()) {
			    System.out.println(alt);
			}

			System.out.print("Sua resposta (A–E): ");
			char marcada;
			try {
				marcada = Questao.normalizar(in.nextLine().trim().charAt(0));
			} catch (Exception e) {
				System.out.println("resposta inválida (marcando como errada)");
				marcada = 'X';
			}

			var r = new Resposta();
			r.setQuestaoId(q.getId());
			r.setAlternativaMarcada(marcada);
			r.setCorreta(q.isRespostaCorreta(marcada));

			tentativa.getRespostas().add(r);
		}

		_tentativaRepository.salvar(tentativa);

		int nota = _tentativaRepository.calcularNota(tentativa);
		System.out.println("\n--- Fim da Prova ---");
		System.out.println("Nota (acertos): " + nota + " / " + tentativa.getRespostas().size());
    }

}
