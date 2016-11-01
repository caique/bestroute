# Best Route App

### Sobre
Esse projeto contém a solução para a questão 1 do **Teste Prático #TeamKlickpages**, finalizado em 01/11/2016 por [Caique Peixoto](http://caiquerodrigues.github.io).

Essa aplicação tem como objetivo calcular a melhor rota entre duas localidades considerando as interdições e conexões existentes em um mapa cuja as configurações são definidas em um arquivo .txt fornecido pelo usuário no ínicio da execução.

##### Quanto ao algoritmo...
A escolha da melhor rota foi implementada utilizando duas variações do algoritmo de [Dijkstra](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm) para definição do menor caminho entre os nós de um grafo onde:
  - os vértices representam as localidades/interdições;
  - as arestas representam as ruas; e
  - o grafo a cidade/mapa.

##### Quanto aos objetos de negócio...
Uma localidade é definida por um par ordenado que representa um ponto dentro de um plano cartesiano.
Uma rua é a conexão entre duas localidades e pode ser vertical ou horizontal.
Uma interdição é uma localidade que inabilita a passagem por uma rua.

##### Quanto as definições de melhor trajeto...
O caminho mais curto é aquele cujo trajeto entre origem e destino contém menos pontos.
O caminho mais rápido considera o tempo para percorrer uma rua representada por uma variação da fórmula de velocidade `V=d/t`, sendo o tempo definido como `t=d/V` dado em `pontos por unidade de tempo`.

O caminho mais rápido refere-se ao trajeto entre origem e destino cujo tempo para percorrer todos os pontos é o menor. O tempo para percorrer uma rua é definido como `t=d/V` dado em `pontos por unidade de tempo`, sendo o tempo para atingir uma localidade o somatório dos tempos para percorrer todos os pontos do trajeto desde a origem.

A distância entre duas localidades (A e B) conectadas por uma rua vertical é dada pela fórmula `d(A,B) = |Ay - By|`, enquanto para a distância entre dois pontos conectados por uma rua horizontal é dada por `d(A,B) = |Ax - Bx|`. Ambas as definições só se aplicam caso A e B sejam vizinhos imediatos.

##### Quanto aos resultados.....
A aplicação indica a direção que se deve tomar e por qual rua passar para atingir o próximo ponto do trajeto rumo ao destino. O formato de apresentação é `<DIRECAO>-<NOME_DA_RUA>`, sendo as direções:
- `N` para `Norte`
- `S` para `Sul`
- `E` para `Leste`
- `W` para `Oeste`

A aplicação lança uma exceção caso os pontos sejam desconexos, isto é, não há trajeto viável entre eles.

### Exemplo
##### Configuração da Cidade (city.txt)
Street A-(0,0);(4,0):10  
Street B-(4,0);(4,4):10  
Street C-(4,4);(0,4):100  
Street D-(0,4);(0,0):100  

###### Caso \#1
- **CENÁRIO**: Rota mais rápida entre (0,0) e (4,0)
- **RESULTADO**: E-Street A

###### Caso \#2
- **CENÁRIO**: Rota mais rápida entre (0,0) e (4,0) passando por (4,4)
- **RESULTADO**: N-Street D, E-Street C S-Street B

###### Caso \#3
- **CENÁRIO**: Rota mais curta entre (0,0) e (4,4)
- **RESULTADO**: E-Street A, N-Street B

### Requisitos
1. [Java 1.8+](https://www.java.com/en/download/)
2. [Maven 3.3.x+](http://maven.apache.org/download.cgi)

### Execução
##### Eclipse
1. Instale os requisitos
2. Abra o Eclipse e importe o projeto pela opção `Existing Maven Projects`
3. Clique com o botão direito sobre o arquivo `pom.xml` e depois em `Run As -> Maven install`
4. Clique com o botão direito sobre a pasta raiz do projeto e depois em `Run As -> Java Application`

##### Terminal (Bash)
1. Instale os requisitos
2. Abra o terminal e navegue até a pasta raiz do projeto
3. Conceda privilégios de execução para o arquivo `bestroute.sh`
```sh
$ sudo chmod 777 bestroute.sh
```
4. Execute a aplicação com o comando:
```sh
$ ./bestroute.sh run
```

### Links Úteis
- [How to install Java](https://www.java.com/en/download/help/download_options.xml)
- [How to install Maven](http://maven.apache.org/install.html)
- [How to install Eclipse](https://www.ntu.edu.sg/home/ehchua/programming/howto/EclipseJava_HowTo.html)
