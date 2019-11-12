Importar como maven project.

Ter servidor mysql local rodando com banco criado chamado "teste_vaga" 
//podendo alterar as configurações de conexão no arquivo application.properties

Rodar projeto com spring boot classe principal: TesteVagaApplication.

A API contém os seguintes endpoints:

GET: http://localhost:8080/transferencia/
Parametros:
  -remetente:Texto //atualmente só aceita 2 valores [BANCOY,BANCOX] mas esta bem dinamico o código para acrescentar mais possibilidades. 
Exemplo: 
  http://localhost:8080/transferencia?remetente=BANCOX
  Retorna todas as transferencias enviadas pelo "BANCOX"
  
GET: http://localhost:8080/transferencia/paginada
Parametros:
  -remetente:Texto //idem, atualmente só aceita 2 valores [BANCOY,BANCOX] 
  -size:Inteiro //quantidade de itens por pagina
  -page:Inteiro //pagina
  -sort:Texto //modo de ordenacao, pode repetir esse parametro varias vezes a fim de ordenar por mais campos
Exemplo: 
  http://localhost:8080/transferencia/paginada?remetente=BANCOX&page=0&size=20&sort=id,desc
  Faz o retorno paginado das transferencias enviadas pelo "BANCOX" conforme passagem de parametros 
  e segue a ordenacao solicitada no caso por Id descrescente no caso.
  
OBS: Endpoint colocado para trazer os dados aos poucos ao invés de todos de uma vez, pensando no número de dados que pode ser grande

POST: http://localhost:8080/transferencia/unica
Parametros:
  -remetente:Texto //atualmente só aceita 2 valores [BANCOY,BANCOX]
  -destinatario:Texto //atualmente só aceita 2 valores [BANCOY,BANCOX]
  -linha:Texto //linha contendo o arquivo no formado do remetente
Exemplo: 
  http://localhost:8080/transferencia/unica
  Headers: Content-Type = application/json
  Body: 
  {
    "remetente":"BANCOX",
    "destinatario":"BANCOY",
    "linha":"TED0114004851Antonio Alves da Silva        54561747150003256924032017","TED0114004851Antonio Alves da Silva        54561747150003256924032017"
  }
  
  Retorna a linha convertida no formato do destinatario, e realiza as operações de persistencia necessárias.

POST: http://localhost:8080/transferencia/lote
Parametros:
  -remetente:Texto //atualmente só aceita 2 valores [BANCOY,BANCOX]
  -destinatario:Texto //atualmente só aceita 2 valores [BANCOY,BANCOX]
  -linhas:Texto[] //vetor de string contendo as linhas no formato do remetente
Exemplo: 
  http://localhost:8080/transferencia/unica
  Headers: Content-Type = application/json
  Body: 
  {
    "remetente":"BANCOX",
    "destinatario":"BANCOY",
    "linha":[
            "TED0114004851Antonio Alves da Silva        54561747150003256924032017",
            "TED0114004851Antonio Alves da Silva        54561747150003256924032017",
            "TED0114004851Antonio Alves da Silva        54561747150003256924032017",
            "TED0114004851Antonio Alves da Silva        54561747150003256924032017"
            ]
  }
  
  Retorna vetor com as linhas convertidas no formato do destinatario na ordem de recebimento, 
  e realiza as operações de persistencia necessárias.

Deixei o Actuator ativado para o monitoramento dos endpoints.

OBS: Nao utilizei nenhum tipo de segurança por se tratar de uma aplicação de testes.

Foi criada a interface ConversorTransferencia, ao surgir um novo banco por exemplo basta criar uma implementação dessa interface para ele,
escrevendo os 2 metodos de conversao para Transferencia e de Transferencia para texto, 
que esse banco ja vai estar apto a se comunicar com todos os outros. 






