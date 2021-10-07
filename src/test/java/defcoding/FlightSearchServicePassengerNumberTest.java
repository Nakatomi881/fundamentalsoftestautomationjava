package defcoding;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static defcoding.FlightSearchService.flightSearch;

public class FlightSearchServicePassengerNumberTest {

    private static final FlightSearchService searchService = flightSearch();
    private static final String validFromDest = "Boston";
    private static final String validToDest = "Washington";
    private static final String date = "15-10-2019";

    @DataProvider
    public static Object[][] validPassengerNumProvider() {
        return new Object[][]{
                {1}, {7},
        };
    }

    // no exception means passed test
    @Test(dataProvider = "validPassengerNumProvider")
    void onePassengerIsAllowed(int passengerNum) {
        searchService.search(validFromDest, validToDest, date, passengerNum);
    }

    @DataProvider
    public static Object[][] invalidPassengerNumProvider() {
        return new Object[][]{
                {0}, {8},
        };
    }

    @Test(dataProvider = "invalidPassengerNumProvider", expectedExceptions = IllegalArgumentException.class)
    void zeroOrEightPassengersNotAllowed(int passengerNum) {

        searchService.search(validFromDest, validToDest, date, passengerNum);

    }



//    @Test
//    void reallyBigNumber() {
//        searchService.search(validFromDest, validToDest, date, 77777777777777777777);
//    }


}
