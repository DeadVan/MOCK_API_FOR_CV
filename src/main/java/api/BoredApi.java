package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.ActivityDto;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.DataReader;
import utils.LogUtils;
import utils.StatusCode;

import java.util.ArrayList;
import java.util.List;

public class BoredApi {
    private static final String boredBase_url = DataReader.readEndpoint("base_url");
    private static final String mockBase_url = DataReader.readEndpoint("mock_base_url");
    private static final String activityEndpoint = DataReader.readEndpoint("activity_endpoint");

    public static List<ActivityDto> getBoredActivities() {
        List<ActivityDto> activities = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            LogUtils.info("Sending GET request for activity #" + (i + 1));
            Response response = RestAssured.given()
                    .get(boredBase_url + activityEndpoint);
            if (response.getStatusCode() == StatusCode.OK.getCode()) {
                ActivityDto activity = response.getBody().as(ActivityDto.class);
                activities.add(activity);
            } else {
                LogUtils.info(String.valueOf(response.getStatusCode()));
                LogUtils.error(response.getBody().asString());
            }
        }
        for (ActivityDto activityDto : activities){
            LogUtils.info("Bored Activity - " + activityDto);
        }
        return activities;
    }

    public static List<ActivityDto> getMockActivities(){
        LogUtils.info("starting to get 20 activity from mockserver");
        List<ActivityDto> activityList = new ArrayList<>();
        Response response = RestAssured.given()
                .get(mockBase_url + activityEndpoint);
        if (response.getStatusCode() == StatusCode.OK.getCode()) {
            String jsonString = response.getBody().asString();
            Gson gson = new GsonBuilder().create();
            ActivityDto[] activityArray = gson.fromJson(jsonString, ActivityDto[].class);
            activityList = List.of(activityArray);
        } else {
            LogUtils.info(String.valueOf(response.getStatusCode()));
            LogUtils.error(response.getBody().asString());
        }
        for (ActivityDto activityDto : activityList){
            LogUtils.info("Mocked Activity -- " + activityDto);
        }
        return activityList;
    }
}
