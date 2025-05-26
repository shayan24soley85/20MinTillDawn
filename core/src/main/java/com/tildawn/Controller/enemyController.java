package com.tildawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.tildawn.Main;
import com.tildawn.Model.Character;
import com.tildawn.Model.enemy.*;

import java.util.ArrayList;
import java.util.Iterator;

public class enemyController {
    private float spawnCheckElder=0f;
    private float spawnCheckBat=0f;
    private float spawnCheckSimple=0f;
    private boolean treeSpawned=false;
    private float allTime=0f;
    private boolean bossStarted=false;
    private boolean bossDropped=false;
    private float bossTime=0f;
    private ArrayList<Enemy> allMapEnemies=new ArrayList<>();
    public int randomInt(int min, int max) {
        return (int)(Math.random() * (max - min) + min);
    }
     public void update(float deltaTime) {
        // in bakhsh baa komk <<arshia arab behjat>> zade shod
         allTime=Main.getMain().getApp().getCurrentGame().getGameView().getElapsedSeconds();

         int range = 8000;
         int range2=600;
         Character player= Main.getMain().getApp().getCurrentGame().getCharacter();
         int px = (int) player.getPosX();
         int py = (int) player.getPosY();

          if(!treeSpawned){
              treeSpawned=true;
              int numberOfTrees=0;
              do {
                  int spawnX = randomInt(px - range, px + range);
                  int spawnY = randomInt(py - range, py + range);
                  allMapEnemies.add(new Tree(spawnX, spawnY));
                  numberOfTrees++;
              } while (numberOfTrees < 200);

          }
         spawnCheckSimple += deltaTime;
         if(spawnCheckSimple>=3){
             spawnCheckSimple=0;
             int number=(int)allTime/30;
             while (number>0){
                 int spawnX = randomInt(px - (range2+900), px + range2+900);
                 int spawnY = randomInt(py - (900+range2), py + range2+900);
                 allMapEnemies.add(new TentacleMonster(spawnX, spawnY));
                 number--;
             }
         }
         spawnCheckBat += deltaTime;
         if (allTime > Main.getMain().getApp().getCurrentGame().getGameTime()*15) {
             if(spawnCheckBat>=10){
                 spawnCheckBat=0;
                 int number=(int)((4*allTime-Main.getMain().getApp().getCurrentGame().getGameTime()*60+30)/30);
                 while (number>0){
                     int spawnX = randomInt(px - (range2+900), px + range2+900);
                     int spawnY = randomInt(py - (900+range2), py + range2+900);
                     allMapEnemies.add(new EyeBat(spawnX, spawnY));
                     number--;
                 }
             }
         }
         if (allTime >(Main.getMain().getApp().getCurrentGame().getGameTime()*30)) {
             System.out.println(allTime);
             System.out.println(Main.getMain().getApp().getCurrentGame().getGameTime());
             bossStarted=true;
         }
         if (bossStarted&&!bossDropped) {
             bossDropped=true;
             int spawnX = randomInt(px - (range2+900), px + range2+900);
             int spawnY = randomInt(py - (900+range2), py + range2+900);
             allMapEnemies.add(new Elder(spawnX, spawnY));
         }
         Iterator<Enemy> iterator = allMapEnemies.iterator();
         while (iterator.hasNext()) {

             Enemy enemy = iterator.next();
             enemy.update(deltaTime, new Vector2(px, py));
             if (!enemy.isAlive()) {
                 iterator.remove();
                 xpDrops drops=new xpDrops(enemy.getPosX(), enemy.getPosY());
                 Main.getMain().getApp().getCurrentGame().getGameView().getController().getDrops().add(drops);
                 drops.getSprite().draw(Main.getBatch());
             }
             if (bossDropped&&(enemy instanceof Elder)) {
                 bossTime+=deltaTime;
                 if (bossTime>=5f){
                     bossTime=0;
                     dashToPlayer(enemy);
                 }
             }

         }
         allMapEnemies.removeIf(enemy -> !enemy.isAlive());
     }
  public void dashToPlayer(Enemy enemy){
        int oldSpeed=enemy.getSpeed();
        enemy.setSpeed(enemy.getSpeed()*10);
      Timer.schedule(new Timer.Task() {
          @Override
          public void run() {
              enemy.setSpeed(oldSpeed);
          }
      }, 3);
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

    public float getAllTime() {
        return allTime;
    }

    public void setAllTime(float allTime) {
        this.allTime = allTime;
    }

    public boolean isBossStarted() {
        return bossStarted;
    }

    public void setBossStarted(boolean bossStarted) {
        this.bossStarted = bossStarted;
    }

    public boolean isBossDropped() {
        return bossDropped;
    }

    public void setBossDropped(boolean bossDropped) {
        this.bossDropped = bossDropped;
    }

    public float getBossTime() {
        return bossTime;
    }

    public void setBossTime(float bossTime) {
        this.bossTime = bossTime;
    }
}
