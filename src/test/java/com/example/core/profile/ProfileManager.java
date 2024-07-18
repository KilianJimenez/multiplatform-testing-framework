package com.example.core.profile;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class ProfileManager {

    /**
     * Stores the map of the parsed json text.
     */

    /**
     * Where the profiles file is stored
     */
    private static JSONObject jsonObject = null;
    private static JSONArray profile_property = null;

    private static final String PROFILES_FILE = "src/test/resources/properties/profiles.json";

    public static String getProfileProperties(String profile, String property) {
        JSONParser parser = new JSONParser();
        try {
            if (jsonObject == null) {
                jsonObject = (JSONObject) parser.parse(new FileReader(PROFILES_FILE));
                profile_property = (JSONArray) parser.parse(jsonObject.get(profile).toString());
            }
            return ((JSONObject) profile_property.get(0)).get(property).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return String.valueOf(e);
        }
    }
}
