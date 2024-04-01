package com.example.QuizeappSB.controller;

import com.example.QuizeappSB.Model.Question;
import com.example.QuizeappSB.Model.QuestionWrapper;
import com.example.QuizeappSB.Model.Responsee;
import com.example.QuizeappSB.service.QuizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Url")
public class QuizeController {
    @Autowired
    QuizeService quizeService;
    @PostMapping ("create")
    public ResponseEntity<String> createQuize(@RequestParam String category , @RequestParam int numQues ,  @RequestParam String tittle)
    {
        return quizeService.createQuize(category,numQues,tittle);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id)
    {
      return quizeService.getQuizeQuestions(id);
    }
   @PostMapping("submit/{id}")
    public ResponseEntity<Integer> ubmitQuize(@PathVariable Integer id, @RequestBody List<Responsee> responsees)
   {
return quizeService.calculateResult(id,responsees);
   }
}
