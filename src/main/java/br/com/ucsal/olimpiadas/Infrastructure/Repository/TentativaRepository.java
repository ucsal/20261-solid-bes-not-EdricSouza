package br.com.ucsal.olimpiadas.Infrastructure.Repository;

import br.com.ucsal.olimpiadas.Domain.*;
import java.util.ArrayList;
import java.util.List;

public class TentativaRepository {

    private List<Tentativa> tentativas = new ArrayList<>();
    private long proximoId = 1;

    public Tentativa salvar(Tentativa t) {
        t.setId(proximoId++);
        tentativas.add(t);
        return t;
    }

    public boolean isEmpty() {
        return tentativas.isEmpty();
    }

    public List<Tentativa> listar() {
        return tentativas;
    }


	public int calcularNota(Tentativa tentativa) {
		int acertos = 0;
		for (var r : tentativa.getRespostas()) {
			if (r.isCorreta())
				acertos++;
		}
		return acertos;
	}
}
