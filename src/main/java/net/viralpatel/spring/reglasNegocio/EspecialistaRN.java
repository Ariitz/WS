package net.viralpatel.spring.reglasNegocio;

public class EspecialistaRN {

    public static boolean validaRN1(String id){
        Boolean regresa;
        if (id != null){
            regresa = true;
        }else {
            regresa = false;
        }

            return regresa;
    }
}
