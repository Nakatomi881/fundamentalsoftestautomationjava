package defcoding;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class FlightSearchServiceSearchResultTest {

    private final FlightSearchService searchService = getTestFlightSearchService();

    private static final String validFromDest = "Boston";
    private static final String validToDest = "New York";
    private static final String date = "15-10-2019";
    private static final int validPassengerNum = 2;

    @Test
    void searchFindsValidFlights(){
        List<Flight> flights = searchService.search(validFromDest, validToDest, date, validPassengerNum);
        assertEquals(flights.size(), 1);
    }

    @Test
    void searchFiltersByDestinationsCorrectly(){

        List<Flight> flights = searchService.search("The Shire", validToDest, date, validPassengerNum);
        assertEquals(flights.size(), 0);


        List<Flight> flights2 = searchService.search(validFromDest, "Mordor", date, validPassengerNum);
        assertEquals(flights2.size(), 0);
    }


    @Test
    void searchFiltersByDateCorrectly(){

        List<Flight> flights = searchService.search(validFromDest, validToDest, "15-10-2025", validPassengerNum);
        assertEquals(flights.size(), 0);

    }

    @Test
    void searchFiltersByPassengerNumCorrectly(){
        List<Flight> flights = searchService.search(validFromDest, validToDest, "16-10-2019", validPassengerNum);
        assertEquals(flights.size(), 1);

        List<Flight> flights2 = searchService.search(validFromDest, validToDest, "16-10-2019", 7);
        assertEquals(flights2.size(), 0);
    }


    private FlightSearchService getTestFlightSearchService() {
        return new FlightSearchService(new TestFlightStore());
    }

    public class TestFlightStore implements FlightStore {
        @Override
        public List<Flight> getFlights() {
            return List.of(
                    // date format DD-MM-YYYY
                    new Flight("Boston","New York","15-10-2019", 10),
                    new Flight("Boston","New York","16-10-2019", 6),
                    new Flight("Boston","New York","17-10-2019", 1),


                    new Flight("New York","Boston","18-10-2019", 2),
                    new Flight("New York","Boston","19-10-2019", 2)
            );
        }
    }


}
