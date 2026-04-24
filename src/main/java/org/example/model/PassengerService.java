package org.example.model;

import java.util.*;
import java.util.stream.Collectors;

public class PassengerService {

    public List<Passenger> filterPassengersByType(List<Passenger> people, PassengerType... passengerType)
    {
        // to filter passengers by type, I will use the stream api and the filter method to filter out the passengers that match the given types
        // I will stream the list of passengers and then use the filter method to check if the passenger type matches any of the given types,
        // I will then use a for loop within the lambda to check if the passenger type matches any of the given types, if it does then I will return true and if it doesn't then I will return false, this way I can filter out the passengers that match the given types.
        return people.stream().filter((passenger) -> {
            for (PassengerType type : passengerType) {
                if (passenger.getType() == type) {
                    return true;
                }
            }
            return false;
        }).toList();
    }

    public List<Passenger> filterPassengersByFareAsInt(List<Passenger> people, int fareTo)
    {
        // to filter passengers by fare, I will use the stream api and the filter method to filter out the passengers that match the given fare
        return people.stream().filter((passenger) -> passenger.getFarePrice() <= fareTo).toList();
    }

    public Passenger upgradeToFirstClass(Passenger passenger)
    {
        // to upgrade a passenger to first class, I will create a new passenger with the same properties as the given
        // passenger but with the type set to first class
        return new Passenger(
                passenger.getId(),
                passenger.getName(),
                PassengerType.FIRST_CLASS,
                passenger.getLuggageCount(),
                passenger.getFarePrice(),
                passenger.getDescription(),
                passenger.getSeatNumber()
        );
    }

    public Double computeTotalCost(Passenger passenger)
    {
        // to compute the total cost for a passenger, I will check if the passenger has any luggage,
        // if they do then I will add 5, this is inspired by the tests as it charges a 5 for every luggage
        // to the fare price for each piece of luggage, if they don't then I will just return the fare price
        if (passenger.getLuggageCount() != null && passenger.getLuggageCount() > 0) {
            return passenger.getFarePrice() + (passenger.getLuggageCount() * 5);
        }
        return passenger.getFarePrice();
    }

    public List<Passenger> filterByType(List<Passenger> passengers, PassengerType... passengerType)
    {
        // I will use a for loop to filter out the passengers that match the given types
        // I will create a new list to store the filtered passengers, then I will loop through the list of passengers and check if
        // he passenger type matches any of the given types, if it does
        // then I will add the passenger to the filtered passengers list, and at the end I will return the filtered passengers list.
        throw new UnsupportedOperationException ("Implement function that filters passengers by given types");
    }

    public List<Passenger> filterByFare(List<Passenger> passengers, int fareFrom)
    {
        // to filter passengers by fare, I will use the stream api and the filter method to filter out the passengers that match the given fare
        // and since the method return type is a list of passengers, I will convert the stream to a list using the toList method.
        return passengers.stream().filter((passenger) -> passenger.getFarePrice() >= fareFrom).toList();
    }

    public PassengerType findMostCommonPassengerType(List<Passenger> passengers)
    {
        // I will use the stream api and the collectors to find the most common type
        // I will use the groupingBy collector to group the passengers by type and then use
        // the counting collector to count the number of passengers in each group, then I will use the maxBy collector
        // to find the group with the maximum count, and then I will use the get method to get the most common type from the optional.
        // if there is a value present otherwise I return a null value.

        return passengers.stream().collect(Collectors.groupingBy(Passenger::getType, Collectors.counting()))
                .entrySet().stream().max(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getKey).orElse(null);
    }

    public int boardOrder(Passenger passenger) {
        // to get the board order for a passenger, I will check the type of the passenger and return the corresponding board order,
        // first class passengers board first, then business class passengers, and then economy passengers
        if (passenger.getType() == PassengerType.FIRST_CLASS) {
            return 1;
        } else if (passenger.getType() == PassengerType.BUSINESS_CLASS) {
            return 2;
        } else {
            return 3;
        }
    }

    public  List<Passenger> sortBySeatNumber(List<Passenger> passengers) {
        // to sort the passengers by seat number, I will use the stream api and the sorted method
        // the sorted method takes a comparator as an argument, the comparator is used to compare the passengers by seat number
        // the comparator will be given a method reference to the getSeatNumber method of the passenger class to compare the seat number of the passengers.
        // the sorted method will return a stream of passengers sorted by seat number, I will convert it to a list using the toList method.
        return passengers.stream().sorted(Comparator.comparing(Passenger::getSeatNumber)).toList();
    }

    public UUID findPassengerIdBySeatNumber(List<Passenger> passengers, String seatNumber) {
        // to find the passenger id by seat number, I will use the stream api and the filter method to filter out the passenger that matches the given seat number
        // then I will use the findFirst method to get the first passenger that matches the given seat number,
        // since there should be only one passenger with a given seat number, I will use the get method to get
        // the passenger from the optional and then return the id of the passenger.
        return passengers.stream().filter((passenger) -> passenger.getSeatNumber().equals(seatNumber)).findFirst().get().getId();
    }

    public UUID findPassengerIdWithLowestSeatNumber(List<Passenger> passengers) {
        // to find the passenger id with the lowest seat number, I will use the stream api and the min method to find the passenger with the lowest seat number
        // the min method takes a comparator as an argument, the comparator is used to compare the passengers by seat number, the comparator will be given a method reference to the getSeatNumber method of
        // the passenger class to compare the seat number of the passengers, the min method will return an optional of passenger, I will use the get method to get the passenger from the optional and then return the id of the passenger.
        return passengers.stream().min(Comparator.comparing(Passenger::getSeatNumber)).get().getId();
    }
}
