package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.ArrayList;
import java.util.Random;
/*My next idea is to have multiple levels (probably easy, medium, and hard) where each level varies the amount of
ammo that you get (the number of bullets shot), the rate of the enemies' movement, and the number of enemies (different sizes) spawned
Also later I should do another whole set of "adventure games" where people get new weapons and stuff
competition: least amount of time to kill the most enemies in different levels
 */

/**
 * Created by christopherelliott on 12/5/16.
 */
public class Fighter implements ActionListener, KeyListener {

    public static Fighter fighter;
    public JFrame jframe;
    public MakeObj makeObj;
    public static ArrayList<Point> spaceship = new ArrayList<Point>();
    public static ArrayList<Point> bullets = new ArrayList<Point>();
    public static ArrayList<Point> invaders = new ArrayList<Point>();
    public Timer timer = new Timer(20, this);
    public static final int SHIP_Y = 500, LENGTH = 5, LEFT = 0, RIGHT = 1, STAY = 2, SCALE = 10, WIDTH = 600;
    public static int shipPositionX = (WIDTH/2) - (2*SCALE), direction = STAY, ticks = 0, timeBetweenBullets = 0, life = 10, score = 0, time = 0, ammo = 0, blinkTick = 0, select = 1;
    public Dimension dim;
    public static boolean over = false, paused = false, shooter = false, startGame = false, blink = true, prompt = false;
    //prompt controlls many different things based on the current menu
    public Random random = new Random();
    public static int menu = 1;

