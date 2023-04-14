## Past time

- 8-9 hours
- Read the documentation (1h)
- init the starter with latest spring boot version (1h)
- setup the usecase and the signature (2h)
- Http gateway (2h)
- Some manual test (1h)
- Write documentation (1h)

## Missing

- Integration test about "grandpoitiers" API
- Test of the algorithm that calculates the distance between two coordinates
- Checks on the provided parameters (Radius, coordinate etc.) in order to return a 400 http response
- Check the data integrity of the API (Some coordinates are null)

## Included dependencies

- Lombok
- Junit 5/ assertj
- Spring boot (web)
- Open API (Swagger)

## Design patterns applied in this code

- Port adapters architecture (Hexagonal type)
- Tasked based UI with a command and query separation (CQRS light)
- Manual dependency injection

### About the usecase

The usecase responsibility is to check if the country is handled or not.
I decided to put most of the intelligence (sorting, compute distance, mapping etc) to the adapter (See
@HttpPoitiersCityCarParkDataProvider)

If we want to handle another city, we juste have to create a new implementation of CityCarParkDataProvider and inject
that in the usecase (See @CompositionRoot).
Maybe we will have to rethink this when we really integrate another city ...

## Workspace prerequisites

- Apache Maven 3.8.*
- OpenJDK 20

## Usage

- Swagger url: http://localhost:8080/swagger-ui.html




