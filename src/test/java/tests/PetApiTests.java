package tests;

import pages.PetApiPage;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PetApiTests {

    private PetApiPage petApiPage = new PetApiPage();

    // Positive Test Case: Create Pet
    @Test
    public void testCreatePetPositive() {
        String petJson = "{\"id\": 101, \"name\": \"Doggie\", \"status\": \"available\"}";
        Response response = petApiPage.createPet(petJson);

        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        Assert.assertTrue(response.getBody().asString().contains("Doggie"), "Pet name should be present in the response");
    }

    // Negative Test Case: Create Pet (Invalid data)
    @Test
    public void testCreatePetNegative() {
        String petJson = "{\"id\": 0, \"name\": \"\", \"status\": \"\"}"; // Invalid pet data
        Response response = petApiPage.createPet(petJson);

        Assert.assertEquals(response.getStatusCode(), 400, "Status code should be 400 for invalid input");
    }

    // Positive Test Case: Get Pet by ID
    @Test
    public void testGetPetByIdPositive() {
        Response response = petApiPage.getPetById(101);

        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        Assert.assertTrue(response.getBody().asString().contains("Doggie"), "Pet name should be present");
    }

    // Negative Test Case: Get Pet by Non-Existent ID
    @Test
    public void testGetPetByIdNegative() {
        Response response = petApiPage.getPetById(99);

        Assert.assertEquals(response.getStatusCode(), 404, "Status code should be 404 for non-existent pet");
    }

    // Positive Test Case: Update Pet
    @Test
    public void testUpdatePetPositive() {
        String updatedPetJson = "{\"id\": 101, \"name\": \"DoggieUpdated\", \"status\": \"available\"}";
        Response response = petApiPage.updatePet(updatedPetJson);

        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200 after updating pet");
        Assert.assertTrue(response.getBody().asString().contains("DoggieUpdated"), "Updated pet name should be present");
    }

    // Negative Test Case: Update Pet (Invalid data)
    @Test
    public void testUpdatePetNegative() {
        String invalidPetJson = "{\"id\": 101, \"name\": \"\", \"status\": \"\"}"; // Invalid data
        Response response = petApiPage.updatePet(invalidPetJson);

        Assert.assertEquals(response.getStatusCode(), 400, "Status code should be 400 for invalid pet update");
    }

    // Positive Test Case: Delete Pet
    @Test
    public void testDeletePetPositive() {
        Response response = petApiPage.deletePet(101);

        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200 after deleting pet");
    }

    // Negative Test Case: Delete Pet (Non-Existent Pet)
    @Test
    public void testDeletePetNegative() {
        Response response = petApiPage.deletePet(999);

        Assert.assertEquals(response.getStatusCode(), 404, "Status code should be 404 for non-existent pet");
    }
}
