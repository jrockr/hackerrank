/*
https://www.hackerrank.com/challenges/sherlock-and-valid-string/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=strings

Sherlock considers a string to be valid if all characters of the string appear the same number of times. It is also valid if he can remove just  character at  index in the string, and the remaining characters will occur the same number of times. Given a string , determine if it is valid. If so, return YES, otherwise return NO.

Example

This is a valid string because frequencies are .


This is a valid string because we can remove one  and have  of each character in the remaining string.


This string is not valid as we can only remove  occurrence of . That leaves character frequencies of .

Function Description

Complete the isValid function in the editor below.

isValid has the following parameter(s):

string s: a string
Returns

string: either YES or NO
Input Format

A single string .

Constraints

Each character 

*/

package com.jrockr.hackerrank;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Result {

    /*
     * Complete the 'isValid' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String isValid(String s) {
        // Write your code here
        List<Character> uniqueCharArray = uniqueChar(s);
        System.out.println(uniqueCharArray);
        List<Long> countAlphaberArray = new ArrayList<>();

        for (int i = 0; i < uniqueCharArray.size(); i++) {
            countAlphaberArray.add(charCount(uniqueCharArray.get(i), s));
            System.out.println(uniqueCharArray.get(i) + " : " + charCount(uniqueCharArray.get(i), s));
        }

        Long max = countAlphaberArray.stream().max(Long::compareTo).get();
        Long min = countAlphaberArray.stream().min(Long::compareTo).get();

        long totalMaxFreq = countAlphaberArray.stream().filter(e -> e.equals(max)).count();
        long totalMinFreq = countAlphaberArray.stream().filter(e -> e.equals(min)).count();

        System.out.println("max :: " + max + " Min :: " + min);
        System.out.println("totalMaxFreq :: " + totalMaxFreq + " totalMinFreq :: " + totalMinFreq);
        System.out.println("Inbetween value :: " + countAlphaberArray.stream().filter(e -> e > min && e < max).count());

        if (max - min == 0) {
            return "YES";
        } else {
            if (countAlphaberArray.stream().filter(e -> e > min && e < max).count() != 0) {
                return "NO";
            } else {
                if ((max - min == 1) && (totalMaxFreq == 1 || totalMinFreq == 1)) {
                    return "YES";
                } else {
                    if ((max - min > 1) && (totalMinFreq == 1)) {
                        return "YES";
                    } else {
                        return "NO";
                    }

                }
            }
        }

    }

    private static List<Character> uniqueChar(String str) {
        return str.chars().distinct().mapToObj(e -> (char) e).collect(Collectors.toList());
    }

    private static long charCount(Character c, String str) {
        return str.chars().mapToObj(e -> (char) e).filter(e -> e.equals(c)).count();
    }

}

public class SherlockAndTheValidString {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("hacker-app/src/main/java/resources/testdata.txt");

        String input = new String(Files.readAllLines(path).get(0));
        System.out.println(Result.isValid(input) + " :: expected YES");

        Path path2 = Paths.get("hacker-app/src/main/java/resources/testdata2.txt");

        String input2 = new String(Files.readAllLines(path2).get(0));
        System.out.println(Result.isValid(input2) + " :: expected NO");
    }
}
