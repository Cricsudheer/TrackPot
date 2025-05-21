# TrackPot API Postman Collection

This document provides instructions on how to use the Postman collection for testing the TrackPot API.

## Overview

The TrackPot API Postman collection includes all the endpoints available in the TrackPot application, organized into the following sections:

1. **Players** - Endpoints for managing players
2. **Matches** - Endpoints for managing matches
3. **Health** - Endpoints for health checks
4. **WebSocket** - WebSocket endpoints for real-time updates

## Getting Started

### Prerequisites

- [Postman](https://www.postman.com/downloads/) installed on your machine
- TrackPot application running locally or on a server

### Importing the Collection

1. Open Postman
2. Click on "Import" in the top left corner
3. Select "File" and choose the `postman_collection.json` file from the project root
4. Click "Import"

### Setting Up Environment Variables

The collection uses environment variables to make it easier to switch between different environments (e.g., local, development, production). You should create an environment in Postman with the following variables:

1. `baseUrl` - The base URL of the TrackPot API (default: `http://localhost:8080`)
2. `playerId` - A UUID for a player (example value is provided)
3. `player1Id` - A UUID for player 1 (example value is provided)
4. `player2Id` - A UUID for player 2 (example value is provided)
5. `matchId` - A UUID for a match (example value is provided)
6. `frameId` - A UUID for a frame (example value is provided)

To create an environment:

1. Click on the "Environments" tab in Postman
2. Click "Add" to create a new environment
3. Name it (e.g., "TrackPot Local")
4. Add the variables listed above with appropriate values
5. Click "Save"

## Using the Collection

### Players

#### Get All Players
- **Method**: GET
- **URL**: `{{baseUrl}}/api/players`
- **Description**: Returns a list of all players

#### Get Player by ID
- **Method**: GET
- **URL**: `{{baseUrl}}/api/players/{{playerId}}`
- **Description**: Returns a player as per the ID

#### Create Player
- **Method**: POST
- **URL**: `{{baseUrl}}/api/players`
- **Headers**: Content-Type: application/json
- **Body**:
  ```json
  {
      "name": "John Smith",
      "avatarUrl": "https://example.com/avatar.jpg"
  }
  ```
- **Description**: Creates a new player and returns the created player

#### Update Player
- **Method**: PUT
- **URL**: `{{baseUrl}}/api/players/{{playerId}}`
- **Headers**: Content-Type: application/json
- **Body**:
  ```json
  {
      "name": "John Smith Updated",
      "avatarUrl": "https://example.com/avatar-updated.jpg"
  }
  ```
- **Description**: Updates a player and returns the updated player

#### Delete Player
- **Method**: DELETE
- **URL**: `{{baseUrl}}/api/players/{{playerId}}`
- **Description**: Deletes a player

### Matches

#### Get All Matches
- **Method**: GET
- **URL**: `{{baseUrl}}/api/matches`
- **Description**: Returns a list of all matches

#### Get Match by ID
- **Method**: GET
- **URL**: `{{baseUrl}}/api/matches/{{matchId}}`
- **Description**: Returns a match as per the ID

#### Get Matches by Player ID
- **Method**: GET
- **URL**: `{{baseUrl}}/api/matches/player/{{playerId}}`
- **Description**: Returns a list of matches for a specific player

#### Create Match
- **Method**: POST
- **URL**: `{{baseUrl}}/api/matches`
- **Headers**: Content-Type: application/json
- **Body**:
  ```json
  {
      "player1Id": "{{player1Id}}",
      "player2Id": "{{player2Id}}",
      "bestOfFrames": 7,
      "startTime": "2023-06-15T10:15:30Z",
      "status": "NOT_STARTED"
  }
  ```
- **Description**: Creates a new match and returns the created match

#### Update Match
- **Method**: PUT
- **URL**: `{{baseUrl}}/api/matches/{{matchId}}`
- **Headers**: Content-Type: application/json
- **Body**:
  ```json
  {
      "player1Id": "{{player1Id}}",
      "player2Id": "{{player2Id}}",
      "bestOfFrames": 7,
      "startTime": "2023-06-15T10:15:30Z",
      "endTime": "2023-06-15T12:30:45Z",
      "status": "IN_PROGRESS"
  }
  ```
- **Description**: Updates a match and returns the updated match

#### Delete Match
- **Method**: DELETE
- **URL**: `{{baseUrl}}/api/matches/{{matchId}}`
- **Description**: Deletes a match

### Health

#### Check Database Connection
- **Method**: GET
- **URL**: `{{baseUrl}}/api/health/db`
- **Description**: Tests the database connection by executing a simple query

### WebSocket

**Note**: Testing WebSocket endpoints in Postman requires using the WebSocket Request feature, which is available in Postman version 8.0 and above.

#### Connect to WebSocket
- **Method**: GET
- **URL**: `{{baseUrl}}/ws`
- **Description**: Connect to the WebSocket endpoint

#### Send Score Update
- **Method**: POST
- **URL**: `{{baseUrl}}/app/score`
- **Headers**: Content-Type: application/json
- **Body**:
  ```json
  {
      "frameId": "{{frameId}}",
      "playerId": "{{playerId}}",
      "points": 7
  }
  ```
- **Description**: Send a score update to the WebSocket endpoint

#### Subscribe to Score Updates
- **Method**: GET
- **URL**: `{{baseUrl}}/topic/match.{{frameId}}`
- **Description**: Subscribe to score updates for a specific frame

## Workflow Examples

### Creating and Managing Players

1. Create a player using the "Create Player" request
2. Copy the returned player ID and set it as the `playerId` environment variable
3. Get the player details using the "Get Player by ID" request
4. Update the player using the "Update Player" request
5. Delete the player using the "Delete Player" request

### Creating and Managing Matches

1. Create two players using the "Create Player" request
2. Copy the returned player IDs and set them as the `player1Id` and `player2Id` environment variables
3. Create a match using the "Create Match" request
4. Copy the returned match ID and set it as the `matchId` environment variable
5. Get the match details using the "Get Match by ID" request
6. Update the match using the "Update Match" request
7. Delete the match using the "Delete Match" request

## Troubleshooting

- If you get a 404 error, make sure the TrackPot application is running and the base URL is correct
- If you get a 400 error, check the request body for missing or invalid fields
- If you get a 500 error, check the application logs for more information