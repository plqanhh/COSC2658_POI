public enum ServiceType {
    RESTAURANT, HOSPITAL, ATM, GAS_STATION, COFFEE_SHOP,
    PHARMACY, SUPERMARKET, HOTEL, MOVIE_THEATER, PARK,
    BANK, SCHOOL, UNIVERSITY, GYM, LIBRARY, MUSEUM,
    NIGHT_CLUB, ZOO, SALON, BOOK_STORE;
    
    public static boolean isValid(String test) {
        for (ServiceType service : ServiceType.values()) {
            if (service.name().equals(test)) {
                return true;
            }
        }
        return false;
    }
}