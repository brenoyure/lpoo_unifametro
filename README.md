# lpoo_unifametro
Repositório da Cadeira de Linguagem de Programação Orientada a Objeto da Unifametro - SINF3N

Projeto da APS - Sistema de Cadastro de Despesas

Pastas do projeto:

Modelo -> representam os modelos de um Aluno, Despesa (Apenas a com Prioridade) e Reserva.

Persistência -> possui as classes Dao, responsáveis por manipular os arquivos de cada modelo,
exemplo AlunoDao, contém os registros dos alunos.

Services -> tem os serviços para cada modelo, exemplo, cadastrar, editar, buscar e listar, ainda não criei o delete.
os services, "chamam" os métodos das Daos, para recuperar informações ou alterar os TXTs.

Testes -> algumas classes de testes, que já recebem dados do usuário, via teclado, utilizei quando estava começando o projeto, para chamar os métodos dos Services.

Menu -> O Menu Principal contendo as opções para os outros Menus dos Serviços.

Classe RepublicaApplication -> resposável por iniciar o aplicativo.


- não implementado ainda -
Modelo de Despesas FIXAS
