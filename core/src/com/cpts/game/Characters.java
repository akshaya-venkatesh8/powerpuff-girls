package com.cpts.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

abstract class Characters {

    // ship characteristics
    float movementSpeed; // world units per second
    int shield;

    // position & dimension
    Rectangle boundingBox;

    // laser information
    float laserWidth, laserHeight;
    float laserMovementSpeed;
    float timeBetweenShots;
    float timeSinceLastShot = 0;

    // graphics
    TextureRegion shipTextureRegion, shieldTextureRegion, laserTextureRegion;

    public Characters(CharacterBuilder<?> builder) {
        this.movementSpeed = builder.movementSpeed;
        this.shield = builder.shield;

        this.boundingBox = new Rectangle(builder.x - builder.width / 2, builder.y - builder.height / 2,
                builder.width, builder.height);
        this.shipTextureRegion = builder.shipTextureRegion;
        this.shieldTextureRegion = builder.shieldTextureRegion;
        // laser attributes
        this.laserWidth = builder.laserWidth;
        this.laserHeight = builder.laserHeight;
        this.timeBetweenShots = builder.timeBetweenShots;
        this.laserMovementSpeed = builder.laserMovementSpeed;
        this.laserTextureRegion = builder.laserTextureRegion;
    }

    public void update(float deltaTime) {
        timeSinceLastShot += deltaTime;
    }

    public boolean canFireLaser() {
        return (timeSinceLastShot - timeBetweenShots >= 0);
    }

    public abstract Laser[] fireLasers();

    public boolean intersects(Rectangle otherRectangle) {
        return boundingBox.overlaps(otherRectangle);
    }

    public boolean hitAndCheckDestroyed(Laser laser) {
        if (shield > 0) {
            shield--;
            return false;
        }
        return true;
    }

    public void translate(float xChange, float yChange) {
        boundingBox.setPosition(boundingBox.x + xChange, boundingBox.y + yChange);
    }

    public void draw(Batch batch) {
        batch.draw(shipTextureRegion, boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
        if (shield > 0) {
            batch.draw(shieldTextureRegion, boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
        }
    }

    abstract static class CharacterBuilder<T extends CharacterBuilder<T>> {
        float movementSpeed; // world units per second
        int shield;

        // laser information
        float laserWidth, laserHeight;
        float laserMovementSpeed;
        float timeBetweenShots;

        TextureRegion shipTextureRegion, shieldTextureRegion, laserTextureRegion;
        float x;
        float y;
        float width;
        float height;

        public T setX(float x) {
            this.x = x;
            return self();
        }

        public T setY(float y) {
            this.y = y;
            return self();
        }

        public T setWidth(float width) {
            this.width = width;
            return self();
        }

        public T setHeight(float height) {
            this.height = height;
            return self();
        }

        public T setMovementSpeed(float movementSpeed) {
            this.movementSpeed = movementSpeed;
            return self();
        }

        public T setShield(int shield) {
            this.shield = shield;
            return self();
        }

        public T setLaserWidth(float laserWidth) {
            this.laserWidth = laserWidth;
            return self();
        }

        public T setLaserHeight(float laserHeight) {
            this.laserHeight = laserHeight;
            return self();
        }

        public T setLaserMovementSpeed(float laserMovementSpeed) {
            this.laserMovementSpeed = laserMovementSpeed;
            return self();
        }

        public T setTimeBetweenShots(float timeBetweenShots) {
            this.timeBetweenShots = timeBetweenShots;
            return self();
        }

        public T setShipTextureRegion(TextureRegion shipTextureRegion) {
            this.shipTextureRegion = shipTextureRegion;
            return self();
        }

        public T setShieldTextureRegion(TextureRegion shieldTextureRegion) {
            this.shieldTextureRegion = shieldTextureRegion;
            return self();
        }

        public T setLaserTextureRegion(TextureRegion laserTextureRegion) {
            this.laserTextureRegion = laserTextureRegion;
            return self();
        }

        protected abstract T self();

        abstract Characters build();
    }
}
