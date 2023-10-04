package org.example;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) {
        Developer dev1 = new Developer("Наташа", Arrays.asList("Java", "C++"));
        Developer dev2 = new Developer("Эрнест", Arrays.asList("Java", "Python"));
        Developer dev3 = new Developer("Элла", Arrays.asList("C#","Python"));
        Developer dev4 = new Developer("Никита", Arrays.asList("Ruby","Java"));
        Developer dev5 = new Developer("Анастасия", Arrays.asList("JavaScript","Python"));
        Stream<Developer> developerStream = Stream.of(dev1, dev2, dev3,dev4,dev5);
        Map<String, List<String>> languageMap = developerStream
                .flatMap(dev -> dev.getLanguages().stream().map(lang -> new AbstractMap.SimpleEntry<>(lang, dev.getName())))
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
        System.out.println(languageMap);
        List<String> uniqueDevelopers = languageMap.values().stream()
                .filter(strings -> strings.size() == 1)
                .map(strings -> strings.get(0))
                .distinct()
                .collect(Collectors.toList());

        System.out.println(uniqueDevelopers);
 
    }
}