package com.example.ListyCity;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This is a class that tests the CityList class.
 */
public class CityListTest {

    private CityList mockCityList() {
        CityList cityList = new CityList();
        cityList.add(mockCity());
        return cityList;
    }

    private City mockCity() {
        return new City("Edmonton", "Alberta");
    }

    @Test
    public void testAdd() {
        CityList cityList = mockCityList();
        assertEquals(1, cityList.getCities().size());
        City city = new City("Regina", "Saskatchewan");
        cityList.add(city);
        assertEquals(2, cityList.getCities().size());
        assertTrue(cityList.getCities().contains(city));
    }

    @Test
    public void testAddException() {
        CityList cityList = mockCityList();
        City city = new City("Yellowknife", "Northwest Territories");
        cityList.add(city);
        assertThrows(IllegalArgumentException.class, () -> {
            cityList.add(city);
        });
    }

    @Test
    public void testGetCities() {
        CityList cityList = mockCityList();
        assertEquals(0, mockCity().compareTo(cityList.getCities().get(0)));
        City city = new City("Charlottetown", "Prince Edward Island");
        cityList.add(city);
        assertEquals(0, city.compareTo(cityList.getCities().get(0)));
        assertEquals(0, mockCity().compareTo(cityList.getCities().get(1)));
    }

    @Test
    public void testHasCity() {
        CityList cityList = mockCityList();
        // Test that the city we added is in the list
        assertTrue(cityList.hasCity(mockCity()));

        // Test that a city not in the list returns false
        City newCity = new City("Calgary", "Alberta");
        assertFalse(cityList.hasCity(newCity));

        // Adds the new city and verify it is now in the list
        cityList.add(newCity);
        assertTrue(cityList.hasCity(newCity));
    }

    @Test
    public void testDelete() {
        CityList cityList = mockCityList();
        City city = mockCity();

        // Verify city is in the list
        assertTrue(cityList.hasCity(city));
        assertEquals(1, cityList.countCities());

        // Delete the city
        cityList.delete(city);

        // Verify it was removed
        assertFalse(cityList.hasCity(city));
        assertEquals(0, cityList.countCities());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteException() {
        CityList cityList = mockCityList();
        City cityNotInList = new City("Toronto", "Ontario");

        // Try to delete a city that's not in the list - should throw exception
        cityList.delete(cityNotInList);
    }

    @Test
    public void testCountCities() {
        CityList cityList = new CityList();

        // Empty list should have 0 cities
        assertEquals(0, cityList.countCities());

        // Add one city
        cityList.add(mockCity());
        assertEquals(1, cityList.countCities());

        // Add another city
        City city2 = new City("Vancouver", "British Columbia");
        cityList.add(city2);
        assertEquals(2, cityList.countCities());

        // Delete a city
        cityList.delete(city2);
        assertEquals(1, cityList.countCities());
    }
}