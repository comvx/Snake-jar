/**
 * author (Max Ole Kleffmann ~ null)
 * version 2.0
 * date (11th of January 2019)
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Sketch implements ActionListener, KeyListener {
    public static Sketch sketch;
    public JFrame jFrame;
    public boolean gameStatus = true;
    public EntityHandler entityHandler = new EntityHandler();
    public Random random = new Random();
    public int cols, rows, scl = 0, delay = 90, total, width, height, countDelay;
    public Timer timer = new Timer(delay, this);
    public ArrayList<Point> snakeParts;

    public Sketch() {
        jFrame = new JFrame("Snake - game");
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(entityHandler);
        jFrame.setResizable(false);
        jFrame.setSize(600, 600);
        jFrame.addKeyListener(this);
        jFrame.setBackground(new Color(52, 52, 52));

        if (gameStatus) {
            startGrame();
            timer.start();
        }
    }

    public static void main(String[] args) {
        sketch = new Sketch();
    }

    private void startGrame() {
        scl = 40;
        total = 0;

        width = 600;
        height = 600;

        cols = width / scl;
        rows = height / scl;

        int xRandom = random.nextInt(cols - 1);
        int yRandom = random.nextInt(rows - 1);

        entityHandler.xFood = (xRandom * scl);
        entityHandler.yFood = (yRandom * scl);

        countDelay = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameStatus) {
            snakeParts = new ArrayList<Point>(entityHandler.snakeParts);

            total = entityHandler.total;

            Point foodLocation = new Point(entityHandler.xFood, entityHandler.yFood);
            Point snakeHeadLocation = new Point(entityHandler.x, entityHandler.y);


            if (foodLocation.x == snakeHeadLocation.x && foodLocation.y == snakeHeadLocation.y) {
                jFrame.setTitle("Snake - game  SCORE: " + (entityHandler.score + 1));

                int xRandom = random.nextInt(cols - 1);
                int yRandom = random.nextInt(rows - 1);

                entityHandler.setNewFoodLocation();
                entityHandler.score += 1;
                total++;
                delay -= (entityHandler.score * 8);
                timer = new Timer(delay, this);
                entityHandler.eatAddPart(snakeHeadLocation);
            }
            if (total > 0) {
                for (int i = 0; i < 1; i++) {
                    entityHandler.snakeParts.set(i, snakeHeadLocation);
                }
                for (int i = 1; i < total; i++) {
                    entityHandler.snakeParts.set(i, snakeParts.get(i - 1));
                }
            }

            entityHandler.checkCollision(snakeHeadLocation.x, snakeHeadLocation.y);

            entityHandler.repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (entityHandler.gameStatus) {
            if (key == KeyEvent.VK_UP) {
                if (!entityHandler.BOTTOM) {
                    entityHandler.BOTTOM = false;
                    entityHandler.TOP = true;
                    entityHandler.LEFT = false;
                    entityHandler.RIGHT = false;
                    entityHandler.setDirSnake(0, -1);
                }
            } else if (key == KeyEvent.VK_DOWN) {
                if (!entityHandler.TOP) {
                    entityHandler.TOP = false;
                    entityHandler.BOTTOM = true;
                    entityHandler.LEFT = false;
                    entityHandler.RIGHT = false;
                    entityHandler.setDirSnake(0, 1);
                }
            } else if (key == KeyEvent.VK_RIGHT) {
                if (!entityHandler.LEFT) {
                    entityHandler.RIGHT = true;
                    entityHandler.BOTTOM = false;
                    entityHandler.LEFT = false;
                    entityHandler.TOP = false;
                    entityHandler.setDirSnake(1, 0);
                }
            } else if (key == KeyEvent.VK_LEFT) {
                if (!entityHandler.RIGHT) {
                    entityHandler.LEFT = true;
                    entityHandler.RIGHT = false;
                    entityHandler.BOTTOM = false;
                    entityHandler.TOP = false;
                    entityHandler.setDirSnake(-1, 0);
                }
            }else if(key == KeyEvent.VK_A){
                if (!entityHandler.RIGHT2) {
                    entityHandler.LEFT2 = true;
                    entityHandler.RIGHT2 = false;
                    entityHandler.BOTTOM2 = false;
                    entityHandler.TOP2 = false;
                    entityHandler.setDirSnake2(-1, 0);
                }
            }
            else if(key == KeyEvent.VK_W){
                if (!entityHandler.TOP2) {
                    entityHandler.LEFT2 = false;
                    entityHandler.RIGHT2 = false;
                    entityHandler.BOTTOM2 = false;
                    entityHandler.TOP2 = true;
                    entityHandler.setDirSnake2(0, -1);
                }
            }
            else if(key == KeyEvent.VK_S){
                if (!entityHandler.RIGHT2) {
                    entityHandler.LEFT2 = false;
                    entityHandler.RIGHT2 = false;
                    entityHandler.BOTTOM2 = true;
                    entityHandler.TOP2 = false;
                    entityHandler.setDirSnake2(0, 1);
                }
            }
            else if(key == KeyEvent.VK_D){
                if (!entityHandler.RIGHT2) {
                    entityHandler.LEFT2 = false;
                    entityHandler.RIGHT2 = true;
                    entityHandler.BOTTOM2 = false;
                    entityHandler.TOP2 = false;
                    entityHandler.setDirSnake2(1, 0);
                }
            }
        } else {
            if (key == KeyEvent.VK_ENTER) {
                entityHandler.reloadGame();
            }
            if (key == KeyEvent.VK_BACK_SPACE) {
                JOptionPane.showMessageDialog(null, "See ya \n ~~~~~~~~~~ \n by: \n comvx ~ Max Ole Kleffmann \n MLGenie ~ Noah Spreen", "Snake - game", JOptionPane.CLOSED_OPTION);
                System.exit(0);
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}