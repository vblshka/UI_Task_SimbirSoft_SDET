package com.ui_autotests.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class RandomUtils {
    private static final Random random = new Random();

    public static int getRandomInt(int bound) {
        return random.nextInt(bound);
    }

    public static int getRandomInt(int min, int max) {
        return min + random.nextInt(max - min + 1);
    }

    public static String getRandomProduct(List<String> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Список не должен быть пустым");
        }
        return list.get(random.nextInt(list.size()));
    }
}