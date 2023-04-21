package com.severalcircles.flames.upgrade;

import com.severalcircles.flames.FLogger;

import java.io.File;

public class IncompatibilityException extends RuntimeException{
    public IncompatibilityException(File file) {
        super("Flames data is not compatible with this version of the upgrade tool.");
        new FLogger().severe("The upgrade tool encountered an incompatibility. Data should be present at " + file.getAbsolutePath() + ", but no such file was found.");
        System.exit(1);
    }
}
