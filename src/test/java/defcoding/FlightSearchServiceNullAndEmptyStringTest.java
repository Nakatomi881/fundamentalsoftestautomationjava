package defcoding;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static defcoding.FlightSearchService.flightSearch;

public class FlightSearchServiceNullAndEmptyStringTest {

    private static final FlightSearchService searchService = flightSearch();
    private static final String validFromDest = "Boston";
    private static final String validToDest = "Washington";
    private static final String date = "15/10/2019";
    private static final int validPassengerNum = 2;

    @DataProvider
    public static Object[][] invalidStringProvider() {
        return new Object[][]{
                {null},
                {""},
                {" "}
        };
    }

    @Test(dataProvider = "invalidStringProvider", expectedExceptions = IllegalArgumentException.class)
    void invalidDestinationFirstArgNotAllowed(String from) {

        searchService.search(from, validToDest, date, validPassengerNum);
    }

    @Test(dataProvider = "invalidStringProvider", expectedExceptions = IllegalArgumentException.class)
    void invalidDestinationSecondArgNotAllowed(String to) {
        searchService.search(validFromDest, to, date, validPassengerNum);
    }

    @Test(dataProvider = "invalidStringProvider", expectedExceptions = IllegalArgumentException.class)
    void invalidDateNotAllowed(String date) {

        searchService.search(validFromDest, validToDest, date, validPassengerNum);


        searchService.search(validFromDest, validToDest, date, validPassengerNum);


        searchService.search(validFromDest, validToDest, date, validPassengerNum);

    }


}
