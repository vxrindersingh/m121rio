package com.example.m12;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import static javafx.scene.input.KeyCode.*;

public class HelloApplication extends Application {
    private Group root;
    private Scene scene;
    private int dx = 10, dy = 410;
    private ImageView mario, mariol;
    private ImageView br;
    private final int bx = 300;
    private final int by = 350;

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        root = new Group();
        Random random=new Random();
        int tmp = random.nextInt(2) + 1;
        System.out.println(tmp);
        if (tmp==1)
        {
            ImageView wall = new ImageView(new Image(getClass().getResourceAsStream("wall.png")));
            wall.setFitHeight(500);
            wall.setFitWidth(500);
            wall.setLayoutX(0);
            wall.setLayoutY(0);
            root.getChildren().add(wall);
        }else{
            ImageView wall = new ImageView(new Image(getClass().getResourceAsStream("mw1.png")));
            wall.setFitHeight(500);
            wall.setFitWidth(500);
            wall.setLayoutX(0);
            wall.setLayoutY(0);
            root.getChildren().add(wall);
        }



        mario = new ImageView(new Image(getClass().getResourceAsStream("m3.png")));
        root.getChildren().add(mario);
        br = new ImageView(new Image(getClass().getResourceAsStream("bricks.png")));
        root.getChildren().add(br);

        mario.setFitHeight(40);
        mario.setFitWidth(30);
        mario.setRotate(0);
        mario.setLayoutX(dx);
        mario.setLayoutY(dy);

        mariol = new ImageView(new Image(getClass().getResourceAsStream("m3yflip.png")));
        mariol.setFitHeight(40);
        mariol.setFitWidth(30);
        mariol.setLayoutX(dx);
        mariol.setLayoutY(dy);

        br.setFitHeight(30);
        br.setFitWidth(90);
        br.setLayoutX(bx);
        br.setLayoutY(by);



        scene = new Scene(root, 500, 500);
        gameloop();
        primaryStage.setTitle("mario");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void gameloop() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {

                scene.setOnKeyPressed(e ->
                {

                    if (e.getCode() == LEFT)
                    {
                        mariol.setLayoutX(dx - 80);
                        mariol.setLayoutY(dy);
                        mariol.setLayoutX(dx -= 10);

                        root.getChildren().remove(mario);
                        root.getChildren().add(mariol);

                    }
                    if (e.getCode() == RIGHT)
                    {
                        mario.setLayoutX(dx += 10);
                        mario.setLayoutY(dy);

                        root.getChildren().remove(mariol);
                        root.getChildren().add(mario);
                    }
                    if (e.getCode() == SPACE)
                    {
                        if ((dx >= bx - 20 && dx < bx + 100)|| (dy == by - 40))
                        {

                            mario.setLayoutY(dy -= 20);
                            mariol.setLayoutY(dy -= 20);
                        } else
                        {
                            mario.setLayoutY(dy -= 90);
                            mariol.setLayoutY(dy -= 90);
                        }
                    }

                });

                if ((dx >= bx - 20 && dx < bx + 100) && (dy == by - 40))
                {
                    dy = by - 40;
                    mario.setLayoutY(dy);
                    mariol.setLayoutY(dy);

                } else if (dy != 410)
                {
                    mario.setLayoutY(dy += 2);
                    mariol.setLayoutY(dy += 2);
                }

            }
        }.start();
    }
}