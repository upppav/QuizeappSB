package com.example.QuizeappSB.service;

import com.example.QuizeappSB.DAO.QuestionDao;
import com.example.QuizeappSB.Model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

@Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<Question>>allQuestions() {
        return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<List<Question>> getallQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findBycategory(category), HttpStatus.OK);
        }
        catch (Exception e)
        {
             e.printStackTrace();
        }
        return new ResponseEntity<>(questionDao.findBycategory(category), HttpStatus.BAD_REQUEST);
    }

    public String addQuestion(Question question) {
         questionDao.save(question);
        return "sucess";
    }

    public String  deleteQuestion(Integer id) {
        questionDao.deleteById(id);
        return "the fallowing question with id :"+ id+" is deleted sucessfully !.";
    }

    public String updateQuestion(Question question) {
        questionDao.save(question);
        return "sucess";
    }
}
