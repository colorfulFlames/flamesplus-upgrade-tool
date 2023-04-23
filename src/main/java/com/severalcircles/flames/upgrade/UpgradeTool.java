package com.severalcircles.flames.upgrade;

import com.severalcircles.flames.FLogger;

import java.io.*;
import java.nio.file.Files;
import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

public class UpgradeTool {
    static final OldDataRecord defaultOldDataRecord = new OldDataRecord(
            true,
            true,
            Locale.ENGLISH,
            0,
            Instant.now(),
            0,
            "",
            Instant.now(),
            0,
            0,
            0,
            0,
            "",
            0
    );
    public static final List<String> ids = new LinkedList<>();
    static final File flamesDir = new File(System.getProperty("user.dir") + "/Flames");
    static final FLogger fl = new FLogger();
    static final Instant start = Instant.now();
    public static void main(String[] args) {
        fl.info("Welcome to Flames Upgrade Tool!");
        fl.info("This tool will help you upgrade your Flames data in 7.x format to the new plus format.");
        System.out.println();
        fl.info("Looking for Flames directory");
        if (!flamesDir.exists()) {
            fl.info("Flames directory not found at " + flamesDir.getAbsolutePath());
            fl.severe("Flames directory not found. Please run this utility in a directory with a subdirectory called \"Flames\".");
            System.exit(1);
        } else fl.info("Flames directory found at " + flamesDir.getAbsolutePath());
        fl.info("Checking for flamesfiles");
        File[] flamesFiles = flamesDir.getParentFile().listFiles();
        boolean compatible = false;
        for (File flamesFile : flamesFiles) {
            if (flamesFile.getName().endsWith(".flamesfile")) {
                fl.info("Found flamesfile " + flamesFile.getName());
                if (flamesFile.getName().contains("7") | flamesFile.getName().contains("6")) {
                    fl.info("Flamesfile for compatible version " + flamesFile.getName().replace(".flamesfile", "") + " found.");
                    compatible = true;
                } else {
                    fl.warning("This upgrade tool is explicitly designed for Flames 7.x and 6.x. You may experience issues with other versions.");
                    fl.warning("If you experience issues, please upgrade Flames to 6.0 or higher before upgrading your data.");
                }
            }
        }
        if (!compatible) {
            fl.warning("No compatible flamesfile found. This tool may behave unexpectedly.");
            fl.info("Continuing in 5 seconds...");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        File newDir = new File(flamesDir.getParentFile().getAbsolutePath() + "/plus");
        File oldDir = new File(flamesDir.getParentFile().getAbsolutePath() + "/old");
        fl.info("Indexing Flames data");
        File userDir = new File(flamesDir.getAbsolutePath() + "/user");
        if (!userDir.exists()) throw new IncompatibilityException(userDir);
        if (oldDir.mkdir()) fl.info("Created old directory at " + oldDir.getAbsolutePath());
        if (newDir.mkdir()) fl.info("Created plus directory at " + newDir.getAbsolutePath());
        File[] userDirs = userDir.listFiles();
        AtomicInteger users = new AtomicInteger();
        for (File user : userDirs) {
            if (user.isDirectory()) {
                ids.add(user.getName());
                fl.info("Found user " + user.getName());
                users.getAndIncrement();
            }
        }
        fl.info("Found " + users + " users");
        int finalUsers1 = users.get();
        List<String> idsCopy = new LinkedList<>(ids);
        idsCopy.forEach((id) -> {
            int finalUsers = finalUsers1;
            File user = new File(flamesDir.getAbsolutePath() + "/user/" + id);
            Properties properties = new Properties();
            try {
                properties.load(new FileInputStream(user.getAbsolutePath() + "/user.fl"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            int consent = Integer.parseInt(properties.getProperty("consent"));
            if (consent != 1) {
                fl.info("User " + id + " has consent value " + consent + " and will be skipped.");
                ids.remove(id);
                finalUsers--;
            }
            fl.info(finalUsers + " users remaining");
            users.set(finalUsers);
        });
        fl.info("Beginning upgrade process");
        ids.forEach((id) -> {
            File user = new File(flamesDir.getAbsolutePath() + "/user/" + id);
            Properties properties = new Properties();
            try {
                properties.load(new FileInputStream(user.getAbsolutePath() + "/user.fl"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            OldDataRecord oldDataRecord = createOldDataRecord(id);
            NewDataRecord newDataRecord = new NewDataRecord(1, oldDataRecord.consent(), 0, oldDataRecord.favoriteQuote(), oldDataRecord.happyDay(), oldDataRecord.highScore(), Instant.ofEpochSecond(0), oldDataRecord.locale(), oldDataRecord.lowScore(), 0, "UNRANKED", oldDataRecord.sadDay(), (double) Math.ceil(oldDataRecord.score() / 1000));
            Properties newProperties = new Properties();
            try {
                newProperties.load(new FileReader(UpgradeTool.class.getResource("/user.properties").getFile()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            newProperties.setProperty("bonusMultiplier", String.valueOf(newDataRecord.bonusMultiplier()));
            newProperties.setProperty("consent", String.valueOf(newDataRecord.consent()));
            newProperties.setProperty("conversations", String.valueOf(newDataRecord.conversations()));
            newProperties.setProperty("favoriteQuote", newDataRecord.favoriteQuote());
            newProperties.setProperty("happyDay", newDataRecord.happyDay().toString());
            newProperties.setProperty("highScore", String.valueOf(newDataRecord.highScore()));
            newProperties.setProperty("lastBonus", newDataRecord.lastBonus().toString());
            newProperties.setProperty("locale", newDataRecord.locale().toString());
            newProperties.setProperty("lowScore", String.valueOf(newDataRecord.lowScore()));
            newProperties.setProperty("messages", String.valueOf(newDataRecord.messages()));
            newProperties.setProperty("rank", newDataRecord.rank());
            newProperties.setProperty("sadDay", newDataRecord.sadDay().toString());
            newProperties.setProperty("score", String.valueOf(newDataRecord.score()));
            File newFile = new File(newDir.getAbsolutePath() + "/user/" + id + ".flp");
            try {
                if (newFile.getParentFile().mkdir())
                    fl.info("Created plus directory while processing user " + id + " at " + newFile.getParentFile().getAbsolutePath());
                if (newFile.createNewFile())
                    fl.info("Created new data file for user " + id + " at " + newFile.getAbsolutePath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                newProperties.store(new FileOutputStream(newFile), "Upgraded Flames Plus Data File");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            users.set(users.get() - 1);
            fl.info("Upgraded user " + id + " (" + users.get() + " to go)");
        });
        fl.info("Your new data is in a folder called plus in this directory. Rename your old Flames directory to old and rename plus to Flames to use your new data. You shouldn't delete your old data until you're sure the new data works.");
        fl.resetLastClass();
        fl.info("Upgrade successful! I upgraded " + ids.size() + " users in " + (System.currentTimeMillis() - start.toEpochMilli()) / 1000 + " seconds.");
    }
    static OldDataRecord createOldDataRecord(String id) {
        Properties user = new Properties();
        Properties stats = new Properties();
        Properties funfacts = new Properties();
        Properties config = new Properties();
        try {
            user.load(new FileInputStream(flamesDir.getAbsolutePath() + "/user/" + id + "/user.fl"));
            stats.load(new FileInputStream(flamesDir.getAbsolutePath() + "/user/" + id + "/stats.fl"));
            funfacts.load(new FileInputStream(flamesDir.getAbsolutePath() + "/user/" + id + "/funfacts.fl"));
            config.load(new FileInputStream(flamesDir.getAbsolutePath() + "/user/" + id + "/config.fl"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Properties finalProps = mergeProperties(user, stats, funfacts, config);
        String[] fields = {"qotdAllowed", "favQuoteAllowed", "locale", "highScore", "sadDay", "lowestEmotion", "favoriteQuote", "happyDay", "highestEmotion", "lowScore", "score", "emotion", "discordId", "consent"};
        for (String field : fields) {
            try {
                finalProps.getProperty(field);
            } catch (NullPointerException e) {
                fl.warning("Couldn't find field " + field + " for user " + id + ". Using default value.");
                finalProps.put(field, defaultOldDataRecord.getFieldbyName(field));
            }
        }
        return new OldDataRecord(
                Boolean.parseBoolean(finalProps.getProperty("qotdAllowed")),
                Boolean.parseBoolean(finalProps.getProperty("favQuoteAllowed")),
                Locale.forLanguageTag(finalProps.getProperty("locale")),
                Integer.parseInt(finalProps.getProperty("highScore")),
                Instant.parse(finalProps.getProperty("sadDay")),
                Double.parseDouble(finalProps.getProperty("lowestEmotion")),
                finalProps.getProperty("favoriteQuote"),
                Instant.parse(finalProps.getProperty("happyDay")),
                Double.parseDouble(finalProps.getProperty("highestEmotion")),
                Integer.parseInt(finalProps.getProperty("lowScore")),
                Integer.parseInt(finalProps.getProperty("score")),
                Double.parseDouble(finalProps.getProperty("emotion")),
                finalProps.getProperty("discordId"),
                Integer.parseInt(finalProps.getProperty("consent"))
        );
    }
    static private Properties mergeProperties(Properties... properties) {
        Properties mergedProperties = new Properties();
        for (Properties property : properties) {
            mergedProperties.putAll(property);
        }
        return mergedProperties;
    }
}
