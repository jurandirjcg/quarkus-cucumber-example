# UCS – Esqueci a Senha

## 1. Introdução
Este caso de uso descreve as funções para alteração de senha
 
## 3. Fluxo
<!BDD.INICIO>

### Tag
@analise

### Funcionalidade: UCS - Esqueci a Senha
Como Usuario 
Precisa altera a senha cadastrada

#### Regras de Negocio:
RN01: O sistema deverá alterar a senha por "Email" do usuario

### Contexto: Tela esqueci a senha
Dado que eu esteja na tela de esqueci minha senha. (Esqueci a Senha)  

### Esquema do cenario: Esqueci a Senha
Quando eu preencher os campos `<Email>` (RN01) 
E clicar no botão **Redefinir Senha**  
Então o sistema deverá enviar um link para o email  
E redirecionar para tela do Autenticação do Usuario

Exemplos:

|Email|  
|----|  
|allanlsilvestre@gmail.com|  
|jurandir@gmail.com|

### Esquema do Cenario: Email Incorreto
Quando eu preencher o campo `<Email>`  
E clicar no botão **Redefinir Senha**  
Entao o sistema deverá retornar a mensagem de "AVISO.001"

Exemplos:

|Email|  
|----|  
|allanlsilvestre|

### Cenario: Redirecionar para UCS - Autenticação do Usuairo  (UCS - Autenticação do Usuairo) 
Quando eu clicar no botão **Redefinir**  
Então o sistema deverá direcionar para tela **Autenticação do Usuairo** (UCS-Efetuar Login)

<!BDD.FIM>

## 4. Casos de Uso Relacionados
* UCS - Efetuar Login

## 5. Interface
| Protótipo                                                    | Path                       | Desenv | Homolog | Prod | Arquivo |
| ------                                                       | ------                     | -----  | -----   |----- |---------|
| Redefinir Senha                                              | /Redefinir Senha /         |        |         |      |         |
| Login                                                        | /Login/                    |        |         |      |         |        

## 6. Mensagens ([ApplicationMessages](src/main/resources/ApplicationMessages.properties))
* AVISO.001
* AVISO.002

## 7. Projeto
> Link para os artefatos de projeto relacionados ao caso de uso