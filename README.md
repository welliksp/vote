# Vote API

O [VOTE] disponibiliza uma API RESTful que permite cadastro e votação de pautas.

Recursos disponíveis para acesso via API:

* [**Docker**](#docker-docker)
* [**Ruling**](#ruling-ruling)
* [**Vote**](#reference/recursos/contatos)

## Métodos

Requisições para a API devem seguir os padrões:
| Método | Descrição |
|---|---|
| `GET` | Retorna informações de um ou mais registros. |
| `POST` | Utilizado para criar um novo registro. |
| `PUT` | Atualiza dados de um registro ou altera sua situação. |
| `DELETE` | Remove um registro do sistema. |

## Respostas

| Código | Descrição                                                          |
|--------|--------------------------------------------------------------------|
| `200`  | Requisição executada com sucesso (success).                        |
| `400`  | Erros de validação ou os campos informados não existem no sistema. |
| `401`  | Dados de acesso inválidos.                                         |
| `404`  | Registro pesquisado não encontrado (Not found).                    |
| `500`  | Internal Server Error.                                             |

# Docker [/docker]

Criação do container do banco de dados:

    docker run --detach --name vote -p 3306:3306  -e MARIADB_USER=vote -e MARIADB_PASSWORD=vote -e  MARIADB_ROOT_PASSWORD=123456 -e MYSQL_DATABASE=vote mariadb:10.6

# Ruling [/ruling]

Cadastro de pautas e consultas

### Listar (List) [GET /{id} ]

+ Parameters
    + id long - Busca o id fornecido.


+ Request (curl)
    +
  curl --location 'http://localhost:8080/api/ruling/v1/1'

    + Response 200 (application/json)

              {
                  "name": "Distribuicao",
                   "validated": 168866580093000,
                    "link": {
                   "rel": "self",
                    "href": "http://localhost:8080/api/ruling/v1/1"
                    }
                   }

        + Response 404 (application/json)

                  {
                     "timestamp": "2023-07-04T14:21:58.020+00:00",
                     "message": "Not Found Ruling By Id: 5",
                        "details": "uri=/api/ruling/v1/5"
                     }

### Listar Todos (List) [GET ]

+ Parameters
    + Não possui parametros

+ Request (curl)
    +
  curl --location 'http://localhost:8080/api/ruling/v1'

    + Response 200 (application/json)

              [
                {
                "name": "Distribuicao",
                "validated": 1688665893000000,
                "link": {
                "rel": "self",
                "href": "http://localhost:8080/api/ruling/v1"
                        }
                    },
                {
                "name": "Distribuicao",
                "validated": 1688560006370000,
                "link": {
                "rel": "self",
                "href": "http://localhost:8080/api/ruling/v1"
                            }
                        }
                ]

### Novo (Create) [POST]

+ Attributes (object)

    + name: nome da pauta (string, required) - limite 50 caracteres
    + validated (long, optional) - limite de duração da pauta em minutos, caso não seja informado a duração da pauta
      será atribuído 1 munitos de duração.


+ Request (curl)

            {
            curl --location 'http://localhost:8080/api/ruling/v1' \
            --header 'Content-Type: application/json' \
            --data '{
             "name": "Distribuicao",
            "validated":10
                }'
  }

+ Response 200 (application/json)

              {
                  "name": "Distribuicao",
                  "validated": 1688650004735490,
                  "link": {
                  "rel": "self",
                  "href": "http://localhost:8080/api/ruling/v1"
                }
              }

### Editar (Edit) [PUT]

+ Attributes (object)

    + id (long, required) - id da pauta.
    + name: nome da pauta (string, required) - limite 50 caracteres
    + id (long, required) - limite de duração da pauta em minutos, caso não seja informado a duração da pauta será
      atribuído 1 munitos de duração.


+ Request (curl)

            {
            curl --location 'http://localhost:8080/api/ruling/v1' \
            --header 'Content-Type: application/json' \
            --data '{
             "name": "Distribuicao",
            "validated":10
                }'
  }

    + Response 200 (application/json)

                  {
                       "id": 1,
                  "name": "Divisao",
                  "validated": 1688656000390669,
                  "link": {
                  "rel": "self",
                  "href": "http://localhost:8080/api/ruling/v1"
                  }
                  }
    +
+ Response 404 (application/json)
    +       {
              "timestamp": "2023-07-04T14:58:53.487+00:00",
              "message": "Not Found Ruling by ID: 10",
              "details": "uri=/api/ruling/v1"
              }

### Apagar (Delete) [DELETE]

+ Attributes (object)

    + id (long, required) - id da pauta.


+ Request (curl)

            {
            curl --location --request DELETE 'http://localhost:8080/api/ruling/v1/4'
                }'
  }

    + Response 200 (application/json)

                  {
                       "id": 1,
                  "name": "Divisao",
                  "validated": 1688656000390669,
                  "link": {
                  "rel": "self",
                  "href": "http://localhost:8080/api/ruling/v1"
                  }
                  }
    +
+ Response 204 - No Content

+ Response 404 (application/json)
    +       {
              "timestamp": "2023-07-04T14:58:53.487+00:00",
              "message": "Not Found Ruling by ID: 10",
              "details": "uri=/api/ruling/v1"
              }

# Vote [/vote]

### Novo (Create) [POST]

+ Attributes (object)

    + rulingId: id da pauta (long, required)
    + rulingName: nome da pauta
    + name: nome do usuário
    + result: (string, required) SIM/NAO - a favor ou não da pauta

        + Request (curl)

                    {
                    curl --location 'localhost:8080/api/vote/v1' \
                        --header 'Content-Type: application/json' \
                        --data '{
                            "rulingId": 1,
                            "name": "welliksp",
                            "result":"SIM",
                            "rulingName":"Distribuicao"
                            }
                          }
          }

            + Response 200 (application/json)

                          {
                              "id": 4,
                            "rulingId": 1,
                            "rulingName": "Divisao",
                            "result": "SIM",
                                "link": {
                                "rel": "self",
                                "href": "http://localhost:8080/api/vote/v1"
                                }
                          }
+ Response 404 (application/json)
    +       {
              "timestamp": "2023-07-04T14:58:53.487+00:00",
              "message": "Not Found Ruling by ID: 1",
              "details": "uri=/api/ruling/v1"
              }

      + Response 400 (application/json)
    
                  {
                      "timestamp": "2023-07-04T15:15:44.905+00:00",
                      "message": "Voting Time Is Up",
                          "details": "uri=/api/vote/v1"
                      }