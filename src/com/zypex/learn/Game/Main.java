package com.zypex.learn.Game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.settings.GameSettings;
import javafx.scene.Cursor;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class Main extends GameApplication {

    private ArrayList<Enemy> enemies;
    private Entity player;
    private Player p;
    private boolean keyW, keyS, keyD, keyA;
    private Random R;

    //    Launches the game
    public static void main(String args) {
        launch(args);
    }

    protected void initSettings(GameSettings s) {
        s.setTitle("Deep learning");
        s.setVersion("");
    }

    protected void initInput() {
        Input input = getInput();

        input.addAction(new UserAction("Up") {
            @Override
            protected void onActionBegin() {
                keyW = true;
            }

            protected void onActionEnd() {
                keyW = false;
            }
        }, KeyCode.W);

        input.addAction(new UserAction("Down") {
            @Override
            protected void onActionBegin() {
                keyS = true;
            }

            protected void onActionEnd() {
                keyS = false;
            }
        }, KeyCode.S);

        input.addAction(new UserAction("Right") {
            @Override
            protected void onActionBegin() {
                keyD = true;
            }

            protected void onActionEnd() {
                keyD = false;
            }
        }, KeyCode.D);

        input.addAction(new UserAction("Left") {
            @Override
            protected void onActionBegin() {
                keyA = true;
            }

            protected void onActionEnd() {
                keyA = false;
            }
        }, KeyCode.A);

    }

    //    Will run when game starts
    protected void initGame() {
//        Creates a random object
        R = new Random();

//        Puts all the enemies into an array list so we can keep track of them
        enemies = new ArrayList<>();

//        This will be the main character, which the AI will control
        player =
                Entities.builder()
                        .viewFromNodeWithBBox(new Rectangle(15, 15))
                        .type(EntityType.PLAYER)
                        .with(new CollidableComponent(true))
                        .at(392.5, 292.5)
                        .buildAndAttach(getGameWorld());

//        Sends the player entity to the player class
        p = new Player(player);

//      Sets the cursor to the default cursor
        getGameScene().getRoot().setCursor(Cursor.DISAPPEAR);
    }

    protected void initPhysics() {
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.ENEMY) {

            // order of types is the same as passed into the constructor
            @Override
            protected void onCollisionBegin(Entity player, Entity enemy){
                exit();
            }
        });
    }

    //    Main loop
    protected void onUpdate(double tpf) {
        p.update(
                keyW,
                keyS,
                keyD,
                keyA
        );

        for (Enemy e : enemies) {
            e.update();
        }

        if (R.nextInt(50) == 0) {

                switch (R.nextInt(4) + 1) {
                    case 1:
                        createEnemy(R.nextInt(800), -10);
                        break;
                    case 2:
                        createEnemy(810, R.nextInt(600));
                        break;
                    case 3:
                        createEnemy(R.nextInt(800), 610);
                        break;
                    case 4:
                        createEnemy(-10, R.nextInt(600));
                        break;
            }
        }
    }

    //    This is the function to create an enemy
    public void createEnemy(double x, double y) {

        enemies.add(
                new Enemy(
                        Entities.builder()
                                .viewFromNodeWithBBox(new Circle(5))
                                .with(new CollidableComponent(true))
                                .type(EntityType.ENEMY)
                                .at(x, y)
                                .buildAndAttach(getGameWorld())
                , player.getX(), player.getY())
        );

    }


    private enum EntityType {
        ENEMY, PLAYER
    }

}
