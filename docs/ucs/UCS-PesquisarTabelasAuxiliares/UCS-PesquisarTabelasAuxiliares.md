# UCS - Pesquisar Tabelas Auxiliares

## 1. Introdução
> Este caso de uso descreve as funções para pesquisa das tabelas auxiliares

## 2. Requisitos
> N/A

## 3. Fluxo
<!BDD.INICIO>

### Tag
@analise
### Funcionalidade
Como usuario logado  
Preciso listar e obter as informações das tabelas auxiliares: Escolaridade, Religião, Estado Civil, Orientação Sexual, Etnia e Profissão. 
  
### Esquema do Cenario: Pesquisar Escolaridade
Dado que eu esteja listando as informações da escolaridade  
Quando eu preencher os campos `<id>`, `"<nome>"`  
E eu acionar o botão de pesquisar escolaridade, **Pesquisar**  
Entao o sistema deverá listar as escolaridades correspondentes

Exemplos:

    |id    |nome            |  
    |----  |----            |  
    |1     |                |  
    |2     |ALFABETIZADO    |  
    |      |SUPERIOR        |

### Esquema do Cenário: Obter Escolaridade
Dado que eu esteja obtendo uma escolaridade  
Quando eu buscar pelo `<id>`  
E eu acionar o botão de obter escolaridade, **Obter**  
Entao o sistema deverá retornar a escolaridade `"<nome>"`

Exemplos:

    |id    |nome            |  
    |----  |----            |  
    |1     |ANALFABETO      |  
    |2     |ALFABETIZADO    |  
    |5     |SUPERIOR        |

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