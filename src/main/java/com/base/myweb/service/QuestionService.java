package com.base.myweb.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Service
public interface QuestionService {

    void addNote(Model model, HttpSession session);
}
