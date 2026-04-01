package br.com.ucsal.olimpiadas.Infrastructure.Repository;

import br.com.ucsal.olimpiadas.Domain.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProvaRepository {

    private List<Prova> provas = new ArrayList<>();
    private long proximoId = 1;

    public Prova salvar(Prova p) {
        p.setId(proximoId++);
        provas.add(p);
        return p;
    }

    public List<Prova> listar() {
        return provas;
    }

    public boolean isEmpty() {
        return provas.isEmpty();
    }

	public Long escolherProva(Scanner in) {
		System.out.println("\nProvas:");
		for (var p : provas) {
			System.out.printf("  %d) %s%n", p.getId(), p.getTitulo());
		}
		System.out.print("Escolha o id da prova: ");

		try {
			long id = Long.parseLong(in.nextLine());
			boolean existe = provas.stream().anyMatch(p -> p.getId() == id);
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
