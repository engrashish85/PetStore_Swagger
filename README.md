# PetStore_Swagger
This will validate pet store requests and responses taking api documentation from swagger 

This project is maven and cucumber based framework using rest assured native dependencies and methods covering following following functionalities- 

1) Find Pet
2) Update Pet
3) add a new pet
4) Update existing pet
5) find pets by status

The project can be run using cucumber by simply giving tag names. The tests are provided in feature file with corresponding 
tag names.

Project Structure - Project is based on maven and cucumber where -

feature file containing all the test cases is kept in - src/test/resources/features/Pet.feature
step definitions file has been created in - src/test/java/stepDefinitions
runner file has been created in - src/test/java/runners
generic utlitility functions have been created in - src/main/java. Here is a detailed functionality on the same - 

1) util/api/Rest Assured functions - These are generic functions using rest assured specifications builder object. This builder 
takes into account query parameters, proxy, body, credentials and heders and does API operation as per the test case
2) custom/api/PetStore - These are custom defined functions based on features which creates POJO requests, headers and calls rest
assured generic functions to submit requests
3) payload/pojo - These contain request and response file templates which will be taken as input to serialize in the code
4) util/files/Globals - This is an enum where Global variables are stored along with their values
5) util/files/PropertyFileReader - This contains function to read values from properties file (in this scenario - Base URL)