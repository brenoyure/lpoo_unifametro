# lpoo_unifametro
Repositório da Cadeira de Linguagem de Programação Orientada a Objeto da Unifametro - SINF3N

Projeto da APS - Sistema de Cadastro de Despesas

Pastas (packages) do projeto:

Modelo -> representam os modelos de um Aluno, Despesa (Apenas a com Prioridade) e Reserva.

Persistência -> possui as classes Dao, responsáveis por manipular os arquivos de cada modelo,
exemplo AlunoDao, contém os registros dos alunos.

Services -> tem os serviços para cada modelo: Cadastrar, Editar, Buscar, Listar e Deletar.
os services, "chamam" os métodos das Daos, para recuperar informações ou alterar os TXTs.

Services.Auxiliares -> algumas classes e interfaces auxiliares, para os métodos de pedir os dados de algum modelo, fiz isso para evitar de "encher" as classes Services, visto que os dados viram do teclado.

Testes -> algumas classes de testes, que já recebem dados do usuário, via teclado, utilizei quando estava começando o projeto, para chamar os métodos dos Services.

Menu -> O Menu Principal contendo as opções para os outros Menus dos Serviços, utilizei o Menu em Switch Case com o while.

Classe RepublicaApplication -> resposável por iniciar o aplicativo.


- não implementados ainda -
* Modelo de Despesas FIXAS
* Blocos try_catch para evitar exceções ao Cadastrar ou Editar algum Modelo. através dos métodos getDados() dos services-auxiliares.
