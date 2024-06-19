# BookProject

Este projeto consiste em um servidor e um cliente para manipulação de objetos `Book`. O `ServerThread` inicia um servidor na porta 8080. O `ClientService` se conecta ao servidor usando o endereço IP `127.0.0.1` e porta 8080. A classe `Client` permite a interação com o servidor. A função `parsingTests` demonstra a serialização e desserialização de objetos `Book`, e a impressão dos dados dos livros tanto no cliente quanto no servidor.

## Classes Principais

- **Main:** Ponto de entrada, inicializa servidor e cliente.
- **ServerThread e Server:** Gerencia o servidor.
- **ClientService e Client:** Gerencia o cliente.
- **BookParser:** Serializa e desserializa objetos `Book`.

## A Classe Server

Usa a porta especificada e se comunica com um cliente para realizar operações como registrar, alugar e devolver livros. Os dados dos livros são armazenados em um arquivo JSON, `livros.json`.

### Funcionamento

- **Iniciar o servidor:** O método `start(int port)` inicia o servidor na porta fornecida, aguardando uma conexão de cliente.
- **Enviar dados ao cliente:** O método `sendData()` envia o conteúdo atual do banco de dados ao cliente.
- **Registrar, alugar e devolver livros:** Métodos `registerBook(String data)`, `rentBook(String data)` e `returnBook(String data)` manipulam os dados dos livros conforme os comandos recebidos do cliente.
- **Manipulação de arquivos:** Métodos `scanFile()` e `saveFile(Book[] list)` leem e escrevem no arquivo JSON respectivamente.

## A Classe ServerThread

Estende `Thread` e contém um objeto `Server` e um número de porta. Quando `run()` é chamado, o servidor é iniciado na porta especificada. O servidor manipula operações como registro, aluguel e devolução de livros, com dados armazenados em um arquivo JSON.

### Funcionamento

- **ServerThread:** Inicializa e executa o servidor na porta especificada ao chamar o método `run()`.
- **Server:** Responsável por iniciar o servidor, aceitar conexões de clientes e processar comandos de registro, aluguel e devolução de livros.

## A Classe ClientService

Implementa um cliente para interagir com o servidor de gerenciamento de livros. A classe `ClientService` gerencia a conexão do cliente com o servidor, permitindo a realização de operações como registrar, alugar e devolver livros. A comunicação é feita através de sockets.

### Funcionamento

- **Conexão:** O método `start()` estabelece a conexão com o servidor usando o IP e porta fornecidos.
- **Obtenção de Livros:** O método `getAllBooks()` recebe e desserializa a lista de livros do servidor.
- **Registro de Livro:** O método `registerNewBook(Book book)` envia um novo livro para o servidor registrar.
- **Aluguel e Devolução:** Os métodos `rent(int index)` e `returnBook(int index)` enviam comandos para alugar ou devolver um livro, respectivamente.

## A Classe Client 

É responsável por interagir com o servidor de gerenciamento de livros através do `ClientService`. O cliente pode visualizar a lista de livros, registrar novos livros, alugar e devolver livros.

## Funcionamento

- **start():** Inicia o cliente, conectando-se ao servidor e permitindo interações repetidas até que o usuário decida sair.
- **showBookList(Book[] books):** Exibe a lista de livros disponíveis e permite ao usuário escolher entre alugar, devolver um livro ou registrar um novo.
- **showBookData(Book book, int index):** Apresenta os detalhes de um livro específico e oferece opções para alugar, devolver ou voltar ao menu anterior.
- **registerNewBook():** Solicita ao usuário informações sobre um novo livro e envia os dados ao servidor para registro.
- **rentBook(int index) e returnBook(int index):** Envia solicitações ao servidor para alugar ou devolver um livro com base no índice fornecido.
- **readInt() e readString():** Métodos auxiliares para ler entrada do usuário, tratando tanto inteiros quanto strings.
- **print(Book book) e print(String string):** Métodos para exibir informações formatadas na saída do cliente.

Este cliente facilita a interação com o servidor de gerenciamento de livros, permitindo operações simples e eficazes através de uma interface de linha de comando.

## A Classe BookParser

É a que facilita a conversão de objetos `Book` para strings JSON e vice-versa, usando a biblioteca Gson para serialização e desserialização.

## Funcionamento

- **toString(Book book):** Converte um objeto `Book` em uma string JSON formatada, usando os campos especificados (`titulo`, `autor`, `genero`, `exemplares`).
- **toString(Book[] books):** Converte um array de objetos `Book` em uma string JSON completa, iniciada com o prefixo `{"livros": [` e finalizada com `]}`.
- **toBook(String string):** Converte uma string JSON em um objeto `Book`, utilizando Gson para realizar a desserialização.
- **toBookList(String string):** Converte uma string JSON em um array de objetos `Book`, utilizando a classe interna `BooksJSONFile` para mapear a estrutura JSON para um objeto Java.

Esta classe é essencial para a integração entre o servidor e o cliente do sistema de gerenciamento de livros, garantindo a correta manipulação e comunicação dos dados no formato JSON.



## A Classe Book

Representa um livro com atributos como autor, título, gênero e número de exemplares. Cada atributo é mapeado para chaves específicas no JSON usando a anotação `@SerializedName`.

## Funcionamento

- **Construtor:** Inicializa um objeto `Book` com os atributos fornecidos (`author`, `title`, `theme`, `numCopies`).
- **Getters:** Métodos para obter os valores dos atributos (`getAuthor()`, `getTitle()`, `getTheme()`, `getNumCopies()`).
- **Métodos de Manipulação de Exemplares:** 
  - `takeAllCopies()`: Retorna e zera o número total de exemplares.
  - `takeCopies(int quantity)`: Tenta retirar uma quantidade específica de exemplares, retornando a quantidade realmente retirada.
  - `returnCopies(int quantity)`: Adiciona uma quantidade específica de exemplares de volta ao estoque.

Essa classe é fundamental para o sistema de gerenciamento de livros, oferecendo métodos simples e eficazes para manipulação dos dados dos livros.

### ALUNOS

#### Bruno Rocha de Araujo - UC20100464
#### Larissa Carlos de Paulo - UC20103063



