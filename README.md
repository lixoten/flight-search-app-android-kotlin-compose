# Flight Search App
An Android app that asks the user for a departure airport, searches a
pre-populated database to present a list of flights that depart from that
airport, lets the user save favorite flights, and updates the database with Room.

#### Please fill free to review the code and comment, submit an issue to comment on it  

# App Objective
Retrieve Data from a Room Database with 2 tables Airports and Favorites.
Save Search value to user preferences.  
When the user reopens the app, the search text is prepopulate from preferences 

### Screens
- Search Screen with an empty textField to search DB
- On Empty Search Text
  - we show a list of Saved Favorite Flights if saved flight are found
- On Search Text entering
  - we show search results, a list of airports
    - Clicking on an item (Airport), takes as to the Flight Screen 
- Flight Screen 
  - is a list of flights against all other Airports in DB
  - Clicking on a flight save it to Favorites

## Codelab: Project:Create a Flight Search app
https://developer.android.com/codelabs/basic-android-kotlin-compose-flight-search?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-compose-unit-6-pathway-3%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-compose-flight-search#3

ref: flight-search-app-android-kotlin-compose