package kz.ogfox.tictactoe.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Control;


import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    private Button btn1;
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

    boolean turn = false;
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
            turn = !turn;
            turnCounter++;
        ((Button) event.getSource()).setDisable(true);
        checkWinner();
        //System.out.println(turnCounter);

        }

    public boolean checkWinner() {

        verticalCheck();
        horizontalCheck();
        diagonalCheck();

        if(isWinner) {
            if(!turn) {
                System.out.println("Winner is X");
            }
            else {
                System.out.println("Winner is O");
            }
        }
        else {
            if(turnCounter == 9) {
                System.out.println("Draw");
            }
        }
        return isWinner;
    }

    public boolean verticalCheck() {
        if ((btn1.getText() == btn4.getText()) && (btn4.getText() == btn7.getText()) &&(btn1.isDisable())) {
            isWinner = true;
        }
        else if ((btn2.getText() == btn5.getText()) && (btn5.getText() == btn8.getText())  && (btn2.isDisable())) {
            isWinner = true;
        }
        else if((btn3.getText() == btn6.getText()) && (btn6.getText() == btn9.getText()) && (btn3.isDisable())) {
            isWinner = true;
        }
        return isWinner;
    }

    public boolean horizontalCheck() {
        if((btn1.getText() == btn2.getText()) && (btn2.getText() == btn3.getText()) && (btn1.isDisable())) {
            isWinner = true;
        }
        if((btn4.getText() == btn5.getText()) && (btn5.getText() == btn6.getText()) &&(btn4.isDisable())) {
            isWinner = true;
        }
        if((btn7.getText() == btn8.getText()) && (btn8.getText() == btn9.getText()) && (btn7.isDisable())) {
            isWinner = true;
        }
        return isWinner;
    }
    public boolean diagonalCheck() {
        if((btn1.getText() == btn5.getText()) && (btn5.getText() == btn9.getText()) && (btn1.isDisable())) {
            isWinner = true;
        }
        if((btn3.getText() == btn5.getText()) &&(btn5.getText() == btn7.getText()) && (btn3.isDisable())) {
            isWinner = true;
        }
        return isWinner;
    }

    public  void playAgain() {
        List<Button> list = Arrays.asList(btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9);
        list.forEach(i -> {
            System.out.println(i);
            i.setText("");
            i.setDisable(false);
        });

    }

}
