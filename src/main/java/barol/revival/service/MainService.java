package barol.revival.service;

import barol.revival.controller.MainController;
import barol.revival.models.Ship;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;

/**
 * Created by Bartosz on 11.07.2017.
 */
public class MainService {

    private Ship ship;
    private AnchorPane mainPane;
    private Scene scene;
    private double BEGINNING_OFFSET = 0;
    private double offset = BEGINNING_OFFSET;

    public MainService(AnchorPane mainPane, Scene scene) {
        System.out.println("creating service...");
        System.out.println("mainPane is: " + mainPane);
        this.ship = new Ship();
        this.mainPane = mainPane;
        this.scene = scene;
        this.addShipToBoard();
    }

    private void addShipToBoard() {
        System.out.println("dodaje statek");
        this.mainPane.getChildren().add(this.ship.getShipImage());
    }


    private double WectorX = 0;
    private double WectorY = 0;
    private double oldWectorX = 0;
    private double oldWectorY = 0;
    private int TERMINAL_OFFSET = 10;

    public void moveAndRotateShip(int power, int dr) {


        if (power == 0 && dr == 0) return;

/*
        System.out.println("dx, dy, dr : " + dx + " " + dy + " " + dr);


        final double cx = this.ship.getShipImage().getBoundsInLocal().getWidth()  / 2;
        final double cy = this.ship.getShipImage().getBoundsInLocal().getHeight() / 2;

        System.out.println("cx, cy : " + cx + " " + cy);

        double x = cx + this.ship.getShipImage().getLayoutX() + dx;
        double y = cy + this.ship.getShipImage().getLayoutY() + dy;

        System.out.println("x, y : " + x + " " + y);
*/
        this.rotateShipBy(dr);


        if(power > 0) {

            if(this.offset < TERMINAL_OFFSET) {
                this.offset = this.offset + 0.2;
            }
            else {
                this.offset = TERMINAL_OFFSET;
            }

            double p = this.ship.getShipImage().getLocalToSceneTransform().getMyx();
            double q = this.ship.getShipImage().getLocalToSceneTransform().getMyy();
            double angle = Math.atan2(p, q);

           // System.out.println("angle: " + angle);

            double currentX = this.ship.getShipImage().getLayoutX();
            double currentY = this.ship.getShipImage().getLayoutY();

            //System.out.println("pos: " + currentX + ", " + currentY);

            double a = 0;
            double b = 0;
            this.oldWectorX = this.WectorX;
            this.oldWectorY = this.WectorY;


            if(angle > 0 && angle < Math.PI/2) {


                //System.out.println("++++++++++++++++++++++");
                //System.out.println(angle);
                //System.out.println(Math.sin(angle));
                //System.out.println(Math.sin(Math.abs(angle)));


                a = Math.abs(this.offset * Math.sin(angle));
                b = Math.abs(this.offset * Math.cos(angle));

                //System.out.println("a: " + a);


                this.ship.getShipImage().setLayoutX(currentX + a);
                this.ship.getShipImage().setLayoutY(currentY - b);

                this.WectorX = a;
                this.WectorY = -b;


            }
            else if(angle > Math.PI/2 && angle < Math.PI) {
                double beta = Math.PI - angle;

                a = Math.abs(this.offset * Math.sin(beta));
                b = Math.abs(this.offset * Math.cos(beta));

                this.ship.getShipImage().setLayoutX(currentX + a);
                this.ship.getShipImage().setLayoutY(currentY + b);

                this.WectorX = a;
                this.WectorY = b;
            }
            else if(angle < 0 && angle > -1 * Math.PI/2) {
                a = Math.abs(this.offset * Math.sin(Math.abs(angle)));
                b = Math.abs(this.offset * Math.cos(Math.abs(angle)));


                this.ship.getShipImage().setLayoutX(currentX - a);
                this.ship.getShipImage().setLayoutY(currentY - b);


                this.WectorX = -a;
                this.WectorY = -b;
            }
            else if(angle < -1 * Math.PI/2 && angle > -1 * Math.PI) {
                double beta = Math.PI - Math.abs(angle);

                a = Math.abs(this.offset * Math.sin(beta));
                b = Math.abs(this.offset * Math.cos(beta));

                this.ship.getShipImage().setLayoutX(currentX - a);
                this.ship.getShipImage().setLayoutY(currentY + b);

                this.WectorX = -a;
                this.WectorY = b;
            }
            else if(angle == 0) {
                this.ship.getShipImage().setLayoutY(currentY - this.offset);
                this.WectorX = 0;
                this.WectorY = -this.offset;
            }
            else if(angle == Math.PI/2) {
                this.ship.getShipImage().setLayoutX(currentX + this.offset);
                this.WectorX = this.offset;
                this.WectorY = 0;
            }
            else if(angle == Math.PI || angle == -1 * Math.PI) {
                this.ship.getShipImage().setLayoutY(currentY + this.offset);
                this.WectorX = 0;
                this.WectorY = this.offset;
            }
            else if(angle == -1 * Math.PI/2) {
                this.ship.getShipImage().setLayoutX(currentX - this.offset);
                this.WectorX = -this.offset;
                this.WectorY = 0;
            }



           // System.out.println("a, b: " + a + ", " + b);
            //System.out.println("ustawilem wektory: " + this.WectorX + ", " + this.WectorY);
        }




    }

