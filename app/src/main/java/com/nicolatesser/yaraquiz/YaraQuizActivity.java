package com.nicolatesser.yaraquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nicolatesser.androidquiztemplate.activity.QuizActivity;
import com.nicolatesser.androidquiztemplate.quiz.Answer;
import com.nicolatesser.androidquiztemplate.quiz.EduGame;
import com.nicolatesser.androidquiztemplate.quiz.Game;
import com.nicolatesser.androidquiztemplate.quiz.GameHolder;
import com.nicolatesser.androidquiztemplate.quiz.Question;
import com.nicolatesser.androidquiztemplate.quiz.QuestionDatabase;
import com.nicolatesser.androidquiztemplate.quiz.Session;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class YaraQuizActivity extends QuizActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        QuestionDatabase questionDatabase = new QuestionDatabase();
        questionDatabase.prepare(loadQuestions());
        Game game = new EduGame(questionDatabase, new ArrayList<>());

        GameHolder.setInstance(game);

        loadSession();
        super.onCreate(savedInstanceState);
    }

    public List<Question> loadQuestions(){
        List<Question> questions = new ArrayList<>();
        questions.add(createQuestion("Pferde sind", "Tiere", "Menschen", "Pflanzen"));
        questions.add(createQuestion("Füchse sind ", "schlau", "dumm", "vergesslich"));
        questions.add(createQuestion("Menschen haben", "Haare", "Mähne", "Fell"));
        questions.add(createQuestion("In der Schule wird", "gelernt", "gespielt", "Quatsch gemacht"));
        questions.add(createQuestion("Tennis spielt man mit", "Schlägern", "Löffeln", "Lollis"));
        questions.add(createQuestion("Wäschbäre sind", "gefräßig", "blau", "langsam"));
        questions.add(createQuestion("Koalas sind", "schläfrig", "verärgert", "schnell"));
        questions.add(createQuestion("14-8", "6", "5", "7", "8"));
        return questions;
    }


    public Question createQuestion(String questionText, String correctAnswer, String...wrongAnswers){
        List<Answer> answers = new LinkedList<Answer>();
        answers.add(new Answer(correctAnswer, true));
        for (String wrongAnswer:wrongAnswers){
            answers.add(new Answer(wrongAnswer, false));
        }
        return new Question(questionText, answers, new ArrayList<>());
    }


    public Question createMathQuestion(){

        Random rn = new Random();
        int sub1 = rn.nextInt() % 20;
        int sub2 = rn.nextInt() % 10;
        int res = sub1-sub2;


        List<Answer> answers = new LinkedList<Answer>();


        answers.add(new Answer(Integer.toString(res), true));
        for (int i=1;i<4;i++){
            int delta = rn.nextInt() % 2 +1;
            if (rn.nextBoolean()){
                answers.add(new Answer(Integer.toString(res+delta), false));

            }
            else {
                answers.add(new Answer(Integer.toString(res-delta), false));

            }
        }
        return new Question(sub1+"-"+sub2, answers, new ArrayList<>());
    }
}