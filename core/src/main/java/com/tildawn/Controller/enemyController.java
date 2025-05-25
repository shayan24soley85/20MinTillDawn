package com.tildawn.Controller;

import com.badlogic.gdx.Gdx;
import com.tildawn.Model.enemy.Enemy;
import com.tildawn.Model.enemy.Tree;

import java.util.ArrayList;

public class enemyController {
    private float spawnCheckElder=0f;
    private float spawnCheckBat=0f;
    private float spawnCheckSimple=0f;
    private boolean treeSpawned=false;
    private ArrayList<Enemy> allMapEnemies=new ArrayList<>();
    public int randomInt(int min, int max) {
        return (int)(Math.random() * (max - min) + min);
    }
     public void update(float deltaTime) {
          if(!treeSpawned){
              treeSpawned=true;
              int numberOfTrees=0;
              do {
                  allMapEnemies.add(new Tree(randomInt(0, Gdx.graphics.getWidth()), randomInt(0, Gdx.graphics.getHeight())));
                  numberOfTrees++;
              } while (numberOfTrees < 50);

          }
     }

    public float getSpawnCheckElder() {
        return spawnCheckElder;
    }

    public void setSpawnCheckElder(float spawnCheckElder) {
        this.spawnCheckElder = spawnCheckElder;
    }

    public float getSpawnCheckBat() {
        return spawnCheckBat;
    }

    public void setSpawnCheckBat(float spawnCheckBat) {
        this.spawnCheckBat = spawnCheckBat;
    }

    public float getSpawnCheckSimple() {
        return spawnCheckSimple;
    }

    public void setSpawnCheckSimple(float spawnCheckSimple) {
        this.spawnCheckSimple = spawnCheckSimple;
    }

    public boolean isTreeSpawned() {
        return treeSpawned;
    }

    public void setTreeSpawned(boolean treeSpawned) {
        this.treeSpawned = treeSpawned;
    }

    public ArrayList<Enemy> getAllMapEnemies() {
        return allMapEnemies;
    }

    public void setAllMapEnemies(ArrayList<Enemy> allMapEnemies) {
        this.allMapEnemies = allMapEnemies;
    }
}
