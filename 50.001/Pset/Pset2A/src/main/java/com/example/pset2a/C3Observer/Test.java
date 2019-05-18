package com.example.pset2a.C3Observer;

public class Test {
    public static void main(String[] args) {
        System.out.println("1");
        AirPollutionAlert singaporealert = new AirPollutionAlert();
        Subscriber me = new Subscriber("me",singaporealert);
        Subscriber me2 = new Subscriber("me2",singaporealert);

        singaporealert.setAirPollutionIndex(200);
        singaporealert.setAirPollutionIndex(50);
        singaporealert.setAirPollutionIndex(120);

        singaporealert.unregister(me);
        singaporealert.setAirPollutionIndex(300);
        System.out.println("2");
    }
}
