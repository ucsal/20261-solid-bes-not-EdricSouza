package br.com.ucsal.olimpiadas.Application.UseCase;

import java.util.Scanner;

import br.com.ucsal.olimpiadas.Domain.*;
import br.com.ucsal.olimpiadas.Infrastructure.Repository.*;

public class CadastrarParticipanteUseCase implements IUseCase {

    private ParticipanteRepository _participanteRepository;

    public CadastrarParticipanteUseCase (ParticipanteRepository participanteRepository) {
        this._participanteRepository = participanteRepository;
    }

    public void Execute(Scanner in) {

        System.out.print("Nome: ");
		var nome = in.nextLine();

		System.out.print("Email (opcional): ");
		var email = in.nextLine();

		if (nome == null || nome.isBlank()) {
			System.out.println("nome inválido");
			return;
		}

		var p = new Participante();
		p.setNome(nome);
		p.setEmail(email);

		_participanteRepository.salvar(p);
		System.out.println("Participante cadastrado: " + p.getId());
    }
}
