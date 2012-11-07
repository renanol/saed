package br.ufpb.saed;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;

/**
 *
 * @author Erick Costa e Renan Oliveira
 */
public class Vetor extends Group {

    Celula[] celulas = null;
    Point2D pb;

    public Vetor(Point2D pb, int size) {
        this.pb = pb;
        this.celulas = new Celula[size];

        for (int i = 0; i < size; ++i) {
            this.celulas[i] = new Celula(new Point2D(pb.getX() + i * Celula.DIMENSAO, pb.getY()), i);
            this.getChildren().add(this.celulas[i]);
        }
    }

    private Vetor(int size) {
        this.celulas = new Celula[size];
    }

    public void setValue(int pos, int v) {
        this.celulas[pos].Repintar();
        this.celulas[pos].setValue(v);
    }

    public int getValue(int pos) {
        return this.celulas[pos].getValue();
    }

    public void moveLeft(EventHandler<ActionEvent> value) {
        for (int k = 0; k < celulas.length; k++) {
            if (k == celulas.length - 1) {
                celulas[k].moveLeft(value);
            } else {
                celulas[k].moveLeft(null);
            }
        }
    }

    public void moveRight(EventHandler<ActionEvent> value) {
        for (int k = 0; k < celulas.length; k++) {
            if (k == celulas.length - 1) {
                celulas[k].moveRight(value);
            } else {
                celulas[k].moveRight(null);
            }

        }
    }

    public void alteraCor(int meio) {
        celulas[meio].alteraCor();
    }

    public void movePonto(int meio) {
        celulas[meio].movePonto(new Point2D(400, 600));
    }

    public Vetor getSubVetor(int ini, int fim) {
        Vetor subVetor = new Vetor(fim - ini);
        for (int i = ini, j = 0; i < fim; ++i, j++) {
            subVetor.celulas[j] = this.celulas[i];
        }

        return subVetor;
    }

    public void redesenharvetor() {
        for (int i = 0; i < this.celulas.length; ++i) {
            this.celulas[i].movePonto(new Point2D(pb.getX() + i * Celula.DIMENSAO, pb.getY()));
        }
    }
}
