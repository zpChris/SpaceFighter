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

    @SuppressWarnings("serial")

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Fighter fighter = Fighter.fighter;

        if (fighter.menu == 1) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 600, 600);
            Font titleFont = new Font("Arial", Font.PLAIN, 75);
            FontMetrics metrics = g.getFontMetrics(titleFont);
            g.setFont(titleFont);
            //title
            String title = "SpaceShooter";
            g.setColor(new Color(16249691));
            g.drawRect(getWidth()/2 - metrics.stringWidth(title)/2 - 10, 15, metrics.stringWidth(title) + 20, 80);
            g.setColor(Color.WHITE);
            g.drawString(title, getWidth()/2 - metrics.stringWidth(title)/2, 75);
            //author
            Font author = new Font("Arial", Font.PLAIN, 50);
            g.setFont(author);
            g.setColor(Color.WHITE);
            metrics = g.getFontMetrics(author);
            g.drawString("Made by Chris Elliott", getWidth()/2 - metrics.stringWidth("Made by Chris Elliott")/2, 150);
            g.setColor(Color.ORANGE);
            //buttons:
            //borders
            g.drawRect(150, 200, 300, 75);
            g.drawRect(150, 300, 300, 75);
            g.drawRect(150, 400, 300, 75);
            //text
            g.setColor(Color.WHITE);
            Font button = new Font("Arial", Font.PLAIN, 30);

            g.drawString("New Game", 300 - metrics.stringWidth("New Game")/2, 250);
            g.setColor(new Color(5394512));
            g.drawString("Stats", 300 - metrics.stringWidth("Stats")/2, 350);
            g.drawString("About", 300 - metrics.stringWidth("About")/2, 450);
            //blinking select button to the side
            g.setColor(Color.WHITE);
            if (fighter.blink) {
                g.fillOval(300 - (metrics.stringWidth("New Game")/2 + 80), 225, 20, 20);
            }
        } else if (fighter.menu == 8) {
            g.setColor(Color.BLACK);

            g.fillRect(0, 0, 600, 600);
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
            g.drawString("Move left and right: ARROW KEYS, Shoot bullets: SPACE, Pause: Q, End game: T", 10, 570);
        }
    }
}
