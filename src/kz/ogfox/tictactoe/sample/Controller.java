package kz.ogfox.tictactoe.sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    public Button btn1;
    @FXML
    private  Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private  Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private  Button btn6;
    @FXML
    private Button btn7;
    @FXML
    private  Button btn8;
    @FXML
    private Button btn9;

    boolean turn = true;
    boolean isWinner = false;
    int turnCounter = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void actionButton(ActionEvent event) {
        //System.out.println(((Control) event.getSource()).getId());

            if (turn) {
                ((Button) event.getSource()).setText("X");
            } else {
                ((Button) event.getSource()).setText("O");
            }
        turnCounter++;
        checkWinner();
        turn = !turn;
        ((Button) event.getSource()).setDisable(true);

        if(!turn && turnCounter !=9) {
            aiMakeMove();
        }
    }

    private boolean checkWinner() {
        /*vertical*/
        if ((btn1.getText() == btn4.getText()) && (btn4.getText() == btn7.getText()) &&(btn1.isDisable())) {
            isWinner = true;
        }
        else if ((btn2.getText() == btn5.getText()) && (btn5.getText() == btn8.getText())  && (btn2.isDisable())) {
            isWinner = true;
        }
        else if((btn3.getText() == btn6.getText()) && (btn6.getText() == btn9.getText()) && (btn3.isDisable())) {
            isWinner = true;
        }
        /*horizontal*/
        if((btn1.getText() == btn2.getText()) && (btn2.getText() == btn3.getText()) && (btn1.isDisable())) {
            isWinner = true;
        }
        if((btn4.getText() == btn5.getText()) && (btn5.getText() == btn6.getText()) &&(btn4.isDisable())) {
            isWinner = true;
        }
        if((btn7.getText() == btn8.getText()) && (btn8.getText() == btn9.getText()) && (btn7.isDisable())) {
            isWinner = true;
        }
        /*diagonal*/
        if((btn1.getText() == btn5.getText()) && (btn5.getText() == btn9.getText()) && (btn1.isDisable())) {
            isWinner = true;
        }
        if((btn3.getText() == btn5.getText()) &&(btn5.getText() == btn7.getText()) && (btn3.isDisable())) {
            isWinner = true;
        }

        if(isWinner) {
            if(turn) {
                System.out.println("Winner is X");
                disableButtons();
            }
            else {
                System.out.println("Winner is O");
                disableButtons();
            }
        }
        else {
            if(turnCounter == 9) {
                System.out.println("Draw");
                disableButtons();
            }
        }

        return isWinner;
    }

    private void disableButtons() {
        List<Button> list = Arrays.asList(btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9);
        list.forEach(i -> i.setDisable(true) );
    }


    private void aiMakeMove() {
        Button move = null;

        move = winOrBlock("0");
        if(move == null) {
            move = winOrBlock("X");
            if(move == null) {
                move = lookCorner();
                if(move == null){
                    move = lookOpenSpace();
                }
            }
        }
        move.fire();
    }



    private Button lookCorner() {
        if(btn1.getText() == "O") {
            if(btn3.getText() == "") {
                return btn3;
            }
            if(btn7.getText() == "") {
                return btn7;
            }
            if(btn9.getText() == "") {
                return  btn9;
            }
        }

        if(btn3.getText() == "O") {
            if(btn1.getText() == "") {
                return btn1;
            }
            if(btn7.getText() == "") {
                return btn7;
            }
            if(btn9.getText() == "") {
                return btn9;
            }
        }

        if(btn7.getText() == "O") {
           if(btn1.getText() == "") {
               return btn1;
           }
           if(btn3.getText() == "") {
               return  btn3;
           }
           if(btn9.getText() == "") {
                return btn9;
           }
        }

        if(btn9.getText() == "O") {
            if(btn1.getText() == "") {
                return btn1;
            }
            if(btn3.getText() == "") {
                return btn3;
            }
            if(btn7.getText() == "") {
                return btn7;
            }
        }
        return null;
    }

    private Button lookOpenSpace() {
        Button bt = null;
        List<Button> list = Arrays.asList(btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9);
        for(Button i : list) {
            bt = i;
            if(bt != null) {
                if(bt.getText() == "") {
                    return i;
                }
            }
        }
        return null;
    }

    private Button winOrBlock(String mark) {
        /*brute force algorithm*/
        /*horizontal*/
        /*1st row*/
        if((btn1.getText() == mark) && (btn2.getText() == mark) && (btn3.getText() == "")) {
            return btn3;
        }
        if((btn1.getText() == mark) && (btn3.getText() == mark) && (btn2.getText() == "") ) {
            return btn2;
        }
        if((btn2.getText() == mark) && (btn3.getText() == mark) && (btn1.getText() == "")) {
            return  btn1;
        }
        /*2nd row*/
        if((btn4.getText() == mark) && (btn5.getText() == mark) && (btn6.getText() == "")) {
            return btn6;
        }
        if((btn4.getText() == mark) && (btn6.getText()== mark) && (btn5.getText() == "")) {
            return btn5;
        }
        if((btn5.getText() == mark) &&(btn6.getText() == mark) && (btn4.getText() == "")) {
            return btn4;
        }
        /*3rd row*/
        if((btn7.getText() == mark) && (btn8.getText() == mark) &&(btn9.getText() == "")) {
            return btn9;
        }
        if((btn7.getText() == mark) && (btn9.getText() == mark) && (btn8.getText() == "")) {
            return btn8;
        }
        if((btn9.getText() == mark) && (btn8.getText() == mark) && (btn7.getText() == "")) {
            return btn7;
        }
        /*vertical*/
        /*1st cols*/
        if((btn1.getText() == mark) && (btn4.getText() == mark) && (btn7.getText() == "")) {
            return btn7;
        }
        if((btn1.getText() == mark) && (btn7.getText() == mark) && (btn4.getText() == "")) {
            return btn4;
        }
        if((btn4.getText() == mark) && (btn7.getText() == mark) && (btn1.getText() == "")) {
            return btn1;
        }
        /*2nd cols*/
        if((btn2.getText() == mark) &&(btn5.getText() == mark) && (btn8.getText() == "")) {
            return btn8;
        }
        if((btn2.getText() == mark) && (btn8.getText() == mark) && (btn5.getText() == "")) {
            return btn5;
        }
        if((btn8.getText() == mark) && (btn5.getText() == mark) && (btn2.getText() == "")) {
            return btn2;
        }
        /*3rd cols*/
        if((btn3.getText() == mark) && (btn6.getText() == mark) && (btn9.getText() == "")) {
            return btn9;
        }
        if((btn3.getText() == mark) && (btn9.getText() == mark) &&(btn6.getText() == "")) {
            return btn6;
        }
        if((btn9.getText() == mark) && (btn6.getText() == mark) && (btn3.getText() == "")) {
            return btn3;
        }
        /*main and adverse diagonal*/
        /*main*/
        if((btn1.getText() == mark) &&(btn5.getText() == mark)&& (btn9.getText() == "")) {
            return btn9;
        }
        if((btn1.getText() == mark) && (btn9.getText() == mark) && (btn5.getText() == "")) {
            return btn5;
        }
        if((btn9.getText() == mark) && (btn5.getText() == mark) && (btn1.getText() == "")) {
            return btn1;
        }
        /*adverse*/
        if((btn3.getText() == mark) && (btn5.getText() == mark) && (btn7.getText() == "")) {
            return btn7;
        }
        if((btn3.getText() == mark) && (btn7.getText() == mark) && (btn5.getText() == "")) {
            return btn5;
        }
        if((btn7.getText() == mark) && (btn5.getText() == mark) && (btn3.getText() == "")) {
            return btn3;
        }
        return null;
    }

    public  void playAgain() {
        List<Button> list = Arrays.asList(btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9);
        list.forEach(i -> {
            System.out.println(i);
            i.setText("");
            i.setDisable(false);
        });
        turnCounter = 0;
        isWinner = false;
        turn = true;
    }


}
