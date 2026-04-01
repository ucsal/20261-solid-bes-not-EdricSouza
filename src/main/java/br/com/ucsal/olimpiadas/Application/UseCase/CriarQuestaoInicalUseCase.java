package br.com.ucsal.olimpiadas.Application.UseCase;

import java.util.Scanner;

import br.com.ucsal.olimpiadas.Domain.*;
import br.com.ucsal.olimpiadas.Infrastructure.Repository.*;

public class CriarQuestaoInicalUseCase implements IUseCase{

    private ProvaRepository _provaRepository;
    private QuestaoRepository _questaoRepository;

    public CriarQuestaoInicalUseCase(
        ProvaRepository provaRepository,
        QuestaoRepository questaoRepository
    ) 
    {
        _provaRepository = provaRepository;
        _questaoRepository = questaoRepository;
    }

    @Override
    public void Execute(Scanner in) {
        
        var prova = new Prova();
		prova.setTitulo("Olimpíada 2026 • Nível 1 • Prova A");
		_provaRepository.salvar(prova);

		var q1 = new Questao();
		q1.setProvaId(prova.getId());

		q1.setEnunciado("""
				Questão 1 — Mate em 1.
				É a vez das brancas.
				Encontre o lance que dá mate imediatamente.
				""");

		q1.setFenInicial("6k1/5ppp/8/8/8/7Q/6PP/6K1 w - - 0 1");

		q1.setAlternativas(new String[] { "A) Qh7#", "B) Qf5#", "C) Qc8#", "D) Qh8#", "E) Qe6#" });

		q1.setAlternativaCorreta('C');

		_questaoRepository.salvar(q1);
    }

}
