package br.com.ucsal.olimpiadas.Application.UseCase;

import java.util.Scanner;

import br.com.ucsal.olimpiadas.Infrastructure.Repository.*;

public class ListarTentativasUseCase implements IUseCase{

    private TentativaRepository _tentativaRepository;

    public ListarTentativasUseCase(
        TentativaRepository tentativaRepository
    ) 
    {
        _tentativaRepository = tentativaRepository;
    }

    @Override
    public void Execute(Scanner in) {
        var tentativas = _tentativaRepository.listar();

        System.out.println("\n--- Tentativas ---");
		for (var t : tentativas) {
			System.out.printf("#%d | participante=%d | prova=%d | nota=%d/%d%n", t.getId(), t.getParticipanteId(),
					t.getProvaId(), _tentativaRepository.calcularNota(t), t.getRespostas().size());
		}
    }
    
}
