Feature: Validating Place API's
@AddPlace
Scenario Outline: Verify if place is being Successfully added using AddPlaceAPIs
	Given Add place Payload with "<name>" "<language>" "<Address>"
	When User calls "AddPlaceAPI" with "Post" http request
	Then the API got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_Id created maps to "<name>" using "GetPlaceAPI"
	
Examples:
	|name           | language | Address              	|
	|Mehendale garage| English-Mexico  | 23 side layout Fenoc  |
	|Lawn side		| Hindi    | 55 sidewalk crater road| 
	
@DeletePlace	
Scenario: Verify if Delete place functionality is working successfully using DeletePlaceAPIs
	Given Delete place Payload
	When User calls "DeletePlaceAPI" with "Post" http request
	Then the API got success with status code 200
	And "status" in response body is "OK"