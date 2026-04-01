package br.com.ucsal.olimpiadas.Infrastructure.Repository;

import br.com.ucsal.olimpiadas.Domain.*;
import java.util.ArrayList;
import java.util.List;

public class QuestaoRepository {

    private List<Questao> questoes = new ArrayList<>();
    private long proximoId = 1;

    public List<Questao> getQuestoes() {
        return questoes;
    }

    public Questao salvar(Questao q) {
        q.setId(proximoId++);
        questoes.add(q);
        return q;
    }

    public boolean isEmpty() {
        return questoes.isEmpty();
    }

    public List<Questao> listar() {
        return questoes;
    }
}
