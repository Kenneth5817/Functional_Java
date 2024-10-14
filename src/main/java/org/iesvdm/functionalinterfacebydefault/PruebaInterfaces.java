package org.iesvdm.functionalinterfacebydefault;

import java.util.function.*;

public class PruebaInterfaces {
    public static void main(String[] args) {
        //Ejemplo de Supplier
        Supplier<Integer> integerSupplier = () -> (int) (Math.random()*100);
        System.out.println("IntegerSupplier: "+ integerSupplier.get());

        //Ejemplo de IntSupplier
        IntSupplier intSupplier=()-> (int)(Math.random()*100);
        System.out.println("IntSupplier: "+ intSupplier.getAsInt());

        Function<String,Integer> longCad=(String s)->s.length();
        System.out.println("String: "+ longCad.apply("aba"));

        UnaryOperator<String> gritaCad=(s)->s.toUpperCase();
        System.out.println("grita: "+ gritaCad.apply("Kenneth!"));

        Function<String,String>gritaMasEnojado=(s)->{
            String aux="647%&/"+s.toUpperCase()+"!";
            return aux+"!";
        };
        System.out.println("grita+enojado:"+gritaMasEnojado.apply("Kenneth!"));

        Predicate<Integer> esMayorEdad=(e)-> e > 18;
        Function<Integer,Boolean>esMenorEdad=(s)-> s < 18;
        int edad=2;
        System.out.println("edad= "+edad+(esMayorEdad.test(edad) ? " es mayor":" es menor"));
        System.out.println("edad= "+edad+(esMenorEdad.apply(edad) ? " es menor":" es mayor"));

        TriPredicate<Integer,Boolean,String>checkPerson=(age, tieneCarnet, nombreApellidos )->
                edad>18&&tieneCarnet&&nombreApellidos.length()>3;
        if(checkPerson.test(20,true,"kenneth jensen")){
            System.out.println("Contratado");
        }
    }
}
