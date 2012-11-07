/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpb.saed;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Renan Oliveira e Erick Costa
 */
public class Main extends Application {

    private AnimationTimer animationTimer;
    private int i = 0;
    private int j = 1;
    private long time = 0;
    private int meio;
    private Vetor vetor = null;
    private Vetor vsub = null;
    private TextField txtNumeroOrdenar;
    private Label checked, lblDigiteNumero, caption, unChecked, bg, mensagens, start, apresentacao, valorP;
    private Group root;
    private Button btnBuscar;
    private Rectangle r1, r2;

    private Image imgError, inicio;
    private Button btnBuscaBinaria;
    private Button btnCreditos;
    private Button btnInstrucoes;
    private Button btnGerarVetor;
    private Celula celula;
    private FadeTransition fadeTransition;
    private TranslateTransition translateTransition;
    private HBox hbox;
    private boolean isVetorGerado;

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    @Override
    public void start(Stage primaryStage) {
        
        //Inicializando variáveis
        isVetorGerado = false;
        
        primaryStage.setTitle("Animação - Pesquisa Binária v 1.0");
        root = new Group();
        final Scene scene = new Scene(root, 800, 700, Color.ANTIQUEWHITE);

        this.vetor = new Vetor(new Point2D(200, 140), 10);

        //Animacao do vetor e ordena
        animationTimer = new AnimationTimer() {

            @Override
            public void handle(long l) {
                long diff = l - time;
                
                if (diff < 100000000) {
                    return;
                } else {
                    time = 1;
                }

                if (vetor.getValue(i) > vetor.getValue(j)) {
                    int aux = vetor.getValue(i);
                    vetor.setValue(i, vetor.getValue(j));
                    vetor.setValue(j, aux);
                }

                if (j == 9) {
                    ++i;
                    if (i == 9) {
                        i = j = 0;
                        this.stop();
                    } else {
                        j = i;
                    }
                } else {
                    ++j;
                }
            }
        };



        //Mensagem Inicial
        inicio = new Image(getClass().getResourceAsStream("start.png"));
        start = new Label();
        start.setLayoutX(0);
        start.setLayoutY(0);
        start.setGraphic(new ImageView(inicio));
        //start.setOpacity(0.8);

        r2 = new Rectangle(520, 450);
        r2.setLayoutX(150);
        r2.setFill(Color.WHITE);
        r2.setLayoutY(100);
        r2.setArcHeight(30);
        r2.setArcWidth(30);

        fadeTransition = new FadeTransition(Duration.millis(2000), start);
        fadeTransition.setFromValue(0.8);
        fadeTransition.setToValue(0.0);

        translateTransition = new TranslateTransition(Duration.millis(2000), start);
        translateTransition.setFromX(50);
        translateTransition.setToX(1001);
     
        //Titulo
        caption = new Label();
        caption.setLayoutX(250);
        caption.setStyle("-fx-text-fill: rgb(49, 89, 23);");
        caption.setLayoutY(0);
        caption.setCache(true);
        caption.setText("Animação - Busca Binária");
        caption.setFont(Font.font("null", FontWeight.BOLD, 25));


        Reflection r = new Reflection();
        r.setFraction(0.9);
        caption.setEffect(r);
        caption.setTranslateY(8);

        //Botão Buscar
        Image image = new Image(getClass().getResourceAsStream("search.png"));
        btnBuscar = new Button("Buscar");
        btnBuscar.setLayoutX(546);
        btnBuscar.setLayoutY(70);
        btnBuscar.setMinSize(80, 40);
        btnBuscar.setFont(new Font(20));
        btnBuscar.setTooltip(new Tooltip("Realiza uma pesquisa binária"));
        btnBuscar.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        btnBuscar.setGraphic(new ImageView(image));


        //Gerar Vetor
        Image image2 = new Image(getClass().getResourceAsStream("plus.png"));
        btnGerarVetor = new Button();
        btnGerarVetor.setLayoutX(90);
        btnGerarVetor.setLayoutY(70);
        btnGerarVetor.setMinSize(80, 40);
        btnGerarVetor.setFont(new Font(20));
        btnGerarVetor.setText(" Gerar Vetor ");
        btnGerarVetor.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        btnGerarVetor.setTooltip(new Tooltip("Gera um vetor"));
        btnGerarVetor.setGraphic(new ImageView(image2));

        //Bontão iniciar
        Image imgInicial = new Image(getClass().getResourceAsStream("startImg.png"));
        btnBuscaBinaria = new Button("Busca Binária");
        btnBuscaBinaria.setStyle("-fx-font: 18 arial; -fx-base: #CCC;");
        btnBuscaBinaria.setLayoutX(310);
        btnBuscaBinaria.setLayoutY(280);
        btnBuscaBinaria.setPrefWidth(180);
        
        btnInstrucoes = new Button("Instruções");
        btnInstrucoes.setStyle("-fx-font: 18 arial; -fx-base: #CCC;");
        btnInstrucoes.setLayoutX(310);
        btnInstrucoes.setLayoutY(330);
        btnInstrucoes.setPrefWidth(180);
        
        Image imgBtn = new Image(getClass().getResourceAsStream("startImg.png"));
        btnCreditos = new Button("Créditos");
        btnCreditos.setStyle("-fx-font: 18 arial; -fx-base: #CCC;");
        btnCreditos.setLayoutX(310);
        btnCreditos.setLayoutY(380);
        btnCreditos.setPrefWidth(180);

        //Caixa de entrada        
        txtNumeroOrdenar = new TextField();
        txtNumeroOrdenar.setLayoutX(420);
        txtNumeroOrdenar.setLayoutY(80);
        txtNumeroOrdenar.setPrefWidth(100);
        txtNumeroOrdenar.setPrefHeight(30);
        
        
        //Mensagem da entrada
        lblDigiteNumero = new Label("Digite o número: ");
        lblDigiteNumero.setStyle("-fx-font: 12 tahoma; -fx-text-fill: #ff001b;");
        lblDigiteNumero.setLayoutX(420);
        lblDigiteNumero.setLayoutY(65);
        
        //hbox.getChildren().addAll(label2, text1);

        //Imagem Checked
        Image imgChecked = new Image(getClass().getResourceAsStream("checked.png"));
        checked = new Label();
        checked.setGraphic(new ImageView(imgChecked));
        checked.setLayoutX(570);
        checked.setLayoutY(610);

        //Imagem unChecked
        Image imgUnchecked = new Image(getClass().getResourceAsStream("unchecked.png"));
        unChecked = new Label();
        unChecked.setGraphic(new ImageView(imgUnchecked));
        unChecked.setLayoutX(570);
        unChecked.setLayoutY(610);

        //BackGround
        Image imgBackground = new Image(getClass().getResourceAsStream("bg.jpg"));
        bg = new Label();
        bg.setGraphic(new ImageView(imgBackground));

        //Retangulo de mensagens
        r1 = new Rectangle(300, 120);
        r1.setLayoutY(550);
        r1.setLayoutX(20);
        r1.setArcHeight(20);
        r1.setArcWidth(20);
        r1.setFill(Color.GREEN);
        r1.setOpacity(0.40);

        //Box de Mensagens

        mensagens = new Label("Algumas infomações aparecerá\naqui, sobre  a  execução  do\n Algoritmo ou erros");
        mensagens.setStyle("-fx-font: 20 tahoma;-fx-text-fill: #ff001b;");
        mensagens.setLayoutY(560);
        mensagens.setLayoutX(25);

        //Mensagem de Apresentação

        Image imgApresentacao = new Image(getClass().getResourceAsStream("apresentacao.png"));
        apresentacao = new Label();
        apresentacao.setLayoutX(150);
        apresentacao.setLayoutY(100);
        apresentacao.setGraphic(new ImageView(imgApresentacao));

        //imagem erro
        imgError = new Image(getClass().getResourceAsStream("error.png"));

        //Mensagem para o valor

        valorP = new Label("Buscar esse valor");
        valorP.setStyle("-fx-font: 15 tahoma;-fx-text-fill: #ff001b;");
        valorP.setLayoutX(500);
        valorP.setLayoutY(570);
        
        //EVENTOS
        
        btnInstrucoes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                
                
                
            }
        });
        
        btnCreditos.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                root.getChildren().remove(start);
                root.getChildren().add(apresentacao);
            
            }
        });


        //Ação que remove a introdução
        btnBuscaBinaria.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                fadeTransition.play();
                translateTransition.play();
                removerBotoesMenu(root);
                root.getChildren().remove(r2);
                root.getChildren().remove(apresentacao);

            }
        });


        //Eventos do botão pesquisar
        btnBuscar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                
                vsub = vetor;

                if (!(txtNumeroOrdenar.getText().length() > 2 || 
                        txtNumeroOrdenar.getText() == null || 
                            txtNumeroOrdenar.getText().equals(""))) {

                    int valor = Integer.parseInt(txtNumeroOrdenar.getText());
                    celula = new Celula(new Point2D(500, 600), valor);
                    celula.setValue(valor);
                    root.getChildren().add(celula);
                    mover(valor);
                    root.getChildren().add(valorP);  
                    //root.getChildren().remove(btnGerarVetor);

                    removerBotoesBusca();


                } else {
                    
                    criarMensagem(root, "Caracteres Inválidos", new ImageView(imgError));

                }
                
               

            }
        });

        //Eventos do botão gerar aleatório
        btnGerarVetor.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                isVetorGerado = true;
                removerBotoesBusca();
                root.getChildren().remove(checked);
                root.getChildren().remove(unChecked);
                root.getChildren().remove(valorP);
                root.getChildren().remove(start);
                root.getChildren().remove(celula);
                root.getChildren().remove(mensagens);
                mensagens.setText("O vetor foi gerado\nRandomicamente!");
                root.getChildren().add(mensagens);

                vetor.redesenharvetor();
                
                vsub = vetor;

                for (int i = 0; i < vetor.celulas.length; ++i) {
                    vetor.setValue(i, ((int) (Math.random() * 100)) % 100);
                }

                animationTimer.start();
                root.getChildren().remove(vetor);
                root.getChildren().add(vetor);
                
                adicionarBotoesBusca();
                




            }
        });


        //Adicionando controles no stage

        root.getChildren().add(bg);
        root.getChildren().add(btnGerarVetor);

        root.getChildren().add(caption);
        root.getChildren().add(mensagens);
        root.getChildren().add(r1); 
        
        root.getChildren().add(r2);
        root.getChildren().add(start);
      
        adicionarBotoesMenu(root);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void criarMensagem(Group root, String mensagem, ImageView imageView){
        
        root.getChildren().remove(mensagens);
        mensagens.setText(mensagem);
        mensagens.setStyle("-fx-font: 22 arial;-fx-text-fill: #ff001b;");
        if(imageView != null){
            mensagens.setGraphic(imageView);
        }
        root.getChildren().add(mensagens);
    
    }
    
    public void adicionarBotoesBusca(){    
        root.getChildren().add(lblDigiteNumero);
        root.getChildren().add(btnBuscar);
        root.getChildren().add(txtNumeroOrdenar);
    }
    
    public void removerBotoesBusca(){    
        root.getChildren().remove(lblDigiteNumero);
        root.getChildren().remove(btnBuscar);
        root.getChildren().remove(txtNumeroOrdenar);
    }
    
    
    public void adicionarBotoesMenu(Group root){
        
        root.getChildren().add(btnBuscaBinaria);
        root.getChildren().add(btnInstrucoes);
        root.getChildren().add(btnCreditos);
        
    }
    
    public void removerBotoesMenu(Group root){
        root.getChildren().remove(btnBuscaBinaria);
        root.getChildren().remove(btnCreditos);
        root.getChildren().remove(btnInstrucoes);
    }

    public void mover(final int valor) {
        
        EventHandler<ActionEvent> escutador = new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                mover(valor);
            }
        };

        meio = (vsub.celulas.length) / 2;
        boolean achou = false;

        if (vsub.getValue(meio) == valor) {

            vsub.movePonto(meio);
            vsub.alteraCor(meio);
            root.getChildren().remove(mensagens);
            achou = true;
            mensagens.setStyle("-fx-font: 20 tahoma;-fx-text-fill: #ff001b;");
            mensagens.setText("Calculou o meio do vetor que é\nComparou com o valor\ne localizou");
            root.getChildren().add(mensagens);
            animationChecked();
            root.getChildren().add(btnGerarVetor);

        } else if (vsub.getValue(meio) > valor) {
            root.getChildren().remove(mensagens);
            vsub.alteraCor(meio);
            vsub = vsub.getSubVetor(0, meio);
            vsub.moveLeft(escutador);
            mensagens.setText("Calculou o meio do vetor\ncomparou\nO Valor é menor que o meio");
            root.getChildren().add(mensagens);


        } else if (vsub.getValue(meio) < valor) {
            root.getChildren().remove(mensagens);
            vsub.alteraCor(meio);
            vsub = vsub.getSubVetor(meio + 1, vsub.celulas.length);
            vsub.moveRight(escutador);
            mensagens.setText("Calculou o meio do vetor\ncomparou\nO Valor é maior que o meio");
            root.getChildren().add(mensagens);

        }

        //esse calculo esta incorreto....
        if (!achou) {
            root.getChildren().remove(mensagens);
            mensagens.setText("O valor não foi encontrado.");
            root.getChildren().add(mensagens);
            animationUnChecked();
        }
        
    }

    //Animação se houve igualdade na busca
    public void animationChecked() {


        root.getChildren().add(checked);

        RotateTransition rotateTransition =
                new RotateTransition(Duration.millis(2000), checked);
        rotateTransition.setByAngle(360f);
        rotateTransition.setCycleCount(2);
        rotateTransition.setAutoReverse(true);
        rotateTransition.play();

    }

    //Animação se houve desigualdade na busca
    public void animationUnChecked() {
        
        root.getChildren().remove(unChecked);
        root.getChildren().add(unChecked);
        RotateTransition rotateTransition =
                new RotateTransition(Duration.millis(2000), unChecked);
        rotateTransition.setByAngle(360f);
        rotateTransition.setCycleCount(2);
        rotateTransition.setAutoReverse(true);
        rotateTransition.play();



    }
}
