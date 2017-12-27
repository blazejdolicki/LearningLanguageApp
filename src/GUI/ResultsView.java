package GUI;

import Backend.Exercise;
import Backend.FileReader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sun.awt.image.ImageWatched;

import java.io.FileNotFoundException;
import java.util.LinkedHashMap;

public class ResultsView extends GridPane{
    public ResultsView(String translatedLanguage, String inputLanguageString, LinkedHashMap<String, String> words, TextField[] textFields){
        Main.getStage().setWidth(600);


        CustomLabel mistakes = new CustomLabel("Incorrect answers");
        mistakes.setFont(Font.font("Verdana", FontWeight.BOLD, mistakes.getFontSize()));
        mistakes.setTextFill(Color.RED);
        add(mistakes,1,0);


        CustomLabel correctAnswers = new CustomLabel("Correct answers");
        correctAnswers.setFont(Font.font("Verdana", FontWeight.BOLD, correctAnswers.getFontSize()));
        correctAnswers.setTextFill(Color.GREEN);
        add(correctAnswers,2,0);

        for(int i=1;i<=words.size();i++){
            String outputText = (String)words.keySet().toArray()[i-1];
            CustomLabel wordOutput = new CustomLabel(outputText+"\t\t");

            add(wordOutput,0,i);

        }

        FileReader fileReader = new FileReader();
        CustomLabel[][] resultsLabels = Exercise.results(inputLanguageString, words, textFields);
        for(int i = 0;i<resultsLabels.length;i++){
            for(int j = 0; j<resultsLabels[0].length;j++){
                System.out.println(i+1+" + "+(j+1));
                add(resultsLabels[i][j], i+1,j+1);

            }
        }


        CustomLabel cluesLabel = new CustomLabel();
        cluesLabel.setText("You used "+Exercise.getNumberOfClues()+" clues.");
        add(cluesLabel,1,words.size()+2);
        System.out.println(words.size());

        CustomLabel summaryLabel = new CustomLabel();
        summaryLabel.setText("Correct answers: "+Exercise.getCorrectCounter()+"/"+words.size()+" - "+Exercise.getPercent()+"%");
        add(summaryLabel,1,words.size()+3);

        Button submit = new Button("Submit");
        submit.setMinSize(150, 50);
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                ExerciseView newExerciseView = new ExerciseView(words.size(), translatedLanguage, inputLanguageString);
                Main.getStage().setScene(new Scene(newExerciseView));
            }
        });
        add(submit, 1, words.size()+4);



        setAlignment(Pos.CENTER);

        setHalignment(submit, HPos.CENTER);
        setVgap(20);
        setHgap(10);
    }



}