import org.iesvdm.IFuncionOperacion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JavaFunctionalTest1 {


    @Test
    public void claseAnonimaFuncionesAntesDeJAVA8() {

        // Una interfaz Java a priori no es instanciable -> MEDIA VERDAD S
        // SÍ se puede instancia mediante el mecanismo de clase anónima interna (Anonymous Inner Class)
        // Una especie implementación on-fly (in-line) de una clase que cumpla con la interfaz.

        // Interfaz                       Clase Anónima Interna (se implementa una interfaz on-fly en el código)
        //      |                                   |
        //      ---------                         --
        //              V                        V
        IFuncionOperacion iFuncionOperacionSuma = new IFuncionOperacion() {

            // Se realiza un @Override implementación0 del único método de la interfaz (interfaz funcional, por tanto)
            //                  |
            //     -------------
            //    V             |
            @Override       //  V
            public double operacion(double operando1, double operando2) {
                //En esta clase anónima implementas la suma
                return operando1 + operando2;
            }
        };

        // Interfaz                         Clase Anónima Interna (se implementa una interfaz on-fly)
        //      |                                       |
        //      ---------                             --
        //              V                            V
        IFuncionOperacion iFuncionOperacionProducto = new IFuncionOperacion() {
            @Override
            public double operacion(double operando1, double operando2) {
                //En esta clase anónima implementas el producto
                return operando1 * operando2;
            }
        };

        Assertions.assertEquals(4, iFuncionOperacionSuma.operacion(1, 3));
        Assertions.assertEquals(3, iFuncionOperacionProducto.operacion(1, 3));

    }

    @Test
    public void closureEnClaseAnonima() {

        // Dentro de una clase anónima se puede atrapar una variable en el ámbito externo
        // Se necesita que esa variable sea declarada final (o virtualmente final)
        //                                              |
        //   -------------------------------------------
        //  |
        // V
        final double constante = 1.234;

        IFuncionOperacion iFuncOperProdYConstante = new IFuncionOperacion() {
            @Override
            public double operacion(double operando1, double operando2) {
                //     Variable que se accede por closure desde el ámbito externo
                //                                  |
                //                                  V
                return operando1 * operando2 * constante;
            }
        };

        Assertions.assertEquals(1234, iFuncOperProdYConstante.operacion(10.0, 100.0));

    }

    @Test
    public void closureEnClaseAnonimaConAtributo() {

        final double constante = 1.234;

        IFuncionOperacion iFuncionOperacion = new IFuncionOperacion() {

            //Atributo inicializado a constante final de la closure (ámbito externo del cual se atrapa la variable)
            //                                      |
            //                                      V
            private double constanteInner = constante;
            @Override
            public double operacion(double operando1, double operando2) {
                return operando1 * operando2 * constante;
            }
        };
        Assertions.assertEquals(1234.0, iFuncionOperacion.operacion(10.0, 100.0));


    }

    @Test
    public void pasoDeArgumentosAClaseAnonimaSinClosure() {

        IFuncionOperacion iFuncionOperacion = new IFuncionOperacion() {

            // El atributo interno no se enlaza con variable externa mediante closure, sino que establece mediante cadena de método en setter
            //              |____________________________________
            //              V                                    |
            private double constante;   //                       |
                                        //                       |
                                        //                       |
            //           ----------------------------------------|
            //           V                                       V
            public IFuncionOperacion setConstante(double constante) {
                this.constante = constante;
                return this;
            }

            @Override
            public double operacion(double operando1, double operando2) {
                return operando1 * operando2 * this.constante;
            }
        }.setConstante(1.234);

        Assertions.assertEquals(1234, iFuncionOperacion.operacion(10.0, 100.0));

    }

    @Test
    public void REVOLUCION_JAVA_8_LAMBDAS() {

        final double constante = 1.234;

        //SE REEMPLAZA TODO EL CÓDIGO DE CLASE ANÓNIMA INTERNA POR UN LAMBDA (O DECLARACIÓN DE FUNCIÓN ANÓNIMA)

        //LAMBDA -------------------------
        //   |                            |
        //   SE BASA EN UNA INTERFAZ      |
        //   FUNCNIONAL                   |
        //   |                            |
        //   INTERFAZ CON UN              |
        //   SOLO MÉTODO                  |
        //   ABSTRACTO                    |
        //   |                            |
        //   V                            V
        IFuncionOperacion ifo = (o1, o2) -> o1 * o2 * constante;

        Assertions.assertEquals(1234, ifo.operacion(10.0, 100.0));

    }

}
