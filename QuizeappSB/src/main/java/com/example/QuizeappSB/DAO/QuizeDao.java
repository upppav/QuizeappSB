package com.example.QuizeappSB.DAO;

import com.example.QuizeappSB.Model.Question;
import com.example.QuizeappSB.Model.Quize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuizeDao extends JpaRepository<Quize, Integer> {



}
