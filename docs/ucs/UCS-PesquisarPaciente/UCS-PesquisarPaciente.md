# UCS - Pesquisar Paciente

## 1. Introdução
> Este caso de uso descreve as funções para pesquisa de paciente

## 2. Requisitos
> F01 - Permitir a pesquisa de pacientes

## 3. Fluxo
<!BDD.INICIO>

### Tag
@analise
### Funcionalidade
Como administrador  
Preciso consultar as informações dos pacientes cadastrados 

#### Regras de Negocio:
RN01: Pesquisa por "Nome" deverá possibilitar a busca parcial

### Contexto: Tela de pesquisa
Dado que eu esteja na tela para pesquisa de paciente (Pesquisar Paciente)  
E que os filtros estejam em branco
  
### Esquema do Cenario: Pesquisar pacientes   
Quando eu preencher os campos `<Nome>`, `<DataNascimento>`, `<CPF>`, `<RG>`, `<UFRG>` (RN01)  
E eu acionar o botão de **Pesquisar**  
Entao o sistema deverá listar os pacientes correspondentes
  
Exemplos:
  
| Nome                 | DataNascimento | CPF           | RG             | UFRG |
|-------               |--------        |--------       | --------       |----- |
|JURANDIR CORDEIRO     |                |               |                |      |
|                      |01/01/1983      |               |                |      |
|                      |                |01234567899    |                |      |
|JURANDIR CORDEIRO     |                |               |12345644        |PR    |

### Cenário: Pesquisar pacientes sem resultado
Quando eu preencher o campo Nome com "PACIENTE DE TESTE"  
E eu acionar o botão de **Pesquisar**  
Entao o sistema deverá retornar a mensagem de "AVISO.001"
  
### Cenário: Redirecionar para UCS - Cadastrar Paciente (UCS - Cadastrar Paciente)  
Quando eu acionar o botão **Incluir**    
Entao o sistema deverá apresentar a tela de **Cadastro de Paciente** (Cadastrar Paciente) 

<!BDD.FIM>

## 4. Casos de Uso Relacionados
* UCS - Cadastrar Paciente

## 5. Interface
| Protótipo                                                    | Path                       | Desenv | Homolog | Prod |
| ------                                                       | ------                     | -----  | -----   |----- |
| Pesquisar Paciente                                           | /pacientes/                |        |         |      | 
| Cadastrar Paciente                                           | /pacientes/                |        |         |      |

## 6. Mensagens ([ApplicationMessages](src/main/resources/ApplicationMessages.properties))
* AVISO.001

## 7. Projeto
> Link para os artefatos de projeto relacionados ao caso de uso