package testy;

public class CommunicationBox {
    public static String orderForServer = "hey"; // wydzielone zadanie dla servera, // jeżeli null jest on bezczynny
    public static String whichPlayerServer = "to jest polecenie";
    
    public static String orderForRozgrywka = null; // wydzielone zadanie dla rozgrywki,
                                                                                    // jeżeli null jest ona bezczynna
    public static String whichPlayerRogrywka = null;
}
