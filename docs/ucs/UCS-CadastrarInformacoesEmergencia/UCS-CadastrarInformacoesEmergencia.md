# UCS – Cadastrar Informaçoes de Emergência

## 1. Introdução
Este caso de uso descreve as funções de informaçoes de emergencia.
 
## 3. Fluxo
<!BDD.INICIO>

### Tag
@analise
### Funcionalidade
Como administrador  
Preciso consultar as informações do pacientes  

#### Regras de Negocio:
RN01: Pesquisa por "Nome" deverá possibilitar a busca parcial

### Contexto: Tela de Cadastro Medico do Usuario
Dado que eu esteja na tela pesquisa perfil (Informações do Perfil Emergencia)  

### Esquema do cenario: Tela Cadastro Medico do Usuario
Quando eu preencher os campos `<TipoSanguineo>`, `<FatorRH>`, `<TransfusaoSangue>`, `<NecessidadesEspeciais>`, `<NecessidadeLimitacao>`, `<Preexistentes>`, `<PreexistentesTipo>`  
E `<PreexistentesNomeDoenca>`, `<DoencasGravesFamilia>`, `<NomeDoenca>`, `<QualParentesco>`, `<HouveCura>`, `<Alergico>`, `<AlergicoTipo>`  
E `<Causa>`, `<Reacao>`,` <TratamentoRapido>`, `<DependenciaQuimica>`, `<NaoCompartilharContatos>`, `<VoceFumante>`, `<QuantoTempo>`  
E `<QuantosCigarros>`, `<VoceAlcoolatra>`, `<FrequenciaBebe>`, `<Entorpecentes>`, `<QualEntorpecente>`, `<EntorpecentesFrequencia>` (RN01)  
E clicar no botão **Salvar**  
Entao o sistema deverá cadastrar itens correspondentes

Exemplos:

|TipoSanguineo|FatorRH|TransfusaoSangue|NecessidadesEspeciais|Preexistentes|PreexistentesTipo|PreexistentesNomeDoenca|DoencasGravesFamilia|NomeDoenca|QualParentesco|HouveCura|Alergico|AlergicoTipo|Causa|Reacao|TratamentoRapido|DependenciaQuimica|NaoCompartilharContatos|VoceFumante|QuantoTempo|QuantosCigarros|VoceAlcoolatra|FrequenciaBebe|Entorpecentes|QualEntorpecente|EntorpecentesFrequencia|  
|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|  
|O    |     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |

<!BDD.FIM>

## 4. Casos de Uso Relacionados
* UCS - Main 

## 5. Interface
| Protótipo                                                    | Path                       | Desenv | Homolog | Prod | Arquivo |
| ------                                                       | ------                     | -----  | -----   |----- |---------|
| Pesquisar Paciente                                           | /emergencia/               |        |         |      |         | 

## 6. Mensagens ([ApplicationMessages](src/main/resources/ApplicationMessages.properties))
* AVISO.001

## 7. Projeto
> Link para os artefatos de projeto relacionados ao caso de uso

