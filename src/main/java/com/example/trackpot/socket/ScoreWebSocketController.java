package com.example.trackpot.socket;

import com.example.trackpot.dto.ScoreEventDto;
import com.example.trackpot.dto.ScoreMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.Instant;

@Controller
public class ScoreWebSocketController {

    private final SimpMessagingTemplate template;

    @Autowired
    public ScoreWebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }

    // When client sends to /app/score
    @MessageMapping("/score")
    public void receiveScore(ScoreMessage msg) {
        // build your outbound payload
        ScoreEventDto event = new ScoreEventDto(
                msg.getFrameId(),
                msg.getPlayerId(),
                msg.getPoints(),
                Instant.now()
        );

        // broadcast to all subscribers of /topic/match.{frameId}
        template.convertAndSend(
                "/topic/match." + msg.getFrameId(),
                event
        );
    }
}
