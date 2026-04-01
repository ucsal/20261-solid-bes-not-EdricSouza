package br.com.ucsal.olimpiadas.Infrastructure.Repository;

import br.com.ucsal.olimpiadas.Domain.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParticipanteRepository {

    private List<Participante> participantes = new ArrayList<>();
    private long proximoId = 1;

    public Participante salvar(Participante p) {
        p.setId(proximoId++);
        participantes.add(p);
        return p;
    }

    public List<Participante> listar() {
        return participantes;
    }

    public boolean isEmpty() {
        return participantes.isEmpty();
    }

    public Long escolherParticipante(Scanner in) {
		System.out.println("\nParticipantes:");
		for (var p : participantes) {
			System.out.printf("  %d) %s%n", p.getId(), p.getNome());
		}
		System.out.print("Escolha o id do participante: ");

		try {
			long id = Long.parseLong(in.nextLine());
			boolean existe = participantes.stream().anyMatch(p -> p.getId() == id);
			if (!existe) {
				System.out.println("id inválido");
				return null;
			}
			return id;
		} catch (Exception e) {
			System.out.println("entrada inválida");
			return null;
		}
	}
}