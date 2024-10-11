package org.iesvdm.methodReference;

import java.util.ArrayList;
import java.util.List;

public class MethodReference {

    public static String aMayusculas(String s){
        return s.toUpperCase();
    }
    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        list.forEach(System.out::println);
        list.forEach(MethodReference::aMayusculas);
        // (s)->MethodRefernce.aMayusculas;
    }
}
