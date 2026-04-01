package br.com.ucsal.olimpiadas.Application.UseCase;

import java.util.Scanner;

import br.com.ucsal.olimpiadas.Domain.*;
import br.com.ucsal.olimpiadas.Infrastructure.Repository.*;

public class CadastrarProvaUseCase implements IUseCase{

    private ProvaRepository _provaRepository;

    public CadastrarProvaUseCase(ProvaRepository provaRepository) {
        _provaRepository = provaRepository;
    }

    @Override
    public void Execute(Scanner in) {

		System.out.print("Título da prova: ");
		var titulo = in.nextLine();

		if (titulo == null || titulo.isBlank()) {
			System.out.println("título inválido");
			return;
		}

		var prova = new Prova();
		prova.setTitulo(titulo);

		_provaRepository.salvar(prova);
		System.out.println("Prova criada: " + prova.getId());

    }
}
