-- List all countries along with their region names.
SELECT c.COUNTRY_NAME, r.REGION_NAME
FROM COUNTRIES c
JOIN REGIONS r ON c.REGION_ID = r.REGION_ID;

-- List all locations along with their country names.
SELECT l.LOCATION_ID,  c.COUNTRY_NAME
FROM LOCATIONS l
JOIN COUNTRIES c ON l.COUNTRY_ID = c.COUNTRY_ID;

-- Find all regions, including those without any countries.
SELECT r.REGION_NAME 
FROM REGIONS r
LEFT JOIN COUNTRIES c ON r.REGION_ID = c.REGION_ID
GROUP BY r.REGION_NAME;

SELECT REGION_NAME FROM REGIONS;

-- Find all countries, including those without any locations.
SELECT c.COUNTRY_NAME
FROM COUNTRIES c
LEFT JOIN LOCATIONS l ON c.COUNTRY_ID = l.COUNTRY_ID
GROUP BY c.COUNTRY_NAME;

-- Get the count of countries in each region.
SELECT r.REGION_NAME, COUNT(c.COUNTRY_ID) AS country_count
FROM REGIONS r
LEFT JOIN COUNTRIES c ON r.REGION_ID = c.REGION_ID
GROUP BY r.REGION_NAME;

-- Get the count of locations in each country.
SELECT c.COUNTRY_NAME, COUNT(l.LOCATION_ID) AS location_count
FROM COUNTRIES c
LEFT JOIN LOCATIONS l ON c.COUNTRY_ID = l.COUNTRY_ID
GROUP BY c.COUNTRY_NAME;

-- List regions that have more than 5 countries.
SELECT r.REGION_NAME, COUNT(c.COUNTRY_ID) AS country_count
FROM REGIONS r
JOIN COUNTRIES c ON r.REGION_ID = c.REGION_ID
GROUP BY r.REGION_NAME
HAVING COUNT(c.COUNTRY_ID) > 5;

-- Find all cities with their country and region names.
SELECT l.CITY, c.COUNTRY_NAME, r.REGION_NAME
FROM LOCATIONS l
JOIN COUNTRIES c ON l.COUNTRY_ID = c.COUNTRY_ID
JOIN REGIONS r ON c.REGION_ID = r.REGION_ID;

-- List all countries that do not have any locations.
SELECT c.COUNTRY_NAME
FROM COUNTRIES c
LEFT JOIN LOCATIONS l ON c.COUNTRY_ID = l.COUNTRY_ID
WHERE l.LOCATION_ID IS NULL;

-- List the region name, country name, and the number of locations per country.
SELECT r.REGION_NAME, c.COUNTRY_NAME, COUNT(l.LOCATION_ID) AS location_count
FROM COUNTRIES c
JOIN REGIONS r ON c.REGION_ID = r.REGION_ID
LEFT JOIN LOCATIONS l ON c.COUNTRY_ID = l.COUNTRY_ID
GROUP BY r.REGION_NAME, c.COUNTRY_NAME
ORDER BY location_count DESC;


-- Which countries are located in the "Asia" region?
SELECT c.COUNTRY_NAME
FROM COUNTRIES c
JOIN REGIONS r ON c.REGION_ID = r.REGION_ID
WHERE r.REGION_NAME = "Asia";

-- List the names of all countries in the "Americas" region that have at least one location.
SELECT c.COUNTRY_ID, c.COUNTRY_NAME 
FROM COUNTRIES c
JOIN REGIONS r ON c.REGION_ID = r.REGION_ID
JOIN LOCATIONS l ON c.COUNTRY_ID = l.COUNTRY_ID
WHERE r.REGION_NAME = 'Americas'; 

-- Find all cities in the "Europe" region along with their respective country names.
SELECT l.CITY, c.COUNTRY_NAME
FROM LOCATIONS l
JOIN COUNTRIES c ON l.COUNTRY_ID = c.COUNTRY_ID
JOIN REGIONS r ON c.REGION_ID = r.REGION_ID
WHERE r.REGION_NAME = 'Europe';

-- How many countries are in the "Middle East and Asia" region?
SELECT r.REGION_NAME, COUNT(*) AS country_count
FROM COUNTRIES c
JOIN REGIONS r ON c.REGION_ID = r.REGION_ID
WHERE r.REGION_NAME = 'Middle East and Asia';

-- List all regions along with the number of countries in each region.
SELECT r.REGION_NAME, COUNT(c.COUNTRY_ID) AS country_count
FROM REGIONS r
LEFT JOIN COUNTRIES c ON r.REGION_ID = c.REGION_ID
GROUP BY r.REGION_NAME;

-- Which countries do not have any associated locations?
SELECT c.COUNTRY_NAME, l.LOCATION_ID
FROM COUNTRIES c
LEFT JOIN LOCATIONS l ON c.COUNTRY_ID = l.COUNTRY_ID
WHERE l.LOCATION_ID IS NULL;

-- Find all countries along with their region names, where the region name is either "Europe" or "Asia".
SELECT c.COUNTRY_NAME, r.REGION_NAME
FROM COUNTRIES c
JOIN REGIONS r ON c.REGION_ID = r.REGION_ID
WHERE r.REGION_NAME IN ('Europe', 'Asia');

-- List all locations in "Italy" along with the city and postal code.
SELECT l.CITY, l.POSTAL_CODE, c.COUNTRY_NAME
FROM LOCATIONS l
JOIN COUNTRIES c ON l.COUNTRY_ID = c.COUNTRY_ID
WHERE c.COUNTRY_NAME = 'Italy';

-- Which countries have more than one location?
SELECT c.COUNTRY_NAME, COUNT(l.LOCATION_ID) AS location_count
FROM COUNTRIES c
JOIN LOCATIONS l ON c.COUNTRY_ID = l.COUNTRY_ID
GROUP BY c.COUNTRY_NAME
HAVING COUNT(l.LOCATION_ID) > 1;

-- Retrieve all locations in "Canada" and the United States along with the state/province information.
SELECT l.*, c.COUNTRY_NAME
FROM LOCATIONS l
JOIN COUNTRIES c ON l.COUNTRY_ID = c.COUNTRY_ID
WHERE c.COUNTRY_NAME IN ('Canada', 'USA');


