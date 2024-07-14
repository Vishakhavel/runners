package com.vishakhavel.runnerz.run;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Postive;


public record Run(
        Integer id,
        @NotEmpty
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        @Postive
        Integer miles,
        Location location
) {
    public Run {
        if (!completedOnisAfter(startedOn)) {
            throw new IllegalArgumentException("completedOn must be after startedOn");
        }
    }
}

