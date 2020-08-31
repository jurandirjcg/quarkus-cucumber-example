# UCS001 - Save Movie

## 1. Introduction  

This UCS is used to save the movie information

## 2. Requirements 

- F01 - Allow to the admin register a new movie
- NF02 - Avalilable on Weekdays (12/5)

## 3. Fluxo  
<!BDD.START>  

### Tag 
@prod
### Feature   

### Context:

**Given** that I logged as Admin
**And** that I'm seeing the Movie's Register Interface

### Scenario Outline: New movie  

**When** I put the information `'<name>'`, `'<releaseDate>'`, `<duration>`, `'<description>'`   
**And** click on *Save* button  
**Then** the data are saved  
**And** the `'<message>'` is presented

### Examples:

| name          | releaseDate   | duration  | description                                                                                                                                               | message       |
| ---           | ---           | ---       | ---                                                                                                                                                       | ---           |
| John Wick     | 2014-10-28    | 101       | An ex-hit-man comes out of retirement to track down the gangsters that killed his dog and took everything from him.                                       | SUCCESS.001   |
| The Matrix    | 1999-03-31    | 136       | A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.                     | SUCCESS.001   |
| Inception     | 2010-07-16    | 148       | A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.  | WARN.001      |

<!BDD.END>

## 4. Related Use Cases

- N/A

## 5. Mensagens ([ApplicationMessages](src/main/resources/ApplicationMessages.properties))
* SUCCESS.001
* WARN.001

## 6. Design

### 6.1. Services

| Name                  | HTTP  | Path          | 
| ------                | ---   | --------      | 
| saveMovie             | POST  | /v1/movies    |

### 6.2 Another docs
* [ERD](docs/db/erd/)
* [Class Diagram](docs/diagrams/class)

