package com.leonardofrangelli.floriparoutes.data.common;

/**
 * Created by frangelli on 9/9/14.
 */
public class GeocodingHttpClient extends GenericHttpClient {

    public static String getStreetName(String latLong) {
        String json = getLocationInfo(latLong);

        String streetName = null;

        try {
            streetName = json.substring(0,json.indexOf(",")).trim();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return streetName;
    }

    public static String getLocationInfo(String latLong) {
        String json = GeocodingHttpClient.getInstance().sendRequestByGet("http://maps.google.com/maps/api/geocode/json?sensor=false&latlng=" + latLong);
        return getAddressFromJson(json);
    }

    private static String getAddressFromJson(String json) {
        try {
            String formattedJSON = json.substring(json.indexOf("\"formatted_address\""), json.indexOf("\"geometry\""));
            formattedJSON = formattedJSON.substring(formattedJSON.indexOf(":") +1, formattedJSON.lastIndexOf(","));

            return formattedJSON.replace("\"", "");
        } catch (Exception e) {
            return null;
        }
    }
}
