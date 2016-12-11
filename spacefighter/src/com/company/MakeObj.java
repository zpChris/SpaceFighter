package com.company;

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.text.Style;

/**
 * Created by christopherelliott on 12/5/16.
 */
public class MakeObj extends JPanel {

    public static Fighter fighter;
    public FontMetrics metrics;
    public final int insideTextBuffer = 10, promptBoxOuterBuffer = 5;
    public int scale = 0; //used for scaling all sorts of things (for multiple boxes and such)
    public int selectorShiftRight = 40; //distance from box to selector

    @SuppressWarnings("serial")

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 600, 600);
        Fighter fighter = Fighter.fighter;



        if (fighter.menu != 8 && fighter.menu != 1) {
            g.setColor(Color.GREEN);
            g.drawString("Home: H", 540, 570);
        }



        if (fighter.menu == 1) {
            Font titleFont = new Font("Arial", Font.PLAIN, 75);
            metrics = g.getFontMetrics(titleFont);
            g.setFont(titleFont);
            //title
            String title = "SpaceShooter";
            g.setColor(new Color(16249691));
            g.drawRect(getWidth() / 2 - metrics.stringWidth(title) / 2 - insideTextBuffer, 15, metrics.stringWidth(title) + insideTextBuffer * 2, 80);
            g.setColor(Color.WHITE);
            g.drawString(title, getWidth() / 2 - metrics.stringWidth(title) / 2, 75);
            //author
            Font author = new Font("Arial", Font.PLAIN, 50);
            g.setFont(author);
            g.setColor(Color.WHITE);
            metrics = g.getFontMetrics(author);
            g.drawString("Made by Chris Elliott", getWidth() / 2 - metrics.stringWidth("Made by Chris Elliott") / 2, 150);
            //buttons:
            //borders at end
            g.setColor(Color.ORANGE);
            for (int i = 0; i < 3; i++) {
                g.drawRect(getWidth()/4, 200 + scale, getWidth()/2, 75);
                scale += 100;
            }
            scale = 0; //return scale to 0 so it can be reused as a different scale
            //text
            g.setColor(Color.WHITE);
            g.drawString("New Game", getWidth()/2 - metrics.stringWidth("New Game") / 2, 255);
            g.setColor(new Color(5394512));
            g.drawString("Stats", getWidth()/2 - metrics.stringWidth("Stats") / 2, 355);
            g.drawString("About", getWidth()/2 - metrics.stringWidth("About") / 2, 455);
            //blinking select button to the side
            g.setColor(Color.WHITE);
            if (fighter.blink) {
                //y is ?, x is 80 left, width and height 20
                if (fighter.select == 1) {
                    g.fillOval(getWidth()/5 - (selectorShiftRight - 20), 225, 20, 20);
                } else if (fighter.select == 2) {
                    g.fillOval(getWidth()/5 - (selectorShiftRight - 20), 325, 20, 20);
                } else if (fighter.select == 3) {
                    g.fillOval(getWidth()/5 - (selectorShiftRight - 20), 425, 20, 20);
                }
            }
            //string at bottom (press h to return to home)


        } else if (fighter.menu == 2) {
            Font button = new Font("Arial", Font.PLAIN, 50);
            g.setFont(button);
            g.setColor(Color.WHITE);
            g.drawString("Free Play", getWidth() / 5 + ((getWidth() / 5) * 3 - metrics.stringWidth("Free Play")) / 2, 165);
            g.setColor(new Color(5394512));
            g.drawString("Campaign", getWidth() / 5 + ((getWidth() / 5) * 3 - metrics.stringWidth("Campaign")) / 2, 315);
            g.setColor(Color.WHITE);
            g.drawString("Custom", getWidth() / 5 + ((getWidth() / 5) * 3 - metrics.stringWidth("Custom")) / 2, 465);


        } else if (fighter.menu == 3) {
            Font button = new Font("Arial", Font.PLAIN, 50);
            g.setFont(button);
            g.setColor(Color.WHITE);
            g.drawString("Easy", getWidth()/5 + ((getWidth() / 5) * 3 - metrics.stringWidth("Easy")) / 2, 165);
            g.drawString("Medium", getWidth()/5 + ((getWidth() / 5) * 3 - metrics.stringWidth("Medium")) / 2, 315);
            g.drawString("Hard", getWidth()/5 + ((getWidth() / 5) * 3 - metrics.stringWidth("Hard")) / 2, 465);
        } else if (fighter.menu == 5) {
            for (int i = 0; i < 7; i++) {
                if (i == fighter.select - 1) {
                    g.setColor(Color.WHITE);
                    g.fillOval(112, 70 * i + 55, 15, 15);
                    g.drawLine(150, 70 * i + 65, 455, 70 * i + 65);
                    g.setColor(Color.GREEN); //this and the corresponding green color in the else statement set the text color afterwards
                } else {
                    g.setColor(Color.GRAY);
                    g.drawLine(150, 70 * i + 65, 455, 70 * i + 65);
                    g.setColor(new Color(12971674));
                }
                if (i == 0) {
                    g.drawString("Bullet Speed", 465, 70 * i + 70);
                } else if (i == 1) {
                    g.drawString("Invader Speed", 465, 70 * i + 70);
                } else if (i == 2) {
                    g.drawString("Time Between Bullets", 465, 70 * i + 70);
                } else if (i == 3) {
                    g.drawString("Starting Life", 465, 70 * i + 70);
                } else if (i == 4) {
                    g.drawString("Ammo Limit", 465, 70 * i + 70);
                } else if (i == 5) {
                    g.drawString("Bullet Size", 465, 70 * i + 70);
                } else if (i == 6) {
                    g.drawString("Invader Size", 465, 70 * i + 70);
                }
            }
            if (fighter.select == 7) { //only if selected, 7 is its selection
                g.setColor(new Color(13513780));
                g.drawString("If N/A: ENTER", 465, 510);
            }
            Font customGame = new Font("Arial", Font.PLAIN, 20);
            g.setFont(customGame);
            if (fighter.select != 8) {
                g.setColor(new Color(14126734));
                g.drawRect(225, 525, 150, 35);
                g.setColor(Color.GRAY);
                g.drawString("Start Game", 250, 550);
            } else {
                g.setColor(Color.RED);
                g.drawRect(225, 525, 150, 35);
                g.setColor(Color.WHITE);
                g.drawString("Start Game", 250, 550);
            }




        } else if (fighter.menu == 8) {
            g.setColor(Color.RED);
            for (Point point : fighter.spaceship) {
                g.fillRect(point.x, point.y, fighter.SCALE, fighter.SCALE);
            }
            g.setColor(Color.GREEN);
            for (Point point : fighter.bullets) {
                g.fillRect(point.x, point.y, fighter.SCALE, fighter.SCALE);
            }
            g.setColor(Color.PINK);
            for (Point point : fighter.invaders) {
                g.fillRect(point.x, point.y, fighter.SCALE, fighter.SCALE);
            }
            int life = fighter.life;
            int score = fighter.score;
            int time = fighter.time;
            int ammo = fighter.ammo; //actually number of bullets fired, not available
            g.setColor(Color.WHITE);
            g.drawString("Score: " + score + ", Life: " + life + ", Time : " + time / 20 + ", Ammo shot: " + ammo, 10, 10);
            if (fighter.over) {
                g.setColor(Color.BLACK);
                g.fillRect(10, 40, 450, 50);
                g.setColor(Color.WHITE);
                g.drawString("Nice try! You hit " + Math.round(((double) score / ((double) time / 20.00)) * 100.00) / 100.00 + " enemies per second and " + score + " enemies overall.", 10, 50);
                g.drawString("You also shot " + ammo + " bullets, and killed " + Math.round(((double) score / (double) ammo) * 100.0) / 100.00 + " enemies per bullet.", 10, 70);
                g.drawString("Restart? Press SPACE.", 10, 90);
            }
            //maybe later if wanted
            /*if (ammo <= 0) {
                g.drawString("Out of ammo! Better luck next time. Restart? Press space.", 10, 50);
            }*/
            g.drawString("Move left and right: ARROW KEYS, Shoot bullets: SPACE, Pause: Q, End game: T, Home: H", 10, 570);
            if (fighter.prompt) {
                Font homePromptFont = new Font("Arial", Font.PLAIN, 15);
                String homePrompt = "Are you sure you want to return to the home menu? Yes: ENTER, No: SPACE";
                g.setColor(Color.BLACK);
                g.fillRect(getWidth()/2 - (metrics.stringWidth(homePrompt)/2) - 5, getHeight()/2 - 5, metrics.stringWidth(homePrompt) + 10, 50);
                g.setColor(Color.RED);
                g.drawRect(getWidth()/2 - (metrics.stringWidth(homePrompt)/2), getHeight()/2, metrics.stringWidth(homePrompt), 40);
                metrics = g.getFontMetrics(homePromptFont);
                g.setColor(Color.WHITE);
                g.drawString(homePrompt, getWidth()/2 - (metrics.stringWidth(homePrompt)/2) + 30, getHeight()/2 + 25);

            }
            if (fighter.paused) {
                Font pausedHome = new Font("Arial", Font.PLAIN, 15);
                g.setColor(Color.BLACK);
                g.fillRect(getWidth()/2 - 25, getHeight()/2 - 20, 64, 30);
                g.setFont(pausedHome);
                g.setColor(Color.WHITE);
                g.drawString("Paused", getWidth()/2 - 18, getHeight()/2);
                g.setColor(Color.YELLOW);
                g.drawRect(getWidth()/2 - 20, getHeight()/2 - 15, 54, 20);
            }
        //drawing the rectangles - the same in many screens
        }
        if (fighter.menu == 2 || fighter.menu == 3) {
            g.setColor(Color.ORANGE);
            for (int i = 0; i < 3; i++) {
                g.drawRect(getWidth()/5, 100 + scale, (getWidth()/5) * 3, 100);
                scale += 150;
            }
            scale = 0;
            g.setColor(Color.WHITE);
            if (fighter.blink) {
                if (fighter.select == 1) {
                    g.fillOval(getWidth()/5 - (selectorShiftRight + 15), 135, 23, 23);
                } else if (fighter.select == 2) {
                    g.fillOval(getWidth()/5 - (selectorShiftRight + 15), 285, 23, 23);
                } else if (fighter.select == 3) {
                    g.fillOval(getWidth()/5 - (selectorShiftRight + 15), 435, 23, 23);
                }
            }
        }
    }


}
