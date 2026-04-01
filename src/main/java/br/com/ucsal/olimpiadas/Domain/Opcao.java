package br.com.ucsal.olimpiadas.Domain;

import br.com.ucsal.olimpiadas.Application.UseCase.*;

public class Opcao {
    public int Id;
    public String texto;
    public IUseCase usecase;

    public Opcao(int Id, String texto, IUseCase usecase) {
        this.Id = Id;
        this.texto = texto;
        this.usecase = usecase;
    }

    public int getId() {
        return Id;
    }
    public String getTexto() {
        return texto;
    }
    public IUseCase getUsecase() {
        return usecase;
    }
}
