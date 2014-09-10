package com.leonardofrangelli.floriparoutes.data.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.leonardofrangelli.floriparoutes.data.common.GenericHttpClient;
import com.leonardofrangelli.floriparoutes.data.entities.Departure;
import com.leonardofrangelli.floriparoutes.data.entities.Route;
import com.leonardofrangelli.floriparoutes.data.entities.Stop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by frangelli on 9/8/14.
 */
public class RoutesService extends AbstractService {


    private static RoutesService instance;
    private static final String FIND_ROUTES_BY_STOP_NAME_URL = "https://dashboard.appglu.com/v1/queries/findRoutesByStopName/run";
    private static final String FIND_DEPARTURES_BY_ROUTE_ID_URL = "https://dashboard.appglu.com/v1/queries/findDeparturesByRouteId/run";
    private static final String FIND_STOPS_BY_ROUTE_ID_URL = "https://dashboard.appglu.com/v1/queries/findStopsByRouteId/run";;

    private GenericHttpClient client;
    private Map<String, String> headers;

    public static RoutesService getInstance() {
        if (instance == null) {
           instance = new RoutesService();
        }

        return instance;
    }

    public List<Route> findRoutesByStopName(String stopName) {
        initializeClient();
        initializeHeaders();

        String responseJSON = doPost(FIND_ROUTES_BY_STOP_NAME_URL,"{\"params\": {\"stopName\":\"%" + stopName + "%\"}}");
        String resultJSON = extractRowsFromJSON(responseJSON);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {

            return mapper.readValue(resultJSON, TypeFactory.defaultInstance().constructCollectionType(List.class,Route.class));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<Route>();
    }

    public List<Departure> findDeparturesByRouteId(Integer routeId) {
        initializeClient();
        initializeHeaders();

        String responseJSON = doPost(FIND_DEPARTURES_BY_ROUTE_ID_URL,"{\"params\": {\"routeId\" : " + routeId + " }}");
        String resultJSON = extractRowsFromJSON(responseJSON);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            return mapper.readValue(resultJSON, TypeFactory.defaultInstance().constructCollectionType(List.class,Departure.class));

        }  catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<Departure>();
    }

    public List<Stop> findStopsByRouteId(Integer routeId) {
        initializeClient();
        initializeHeaders();

        String responseJSON = doPost(FIND_STOPS_BY_ROUTE_ID_URL,"{\"params\": {\"routeId\" : " + routeId + " }}");
        String resultJSON = extractRowsFromJSON(responseJSON);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            return mapper.readValue(resultJSON, TypeFactory.defaultInstance().constructCollectionType(List.class,Stop.class));

        }  catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<Stop>();
    }

}
