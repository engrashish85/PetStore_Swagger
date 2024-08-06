package custom.api;

import com.google.gson.Gson;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.Assert;
import payload.pojo.request.addPet.AddPetRequest;
import payload.pojo.request.addPet.Category;
import payload.pojo.request.addPet.Tags;
import payload.pojo.response.addPet.response.addPet.AddPetResponse;
import util.api.RestAssuredFunctions;
import util.files.Globals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PetStore {

    public Response addPet(Map<String, String> dataMap) {
        String petName = dataMap.get("petName");
        String id = dataMap.get("id");
        String categoryId = dataMap.get("categoryId");
        String categoryName = dataMap.get("categoryName");
        String photoUrls = dataMap.get("photoUrls");
        String tagName = dataMap.get("tagName");
        int statusCode = Integer.parseInt(dataMap.get("statusCode"));
        AddPetRequest addPetRequest = returnAddPetRequest(petName, categoryId, categoryName, photoUrls, tagName);
        System.out.println(addPetRequest.toString());
        RestAssuredFunctions restAssuredFunctions = new RestAssuredFunctions();
        Response response = restAssuredFunctions.getResponse(Globals.BASE_URL + "pet", null, returnHeaderParameters(),
                null, null, addPetRequest, Method.POST);
        ResponseBody responseBody = response.getBody();
        int responseStatusCode = response.statusCode();
        Assert.assertEquals("Expected Status code - " + statusCode + "is not matching with the actual status code - " + responseStatusCode, statusCode, responseStatusCode);
        if (statusCode == 200) {
            AddPetResponse addPetResponse = responseBody.as(AddPetResponse.class);
            String responseId = String.valueOf(addPetResponse.getId());
            String responsePetName = String.valueOf(addPetResponse.getName());
            if (!(categoryId.equals("0"))) {
                Assert.assertEquals("Request id - " + id + " should match with response id - " + responseId,
                        id, responseId);
            }
            Assert.assertEquals("Request pet name - " + petName + " should match with response pet name - " +
                    responsePetName, petName, responsePetName);
        }
        return response;
    }

    public Map<String, String> returnHeaderParameters() {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");
        return headerMap;
    }

    public AddPetRequest returnAddPetRequest(String petName, String categoryId, String categoryName, String photoUrls,
                                             String tagName) {
        AddPetRequest addPetRequest = new AddPetRequest();
        addPetRequest.setId(Integer.parseInt(categoryId));
        addPetRequest.setName(petName);
        Category category = new Category();
        category.setId(0);
        category.setName(categoryName);
        addPetRequest.setCategory(category);
        addPetRequest.setPhotoUrls(new String[]{photoUrls});
        Tags tags = new Tags();
        tags.setId(0);
        tags.setName(tagName);
        addPetRequest.setTags(new Tags[]{tags});
        addPetRequest.setStatus("available");
        return addPetRequest;
    }

    public Response updatePet(Map<String, String> dataMap) {
        String petName = dataMap.get("petName");
        String id = dataMap.get("id");
        String categoryName = dataMap.get("categoryName");
        String photoUrls = dataMap.get("photoUrls");
        String tagName = dataMap.get("tagName");
        int statusCode = Integer.parseInt(dataMap.get("statusCode"));
        AddPetRequest addPetRequest = returnAddPetRequest(petName, id, categoryName, photoUrls, tagName);
        System.out.println(addPetRequest.toString());
        RestAssuredFunctions restAssuredFunctions = new RestAssuredFunctions();
        Response response = restAssuredFunctions.getResponse(Globals.BASE_URL + "pet/" + id, null, returnHeaderParameters(),
                null, null, addPetRequest, Method.PUT);
        ResponseBody responseBody = response.getBody();
        int responseStatusCode = response.statusCode();
        Assert.assertEquals("Expected Status code - " + statusCode + "is not matching with the actual status code - " + responseStatusCode, statusCode, responseStatusCode);
        if (statusCode == 200) {
            AddPetResponse addPetResponse = responseBody.as(AddPetResponse.class);
            String responseId = String.valueOf(addPetResponse.getId());
            String responsePetName = String.valueOf(addPetResponse.getName());
            if (!(id.equals("0"))) {
                Assert.assertEquals("Request id - " + id + " should match with response id - " + responseId,
                        id, responseId);
            }
            Assert.assertEquals("Request pet name - " + petName + " should match with response pet name - " +
                    responsePetName, petName, responsePetName);
        }
        return response;
    }

    public Response getPet(Map<String, String> dataMap) {
        String status = dataMap.get("status");
        Response response = null;
        RestAssuredFunctions restAssuredFunctions = new RestAssuredFunctions();
        boolean findById = false;
        String expectedStatusCode = "200";
        if (status!=null) {
            Map<String, String> queryParams = new HashMap<>();
            queryParams.put("status", status);
            response = restAssuredFunctions.getResponse(Globals.BASE_URL + "pet/findByStatus", null, returnHeaderParameters(),
                    queryParams, null, null, Method.GET);
        } else {
            findById = true;
            String id = dataMap.get("id");
            expectedStatusCode = dataMap.get("statusCode");
            response = restAssuredFunctions.getResponse(Globals.BASE_URL + "pet/" + id, null, returnHeaderParameters(),
                    null, null, null, Method.GET);
        }
        ResponseBody responseBody = response.getBody();
        String responseText = response.getBody().asString();
        System.out.println("Response is - "+ responseText);
        int responseStatusCode = response.statusCode();
        Assert.assertEquals("Status code is not -"+expectedStatusCode, Integer.parseInt(expectedStatusCode), responseStatusCode);
        if (expectedStatusCode.equals("200")) {
            if (!findById) {
                List<Map<String, Object>> list = new Gson().fromJson(responseText, List.class);
                for (Map<String, Object> map:list) {
                    System.out.println("individual names of pets are - "+map.get("name"));
                }
            } else {
                Map<Object, Object> map = new Gson().fromJson(responseText, Map.class);
                System.out.println("Map having id - "+ dataMap.get("id") + "is - "+map.get("name"));
            }
        } else {
            Map<String, String> json = new Gson().fromJson(responseText, Map.class);
            Assert.assertEquals("Pet not found", "Pet not found", json.get("message"));
        }



        return response;
    }
}
