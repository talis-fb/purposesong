# PurposeSong

![Screenshot](https://res.cloudinary.com/dfjn94vg8/image/upload/v1687727344/b73d1420-3630-4d7b-90df-e05e21187f77_wmbgzw.jpg)

# Pré-requisitos
Certifique-se de ter o seguinte instalado em seu sistema:

* Java Development Kit (JDK) 17 ou posterior
* [Apache Maven](https://maven.apache.org/install.html)

# Configuração

Para executar a aplicação JavaFX, siga estas etapas:
```sh
git clone https://github.com/talis-fb/purposesong.git
cd purposesong
mvn clean package
mvn javafx:run
```

Isso irá iniciar a aplicação e abrir a janela principal.

Caso você não tenha o Maven instalado na máquina é possivel utilizar os dois executaveis executar os comandos

### Linux
```sh
./mvnw clean package
./mvnw javafx:run
```

### Windows
```sh
./mvnw.cmd clean package
./mvnw.cmd javafx:run
```


# Estrutura do Projeto
O projeto segue a estrutura padrão de projetos Maven. Os principais componentes são:

* `src/main/java`: Contém os arquivos-fonte Java da aplicação.
* `src/main/resouces`: Contém os arquivos de recursos, como arquivos FXML, arquivos CSS, imagens e outros recursos.
* `src/test/java`: Contém os casos de teste da aplicação.
* `pom.xml`: O arquivo de configuração do projeto Maven