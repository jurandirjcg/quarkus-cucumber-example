# UCS - Cadastrar Paciente

## 1. Introdução

Este caso de uso descreve as funções de cadastro do paciente.

## 2. Fluxo
<!BDD.INICIO>

### Tag
@desenv
### Funcionalidade
Como Usuário, após ter realizado o cadastro do login  
Preciso complementar o cadastro com meus dados pessoais

### Contexto: Tela de Cadastro Pessoal
Dado que eu esteja na tela Cadastro de Paciente

### Esquema do cenario: Cadastro Pessoal
E o sistema tenha preenchido o campo `'<nome>'`, obtido do usuário logado  
Quando eu preencher os campos `'<foto>'`, `<dataNascimento>`, `'<CPF>'`, `'<cidadedeNacimento>'`, `'<cidadeResidencia>'`, `'<pai>'`, `'<mae>'`  
E `'<estadoCivil>'`, `'<possuiFilhos>'`, `<quantosFilhos>`, `'<etnia>'`, `'<orientacaoSexual>'`, `'<religiao>'`  
E `'<escolaridade>'`, `'<profissao>'`, `<peso>`, `<altura>`, `'<telefone>'`, `'<telefoneComercial>'`  
E clicar no botão **Salvar** da tela de Cadastro de Paciente  
Então o sistema deverá apresentar a tela Cadastro Adicional.

Exemplos:

    |nome                   |foto   |dataNascimento |CPF            |cidadedeNacimento|cidadeResidencia|pai                         |mae                        |estadoCivil    |possuiFilhos   |quantosFilhos  |raca     |etnia   |orientacaoSexual|religiao |escolaridade   |profissao  |peso   |altura |telefone     |telefoneComercial  |  
    |-----                  |-------|-----          |-----          |-----            |-------         |-----                       |-----                      |-------        |-----          |-----          |-------  |-----   |-----           |-------  |-----          |-----      |-------|-----  |-----        |-----              |  
    |JOSE DA SILVA          |       |2000-01-20     |15437482019    |CURITIBA         |CURITIBA        |ANTONIO CARLOS DA SILVA     |MARIA JOSEFINA DA SILVA    |CASADO         |SIM            |5              |PARDA    |BRANCO  |HETERO          |OUTRO    |ANALFABETO     |OUTRO      |80,5   |1,80   |4133330000   |4133330000         |  
    |MARIA DA SILVA         |       |2001-10-20     |15437482019    |COLOMBO          |CURITIBA        |ANTONIO CARLOS DA COSTA     |MARIA DA SILVA             |SOLTEIRO       |NAO            |0              |PARDA    |BRANCO  |HETERO          |OUTRO    |ANALFABETO     |OUTRO      |90,0   |1,70   |4133330000   |4133330000         |  
    |ADRIANO DA SILVA       |       |1980-01-20     |15437482019    |CURITIBA         |CURITIBA        |MARCO ANTONIO               |JOSEFINA DA SILVA          |CASADO         |NAO            |0              |PARDA    |BRANCO  |HETERO          |OUTRO    |ANALFABETO     |OUTRO      |60,5   |1,50   |4133330000   |4133330000         |

### Esquema do cenario: Realizar Cadastro Incorreto
E o sistema tenha preenchido o campo `'<nome>'`, obtido do usuário logado  
Quando eu preencher os campos `'<foto>'`, `<dataNascimento>`, `'<CPF>'`, `'<cidadedeNacimento>'`, `'<cidadeResidencia>'`, `'<pai>'`, `'<mae>'`  
E `'<estadoCivil>'`, `'<possuiFilhos>'`, `<quantosFilhos>`, `'<etnia>'`, `'<orientacaoSexual>'`, `'<religiao>'`  
E `'<escolaridade>'`, `'<profissao>'`, `<peso>`, `<altura>`, `'<telefone>'`, `'<telefoneComercial>'`  
E clicar no botão **Salvar** da tela de Cadastro de Paciente  
Então o sistema deverá apresentar a mensagem "AVISO.001".

Exemplos:

    |nome                   |foto   |dataNascimento |CPF            |cidadedeNacimento|cidadeResidencia|pai                         |mae                        |estadoCivil    |possuiFilhos   |quantosFilhos  |raca     |etnia   |orientacaoSexual|religiao |escolaridade   |profissao  |peso   |altura |telefone     |telefoneComercial  |  
    |-----                  |-------|-----          |-----          |-----            |-------         |-----                       |-----                      |-------        |-----          |-----          |-------  |-----   |-----           |-------  |-----          |-----      |-------|-----  |-----        |-----              |  
    |JOSE DA SILVA          |       |2000-01-20     |15437482019    |CURITIBA         |CURITIBA        |ANTONIO CARLOS DA SILVA     |MARIA JOSEFINA DA SILVA    |CASADO         |SIM            |5              |PARDA    |BRANCO  |HETERO          |OUTRO    |ANALFABETO     |OUTRO      |80,5   |1,80   |4133330000   |4133330000         |  
    |MARIA DA SILVA         |       |2001-10-20     |15437482019    |COLOMBO          |CURITIBA        |ANTONIO CARLOS DA COSTA     |MARIA DA SILVA             |SOLTEIRO       |NAO            |0              |PARDA    |BRANCO  |HETERO          |OUTRO    |ANALFABETO     |OUTRO      |90,0   |1,70   |4133330000   |4133330000         |  
    |ADRIANO DA SILVA       |       |1980-01-20     |15437482019    |CURITIBA         |CURITIBA        |MARCO ANTONIO               |JOSEFINA DA SILVA          |CASADO         |NAO            |0              |PARDA    |BRANCO  |HETERO          |OUTRO    |ANALFABETO     |OUTRO      |60,5   |1,50   |4133330000   |4133330000         |
<!BDD.FIM>

## 3. Casos de Uso Relacionados
* UCS - Cadastro Inicial

## 4. Interface
    | Protótipo                                                    | Path                       | Desenv | Homolog | Prod | Arquivo |  
    | ------                                                       | ------                     | -----  | -----   |----- |---------|  
    | cadastro pessoal                                             | /cadastro-pessoal/         |        |         |      |         |  
    | home                                                         | /home/                     |        |         |      |         |  

## 5. Mensagens ([ApplicationMessages](src/main/resources/ApplicationMessages.properties))
* AVISO.001

## 6. Projeto
> Link para os artefatos de projeto relacionados ao caso de uso
