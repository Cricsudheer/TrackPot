package com.example.trackpot.cqrs.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Command for creating a new player.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlayerCommand {
    private String name;
    private String avatarUrl;
}