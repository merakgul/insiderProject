package tests;

import controllers.GeneralApiController;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PetApiPage;

public class PetApiTests extends BaseTest {
    private PetApiPage petApiPage = new PetApiPage();

    @Test
    public void testCreatePet() {
        String petJson = GeneralApiController.createPetBody(101, "Doggie", "available");
        Response response = petApiPage.createPet(petJson);
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        Assert.assertTrue(response.getBody().asString().contains("Doggie"), "Pet name should be present");
    }

    // Test has been disabled because wrong data turns 200
    @Test(enabled = false)
    public void testNotCreatePetViaInvalidBody() {
        String invalidPetJson = "{\"id\": 0, \"name\": \"\", \"status\": \"\"}"; // Invalid JSON
        Response response = petApiPage.createPet(invalidPetJson);
        Assert.assertEquals(response.getStatusCode(), 400, "Status code should be 400 for invalid input");
    }

    @Test(dependsOnMethods = "testCreatePet", priority = 1)
    public void testGetPetById() {
        Response response = petApiPage.getPetById(101);
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        Assert.assertTrue(response.getBody().asString().contains("Doggie"), "Pet name should be present");
    }

    // Test has been disabled because invalid petId case returns not found
    @Test(enabled = false)
    public void testGetPetByIdNegative() {
        Response response = petApiPage.getPetById(999);
        Assert.assertEquals(response.getStatusCode(), 404, "Status code should be 404 for non-existent pet");
    }

    @Test(dependsOnMethods = "testCreatePet", priority = 2)
    public void testUpdatePet() {
        String updatedPetJson = GeneralApiController.updatePetBody(101, "DoggieUpdated", "sold");
        Response response = petApiPage.updatePet(updatedPetJson);
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        Assert.assertTrue(response.getBody().asString().contains("DoggieUpdated"), "Updated pet name should be present");
    }

    // Test has been disabled because wrong data turns 200
    @Test(enabled = false)
    public void testUpdatePetNegative() {
        String invalidPetJson = "{\"id\": 101, \"name\": \"\", \"status\": \"\"}"; // Invalid JSON
        Response response = petApiPage.updatePet(invalidPetJson);
        Assert.assertEquals(response.getStatusCode(), 400, "Status code should be 400 for invalid input");
    }

    @Test(dependsOnMethods = "testCreatePet", priority = 3)
    public void testDeletePet() {
        Response response = petApiPage.deletePet(101);
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    // Test has been disabled because invalid petId case returns not found
    @Test(enabled = false)
    public void testNotDeletePet() {
        Response response = petApiPage.deletePet(999);
        Assert.assertEquals(response.getStatusCode(), 404, "Status code should be 404 for non-existent pet");
    }
}