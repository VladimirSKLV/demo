package com.example.demo.controller;

import com.example.demo.model.InputString;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class FrequencyController {

    /**
     * FrequencyController - это класс контроллер Spring MVC, который обрабатывает POST-запросы на эндпоинт "/calculate-frequency".
     */


    /**
     * Метод calculateFrequency принимает объект InputString в качестве параметра, который содержит входную строку.
     * Этот объект извлекается из тела запроса с помощью аннотации @RequestBody.
     * @param input входная строка
     * @return результат в формате JSON
     */
    @PostMapping("/calculate-frequency")
    public Map<String, Map<Character, Integer>> calculateFrequency(@RequestBody InputString input) {
        String str = input.getInputString();

        // Здесь происходит подсчет частоты вхождений каждого символа во входной строке с использованием Map<Character, Integer>.
        Map<Character, Integer> frequencyMap = new LinkedHashMap<>();

        for (char c : str.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        //После того как частота каждого символа подсчитана, происходит сортировка полученной Map по убыванию количества вхождений символа
        Map<Character, Integer> sortedFrequencyMap = new LinkedHashMap<>();
        frequencyMap
                .entrySet()
                .stream()
                .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
                .forEach(x ->
                        sortedFrequencyMap.put(x.getKey(), x.getValue()));

        // Результат представляется в виде Map<String, Map<Character, Integer>>, где ключ "result" содержит отсортированную Map частот символов.
        Map<String, Map<Character, Integer>> result = new LinkedHashMap<>();
        result.put("result", sortedFrequencyMap);
        return result;
    }
}
