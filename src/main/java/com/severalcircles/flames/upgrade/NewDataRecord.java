package com.severalcircles.flames.upgrade;

import java.time.Instant;
import java.util.Locale;
import java.util.Objects;

public final class NewDataRecord {
    private final double bonusMultiplier;
    private final int consent;
    private final int conversations;
    private final String favoriteQuote;
    private final Instant happyDay;
    private final double highScore;
    private final Instant lastBonus;
    private final Locale locale;
    private final int lowScore;
    private final int messages;
    private final String rank;
    private final Instant sadDay;
    private final double score;

    public NewDataRecord(
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
        this.bonusMultiplier = bonusMultiplier;
        this.consent = consent;
        this.conversations = conversations;
        if (favoriteQuote == null) this.favoriteQuote = "";
        else this.favoriteQuote = favoriteQuote;
        if (happyDay == null) happyDay = Instant.EPOCH;
        this.happyDay = happyDay;
        this.highScore = highScore;
        this.lastBonus = lastBonus;
        this.locale = locale;
        this.lowScore = lowScore;
        this.messages = messages;
        this.rank = rank;
        if (sadDay == null) sadDay = Instant.EPOCH;
        this.sadDay = sadDay;
        this.score = score;
    }

    public double bonusMultiplier() {
        return bonusMultiplier;
    }

    public int consent() {
        return consent;
    }

    public int conversations() {
        return conversations;
    }

    public String favoriteQuote() {
        return favoriteQuote;
    }

    public Instant happyDay() {
        return happyDay;
    }

    public double highScore() {
        return highScore;
    }

    public Instant lastBonus() {
        return lastBonus;
    }

    public Locale locale() {
        return locale;
    }

    public int lowScore() {
        return lowScore;
    }

    public int messages() {
        return messages;
    }

    public String rank() {
        return rank;
    }

    public Instant sadDay() {
        return sadDay;
    }

    public double score() {
        return score;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (NewDataRecord) obj;
        return Double.doubleToLongBits(this.bonusMultiplier) == Double.doubleToLongBits(that.bonusMultiplier) &&
                this.consent == that.consent &&
                this.conversations == that.conversations &&
                Objects.equals(this.favoriteQuote, that.favoriteQuote) &&
                Objects.equals(this.happyDay, that.happyDay) &&
                Double.doubleToLongBits(this.highScore) == Double.doubleToLongBits(that.highScore) &&
                Objects.equals(this.lastBonus, that.lastBonus) &&
                Objects.equals(this.locale, that.locale) &&
                this.lowScore == that.lowScore &&
                this.messages == that.messages &&
                Objects.equals(this.rank, that.rank) &&
                Objects.equals(this.sadDay, that.sadDay) &&
                Double.doubleToLongBits(this.score) == Double.doubleToLongBits(that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bonusMultiplier, consent, conversations, favoriteQuote, happyDay, highScore, lastBonus, locale, lowScore, messages, rank, sadDay, score);
    }

    @Override
    public String toString() {
        return "NewDataRecord[" +
                "bonusMultiplier=" + bonusMultiplier + ", " +
                "consent=" + consent + ", " +
                "conversations=" + conversations + ", " +
                "favoriteQuote=" + favoriteQuote + ", " +
                "happyDay=" + happyDay + ", " +
                "highScore=" + highScore + ", " +
                "lastBonus=" + lastBonus + ", " +
                "locale=" + locale + ", " +
                "lowScore=" + lowScore + ", " +
                "messages=" + messages + ", " +
                "rank=" + rank + ", " +
                "sadDay=" + sadDay + ", " +
                "score=" + score + ']';
    }

}
