# TrackPot Functional Requirements Analysis

This document provides an analysis of the functional requirements covered in the TrackPot codebase.

## Requirements from requirements.md

### User Management
- **FR-1**: Create and manage player profiles (name, avatar).
  - **Implementation Status**: Implemented
  - **Evidence**: 
    - Player model with name and avatarUrl fields
    - PlayerController with CRUD endpoints
    - PlayerService for business logic

- **FR-2**: Retrieve player statistics and historical matches.
  - **Implementation Status**: Partially implemented
  - **Evidence**: 
    - MatchController has endpoint to get matches by player ID
    - No dedicated player statistics functionality

### Match Lifecycle
- **FR-3**: Create a new match with two players and a specified "best of" frame count.
  - **Implementation Status**: Implemented
  - **Evidence**: 
    - SnookerMatch model with player1, player2, and bestOfFrames fields
    - MatchController with endpoint to create matches
    - MatchService for business logic

- **FR-4**: Start a new frame within a match.
  - **Implementation Status**: Partially implemented
  - **Evidence**: 
    - Frame model with match relationship
    - FrameRequestDto for frame creation
    - No dedicated controller or service for frame operations

- **FR-5**: Record scoring events (pot or foul) in real time.
  - **Implementation Status**: Partially implemented
  - **Evidence**: 
    - ScoreEvent abstract model with PotEvent and FoulEvent implementations
    - ScoreWebSocketController for real-time updates
    - No persistence of events from WebSocket

- **FR-6**: Fetch current frame state (scores, current break, event log).
  - **Implementation Status**: Partially implemented
  - **Evidence**: 
    - FrameResponseDto includes events list
    - No dedicated endpoint to fetch frame state

- **FR-7**: End a frame and declare a frame winner.
  - **Implementation Status**: Partially implemented
  - **Evidence**: 
    - Frame model has winner field
    - FrameRequestDto allows setting winnerId
    - No dedicated endpoint to end a frame

- **FR-8**: End a match and declare a match winner.
  - **Implementation Status**: Partially implemented
  - **Evidence**: 
    - SnookerMatch model has status and endTime fields
    - MatchController has endpoint to update matches
    - No explicit winner field or logic to determine winner

### Real-time Updates
- **FR-9**: Broadcast scoring events via WebSocket/STOMP.
  - **Implementation Status**: Implemented
  - **Evidence**: 
    - ScoreWebSocketController handles WebSocket communication
    - Events are broadcast to topic/match.{frameId}

- **FR-10**: Subscribe to live updates for a specific frame or match.
  - **Implementation Status**: Implemented
  - **Evidence**: 
    - WebSocket topics are frame-specific (/topic/match.{frameId})
    - Clients can subscribe to specific frames

## Additional Requirements Identified in Code

- **FR-11**: Manage different types of balls in snooker (via BallType enum)
  - **Implementation Status**: Implemented
  - **Evidence**: 
    - BallType enum used in PotEvent
    - Points calculation based on ball type

- **FR-12**: Track match status (NOT_STARTED, IN_PROGRESS, COMPLETED)
  - **Implementation Status**: Implemented
  - **Evidence**: 
    - MatchStatus enum
    - Status field in SnookerMatch model

## Implementation Gaps and Recommendations

1. **Frame Management**: Implement dedicated controller and service for frame operations
2. **Scoring Event Persistence**: Update ScoreWebSocketController to persist events to database
3. **Player Statistics**: Implement calculation and retrieval of player statistics
4. **Match Winner Determination**: Add logic to determine match winner based on frames won
5. **Current Break Tracking**: Add functionality to track current break in a frame

## Conclusion

The TrackPot application has implemented or partially implemented all the functional requirements specified in requirements.md. The core domain models are well-defined, but there are gaps in the implementation of some operations, particularly around frame management and scoring events. The application uses a combination of REST APIs and WebSockets to provide both traditional request-response interactions and real-time updates.