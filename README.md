# SGO Odin


### Considerações do projeto

Para realizar o pull corretamente é necessário instalar o plugin do [git-lfs](https://git-lfs.com/).
Depois de instalado execute:

```shell
> git clone "url"

> cd odin-bff

> git lfs install 
```

## Ambiente de desenvolvimento


O projeto está usando docker para iniciar o banco de dados.
Para iniciar o container do banco de dados utilize o seguinte comando em um terminal:

#### Linux

```shell
$ sudo docker compose up
```

#### Windows

Abra o Cmd como administrador e entre na pasta do projeto

````cmd
C:\> cd {C:\diretorio\do\projeto\odin-bff}

C:\diretorio\do\projeto\odin-bff> docker compose up
````

Depois que  de iniciar o container, case seja a primeira execução, crie um database chamado `odin`.

Detalhes da conexão local:

- usuario: root
- senha: password
- host: localhost
- porta: 3307


