package com.severalcircles.flames.upgrade;

import java.time.Instant;
import java.util.Locale;

public record OldDataRecord(
        boolean qotdAllowed,
        boolean favQuoteAllowed,
        Locale locale,
        int highScore,
        Instant sadDay,
        double lowestEmotion,
        String favoriteQuote,
        Instant happyDay,
        double highestEmotion,
        int lowScore,
        int score,
        double emotion,
        String discordId,
        int consent

) {
    public Object getFieldbyName(String field) {
        switch (field) {
            case "qotdAllowed" -> {
                return qotdAllowed;
            }
            case "favQuoteAllowed" -> {
                return favQuoteAllowed;
            }
            case "locale" -> {
                return locale;
            }
            case "highScore" -> {
                return highScore;
            }
            case "sadDay" -> {
                return sadDay;
            }
            case "lowestEmotion" -> {
                return lowestEmotion;
            }
            case "favoriteQuote" -> {
                return favoriteQuote;
            }
            case "happyDay" -> {
                return happyDay;
            }
            case "highestEmotion" -> {
                return highestEmotion;
            }
            case "lowScore" -> {
                return lowScore;
            }
            case "score" -> {
                return score;
            }
            case "emotion" -> {
                return emotion;
            }
            case "discordId" -> {
                return discordId;
            }
            case "consent" -> {
                return consent;
            }
            default -> {
                return null;
            }
        }
    }
}
