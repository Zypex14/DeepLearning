package com.zypex.learn.Game;

import com.almasb.fxgl.entity.Entity;

public class Enemy {

    private Entity self;
    private double d;

    public Enemy(Entity self, double tx, double ty) {
        this.self = self;
//        Sets the initial direction the enemy will be in
        d = Math.atan2(tx - self.getX(), ty - self.getY());
        System.out.println(Math.toDegrees(d));
    }

    public void update() {
        self.translateX(Math.sin(d) * 5);
        self.translateY(Math.cos(d) * 5);
    }

    public void delete() {
        self.removeFromWorld();
    }

}
