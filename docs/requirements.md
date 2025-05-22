## User Management
- **FR-1**: Create and manage player profiles (name, avatar).
- **FR-2**: Retrieve player statistics and historical matches.

## Match Lifecycle
- **FR-3**: Create a new match with two players and a specified “best of” frame count.
- **FR-4**: Start a new frame within a match.
- **FR-5**: Record scoring events (pot or foul) in real time.
- **FR-6**: Fetch current frame state (scores, current break, event log).
- **FR-7**: End a frame and declare a frame winner.
- **FR-8**: End a match and declare a match winner.

## Real-time Updates
- **FR-9**: Broadcast scoring events via WebSocket/STOMP.
- **FR-10**: Subscribe to live updates for a specific frame or match.