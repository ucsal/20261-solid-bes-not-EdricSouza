Readme inicial

Aplicação dos princípios SOLIDs:

SRP - A aplicação do princípio da responsabilidade única foi aplicada ao remover os métodos do App.java e realocando-os dentro de UseCases, permitindo assim que cada classe seja responsável por métodos independentes.

OCP - O menu permite que a classe menu possa adicionar novas oções sem fazer nenhuma alteração no código, permitindo adições, mas evitando alterações diretas na classe do menu.

LSP - O sistema utiliza do princípio de substituição ao fazer com que a interface seja implementada em cada opção ao invés de implementar diretamente um UseCase, e o trecho "opcao.getUsecase().Execute(in);" faz com que o menu espero qualquer coisa que seja um IUseCase.

ISP - O sistema não obriga nenhuma classe que implementa a IUseCase a utilizar um método não utilizado

DIP - Eu tinha em mente a criação de interfaces para os repositórios, porém por questão de tempo, acabei optando por não criar interfaces dos repositório