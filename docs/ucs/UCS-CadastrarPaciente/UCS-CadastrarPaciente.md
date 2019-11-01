# UCS – Cadastrar Paciente

## 1. Introdução

Este caso de uso descreve as funções de cadastro do paciente.

## 2. Fluxo
<!BDD.INICIO>

### Tag
@analise
### Funcionalidade: UCS - Cadastrar Paciente
Como Usuário, após ter realizado o cadastro do login  
Preciso complementar o cadastro com meus dados pessoais, de endereço e informações de saúde

### Contexto: Tela de Cadastro Pessoal
Dado que eu esteja na tela Cadastro Pessoal

### Esquema do cenario: Cadastro Pessoal
Quando eu preencher os campos `<Foto>`,`<DataNascimento>`, `<CidadedeNacimento>`, `<CidadeResidência>`, `<NomePai>`, `<NomeMãe>`, `<EstadoCivil>`, `<PossuiFihos>`  
E `<QuantosFilhos>`, `<Raca>`, `<Etnia>`, `<OrientacaoSexual>`, `<Religiao>`, `<Escolaridace>`, `<Profissao>`,  
E `<Peso>`, `<Altura>`, `<Telefone>`, `<TelefoneComercial>`  
E clicar no botão **Salvar**  
Então o sistema deverá apresentar a tela Cadastro Adicional.

Exemplos:

|Foto|DataNascimento|CidadedeNacimento|CidadeResidência|NomePai|NomeMae|EstadoCivil|PossuiFilhos|QuantosFihos|Raca|Etnia|OrientacaoSexual|Religiao|Escolaridade|Profissao|Peso|Altura|Telefone|TelefoneComercial|  
|-------|-----|-----|-------|-----|-----|-------|-----|-----|-------|-----|-----|-------|-----|-----|-------|-----|-----|-----|
|imagem|27-01-2000|"CURITIBA"|"CURITIBA"|"JOSE"|"MARIA"|"CASADO"|"SIM"|5|""|"BRANCO"|"HETERO"|"CRISTA"|"TERCEIRO GRAU"|"MEDICO"|80|1.80|4133330000|4133330000|

### Esquema do cenario: Realizar Cadastro Incorreto
Quando eu preencher os campos `<Foto>`,`<DataNascimento>`, `<CidadedeNacimento>`, `<CidadeResidência>`, `<NomePai>`, `<NomeMãe>`, `<EstadoCivil>`, `<PossuiFihos>`  
E `<QuantosFilhos>`, `<Raca>`, `<Etnia>`, `<OrientacaoSexual>`, `<Religiao>`, `<Escolaridace>`, `<Profissao>`,  
E `<Peso>`, `<Altura>`, `<Telefone>`, `<TelefoneComercial>`  
E clicar no botão **Salvar**  
Então o sistema deverá apresentar a mensager 'Item Obrigatorio Não Preenchido'.

Exemplos:

|Foto|DataNascimento|CidadedeNacimento|CidadeResidência|NomePai|NomeMae|EstadoCivil|PossuiFilhos|QuantosFihos|Raca|Etnia|OrientacaoSexual|Religiao|Escolaridade|Profissao|Peso|Altura|Telefone|TelefoneComercial|  
|-------|-----|-----|-------|-----|-----|-------|-----|-----|-------|-----|-----|-------|-----|-----|-------|-----|-----|-----|
|imagem|27-01-2000||"CURITIBA"|"JOSE"|"MARIA"||"SIM"|5|""|"BRANCO"|"HETERO"|"CRISTA"|"TERCEIRO GRAU"|"MEDICO"|80|1.80|4133330000|4133330000|

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