package io;

public class TipsHelper {
    public static void printGreeting() {
        System.out.println("Dear " + System.getenv("USERNAME") + ", welcome to the file renamer");
        System.out.println("Enter the full system path to the directories:");
    }
}
