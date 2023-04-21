package com.severalcircles.flames.upgrade;

import java.time.Instant;
import java.util.Locale;

public record NewDataRecord(
        double bonusMultiplier,
        int consent,
        int conversations,
        String favoriteQuote,
        Instant happyDay,
        double highScore,
        Instant lastBonus,
        Locale locale,
        int lowScore,
        int messages,
        String rank,
        Instant sadDay,
        double score
) {
}
