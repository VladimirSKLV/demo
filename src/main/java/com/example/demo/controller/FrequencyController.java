package com.example.demo.controller;

import com.example.demo.model.InputString;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class FrequencyController {

    @PostMapping("/calculate-frequency")
    public Map<String, Map<Character, Integer>> calculateFrequency(@RequestBody InputString input) {
        String str = input.getInputString();
        Map<Character, Integer> frequencyMap = new LinkedHashMap<>();

        for (char c : str.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        //Сортируем по убыванию
        Map<Character, Integer> sortedFrequencyMap = new LinkedHashMap<>();
        frequencyMap
                .entrySet()
                .stream()
                .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
                .forEach(x ->
                        sortedFrequencyMap.put(x.getKey(), x.getValue()));

        Map<String, Map<Character, Integer>> result = new LinkedHashMap<>();
        result.put("result", sortedFrequencyMap);
        return result;
    }
}
