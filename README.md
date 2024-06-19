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
