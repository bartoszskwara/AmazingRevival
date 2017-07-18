package barol.revival.models;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Bartosz on 11.07.2017.
 */
public class Ship {

    Point2D position;
    Arc shipImage;

    public Ship() {
        this.position = new Point2D(400,400);
        this.shipImage = new Arc(0, 0, 23, 28, -45, 270);
        this.shipImage.setLayoutX(this.position.getX());
        this.shipImage.setLayoutY(this.position.getY());
        this.shipImage.setType(ArcType.ROUND);
        this.shipImage.setFill(Color.BLUE);
        this.shipImage.setStroke(Color.BLACK);
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public Arc getShipImage() {
        return shipImage;
    }

    public void setShipImage(Arc shipImage) {
        this.shipImage = shipImage;
    }
}