    public Fighter() {
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        jframe = new JFrame();
        jframe.setVisible(true);
        jframe.setResizable(false);
        jframe.setSize(600, 600);
        jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2, dim.height / 2 - jframe.getHeight() / 2);
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jframe.add(makeObj = new MakeObj());
        jframe.addKeyListener(this);
        start();
    }

    public void start() {
        if (menu == 1) {
            timer.start();
        }
        if (menu == 8) {
            spaceship.clear();
            bullets.clear();
            invaders.clear();
            ammo = 0;
            life = 10;
            score = 0;
            ticks = 0;
            time = 0;
            timeBetweenBullets = 0;
            shipPositionX = (WIDTH / 2) - (2 * SCALE);
            direction = STAY;
            over = false;
            paused = false;
            for (int i = 0; i < LENGTH; i++) {
                spaceship.add(new Point(shipPositionX + SCALE * i, SHIP_Y));
            }
            timer.start();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();
        if (menu == 5) {
            if (i == KeyEvent.VK_DOWN && select != 8) {
                select++;
            }
            if (i == KeyEvent.VK_UP && select != 1) {
                select--;
            }
            if (i == KeyEvent.VK_ENTER && select == 8) {
                menu = 8;
                start();
                //the actual different levels of the game will be modified in the ActionListener method
            }
        } else if (menu == 3) {
            if (i == KeyEvent.VK_DOWN && select != 3) {
                select++;
                blink = true;
                blinkTick = 0;
            }
            if (i == KeyEvent.VK_UP && select != 1) {
                select--;
                blink = true;
                blinkTick = 0;
            }
            if (i == KeyEvent.VK_ENTER) {
                menu = 8;
                start();
                //the actual different levels of the game will be modified in the ActionListener method
            }
        }
        if (menu == 2) {
            if (i == KeyEvent.VK_DOWN && select != 3) {
                select++;
                blink = true;
                blinkTick = 0;
            }
            if (i == KeyEvent.VK_UP && select != 1) {
                select--;
                blink = true;
                blinkTick = 0;
            }
            if (i == KeyEvent.VK_ENTER && select == 1) {
                select = 1;
                menu = 3;
            }
            if (i == KeyEvent.VK_ENTER && select == 3) {
                select = 1;
                menu = 5;
            }
        }
        if (menu == 1) {
            if (i == KeyEvent.VK_DOWN && select != 3) {
                select++;
                blink = true;
                blinkTick = 0;
            }
            if (i == KeyEvent.VK_UP && select != 1) {
                select--;
                blink = true;
                blinkTick = 0;
            }
            if (i == KeyEvent.VK_ENTER && select == 1) {
                menu = 2;
            }
        }
        if (menu == 8) {
            if (i == KeyEvent.VK_RIGHT) {
                direction = RIGHT;
            }
            if (i == KeyEvent.VK_LEFT) {
                direction = LEFT;
            }
            if (!over) {
                if (i == KeyEvent.VK_SPACE) {
                    shooter = true;
                }
            } else {
                if (i == KeyEvent.VK_SPACE) {
                    over = false;
                    start();
                }
            }
            if (i == KeyEvent.VK_Q) {
                paused = !paused;
            }
            if (i == KeyEvent.VK_T) {
                over = true;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int i = e.getKeyCode();
        if (menu == 8) {
            if (i == KeyEvent.VK_RIGHT) {
                direction = STAY;
            }
            if (i == KeyEvent.VK_LEFT) {
                direction = STAY;
            }
            if (i == KeyEvent.VK_SPACE) {
                shooter = false;
            }
        }
        //press H for home
        if (i == KeyEvent.VK_H) {
            select = 1;
            if (menu == 8) {
                prompt = true;
            } else {
                menu = 1;
            }
        }
        if (i == KeyEvent.VK_ENTER && prompt) {
            menu = 1;
            select = 1;
            prompt = false;
        } else if (i == KeyEvent.VK_SPACE && prompt) {
            prompt = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        makeObj.repaint();
        ticks++;
        //the method to blink

        blinkTick++;
        if (blinkTick >= 40) {
            if (blink) {
                blinkTick = 80;
            }
            blink = false;
            blinkTick-=2;
            if (blinkTick <= 40) {
                blinkTick = 0;
                blink = true;
            }
        }
        if (menu == 8) {
            if (!over && !paused && ticks % 2 == 0 && !prompt) {
                time++;
                if (direction == LEFT && spaceship.get(0).x > 0) {
                    spaceship.remove(LENGTH - 1);
                    spaceship.add(0, new Point(spaceship.get(0).x - SCALE, SHIP_Y));

                }
                if (direction == RIGHT && spaceship.get(LENGTH - 1).x < WIDTH - SCALE) {
                    spaceship.add(new Point(spaceship.get(LENGTH - 1).x + SCALE, SHIP_Y));
                    spaceship.remove(0);
                }
                //only shoots if set amount of time between last bullet
                if (shooter && timeBetweenBullets > 5) {
                    bullets.add(new Point(spaceship.get(2).x, SHIP_Y));
                    timeBetweenBullets = 0;
                    ammo++;
                } else {
                    timeBetweenBullets++;
                }
                //keeps track of bullet position and removes bullet if at top of screen

                for (int i = 0; i < bullets.size(); i++) {
                    if (bullets != null) {
                        bullets.set(i, new Point(bullets.get(i).x, bullets.get(i).y - SCALE));
                    }
                    if (bullets.get(0).y <= 0) {
                        bullets.remove(0);
                        i--;
                    }

                }
                //invaders from top of screen
                if (ticks % 25 == 0) {
                    invaders.add(new Point(random.nextInt((WIDTH / SCALE) - 4) * SCALE + 2 * SCALE, 0));
                }
                if (ticks % 10 == 0) {
                    for (int i = 0; i < invaders.size(); i++) {
                        if (invaders.get(0).y >= 600) {
                            invaders.remove(0);
                            i--;
                            life--;
                        }
                        if (invaders != null) {
                            invaders.set(i, new Point(invaders.get(i).x, invaders.get(i).y + 10));
                        }
                    }
                }
                //if the bullet hits the invader the invader disappears
                //also if the invader is one y-value above (and they are going to pass through each other)
                //the invader disappears
                //bullet also disappears
                for (int i = 0; i < bullets.size(); i++) {
                    if (invaders.contains(bullets.get(i))) {
                        invaders.remove(invaders.indexOf(bullets.get(i)));
                        bullets.remove(i);
                        i--;
                        score++;
                    } else {
                        Point bulletCheck = new Point(bullets.get(i).x, bullets.get(i).y + SCALE);
                        if (invaders.contains(bulletCheck)) {
                            invaders.remove(invaders.indexOf(bulletCheck));
                            bullets.remove(i);
                            i--;
                            score++;
                        }
                    }
                }
                if (life <= 0) {
                    over = true;
                }
                //if invader hits spaceship, invader disappears and minus 3 lives
                for (Point shipPoint : spaceship) {
                    if (invaders.contains(shipPoint)) {
                        invaders.remove(invaders.indexOf(shipPoint));
                        life -= 3;
                    }
                }

            }
        }
    }

    public static void main(String[] args) {
        new Fighter();
    }
}
