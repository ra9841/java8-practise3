package org.example.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JavaStreamFeatures {

    public static void main(String[] args) {

        //1.Stream.ofNullable
        List<String> names = Arrays.asList("rabin", "sabin", "nabin", null, "bikash", null, "sam", "kabin", null);

        List<String> result = names.stream()
                .filter(name -> name != null)
                .collect(Collectors.toList());
        System.out.println(result);

        //other way
        List<String> results = names.stream()
                .flatMap(Stream::ofNullable)
                .collect(Collectors.toList());
        System.out.println(results);


        //2.Stream.iterate() (get infinite value)(we can use this to create sequence of number in pattern)
        Stream.iterate(0, n -> n + 2)
                .limit(5)       //to stop the infinite limit
                .forEach(System.out::println);


        //3.Collectors.collectingAndThen
        List<Employee> employee = Arrays.asList(
                new Employee("rabin", 5000),
                new Employee("sabin", 2000),
                new Employee("kabin", 3000),
                new Employee("resham", 6000)
        );
        //calculate average salary and round up to nearest integer
        Long employeeResult = employee.stream()
                .mapToDouble(Employee::getSalary)
                .boxed()
                .collect(Collectors.collectingAndThen(
                        Collectors.averagingDouble(Double::doubleValue), //1st collectors (after getting the value i want to round up the integer eg.4000.0
                        Math::round
                ));
        System.out.println(employeeResult);

        //without Collectors.collectingAndThen
        Double employeeResults = employee.stream()
                .mapToDouble(Employee::getSalary)
                .boxed()
                .collect((
                        Collectors.averagingDouble(Double::doubleValue) //1st collectors (after getting the value i want to round up the integer eg.4000.0

                ));
        System.out.println(employeeResults);


        //4.Stream.takeWhile and Stream.dropWhile (take the element under certain condition and drop the element under certain condition)
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> numb = numbers.stream()
                .takeWhile(num -> num < 5)
                .collect(Collectors.toList());
        System.out.println(numb);

        List<Integer> numbs = numbers.stream()
                .dropWhile(num -> num < 5)
                .collect(Collectors.toList());
        System.out.println(numbs);

        List<Integer> numb1 = numbers.stream()
                .dropWhile(num -> num < 3)
                .takeWhile(num -> num < 7)
                .collect(Collectors.toList());
        System.out.println(numb1);


        //5.Collectors.teeing() (to run different collectors parallel and after that we can perform any operation)
        //1-> min
        //2-> max
        //map
        List<Integer> numbers1 = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Map<String, Integer> minMaxMap = numbers1.stream()
                .collect(Collectors.teeing(
                        Collectors.maxBy(Integer::compareTo),//1st collector
                        Collectors.minBy(Integer::compareTo),//2nd collector
                        (e1, e2) -> Map.of("max", e1.get(), "min", e2.get())
                ));
        System.out.println(minMaxMap);


        //6.Stream.concat() (adding number of  stream)
        Stream<Integer> stream1 = Stream.of(1, 2, 3);
        Stream<Integer> stream2 = Stream.of(4, 5, 6);
        Stream<Integer> concatStream = Stream.concat(stream1, stream2);
        int sum = concatStream.mapToInt(Integer::intValue)
                .sum();
        System.out.println(sum);


        //7.Collectors.partitioningBy
        List<Integer> numbers2 = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Map<Boolean, List<Integer>> map = numbers2.stream() //if using partioningBy there will be map in which key will be always boolean
                .collect(Collectors.partitioningBy(num -> num % 2 == 0));
        System.out.println(map);
        System.out.println("Odd number : " + map.get(Boolean.FALSE));
        System.out.println("Even number : " + map.get(Boolean.TRUE));


        //8.IntStream for Ranges
        List<Integer> intStream = IntStream.range(1, 20)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(intStream);

        List<Integer> intStream1 = IntStream.rangeClosed(1,20)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(intStream1);


    }
}