    private void moveShipTo(double x, double y) {
        //System.out.println("moving...");

        final double cx = this.ship.getShipImage().getBoundsInLocal().getWidth()  / 2;
        final double cy = this.ship.getShipImage().getBoundsInLocal().getHeight() / 2;
        //System.out.println("cx, cy : " + cx + " " + cy);

        if (x - cx >= 0 &&
                x + cx <= this.scene.getWidth() &&
                y - cy >= 0 &&
                y + cy <= this.scene.getHeight()) {
            double a = x - cx;
            double b = y - cy;
            System.out.println("relocate to " + a + " " + b);

            this.ship.getShipImage().setLayoutX(x - cx);
            this.ship.getShipImage().setLayoutY(y - cy);

        }
    }

    private void rotateShipBy(int angle) {
        //System.out.println(">>center: " + this.ship.getShipImage().getLayoutX() + ", " + this.ship.getShipImage().getLayoutY());

        this.ship.getShipImage().getTransforms().add(new Rotate(angle, 0,0));


        double a = this.ship.getShipImage().getLocalToSceneTransform().getMyx();
        double b = this.ship.getShipImage().getLocalToSceneTransform().getMyy();
        //System.out.println("rotation: " + Math.toDegrees(Math.atan2(a, b)));
    }




    private double vertical_speed = 0;
    private double GRAVITY = 0.1;
    private int TERMINAL_VELOCITY = 5;
    private double offsetDiff = 0.1;

    public void gravity(int power) {
        //this.ship.getShipImage().setLayoutX(this.ship.getShipImage().getLayoutX() );
        //s=1/2att




            //System.out.println("lece dalej: " + this.WectorX + ", " + this.WectorY);

            if(this.oldWectorX >= 0 && this.WectorX > 0) {
                this.WectorX = this.WectorX - 0.1;
            }
            else if(this.oldWectorX < 0 && this.WectorX < 0) {
                this.WectorX = this.WectorX + 0.1;
            }
            else if(this.oldWectorX < 0 && this.WectorX > 0) {
                this.WectorX = this.WectorX - 0.1;
            }
            else if(this.oldWectorX > 0 && this.WectorX < 0) {
                this.WectorX += 0.1;
            }
            else {
               // this.WectorX = 0;
            }

            if(this.oldWectorY >= 0 && this.WectorY > 0) {
                this.WectorY = this.WectorY - 0.1;
            }
            else if(this.oldWectorY < 0 && this.WectorY < 0) {
                this.WectorY = this.WectorY + 0.1;
            }
            else if(this.oldWectorY < 0 && this.WectorY > 0) {
                this.WectorY = this.WectorY - 0.1;
            }
            else if(this.oldWectorY > 0 && this.WectorY < 0) {
                this.WectorY += 0.1;
            }
            else {
                //this.WectorY = 0;
            }
           this.ship.getShipImage().setLayoutX(this.ship.getShipImage().getLayoutX() + this.WectorX);
           this.ship.getShipImage().setLayoutY(this.ship.getShipImage().getLayoutY() + this.WectorY);


/*

            double impetX = 0;
            double impetY = 0;
            //WectorX = Math.abs(WectorX);
            //WectorY = Math.abs(WectorY);



            if (WectorX > 0 && WectorY < 0) {//NE
                double sin = Math.abs(WectorY) / offset;
                double cos = Math.abs(WectorX) / offset;
                offset -= offsetDiff;
                impetX = offset * cos;
                impetY = -(offset * sin);
            } else if (WectorX > 0 && WectorY > 0) {//SE
                double sin = Math.abs(WectorX) / offset;
                double cos = Math.abs(WectorY) / offset;
                offset -= offsetDiff;
                impetX = offset * sin;
                impetY = offset * cos;
            } else if (WectorX < 0 && WectorY > 0) {//SW
                double sin = Math.abs(WectorX) / offset;
                double cos = Math.abs(WectorY) / offset;
                offset -= offsetDiff;
                impetX = -(offset * sin);
                impetY = offset * cos;
            } else if (WectorX < 0 && WectorY < 0) {//NW
                double sin = Math.abs(WectorY) / offset;
                double cos = Math.abs(WectorX) / offset;
                offset -= offsetDiff;
                impetX = -(offset * cos);
                impetY = -(offset * sin);

            } else if (WectorX == 0 && WectorY < 0) {//jedzie w gore
                offset -= offsetDiff;
                impetY = -offset;
            } else if (WectorX == 0 && WectorY > 0) {//jedzie w dol
                offset -= offsetDiff;
                impetY = +offset;
            } else if (WectorX < 0 && WectorY == 0) {//jedzie w lewo
                offset -= offsetDiff;
                impetX = -offset;
            } else if (WectorX > 0 && WectorY == 0) {//jedzie w prawo
                offset -= offsetDiff;
                impetX = offset;
            }


            this.ship.getShipImage().setLayoutX(this.ship.getShipImage().getLayoutX() + impetX);
            this.ship.getShipImage().setLayoutY(this.ship.getShipImage().getLayoutY() + impetY);

*/

        this.vertical_speed = this.vertical_speed + GRAVITY;
            if (this.vertical_speed > TERMINAL_VELOCITY)
            {
                this.vertical_speed = TERMINAL_VELOCITY;
            }
            this.ship.getShipImage().setLayoutY(this.ship.getShipImage().getLayoutY() + this.vertical_speed);
        if(power == 0) {
            if(this.offset > 0) {
                this.offset -= 0.5;
            }
            else {
                this.offset = 0;
            }

        }
        else {
            this.vertical_speed = 0;
            this.ship.getShipImage().setLayoutY(this.ship.getShipImage().getLayoutY() + 1);
        }

    }






    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public AnchorPane getMainPane() {
        return mainPane;
    }

    public void setMainPane(AnchorPane mainPane) {
        this.mainPane = mainPane;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}

