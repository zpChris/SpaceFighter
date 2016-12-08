package com.company;

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JComponent;

/**
 * Created by christopherelliott on 12/5/16.
 */
public class MakeObj extends JPanel {

    public Fighter fighter;

    @SuppressWarnings("serial")

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Fighter fighter = Fighter.fighter;
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 600,600);
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
        g.drawString("Score: " + score + ", Life: " + life + ", Time : " + time/20 + ", Ammo shot: " + ammo, 10, 10);
        if (fighter.over) {
            g.setColor(Color.BLACK);
            g.fillRect(10, 40, 450, 50);
            g.setColor(Color.WHITE);
            g.drawString("Nice try! You hit " + Math.round(((double) score/ ((double) time/20.00)) * 100.00) / 100.00 + " enemies per second and " + score + " enemies overall.", 10, 50);
            g.drawString("You also shot " + ammo + " bullets, and killed " + Math.round(((double) score / (double) ammo)*100.0) / 100.00 + " enemies per bullet.", 10, 70);
            g.drawString("Restart? Press SPACE.", 10, 90);
        }
        //maybe later if wanted
        /*if (ammo <= 0) {
            g.drawString("Out of ammo! Better luck next time. Restart? Press space.", 10, 50);
        }*/
        g.drawString("Move left and right: ARROW KEYS, Shoot bullets: SPACE, Pause: Q, End game: T", 10, 570);

    }


}
