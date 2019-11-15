# UCS – Cadastrar Paciente

## 1. Introdução

Este caso de uso descreve as funções de cadastro do paciente.

## 2. Fluxo
<!BDD.INICIO>

### Tag
@desenv
### Funcionalidade: UCS - Cadastrar Paciente
Como Usuário, após ter realizado o cadastro do login  
Preciso complementar o cadastro com meus dados pessoais

### Contexto: Tela de Cadastro Pessoal
Dado que eu esteja na tela Cadastro de Paciente

### Esquema do cenario: Cadastro Pessoal
Quando eu preencher os campos `<Foto>`, `<DataNascimento>`, `<CidadedeNacimento>`, `<CidadeResidencia>`, `<NomePai>`, `<NomeMae>`, `<EstadoCivil>`  
E `<PossuiFilhos>`, `<QuantosFilhos>`, `<Raca>`, `<Etnia>`, `<OrientacaoSexual>`, `<Religiao>`, `<CPF>`  
E `<Escolaridade>`, `<Profissao>`, `<Peso>`, `<Altura>`, `<Telefone>`, `<TelefoneComercial>`  
E clicar no botão **Salvar**  
Então o sistema deverá apresentar a tela Cadastro Adicional.

Exemplos:

|Foto   |DataNascimento |CidadedeNacimento|CidadeResidencia|NomePai                     |NomeMae                    |EstadoCivil    |PossuiFilhos   |QuantosFilhos  |Raca     |Etnia   |OrientacaoSexual|Religiao |CPF             |Escolaridade   |Profissao  |Peso   |Altura |Telefone     |TelefoneComercial  |  
|-------|-----          |-----            |-------         |-----                       |-----                      |-------        |-----          |-----          |-------  |-----   |-----           |-------  |----            |-----          |-----      |-------|-----  |-----        |-----              |  
|""     |2000-01-20     |"CURITIBA"       |"CURITIBA"      |"ANTONIO CARLOS DA SILVA"   |"MARIA JOSEFINA DA SILVA"  |"CASADO"       |"SIM"          |5              |"PARDA"  |"BRANCO"|"HETERO"        |"CRISTA" |"12345678900"   |"TERCEIRO GRAU"|"MEDICO"   |80,5   |1,80   |"4133330000" |"4133330000"       |  
|""     |2001-10-20     |"PINHAIS"        |"CURITIBA"      |"ANTONIO CARLOS DA COSTA"   |"MARIA DA SILVA"           |"SOLTEIRO"     |"NAO"          |0              |"PARDA"  |"BRANCO"|"HETERO"        |"CRISTA" |"12345678900"   |"TERCEIRO GRAU"|"ESTUDANTE"|90,0   |1,70   |"4133330000" |"4133330000"       |  
|""     |1980-01-20     |"CURITIBA"       |"CURITIBA"      |"MARCO ANTONIO"             |"JOSEFINA DA SILVA"        |"CASADO"       |"NAO"          |0              |"PARDA"  |"BRANCO"|"HETERO"        |"CRISTA" |"12345678900"   |"TERCEIRO GRAU"|"MEDICO"   |60,5   |1,50   |"4133330000" |"4133330000"       |

### Esquema do cenario: Realizar Cadastro Incorreto
Quando eu preencher os campos `<Foto>`, `<DataNascimento>`, `<CidadedeNacimento>`, `<CidadeResidencia>`, `<NomePai>`, `<NomeMae>`, `<EstadoCivil>`  
E `<PossuiFilhos>`, `<QuantosFilhos>`, `<Raca>`, `<Etnia>`, `<OrientacaoSexual>`, `<Religiao>`, `<CPF>`  
E `<Escolaridade>`, `<Profissao>`, `<Peso>`, `<Altura>`, `<Telefone>`, `<TelefoneComercial>`  
E clicar no botão **Salvar**  
Então o sistema deverá apresentar a mensagem 'Item Obrigatorio Não Preenchido'.

Exemplos:

|Foto   |DataNascimento |CidadedeNacimento|CidadeResidencia|NomePai                     |NomeMae                    |EstadoCivil    |PossuiFilhos   |QuantosFilhos  |Raca     |Etnia   |OrientacaoSexual|Religiao |CPF             |Escolaridade   |Profissao  |Peso   |Altura |Telefone     |TelefoneComercial  |  
|-------|-----          |-----            |-------         |-----                       |-----                      |-------        |-----          |-----          |-------  |-----   |-----           |-------  |----            |-----          |-----      |-------|-----  |-----        |-----              |  
|""     |2000-01-20     |"CURITIBA"       |"CURITIBA"      |"ANTONIO CARLOS DA SILVA"   |"MARIA JOSEFINA DA SILVA"  |"CASADO"       |"SIM"          |5              |"PARDA"  |"BRANCO"|"HETERO"        |"CRISTA" |"12345678900"   |"TERCEIRO GRAU"|"MEDICO"   |80,5   |1,80   |"4133330000" |"4133330000"       |  
|""     |2001-10-20     |"PINHAIS"        |"CURITIBA"      |"ANTONIO CARLOS DA COSTA"   |"MARIA DA SILVA"           |"SOLTEIRO"     |"NAO"          |0              |"PARDA"  |"BRANCO"|"HETERO"        |"CRISTA" |"12345678900"   |"TERCEIRO GRAU"|"ESTUDANTE"|90,0   |1,70   |"4133330000" |"4133330000"       |  
|""     |1980-01-20     |"CURITIBA"       |"CURITIBA"      |"MARCO ANTONIO"             |"JOSEFINA DA SILVA"        |"CASADO"       |"NAO"          |0              |"PARDA"  |"BRANCO"|"HETERO"        |"CRISTA" |"12345678900"   |"TERCEIRO GRAU"|"MEDICO"   |60,5   |1,50   |"4133330000" |"4133330000"       |

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
* AVISO.002
* AVISO.003
* AVISO.004
* AVISO.005
* AVISO.006

## 6. Projeto
> Link para os artefatos de projeto relacionados ao caso de uso