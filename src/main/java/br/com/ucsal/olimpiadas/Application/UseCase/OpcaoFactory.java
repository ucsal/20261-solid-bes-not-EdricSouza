package br.com.ucsal.olimpiadas.Application.UseCase;

import br.com.ucsal.olimpiadas.Domain.Opcao;

public class OpcaoFactory {

    private static int proximoId = 1;

    public static Opcao criar(String texto, IUseCase usecase) {
        return new Opcao(proximoId++, texto, usecase);
    }

}
