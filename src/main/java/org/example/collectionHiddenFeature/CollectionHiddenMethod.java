package org.example.collectionHiddenFeature;

import java.util.*;
import java.util.stream.Collectors;

public class CollectionHiddenMethod {

    public static void main(String[] args) {

        //Collections.nCopies
        List<String> names = Collections.nCopies(5, "rabin");
        try {
            names.set(0, "hari");//if we try to change in the elements in collection it is immutable.
        } catch (UnsupportedOperationException e) {
            System.out.println("list is immutable, it can not be modify");
        }
        System.out.println(names);


        //Collection.frequency()
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 3, 4, 3, 2, 3, 2, 34, 45, 5, 3, 3);
        long count = Collections.frequency(numbers, 3);
        System.out.println("3 present" + " " + count + " " + "times in a given list");


        //find occurrence of each elements in given list
        Map<Integer, Integer> countMap = numbers.stream()
                .collect(Collectors.toMap(
                        number -> number,//key
                        number -> Collections.frequency(numbers, number),//value
                        (existingValue, newValue) -> existingValue));
        System.out.println(countMap);


        //Collections.disjoint()
        List<Integer> list1 = new ArrayList<>();
        Collections.addAll(list1, 1, 2, 3, 4, 5);

        List<Integer> list2 = new ArrayList<>();
        Collections.addAll(list2, 2, 3, 4, 5, 6, 7, 8);
        boolean areDisjoint = Collections.disjoint(list1, list2);
        if (areDisjoint) {
            System.out.println("list have no common element");
        } else {
            System.out.println("list have common element");
        }


        //Collections.singleton
        Set<String> myCollection = Collections.singleton("rabin");

        try {
            myCollection.add("keshav");
        } catch (UnsupportedOperationException e) {
            System.out.println("list is immutable, it can not be modify");
        }


        //Collections.rotate
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
        System.out.println("original list" + list);//[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

        // Collections.rotate(list,3);//[8, 9, 10, 1, 2, 3, 4, 5, 6, 7]
        // Collections.rotate(list,-4);//[5, 6, 7, 8, 9, 10, 1, 2, 3, 4]
        //Collections.rotate(list,list.size());//[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        Collections.rotate(list, -15);//[6, 7, 8, 9, 10, 1, 2, 3, 4, 5]
        System.out.println(list);


    }

}
