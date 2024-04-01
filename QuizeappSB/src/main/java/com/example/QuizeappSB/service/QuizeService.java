package com.example.QuizeappSB.service;

import com.example.QuizeappSB.DAO.QuestionDao;
import com.example.QuizeappSB.DAO.QuizeDao;
import com.example.QuizeappSB.Model.Question;
import com.example.QuizeappSB.Model.QuestionWrapper;
import com.example.QuizeappSB.Model.Quize;
import com.example.QuizeappSB.Model.Responsee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizeService {
    @Autowired
    QuizeDao quizeDao;

@Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuize(String category, int numQues, String tittle) {
        List<Question> questions=questionDao.getRandomQuestionByCategory(category,numQues);
        Quize quize=new Quize();
        quize.setTittle(tittle);
        quize.setQuestions(questions);
        quizeDao.save(quize);
        return  new ResponseEntity<>("sucess", HttpStatus.CREATED);

    }


//    public ResponseEntity<List<QuestionWrapper>> getQuizeQuestions(Integer id) {
//        Optional<Quize> quize=quizeDao.findById(id);
//        List<Question> questionsFeomDb=quize.get().getQuestions();
//        List<QuestionWrapper> questionWrappers=new ArrayList<>();
//        for (Question q:questionsFeomDb)
//        {
//            QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
//            questionWrappers.add(qw);
//
//        }
//        return new ResponseEntity<>(questionWrappers,HttpStatus.OK);
//    }
public ResponseEntity<List<QuestionWrapper>> getQuizeQuestions(Integer id) {
    Optional<Quize> quizeOptional = quizeDao.findById(id);
    if (quizeOptional.isPresent()) {
        Quize quize = quizeOptional.get();
        List<Question> questionsFromDb = quize.getQuestions();
        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        for (Question q : questionsFromDb) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionWrappers.add(qw);
        }
        return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
    } else {
        // Handle case where Quize with the given id does not exist
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

    public ResponseEntity<Integer> calculateResult(Integer id, List<Responsee> responsees) {
        Quize quize=quizeDao.findById(id).get();
        int right=0;
        int i=0;
        List<Question> questions=quize.getQuestions();
        for (Responsee responsee:responsees)
        {
            if (responsee.getResponsees().equals(questions.get(i).getRightAnswer()))
                right++;
            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
