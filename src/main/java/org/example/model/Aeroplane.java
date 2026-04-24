package org.example.model;

import java.util.*;
import java.util.stream.Collectors;

public class Aeroplane {

    private final List<Passenger> passengers = new ArrayList<>();

    public String enter(Passenger passenger)
    {
        // to add a passenger to the list, I will use the add method from the collections framework
        // passengers.add(passenger)
        // and since the method return type is a string, I will return a welcome message that includes the passenger's description and name.
        passengers.add(passenger);
        return "Welcome " + passenger.getDescription() + " " + passenger.getName();
    }

    public List<Passenger> bulkEnter(Passenger... passenger)
    {
        // to add multiple passengers, I could loop through the array of passengers argument and add them to the passengers
        // list, this won't be the optimal way as the collections framework already provides a method to do that
        // and this is the addAll method.
        // I will convert the array of passengers to a list and then add all the passengers to the passengers list using the addAll method
        // then since the method return type is a list of passengers, I will return the list of passengers that I just added to the passengers list.
        List<Passenger> passengerList = List.of(passenger);
        passengers.addAll(passengerList);
        return passengerList;
    }

    public boolean exit(UUID passengerId)
    {
        // to remove a passenger from the list, I will use the removeIf method from the collections framework
        // it checks to see if the passenger id matche the ids in the passengers list.
        return passengers.removeIf((passenger) -> passenger.getId().equals(passengerId));
    }

    public int countPassengers()
    {
        // to get the count, the passengers type which is a List has a size method that returns the number of elements in the list
        // this is from the collections framework
        return passengers.size();
    }

    public int countPassengersByType(PassengerType passengerType)
    {
        // since I need to get the passengers by type
        // I will filter out the passengers by type and count them
        // with the help of the stream api
        return passengers.stream().filter((passenger) -> passenger.getType() == passengerType).toList().size();
    }

    public Map<PassengerType, Passenger> mapPassengersByType()
    {
        // to convert the list of passengers to a map of passengers by type, I will use the stream api and the collectors toMap method
        // the toMap method takes two arguments, the first is a function that takes a passenger
        // and returns the key for the map, in this case it will be the passenger type, and the second argument is a
        // function that takes a passenger and returns the value for the map, in this case it will be the passenger itself.
        // the toMap will be given a method reference to the getType method of the passenger class to get the key for the map,
        // and a lambda function that takes a passenger and returns the passenger itself as the value for the map.
        return passengers.stream().collect(Collectors.toMap(Passenger::getType, (passenger) -> passenger));
    }

    public List<Passenger> orderPassengersByFare()
    {
        // to order the passengers by fare, I will use the stream api and the sorted method
        // the sorted method takes a comparator as an argument, the comparator is used to compare the passengers by fare price
        // the comparator will be given a method reference to the getFarePrice method of the passenger class to compare the fare price of the passengers.
        // the sorted method will return a stream of passengers sorted by fare price, I will convert it to a list using the toList method.
        return passengers.stream().sorted(Comparator.comparingDouble(Passenger::getFarePrice)).toList();
    }

    public double totalFare()
    {
        // to get the total fare, I will use the stream api and the mapToDouble method to convert the
        // stream of passengers to a stream of double values representing the fare price of each passenger
        // then I will use the sum method to get the total fare price of all the passengers.
        return passengers.stream().mapToDouble(Passenger::getFarePrice).sum();
    }
}
