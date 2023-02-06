# task-manager

## Sumário

- [task-manager](#task-manager)
  - [Sumário](#sumário)
  - [Motivação](#motivação)
  - [Pilha de tecnologia](#pilha-de-tecnologia)

## Motivação

Este app consiste em um gerenciador de tarefas simples. Nele, um usuário pode se cadastrar e adicionar tarefas com categorias. Isto posto, as telas são protegidas por autenticação por e-mail e senha. Uma tarefa contém os seguintes atributos:

- Título;
- Prazo;
- Prioridade;
- Data de criação;
- Data de edição;
- Categoria.

As quatro prioridades aceitas pelo app são: Baixa; Média; Alta e Pronto-Socorro. Vale destacar que este app não faz conexão com um banco de dados mas dispõe todas as informações armazenadas em três arquivos: [Categorias.json](./src/br/edu/ifms/taskmanager/mockBD/Categorias.json), [Tarefas.json](./src/br/edu/ifms/taskmanager/mockBD/Tarefas.json) e [Usuarios.json](./src/br/edu/ifms/taskmanager/mockBD/Usuarios.json).

Este foi o primeiro repositório de código apresentado no [Curso Técnico Integrado em Informática do IFMS](https://www.ifms.edu.br/campi/campus-aquidauana/cursos/integrado/informatica) como requisito para obtenção da nota parcial das atividades da unidade curricular Linguagem de Programação III.

## Pilha de tecnologia

As seguintes tecnologias foram utilizadas para desenvolver este app:

| Papel | Tecnologia |
|-|-|
| Linguagem de programação | [Java](https://www.java.com/pt-BR/) |
| Ambiente de desenvolvimento integrado | [Eclipse](https://www.eclipse.org/downloads/) |
| Gerenciador de pacotes | [Maven](https://maven.apache.org/) |
| Biblioteca de serialização | [GSON](https://github.com/google/gson) |