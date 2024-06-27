
import org.testng.annotations.Test;

import static api.BoredApi.getMockActivities;
import static utils.Helper.sortActivityList;

public class BoredApiTest extends BoredApiSetup{

    @Test(testName = "create mock server and sort response")
    public void BrandTest() {
        sortActivityList(getMockActivities());
    }
}
