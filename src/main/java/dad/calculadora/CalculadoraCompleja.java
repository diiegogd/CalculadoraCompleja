package dad.calculadora;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Separator;
import javafx.util.converter.NumberStringConverter;

public class CalculadoraCompleja extends Application {
    // Declaración de los componentes de la interfaz de usuario
    private ComboBox<String> operacionesComboBox;
    private TextField real1TextField, imaginario1TextField, real2TextField, imaginario2TextField, result1TextField, result2TextField;
    private SimpleDoubleProperty result1Property = new SimpleDoubleProperty();
    private SimpleDoubleProperty result2Property = new SimpleDoubleProperty();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculadora");

        // Creación del ComboBox y configuración de sus valores
        operacionesComboBox = new ComboBox<>();
        operacionesComboBox.getItems().addAll("+", "-", "*", "/");
        operacionesComboBox.setValue("+");

        // Creación de los campos de texto para los números reales e imaginarios
        real1TextField = new TextField();
        real1TextField.setAlignment(javafx.geometry.Pos.CENTER);
        real1TextField.setPrefWidth(60);

        imaginario1TextField = new TextField();
        imaginario1TextField.setAlignment(javafx.geometry.Pos.CENTER);
        imaginario1TextField.setPrefWidth(60);

        real2TextField = new TextField();
        real2TextField.setAlignment(javafx.geometry.Pos.CENTER);
        real2TextField.setPrefWidth(60);

        imaginario2TextField = new TextField();
        imaginario2TextField.setAlignment(javafx.geometry.Pos.CENTER);
        imaginario2TextField.setPrefWidth(60);

        // Creación de los campos de texto para los resultados
        result1TextField = new TextField();
        result1TextField.setPrefWidth(60);
        result1TextField.setAlignment(Pos.CENTER);
        result1TextField.setEditable(false);

        result2TextField = new TextField();
        result2TextField.setPrefWidth(60);
        result2TextField.setAlignment(Pos.CENTER);
        result2TextField.setEditable(false);

        // Creación de contenedores para la interfaz de usuario y configuración del diseño
        // VBox lista desplegable
        VBox comboBoxBox = new VBox(operacionesComboBox);
        comboBoxBox.setSpacing(5);

        // Contenedor para los campos de texto de entrada
        HBox inputBox1 = new HBox(real1TextField, new Label(" + "), imaginario1TextField, new Label(" i "));
        inputBox1.setSpacing(5);

        HBox inputBox2 = new HBox(real2TextField, new Label(" + "), imaginario2TextField, new Label(" i "));
        inputBox2.setSpacing(5);

        // Separador
        Separator separator = new Separator();
        separator.setPrefHeight(10);

        // Bindings
        result1TextField.textProperty().bindBidirectional(result1Property, new NumberStringConverter());
        result2TextField.textProperty().bindBidirectional(result2Property, new NumberStringConverter());

        // Listeners para los campos de texto de entrada
        real1TextField.textProperty().addListener((o, ov, nv) -> calcularResultado());
        imaginario1TextField.textProperty().addListener((o, ov, nv) -> calcularResultado());
        real2TextField.textProperty().addListener((o, ov, nv) -> calcularResultado());
        imaginario2TextField.textProperty().addListener((o, ov, nv) -> calcularResultado());

        // Listener para detectar cambios en el ComboBox
        operacionesComboBox.valueProperty().addListener((o, ov, nv) -> {
            calcularResultado();
        });

        // Contenedor para el resultado
        HBox result = new HBox(result1TextField, new Label(" + "), result2TextField, new Label(" i "));
        result.setSpacing(5);

        // Configuración de la disposición de los componentes
        VBox inputVBox = new VBox(inputBox1, inputBox2, separator, result);
        inputVBox.setSpacing(10);

        // Contenedor principal
        HBox root = new HBox(comboBoxBox, inputVBox);
        root.setStyle("-fx-padding: 50px");
        root.setSpacing(20);

        // Configuración de la escena
        Scene scene = new Scene(root, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Método para calcular el resultado de la operación
    private void calcularResultado() {
        try {
            // Obtener valores ingresados en los campos de texto
            double real1 = Double.parseDouble(real1TextField.getText());
            double imaginario1 = Double.parseDouble(imaginario1TextField.getText());
            double real2 = Double.parseDouble(real2TextField.getText());
            double imaginario2 = Double.parseDouble(imaginario2TextField.getText());

            // Creación de dos números complejos con los valores ingresados
            Complejo complejo1 = new Complejo(real1, imaginario1);
            Complejo complejo2 = new Complejo(real2, imaginario2);

            // Obtener operación seleccionada en el ComboBox
            String operacion = operacionesComboBox.getValue();
            Complejo resultado = null;

            // Realizar las operaciones
            if (operacion.equals("+")) {
                resultado = complejo1.suma(complejo2);
            } else if (operacion.equals("-")) {
                resultado = complejo1.resta(complejo2);
            } else if (operacion.equals("*")) {
                resultado = complejo1.multiplicacion(complejo2);
            } else if (operacion.equals("/")) {
                resultado = complejo1.division(complejo2);
            }

            // Mostrar el resultado en los campos de texto
            result1TextField.setText(String.valueOf(resultado.getReal()));
            result2TextField.setText(String.valueOf(resultado.getImaginario()));

        } catch (NumberFormatException e) {
            // En caso de que ocurra un error se establecen los resultados en cero
            result1TextField.setText("0");
            result2TextField.setText("0");
        }
    }

}