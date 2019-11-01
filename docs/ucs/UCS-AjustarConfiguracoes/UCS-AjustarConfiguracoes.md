# UCS – Ajustar Configuracoes 

## 1. Introdução
Este caso de uso descreve as funções de Configurações.

## 3. Fluxo
<!BDD.INICIO>
### Tag
@analise
### Funcionalidade: UCS - Ajustar Configuracoes  
Como Usuario
Preciso ultilizar o menu para acessar as funcionalidades do sistema

#### Regras de Negocio:
RN01: Selecionar itens de botão menu disponiveis

### Contexto: Configurações 
Dado que eu esteja na tela para seleção opção menu (Configurações)

### Cenario: Redirecionar para UCS - Configurações (UCS - Redefinir Senha) 
Quando clicar no botão **Mudar Senha**
Entao o sistema deverá apresentar a tela de **Mudar Senha** (Mudar Senha)

### Cenario: Configurações 
Quando clicar no botão **Mudar Dados Clinicos**
Então o sistema deverá apresentar a tela de **Mudar Dados Clinicos** (Mudar Dados Clinicos) 

### Cenario:  Configurações
Quando clicar no botão **Contato de Emergencia**
Então o sistema deverá apresentar a tela de **Contato de Emergencia** (Contato de Emergencia)

<!BDD.FIM>

## 4. Casos de Uso Relacionados
* UCS - Cadastrar Paciente

## 5. Interface
| Protótipo                                                    | Path                       | Desenv | Homolog | Prod | Arquivo |
| ------                                                       | ------                     | -------|---------|----- |---------|
| Mudar Senha                                                  | /mudarsenha/               |        |         |      |         |
| Mudar Dados Clinicos                                         | /dadosclinicos /           |        |         |      |         |
| Contato de Emergencia                                        | /contatoemergencia /       |        |         |      |         |

## 6. Mensagens ([ApplicationMessages](src/main/resources/ApplicationMessages.properties))
* AVISO.001

## 7. Projeto
> Link para os artefatos de projeto relacionados ao caso de uso
