package com.severalcircles.flames.upgrade;

import java.time.Instant;
import java.util.Locale;
import java.util.Objects;

public class OldDataRecord {
    private boolean qotdAllowed;
    private boolean favQuoteAllowed;
    private Locale locale;
    private int highScore;
    private Instant sadDay;
    private double lowestEmotion;
    private String favoriteQuote;
    private Instant happyDay;
    private double highestEmotion;
    private int lowScore;
    private int score;
    private double emotion;
    private String discordId;
    private int consent;

    public OldDataRecord(
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
        this.qotdAllowed = qotdAllowed;
        this.favQuoteAllowed = favQuoteAllowed;
        this.locale = locale;
        this.highScore = highScore;
        if (sadDay == null) sadDay = Instant.EPOCH;
        else this.sadDay = sadDay;
        this.lowestEmotion = lowestEmotion;
        if (favoriteQuote == null) favoriteQuote = "";
        else this.favoriteQuote = favoriteQuote;
        if (happyDay == null) happyDay = Instant.EPOCH;
        else this.happyDay = happyDay;
        this.highestEmotion = highestEmotion;
        this.lowScore = lowScore;
        this.score = score;
        this.emotion = emotion;
        if (discordId == null) discordId = "";
        else this.discordId = discordId;
        this.consent = consent;
    }

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

    public boolean qotdAllowed() {
        return qotdAllowed;
    }

    public boolean favQuoteAllowed() {
        return favQuoteAllowed;
    }

    public Locale locale() {
        return locale;
    }

    public int highScore() {
        return highScore;
    }

    public Instant sadDay() {
        return sadDay;
    }

    public double lowestEmotion() {
        return lowestEmotion;
    }

    public String favoriteQuote() {
        return favoriteQuote;
    }

    public Instant happyDay() {
        return happyDay;
    }

    public double highestEmotion() {
        return highestEmotion;
    }

    public int lowScore() {
        return lowScore;
    }

    public int score() {
        return score;
    }

    public double emotion() {
        return emotion;
    }

    public String discordId() {
        return discordId;
    }

    public int consent() {
        return consent;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (OldDataRecord) obj;
        return this.qotdAllowed == that.qotdAllowed &&
                this.favQuoteAllowed == that.favQuoteAllowed &&
                Objects.equals(this.locale, that.locale) &&
                this.highScore == that.highScore &&
                Objects.equals(this.sadDay, that.sadDay) &&
                Double.doubleToLongBits(this.lowestEmotion) == Double.doubleToLongBits(that.lowestEmotion) &&
                Objects.equals(this.favoriteQuote, that.favoriteQuote) &&
                Objects.equals(this.happyDay, that.happyDay) &&
                Double.doubleToLongBits(this.highestEmotion) == Double.doubleToLongBits(that.highestEmotion) &&
                this.lowScore == that.lowScore &&
                this.score == that.score &&
                Double.doubleToLongBits(this.emotion) == Double.doubleToLongBits(that.emotion) &&
                Objects.equals(this.discordId, that.discordId) &&
                this.consent == that.consent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(qotdAllowed, favQuoteAllowed, locale, highScore, sadDay, lowestEmotion, favoriteQuote, happyDay, highestEmotion, lowScore, score, emotion, discordId, consent);
    }

    @Override
    public String toString() {
        return "OldDataRecord[" +
                "qotdAllowed=" + qotdAllowed + ", " +
                "favQuoteAllowed=" + favQuoteAllowed + ", " +
                "locale=" + locale + ", " +
                "highScore=" + highScore + ", " +
                "sadDay=" + sadDay + ", " +
                "lowestEmotion=" + lowestEmotion + ", " +
                "favoriteQuote=" + favoriteQuote + ", " +
                "happyDay=" + happyDay + ", " +
                "highestEmotion=" + highestEmotion + ", " +
                "lowScore=" + lowScore + ", " +
                "score=" + score + ", " +
                "emotion=" + emotion + ", " +
                "discordId=" + discordId + ", " +
                "consent=" + consent + ']';
    }

}
