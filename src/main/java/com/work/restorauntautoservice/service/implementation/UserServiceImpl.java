package com.work.restorauntautoservice.service.implementation;

import com.work.restorauntautoservice.exception.PasswordAreNotMatchingException;
import com.work.restorauntautoservice.model.User;
import com.work.restorauntautoservice.repository.UserRepository;
import com.work.restorauntautoservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User editUser(String userName,User user) {
        User userToUpdate = userRepository.findByUsername(userName);
        return userRepository.save(userToUpdate);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public static int[] arrayDiff(int[] a, int[] b) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i : a) {
            list.add(i);
        }
        for(int i = 0; i <= list.size(); i++) {
            for(int j = 0; j <= b.length; j++) {
                if (i == j) {
                    list.remove(i);
                }
            }
        }
        int[] newA = list.stream().mapToInt(i -> i).toArray();
        return newA;
    }

    public static String makeComplement(String dna) {
        String[] DNA = dna.split("");
        List<String> dnaList = new ArrayList<>(Arrays.asList(DNA));
        List<String> resultList = new ArrayList<>();
        for (String i: dnaList) {
            switch (i) {
                case "A" -> resultList.add("T");
                case "T" -> resultList.add("A");
                case "C" -> resultList.add("G");
                case "G" -> resultList.add("C");
            }
        }
        return String.join("", resultList);
    }

    /*
    Complete the solution so that it splits the string into pairs of two characters.
    If the string contains an odd number of characters then it
    should replace the missing second character of the final pair with an underscore ('_').
     */
    public static String[] solution(String s) {
        char[] charData = s.toCharArray();
        List<String> strings = new ArrayList<>();
        if(charData.length % 2 == 1) {
            for (int i = 0; i <= charData.length; i = i+2) {
                String val = String.valueOf(charData[i] + charData[i+1]);
                strings.add(val);
            }
            strings.set(strings.size()-1, "_");
        } else {
            for (int i = 0; i <= charData.length; i = i+2) {
                String val = String.valueOf(charData[i] + charData[i+1]);
                strings.add(val);
            }
        }
        String[] result = (String[]) strings.stream().toArray();

        return result;
    }

    /*
    An isogram is a word that has no repeating letters, consecutive or non-consecutive.
    Implement a function that determines whether a string that contains only letters is an isogram.
    Assume the empty string is an isogram. Ignore letter case.
     */

    public static boolean isIsogram(String str) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!set.add(c)) {
                return true;
            }
        }
        if (str.equals("")) {
            return true;
        }
        return false;
    }

    /*
    accum("abcd") -> "A-Bb-Ccc-Dddd"
    accum("RqaEzty") -> "R-Qq-Aaa-Eeee-Zzzzz-Tttttt-Yyyyyyy"
    accum("cwAt") -> "C-Ww-Aaa-Tttt"
     */
    public static String accum(String s) {
        List<String> stringList = new LinkedList<>(Arrays.asList(s.split("")));
        StringBuilder ret = new StringBuilder();
        for (String el:stringList) {
            for(int i = 0; i <= stringList.indexOf(el); i++) {
                ret = new StringBuilder(ret + el);
            }
            ret = new StringBuilder(ret + "-");
        }
        return Objects.requireNonNull(ret).toString();
    }
}
