package dad.calculadora;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
public class Complejo {

    private DoubleProperty real;
    private DoubleProperty imaginario;

    public Complejo(double real, double imaginario) {
        this.real = new SimpleDoubleProperty(real);
        this.imaginario = new SimpleDoubleProperty(imaginario);
    }

    public double getReal() {
        return real.get();
    }

    public double getImaginario() {
        return imaginario.get();
    }

    public Complejo suma(Complejo otro) {
        double nuevoReal = this.getReal() + otro.getReal();
        double nuevoImaginario = this.getImaginario() + otro.getImaginario();
        return new Complejo(nuevoReal, nuevoImaginario);
    }

    public Complejo resta(Complejo otro) {
        double nuevoReal = this.getReal() - otro.getReal();
        double nuevoImaginario = this.getImaginario() - otro.getImaginario();
        return new Complejo(nuevoReal, nuevoImaginario);
    }

    public Complejo multiplicacion(Complejo otro) {
        double nuevoReal = this.getReal() * otro.getReal() - this.getImaginario() * otro.getImaginario();
        double nuevoImaginario = this.getReal() * otro.getImaginario() + this.getImaginario() * otro.getReal();
        return new Complejo(nuevoReal, nuevoImaginario);
    }

    public Complejo division(Complejo otro) {
        double divisor = otro.getReal() * otro.getReal() + otro.getImaginario() * otro.getImaginario();
        double nuevoReal = (this.getReal() * otro.getReal() + this.getImaginario() * otro.getImaginario()) / divisor;
        double nuevoImaginario = (this.getImaginario() * otro.getReal() - this.getReal() * otro.getImaginario()) / divisor;
        return new Complejo(nuevoReal, nuevoImaginario);
    }

}
