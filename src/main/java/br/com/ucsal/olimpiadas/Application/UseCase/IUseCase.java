package br.com.ucsal.olimpiadas.Application.UseCase;

public interface IUseCase<T> {
    
    public T Execute(T valor);
}
