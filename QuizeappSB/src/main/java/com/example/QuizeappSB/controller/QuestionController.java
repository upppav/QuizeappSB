package com.example.QuizeappSB.controller;

import com.example.QuizeappSB.Model.Question;
import com.example.QuizeappSB.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("qestions")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getallQuestions()
    {
        return questionService.allQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>>getallQuestionsByCategory(@PathVariable String category)
    {
        return questionService.getallQuestionsByCategory(category);
    }

    @PostMapping("add")
    public String addQuestion(@RequestBody Question question)
    {
        return questionService.addQuestion(question);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteQuestion(@PathVariable Integer id)
    {
        questionService.deleteQuestion(id);
    }

    @PutMapping("update")
    public String updateQuestion(@RequestBody Question question)
    {
        return questionService.updateQuestion(question);
    }
}
