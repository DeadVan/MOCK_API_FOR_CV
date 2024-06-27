package api;

import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import com.google.gson.Gson;
import dto.ActivityDto;
import utils.DataReader;
import utils.LogUtils;

import java.util.List;
import static api.BoredApi.getBoredActivities;

public class MockServer {
    private static MockServer instance;
    private final WireMockServer wireMockServer;

    private MockServer() {
        wireMockServer = new WireMockServer(DataReader.readConfig("port"));
    }

    public static MockServer getInstance() {
        if (instance == null) {
            instance = new MockServer();
        }
        return instance;
    }

    public void startServer() {
        LogUtils.info("starting server");
        List<ActivityDto> activities = getBoredActivities();
        String activitiesJson = new Gson().toJson(activities);
        wireMockServer.start();

        wireMockServer.stubFor(get(urlEqualTo(DataReader.readEndpoint("activity_endpoint")))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(activitiesJson)));
    }

    public void stopServer() {
        LogUtils.info("stopping server");
        if (wireMockServer != null && wireMockServer.isRunning()) {
            wireMockServer.stop();
        }
    }
}
