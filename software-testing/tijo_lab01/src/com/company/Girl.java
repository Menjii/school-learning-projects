package com.company;

class Girl extends Rose {
    private Flower flower;
    private short age;

    public Flower Flower() {
        return flower;
    }

    public void reciveFlower(Flower flower) {
        this.flower = flower;
    }

    public short Age() {
        return age;
    }

    public void Age(short age) {
        this.age = age;
    }
}