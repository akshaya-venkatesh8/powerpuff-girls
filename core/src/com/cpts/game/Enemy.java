package com.cpts.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

class Enemy extends Characters {

    Vector2 directionVector;
    float timeSinceLastDirectionChange = 0;
    float directionChangeFrequency = 0.75f;


    public Enemy(CharacterBuilder builder) {
        super(builder);
        directionVector = new Vector2(0, -1);
    }

    public Vector2 getDirectionVector() {
        return directionVector;
    }

    private void randomizeDirectionVector() {
        double bearing = PowerPuffGirls.random.nextDouble() * 6.283185; //0 to 2*PI
        directionVector.x = (float) Math.sin(bearing);
        directionVector.y = (float) Math.cos(bearing);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        timeSinceLastDirectionChange += deltaTime;
        if (timeSinceLastDirectionChange > directionChangeFrequency) {
            randomizeDirectionVector();
            timeSinceLastDirectionChange -= directionChangeFrequency;
        }
    }

    @Override
    public Laser[] fireLasers() {
        Laser[] laser = new Laser[2];
        laser[0] = new Laser.LaserBuilder().setxCentre(boundingBox.x + boundingBox.width * 0.18f).
                setyBottom(boundingBox.y - laserHeight).
                setWidth(laserWidth).setHeight(laserHeight).
                setMovementSpeed(laserMovementSpeed).
                setTextureRegion(laserTextureRegion).builder();
        laser[1] = new Laser.LaserBuilder().setxCentre(boundingBox.x + boundingBox.width * 0.82f).
                setyBottom(boundingBox.y - laserHeight).
                setWidth(laserWidth).setHeight(laserHeight).
                setMovementSpeed(laserMovementSpeed).
                setTextureRegion(laserTextureRegion).builder();

        timeSinceLastShot = 0;

        return laser;
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(shipTextureRegion, boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
        if (shield > 0) {
            batch.draw(shieldTextureRegion, boundingBox.x, boundingBox.y - boundingBox.height * 0.2f, boundingBox.width, boundingBox.height);
        }
    }

    public static class CharacterBuilder extends Characters.CharacterBuilder<CharacterBuilder> {
        @Override
        protected CharacterBuilder self() {
            return this;
        }

        @Override
        protected Enemy build() {
            return new Enemy(this);
        }
    }
}