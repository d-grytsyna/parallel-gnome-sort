package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListUtil {
    public static List<Integer> generateList(int size, int min, int max) {
        List<Integer> randomIntegers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int randomInt = random.nextInt(max - min + 1) + min;
            randomIntegers.add(randomInt);
        }
        return randomIntegers;
    }
    public static List<Integer> copyList(List<Integer> originalList) {
        List<Integer> copyList = new ArrayList<>(originalList);
        return copyList;
    }
    public static void writeToFile(List<Integer> list, String fileName){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Integer integer : list) {
                writer.write(integer.toString());
                writer.newLine();
            }
            System.out.println("List has been written to the file successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    public static List<Integer> readFromFile(String fileName) {
        List<Integer> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    int value = Integer.parseInt(line.trim());
                    list.add(value);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("File has been read successfully.");
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return list;
    }
    public static boolean listEqual(List<Integer> list1, List<Integer> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equals(list2.get(i))) {
                return false;
            }
        }
        return true;
    }
    public static boolean isSorted(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i)>list.get(i+1)) {
                return false;
            }
        }
        return true;
    }
}
