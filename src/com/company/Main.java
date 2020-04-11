package com.company;

import java.util.Random;

public class Main {

    static int bossHealth = 750;

    static int bossDamage = 50;

    static String bossDefenceType = " ";

    static int[] heroesHealth = {260, 280, 270, 400};

    static int[] heroesDamage = {20, 25, 15};

    static String[] heroesSuperAbilities = {"Physical", "Magical", "Kinetic", "Medic"};

    static int medicHeal = 10;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void round() {
        changeBossDefence();
        bossHit();
        healing();
        heroesHit();
        printStatistics();
    }

    public static void healing() {
        Random random = new Random();
        int runNum = random.nextInt(heroesDamage.length);
        if (heroesHealth[runNum] < 100 && heroesHealth[3] > 0) {
            heroesHealth[runNum] = heroesHealth[runNum] + medicHeal;
            System.out.println("Medic healed " + heroesSuperAbilities[runNum]);
        }
    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomNumber = r.nextInt(heroesSuperAbilities.length); // 0, 1, 2
        bossDefenceType = heroesSuperAbilities[randomNumber];
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0 && heroesHealth[3] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] - bossDamage < 0) {
                heroesHealth[i] = 0;
            } else {
                heroesHealth[i] = heroesHealth[i] - bossDamage;
            }
        }
    }

    public static void printStatistics() {
        System.out.println("_____________________");
        System.out.println("Boss health: " + bossHealth);
        System.out.println("Warrior health: " + heroesHealth[0]);
        System.out.println("Magic health: " + heroesHealth[1]);
        System.out.println("Kinetic health: " + heroesHealth[2]);
        System.out.println("Medic health: " + heroesHealth[3]);
        System.out.println("______________________");
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesSuperAbilities[i] == bossDefenceType) {
                Random r = new Random();
                int coeff = r.nextInt(5) + 2;
                bossHealth = bossHealth - heroesDamage[i] * coeff;
                System.out.println(heroesSuperAbilities[i] + " hits with critical damage " + heroesDamage[i] * coeff);
            } else {
                bossHealth = bossHealth - heroesDamage[i];
            }
        }
    }
}
