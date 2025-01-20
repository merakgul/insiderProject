package pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PetApiPage {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    private RequestSpecification requestSpec;

    public PetApiPage() {
        RestAssured.baseURI = BASE_URL;
        requestSpec = RestAssured.given().contentType("application/json");
    }

    // Create a pet
    public Response createPet(String petJson) {
        return requestSpec.body(petJson).post("/pet");
    }

    // Get pet by ID
    public Response getPetById(int petId) {
        return requestSpec.get("/pet/" + petId);
    }

    // Update pet details
    public Response updatePet(String petJson) {
        return requestSpec.body(petJson).put("/pet");
    }

    // Delete pet by ID
    public Response deletePet(int petId) {
        return requestSpec.delete("/pet/" + petId);
    }
}
