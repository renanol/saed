/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpb.saed;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author Erick Costa e Renan Oliveira
 */
public class Celula extends Group{
    
    public static final int DIMENSAO = 50; 
    
    private Text value = null;
    private Text id = null;
    private int v;
    private Point2D pb;
    private Button btn;
    private Label lblImg;
    
    
    public Celula(Point2D p, int i){

        pb = p;
               
        Image image = new Image(getClass().getResourceAsStream("box.png"));      
        lblImg = new Label();
        lblImg.setLayoutX(p.getX());
        lblImg.setLayoutY(p.getY());
        lblImg.setGraphic(new ImageView(image));
       
        
        value = new Text(p.getX() + Celula.DIMENSAO/2 - 12, p.getY() + Celula.DIMENSAO/2 + 4, "-");
        value.setFont(new Font(25));
        value.setFill(Color.GOLD);
       
        id = new Text(p.getX() + Celula.DIMENSAO/2, p.getY() + Celula.DIMENSAO + 20, String.valueOf(i));
        v = i;
        
        getChildren().addAll(lblImg, value, id);
    }
    
    
    public void setValue(int va){
        
        this.value.setText(String.valueOf(va));
        this.v = va;
        
        FadeTransition ft = new FadeTransition(Duration.millis(1000), this.lblImg);
        ft.setFromValue(0.1);
        ft.setToValue(1.0);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
        
    }
    
    public int getValue(){
        return this.v;
    }
    
    public void Repintar(){
        this.value.setFill(Color.BLUE);
    }
    
    public void moveLeft(EventHandler<ActionEvent> value){
        TranslateTransition translateTransition =
        new TranslateTransition(Duration.millis(5000), this);
        translateTransition.setByX(-10);
        translateTransition.setByY(100);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(false);
        
        if(value != null) {
            translateTransition.setOnFinished(value);
        }
        
        translateTransition.play();
        
        pb = new Point2D(pb.getX() - 10, pb.getY() + 100);
    }
    
    public void moveRight(EventHandler<ActionEvent> value){
        TranslateTransition translateTransition =
        new TranslateTransition(Duration.millis(5000), this);
        translateTransition.setByX(10);
        translateTransition.setByY(100);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(false);
        
        if(value != null) {
            translateTransition.setOnFinished(value);
        }
        
        translateTransition.play();
        
        pb = new Point2D(pb.getX() + 10, pb.getY() + 100);
    }
    
    public void movePonto(Point2D p){
        
        TranslateTransition translateTransition =
        new TranslateTransition(Duration.millis(2000), this);
        translateTransition.setByX(p.getX() - pb.getX());
        translateTransition.setByY(p.getY() - pb.getY());
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(false);
        translateTransition.play();        
        pb = new Point2D(p.getX(), p.getY());
        
    }
    
    public void alteraCor(){
        
        this.value.setFill(Color.WHITE);
        RotateTransition rotateTransition =
        new RotateTransition(Duration.millis(2000), this);
        rotateTransition.setByAngle(360f);
        rotateTransition.setCycleCount(2);
        rotateTransition.setAutoReverse(true);
        rotateTransition.play();
    
    
    }
    
    public void ZoomPonto( Point2D p){
        this.movePonto(p);
    }        
}

