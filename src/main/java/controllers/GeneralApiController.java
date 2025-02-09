package controllers;

import org.json.JSONObject;

public class GeneralApiController {

    public static String createPetBody(int petId, String petName, String status) {
        JSONObject petJson = new JSONObject();
        petJson.put("id", petId);
        petJson.put("name", petName);
        JSONObject category = new JSONObject();
        category.put("id", 1);
        category.put("name", "Dogs");
        petJson.put("category", category);
        petJson.put("photoUrls", new String[]{"https://example.com/pet.jpg"});
        petJson.put("tags", new JSONObject[]{new JSONObject().put("id", 1).put("name", "tag1")});
        petJson.put("status", status);
        return petJson.toString();
    }

    public static String updatePetBody(int petId, String petName, String status) {
        JSONObject petJson = new JSONObject();
        petJson.put("id", petId);
        petJson.put("name", petName);
        petJson.put("status", status);
        return petJson.toString();
    }
}