package com.cpts.game;

class Player extends Characters {

    int lives;

    public Player(CharacterBuilder builder) {
        super(builder);
        lives = 3;
    }

    @Override
    public Laser[] fireLasers() {
        Laser[] laser = new Laser[2];
        laser[0] = new Laser.LaserBuilder().setxCentre(boundingBox.x + boundingBox.width * 0.07f)
                .setyBottom(boundingBox.y + boundingBox.height * 0.45f).setWidth(laserWidth).setHeight(laserHeight)
                .setMovementSpeed(laserMovementSpeed).setTextureRegion(laserTextureRegion).builder();
        laser[1] = new Laser.LaserBuilder().setxCentre(boundingBox.x + boundingBox.width * 0.93f)
                .setyBottom(boundingBox.y + boundingBox.height * 0.45f).setWidth(laserWidth).setHeight(laserHeight)
                .setMovementSpeed(laserMovementSpeed).setTextureRegion(laserTextureRegion).builder();

        timeSinceLastShot = 0;

        return laser;
    }

    public static class CharacterBuilder extends Characters.CharacterBuilder<CharacterBuilder> {
        @Override
        protected CharacterBuilder self() {
            return this;
        }

        @Override
        protected Player build() {
            return new Player(this);
        }
    }
}
