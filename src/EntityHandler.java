/**
 * author (Max Ole Kleffmann ~ null)
 * version 2.0
 * date (11th of January 2019)
*/

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class EntityHandler extends JPanel {
    public Sketch sketch = Sketch.sketch;

    public int x, y, xspeed, yspeed, width, height;
    public int x2, y2;
    public int scl;
    public int xFood, yFood, score, total, cols, rows;
    public boolean deathStatus = false, gameStatus = true;
    public ArrayList<Point> snakeParts = new ArrayList<Point>();
    public ArrayList<Point> snakePart2 = new ArrayList<Point>();

    public boolean RIGHT = true, LEFT = false, TOP = false, BOTTOM = false;
    public boolean RIGHT2 = true, LEFT2 = false, TOP2 = false, BOTTOM2 = false;

    public String getPathJar = null;

    public EntityHandler() {
        width = 600;
        height = 600;

        x = 0;
        y = 40;

        x = 0;
        y = 40;

        xspeed = 1;
        yspeed = 0;

        scl = 40;

        xFood = 0;
        yFood = 0;

        score = 0;

        total = 0;

        cols = width / scl;
        rows = height / scl;

        getPathJar = new String(new File(".").getAbsolutePath());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        initGame(g);
        initScore(g);

        if (gameStatus) {
            x = x + xspeed * scl;
            y = y + yspeed * scl;

            x2 = x2 + xspeed * scl;
            y2 = y2 + yspeed * scl;

            paintHeadSnake(g);
            paintFood(g);
            paintSnakePart(g);
        }
        stopGameDeath(g);
    }

    private void initGame(Graphics g) { //snake_background.jpg.png
        try {
            BufferedImage image = ImageIO.read(new File(getPathJar + "//Images//snake_background.jpg.png"));
            g.drawImage(image, 0, 40, width, height, null);
            g.setColor(new Color(87, 138, 52));
            g.fillRect(0,0, width, 40);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //g.setColor(new Color(170, 215, 81));
        //g.fillRect(0, 0, width, height);
    }

    private void initScore(Graphics g) {
        try{
            BufferedImage image = ImageIO.read(new File(getPathJar + "//Images//apple_score.png"));
            g.drawImage(image, (width/2)-80, 4, scl-5, scl-5, null);
        }catch (Exception e){
            e.printStackTrace();
        }

            g.setColor(Color.WHITE);
            g.drawString("Score:" + score, (width/2)-40, 26);
    }

    private void paintHeadSnake(Graphics g) {
        try {
            BufferedImage image = null;
            if (RIGHT) {
                image = ImageIO.read(new File(getPathJar + "//Images//snake_head_right.jpg.png"));
            } else if (LEFT) {
                image = ImageIO.read(new File(getPathJar + "//Images//snake_head_left.jpg.png"));
            } else if (TOP) {
                image = ImageIO.read(new File(getPathJar + "//Images//snake_head_top.jpg.png"));
            } else if (BOTTOM) {
                image = ImageIO.read(new File(getPathJar + "//Images//snake_head_botton.jpg.png"));
            }
            //image = ImageIO.read(new File("C:\\Users\\maxol\\OneDrive\\Desktop\\snake_head.jpg.png"));
            g.drawImage(image, x, y, scl, scl, null);
            g.drawImage(image, x2, y2, scl, scl, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //g.setColor(new Color(112, 128, 144));
        //g.fillRect(x, y, scl, scl);
    }

    private void paintSnakePart(Graphics g) {
        for (int i = 0; i < snakeParts.size(); i++) {
            try {
                BufferedImage image = ImageIO.read(new File(getPathJar + "//Images//snake_parts.jpg.png"));
                g.drawImage(image, snakeParts.get(i).x, snakeParts.get(i).y, scl, scl, null); //40 38363424
            } catch (Exception e) {
                e.printStackTrace();
            }
            //g.setColor(Color.WHITE);
            //g.fillRect(snakeParts.get(i).x, snakeParts.get(i).y, scl, scl);
        }
        for (int i = 0; i < snakePart2.size(); i++) {
            try {
                BufferedImage image = ImageIO.read(new File(getPathJar + "//Images//snake_parts.jpg.png"));
                g.drawImage(image, snakePart2.get(i).x, snakePart2.get(i).y, scl, scl, null); //40 38363424
            } catch (Exception e) {
                e.printStackTrace();
            }
            //g.setColor(Color.WHITE);
            //g.fillRect(snakeParts.get(i).x, snakeParts.get(i).y, scl, scl);
        }
    }

    private void stopGameDeath(Graphics g) {
        if (deathStatus) {
            gameStatus = false;
            try {
                BufferedImage image = ImageIO.read(new File(getPathJar + "//Images//Untitled-2.png"));
                g.drawImage(image, 0, 0, 600, 600, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void reloadGame() {
        if (deathStatus) {
            deathStatus = false;
            gameStatus = true;
        }
    }

    private void paintFood(Graphics g) {
        /*Random random = new Random();
        int colorValue = 0;
        colorValue = random.nextInt(6);
        if (colorValue == 0) {
        } else if (colorValue == 1) {
            g.setColor(Color.magenta);
        } else if (colorValue == 2) {
            g.setColor(Color.green);
        } else if (colorValue == 3) {
            g.setColor(Color.yellow);
        } else if (colorValue == 4) {
            g.setColor(Color.ORANGE);
        } else if (colorValue == 5) {
            g.setColor(Color.CYAN);
        }
        g.fillRect(xFood, yFood, scl, scl);*/

        try {
            BufferedImage image = ImageIO.read(new File(getPathJar + "//Images//apple.png"));
            g.drawImage(image, xFood, yFood, scl, scl, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDirSnake(int x, int y) {
        xspeed = x;
        yspeed = y;
    }
    public void setDirSnake2(int x, int y) {
        xspeed = x;
        yspeed = y;
    }

    public void setNewFoodLocation() {
        try {
            Random random = new Random();
            ArrayList<Point> copySnakeParts = new ArrayList<Point>(snakeParts);
            int xRandom = 0, yRandom = 0;

            if (snakeParts.size() == 0 || snakeParts.size() == 1) {
                xRandom = random.nextInt(cols - 1);
                yRandom = random.nextInt(rows - 1);
                xRandom *= scl;
                yRandom *= scl;
                if(yRandom == 0){
                    setNewFoodLocation();
                    return;
                }

                xFood = xRandom;
                yFood = yRandom;

                return;
            }

            for (Point item : copySnakeParts) {
                xRandom = random.nextInt(cols - 1);
                yRandom = random.nextInt(rows - 1);
                xRandom *= scl;
                yRandom *= scl;
                if(yRandom == 0){
                    setNewFoodLocation();
                    return;
                }
                if (item.x != xRandom || item.y != yRandom) {
                    xFood = xRandom;
                    yFood = yRandom;
                } else if (item.x == xRandom || item.y == yRandom) {
                    setNewFoodLocation();
                }
            }
        } catch (StackOverflowError e) {
            e.printStackTrace();
        }
    }
    public void checkCollision(int x, int y) {
        ArrayList<Point> copySnakeParts = new ArrayList<Point>(snakeParts);
        int index = 0;

        if (x == width) {
            Death();
        } else if (x <= -40) {
            Death();
        } else if (y >= (height-40)) {
            Death();
        } else if (y <= 30) {
            Death();
        }

        for (Point item : copySnakeParts) {
            if (!(index < 1)) {
                if (item.x == this.x && item.y == this.y) {
                    Death();
                }
                if(copySnakeParts.get(copySnakeParts.size()-1) == new Point(this.x, this.y)){
                    Death();
                }
            } else {
                index++;
            }
        }
    }

    public void eatAddPart(Point input) {
        snakeParts.add(input);
        total++;
    }

    public void Death() {
        deathStatus = true;
        setDirSnake(0, 1);

        this.x = 40;
        this.y = 40;
        setNewFoodLocation();

        snakeParts.clear();
        total = 0;
        score = 0;
    }
}