public enum ServiceType {
    RESTAURANT, HOSPITAL, ATM, PHARMACY, SUPERMARKET, HOTEL, PARK,
    BANK, SCHOOL, UNIVERSITY, GYM, LIBRARY, MUSEUM,
    ZOO, SALON;
    
    public static boolean isValid(String test) {
        for (ServiceType service : ServiceType.values()) {
            if (service.name().equals(test)) {
                return true;
            }
        }
        return false;
    }
}