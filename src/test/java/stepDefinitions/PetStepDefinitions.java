package stepDefinitions;

import util.api.RestAssuredFunctions;
import io.cucumber.java.en.Given;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import payload.pojo.addPet.AddPetRequest;
import payload.pojo.addPet.Category;
import payload.pojo.addPet.Tags;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PetStepDefinitions {

    private String baseUrl = "https://petstore.swagger.io/v2";

    @Given("the user is able to add Pet {string} successfully")
    public void theUserIsAbleToAddPetSuccessfully(String petName) {
        RestAssuredFunctions restAssuredFunctions = new RestAssuredFunctions();
        Response response = restAssuredFunctions.getResponse(baseUrl + "/pet", null, returnHeaderParameters(),
                null, null, returnAddPetRequest("dog"), Method.POST);
        ResponseBody responseBody = response.getBody();
//        if (response.statusCode() == 404) {
//            GetBooksResponse getBooksResponse = responseBody.as(GetBooksResponse.class);
//            String msg = getBooksResponse.getMsg();
//            System.out.println(msg);
//        }
        System.out.println(response.getBody().asString());
    }

    public Map<String, String> returnHeaderParameters() {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");
        return headerMap;
    }

    public AddPetRequest returnAddPetRequest(String pet) {
        AddPetRequest addPetRequest = new AddPetRequest();
        addPetRequest.setId(0);
        Category category = new Category();
        category.setId(0);
        category.setName("string");
        addPetRequest.setCategory(category);
        addPetRequest.setPhotoUrls(new String[]{"string"});
        Tags tags = new Tags();
        tags.setId(0);
        tags.setName("string");
        addPetRequest.setTags(new Tags[]{tags});
        addPetRequest.setStatus("available");
        return addPetRequest;
    }

}
