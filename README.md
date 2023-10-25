# SGO Odin
-

## Ambiente de desenvolvimento
-

O projeto está usando docker para iniciar o banco de dados.
Para iniciar o container do banco de dados utilize o seguinte comando em um terminal:
#### Linux
```shell
$ sudo docker compose up
```
#### Windows

````cmd
# abra o Cmd como administrador e entre na pasta do projeto
C:\> cd {C:\diretorio\do\projeto\odin-bff}
C:\diretorio\do\projeto\odin-bff> docker compose up
````
Depois que  de iniciar o container, case seja a primeira execução, crie a tabela chamada `odin` no banco de dados.

Detalhes da conexão local:

- usuario: root
- senha: password
- host: localhost
- porta: 3307
