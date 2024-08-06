package stepDefinitions;

import custom.api.PetStore;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class PetStepDefinitions {

    @Given("the user is able validate following data in the add pet request")
    public void validateAddPetRequest(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            Response response = new PetStore().addPet(row);
            System.out.println(response.getBody().asString());
        }

    }

    @Given("the user is able validate following data in the update pet request")
    public void theUserIsAbleValidateFollowingDataInTheUpdatePetRequest(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            Response response = new PetStore().updatePet(row);
            System.out.println(response.getBody().asString());
        }
    }

    @Given("the user is able validate following data in the get pet request")
    public void theUserIsAbleValidateFollowingDataInTheGetPetRequest(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            Response response = new PetStore().getPet(row);
            System.out.println(response.getBody().asString());
        }
    }
}
