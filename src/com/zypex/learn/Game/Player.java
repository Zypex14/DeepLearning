package com.zypex.learn.Game;

import com.almasb.fxgl.entity.Entity;

public class Player {

    private Entity self;
    private boolean up, down, right, left;
    private double xVel, yVel;

    public Player(Entity self) {
//        This will have the player be controlled from this class
        this.self = self;

//        Sets all variables to default
        up = false;
        down = false;
        right = false;
        left = false;

        xVel = 0;
        yVel = 0;
    }

    public void update(boolean up, boolean down, boolean right, boolean left) {

//        Stores all the input booleans to the class for later  use
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;

//        Assigns the inputs to their jobs
        if (up) {
            yVel -= 1;
        }

        if (down) {
            yVel += 1;
        }

        if (right) {
            xVel += 1;
        }

        if (left) {
            xVel -= 1;
        }

//        The changing of position
        self.translateX(xVel);
        self.translateY(yVel);

//        Collision for the box boundaries
        if(self.getX() < 0){
            xVel = 0;
            self.setX(0);
        }

        if(self.getRightX() > 800){
            xVel = 0;
            self.setX(800 - 15);
        }

        if(self.getY() < 0){
            yVel = 0;
            self.setY(0);
        }

        if(self.getBottomY() > 600){
            yVel = 0;
            self.setY(600 - 15);
        }

//        Friction for velocity
        xVel = xVel * 0.9;
        yVel = yVel * 0.9;

    }

}
