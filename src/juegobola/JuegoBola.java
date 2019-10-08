/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegobola;

import com.sun.javafx.perf.PerformanceTracker;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JuegoBola extends Application {

    public static double ballSpeed = 1;
    public static double ballSpeed2 = 1;

    @Override
    public void start(Stage primaryStage) {

        Group pane = new Group();

        Circle ball = new Circle(10);
        ball.setTranslateX(300 * 0.5);
        ball.setTranslateY(250 * 0.5);

        pane.getChildren().addAll(ball);

        // Etiqueta que mostrará el valor de frames por segundo (FPS) 
        Label label = new Label();
        label.setTranslateX(10);
        label.setTranslateY(10);

        pane.getChildren().addAll(label);
        Scene scene = new Scene(pane, 300, 250);

        //Escuchador a incluir en el bucle de Timeline
        EventHandler<ActionEvent> eH = e -> {
            double variableX;
            double variableY;
            // Mostrar la frecuencia de refresco FPS 
            PerformanceTracker perfTracker = PerformanceTracker.getSceneTracker(scene);
            label.setText("FPS (Timeline) = " + perfTracker.getInstantFPS());

            variableX=ball.getTranslateX();
            variableY=ball.getTranslateY();
            

            
            // Cambiar la dirección de la bola si llega a los extremos 
            if ((variableX < 0 || variableX > 300)) {
                ballSpeed *= -1;
            }
            else if(variableY < 0 || variableY > 250)
            {
                ballSpeed2 *= -1;
            }
            

            ball.setTranslateX(ball.getTranslateX() + ballSpeed);
            ball.setTranslateY(ball.getTranslateY() + ballSpeed2);
        };

        //Definimos el bucle con la duración, cada 5 milisegundos que son aproximadamente 60 FPS 
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(5), eH));
        animation.setCycleCount(Timeline.INDEFINITE);

        // iniciamos animation
        animation.play();

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
