# lpoo_unifametro
Repositório da Cadeira de Linguagem de Programação Orientada a Objeto da Unifametro - SINF3N

Projeto da APS - Sistema de Cadastro de Despesas -
Equipe: Breno, Wilson, Ricardo

Pastas do projeto:

Modelo -> representam os modelos de um aluno, despesa e reserva

Persistência -> possui as classes Dao, responsáveis por manipular os arquivos de cada modelo,
exemplo AlunoDao, contém os registros dos alunos.

Services -> tem os serviços para cada modelo, exemplo, cadastrar, editar, buscar e listar, ainda não criei o delete.
os services, "chamam" os métodos das Daos, para recuperar informações ou alterar os TXTs.

Testes -> algumas classes de testes, que já recebem dados do usuário, via teclado.


-- não criado ainda --
Ainda serão criados os menus, estes, "chamaram" os métodos das classes de Service de cada modelo.

delete para os registros de aluno e despesas.

criar uma despesa, passando outras "prioridades"

a classe de Reserva

