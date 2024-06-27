import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import api.MockServer;
import utils.LogUtils;

public class BoredApiSetup {

    @BeforeMethod
    public void serverSetup(){
        LogUtils.info("starting and configuring mock server");
        MockServer.getInstance().startServer();
    }

    @AfterTest
    public void StopServer() {
        LogUtils.info("stop mock server");
        MockServer.getInstance().stopServer();
    }
}
