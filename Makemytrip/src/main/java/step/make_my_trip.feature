# My eclipse got crashed and I am not able to work for 2 days and on Monday also it took 3.5 hrs to become normal. Kindly please consider that!!!! 
# Travel Type 1. oneWayTrip 2. roundTrip

@Login
Feature: Login to Makemytrip

  Scenario: Makemytrip login
    Given Open chrome browser
    Then Enter travel type "<travel_type>"
    Then Enter Deperture city "<city1>" and destination City "<city2>"
    Then Enter Date "<date>" and return date "<return date>" for "<travel_type>" round trip
    Then Enter traveller detail adult "<adult>" and children "<children>" and infant "<infant>"
    Then Enter Search button
    Then Enter flight name "<flight>"
    Then Enter Sorting order "<order1>" for return flight "<order2>" in case of "<travel_type>" round trip and passing "<city1>" and "<city2>" for validation
    Then Enter flight row "<flight_row1>" and flight card "<flight_type1>" for return deperture flight row "<flight_row1>", return flight row "<flight_row2>", deperture flight card "<flight_type1>" and return flight card "<flight_type2>" in case of "<travel_type>" round trip
    Then Deselect the Donate option

    Examples: 
      | travel_type | city1   | city2 | date        | return date | adult    | children   |infant    |flight | order1  | order2   | flight_row1 | flight_type1 | flight_row2 | flight_type2  |
      | roundTrip   | Kolkata | Mumbai | Aug 10 2021 | Aug 12 2021 | adults-2 | children-1|infants-0 |Vistara| Arrival | Duration |           1 | Economy Standard |           2 | Economy Flexi|
