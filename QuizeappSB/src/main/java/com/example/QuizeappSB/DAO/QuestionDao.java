package com.example.QuizeappSB.DAO;

import com.example.QuizeappSB.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {
    public List<Question> findBycategory(String category);
    @Query("SELECT q FROM Question q WHERE q.category = :category ORDER BY RAND() LIMIT :numQues")
    List<Question> getRandomQuestionByCategory(String category, int numQues);



}
