# UCS – Cadastrar Login  

## 1. Introdução
Este caso de uso descreve as funções de Cadastro inicial do Usuario.

## 3. Fluxo
<!BDD.INICIO>

### Tag
@analise
### Funcionalidade: UCS - Cadastrar Login
Como Usuario 
Precisa efetuar o cadastro no sistema

#### Regras de Negocio:
RN01: Efetivar o cadastro por "Nome"

### Contexto: Tela de cadastro
Dado que eu esteja na tela para cadastro inicial (Cadastro Inicial)

### Esquema do cenario: Cadastrar usuarios
Quando eu preencher os campos `<usuario>`, `<email>`, `<senha>` (RN01)
E clicar no botão **Criar**
Então o sistema deverá apresentar a tela cadastro efetivado

Exemplos:

| usuario |email|senha|
| ------- |-----|-----|
| allan   | allanlsilvestre@gmail.com|1234|
| jurandir| jurandir@gmail.com|1234|

 
### Cenario: Realizar cadastro incorreto
Quando eu preencher os campos `<usuario>`, `<email>`, `<senha>`
E clicar no botão **Criar**
Então o sistema deverá apresentar a mensagem "AVISO.001"


### Cenario: Redirecionar para UCS - Cadastro Pessoal (UCS - Cadastro Pessoal) 
Quando eu clicar no botão **Salvar**
Então o sistema deverá direcionar para tela **Cadastro inicial** (Cadastro inicial)

<!BDD.FIM>

## 4. Casos de Uso Relacionados
* UCS - Cadastrar Paciente

## 5. Casos de Uso Relacionados
* UCS - Cadastro Inicial

## 5. Interface
| Protótipo                                                    | Path                       | Desenv | Homolog | Prod | Arquivo |
| ------                                                       | ------                     | -------|---------|----- |---------|
| Nome                                                         | /login/                    |        |         |      |         |
| home                                                         | /cadastro inicial/         |        |         |      |         |


## 6. Mensagens ([ApplicationMessages](src/main/resources/ApplicationMessages.properties))
* AVISO.001
* AVISO.002
* AVISO.003
* AVISO.004
* AVISO.005
* AVISO.006

## 7. Projeto
> Link para os artefatos de projeto relacionados ao caso de usoo