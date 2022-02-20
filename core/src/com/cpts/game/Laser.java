package com.cpts.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

class Laser {

    //position and dimensions
    Rectangle boundingBox;

    //laser physical characteristics
    float movementSpeed; //world units per second

    //graphics
    TextureRegion textureRegion;

    public Laser(LaserBuilder builder) {
        this.movementSpeed = builder.movementSpeed;
        this.textureRegion = builder.textureRegion;
        this.boundingBox = new Rectangle(builder.xCentre - builder.width / 2, builder.yBottom, builder.width, builder.height);

    }
    // render laser
    public void draw(Batch batch) {
        batch.draw(textureRegion, boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
    }

    public static class LaserBuilder {
        Rectangle boundingBox;

        //laser physical characteristics
        float movementSpeed; //world units per second
        float xCentre, yBottom;
        float width, height;

        public LaserBuilder setxCentre(float x) {
            this.xCentre = x;
            return this;
        }

        public LaserBuilder setyBottom(float y) {
            this.yBottom = y;
            return this;
        }

        public LaserBuilder setWidth(float width) {
            this.width = width;
            return this;
        }

        public LaserBuilder setHeight(float height) {
            this.height = height;
            return this;
        }

        public LaserBuilder setMovementSpeed(float movementSpeed) {
            this.movementSpeed = movementSpeed;
            return this;
        }

        public LaserBuilder setTextureRegion(TextureRegion textureRegion) {
            this.textureRegion = textureRegion;
            return this;
        }

        //graphics
        TextureRegion textureRegion;

        public Laser builder() {
            return new Laser(this);
        }
    }
}