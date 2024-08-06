Feature: Add Book

  @addPet
  Scenario: User should be able to add pet successfully
    Given the user is able validate following data in the add pet request
      | petName | id    |categoryId  | categoryName    | photoUrls | tagName | statusCode  |
      | Dog     | 1234  | 1200       | German Shepherd | string    | S       | 200         |
      | Cat     | 0     | 20011      | German Shepherd | string    | S       | 200         |


  @updatePet
  Scenario: User should be able to update pet successfully
    Given the user is able validate following data in the update pet request
      | petName | id    |categoryId  | categoryName    | photoUrls | tagName | statusCode  |
      | Dog     | 1234  | 1200       | German Shepherd | string    | S       | 200         |
      | Cat     | 0     | 20011      | German Shepherd | string    | S       | 200         |

  @getPet
  Scenario: User should be able to update pet successfully
    Given the user is able validate following data in the get pet request
      | status   |
      | available |
      | pending   |
      | sold      |
    Given the user is able validate following data in the get pet request
      | id                  |  statusCode |
      | 3932502729945105400 |  200        |
      | 123400000           |  404        |