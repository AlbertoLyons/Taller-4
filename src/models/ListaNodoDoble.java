package models;

import ucn.StdOut;

public class ListaNodoDoble {

    private NodoDoble cabeza;
    private NodoDoble cola;

    public ListaNodoDoble() {
        this.cabeza = null;
        this.cola = null;
    }

    public boolean agregar(Pokemon pokemon){

        NodoDoble nuevoPokemon = new NodoDoble(pokemon);

        if (this.cabeza == null){
            this.cabeza = nuevoPokemon;
            this.cola = nuevoPokemon;
            return true;
        }

        this.cabeza.setAnterior(nuevoPokemon);
        nuevoPokemon.setSiguiente(this.cabeza);
        this.cabeza = nuevoPokemon;

        return true;
    }
    
    public void despliegue(Pokemon pokemon){
        StdOut.println("ID: " + pokemon.getId());
        StdOut.println("NOMBRE: " + pokemon.getNombre());

        if (pokemon instanceof Basico){
            if (((Basico) pokemon).getPrimerEvolucion() != null) {
                StdOut.println("PRIMER EVOLUCION: " + ((Basico) pokemon).getPrimerEvolucion());
            }
            if (((Basico) pokemon).getSegundaEvolucion() != null) {
                StdOut.println("SEGUNDA EVOLUCION: " + ((Basico) pokemon).getSegundaEvolucion());
            }
            if (pokemon.getPrimerTipo().equals(pokemon.getSegundoTipo())){
                StdOut.println("TIPO: " + pokemon.getPrimerTipo());
            }else {
                StdOut.println("PRIMER TIPO: " + pokemon.getPrimerTipo());
                StdOut.println("SEGUNDO TIPO: " + pokemon.getSegundoTipo());
            }
        } else if (pokemon instanceof PrimeraEvolucion) {
            if (((PrimeraEvolucion) pokemon).getSegundaEvolucion() != null) {
                StdOut.println("SEGUNDA EVOLUCION: " + ((PrimeraEvolucion) pokemon).getSegundaEvolucion());
            }

            StdOut.println("POKEMON BASICO: " + ((PrimeraEvolucion) pokemon).getBasico());

            if (pokemon.getPrimerTipo().equals(pokemon.getSegundoTipo())){
                StdOut.println("TIPO: " + pokemon.getPrimerTipo());
            }else {
                StdOut.println("PRIMER TIPO: " + pokemon.getPrimerTipo());
                StdOut.println("SEGUNDO TIPO: " + pokemon.getSegundoTipo());
            }

        } else if (pokemon instanceof SegundaEvolucion) {
            StdOut.println("PRIMERA EVOLUCION: " + ((SegundaEvolucion) pokemon).getPrimeraEvolucion());
            StdOut.println("POKEMON BASICO: " + ((SegundaEvolucion) pokemon).getBasico());

            if (pokemon.getPrimerTipo().equals(pokemon.getSegundoTipo())){
                StdOut.println("TIPO: " + pokemon.getPrimerTipo());
            }else {
                StdOut.println("PRIMER TIPO: " + pokemon.getPrimerTipo());
                StdOut.println("SEGUNDO TIPO: " + pokemon.getSegundoTipo());
            }
        }
        StdOut.println("");
        /*
        int contador = 1;

        for (NodoDoble aux = this.cola; aux != null; aux = aux.getAnterior()){


            StdOut.println("////// POKEMON " + contador +" //////");
            Pokemon pokemon = aux.getPokemon();
            StdOut.println("ID: " + pokemon.getId());
            StdOut.println("NOMBRE: " + pokemon.getNombre());
            
            if (pokemon instanceof Basico){
                if (((Basico) pokemon).getPrimerEvolucion() != null) {
                    StdOut.println("PRIMER EVOLUCION: " + ((Basico) pokemon).getPrimerEvolucion());
                }
                if (((Basico) pokemon).getSegundaEvolucion() != null) {
                    StdOut.println("SEGUNDA EVOLUCION: " + ((Basico) pokemon).getSegundaEvolucion());
                }
                if (pokemon.getPrimerTipo().equals(pokemon.getSegundoTipo())){
                    StdOut.println("TIPO: " + pokemon.getPrimerTipo());
                }else {
                    StdOut.println("PRIMER TIPO: " + pokemon.getPrimerTipo());
                    StdOut.println("SEGUNDO TIPO: " + pokemon.getSegundoTipo());
                }
            } else if (pokemon instanceof PrimeraEvolucion) {
                if (((PrimeraEvolucion) pokemon).getSegundaEvolucion() != null) {
                    StdOut.println("SEGUNDA EVOLUCION: " + ((PrimeraEvolucion) pokemon).getSegundaEvolucion());
                }

                StdOut.println("POKEMON BASICO: " + ((PrimeraEvolucion) pokemon).getBasico());

                if (pokemon.getPrimerTipo().equals(pokemon.getSegundoTipo())){
                    StdOut.println("TIPO: " + pokemon.getPrimerTipo());
                }else {
                    StdOut.println("PRIMER TIPO: " + pokemon.getPrimerTipo());
                    StdOut.println("SEGUNDO TIPO: " + pokemon.getSegundoTipo());
                }
                
            } else if (pokemon instanceof SegundaEvolucion) {
                StdOut.println("PRIMERA EVOLUCION: " + ((SegundaEvolucion) pokemon).getPrimeraEvolucion());
                StdOut.println("POKEMON BASICO: " + ((SegundaEvolucion) pokemon).getBasico());

                if (pokemon.getPrimerTipo().equals(pokemon.getSegundoTipo())){
                    StdOut.println("TIPO: " + pokemon.getPrimerTipo());
                }else {
                    StdOut.println("PRIMER TIPO: " + pokemon.getPrimerTipo());
                    StdOut.println("SEGUNDO TIPO: " + pokemon.getSegundoTipo());
                }
            }
            StdOut.println("");
            contador++;
        }

         */
    }
    public boolean contieneID(String id) {
        NodoDoble actual = this.cola;

        while(actual != null){
            if (actual.getPokemon().getId().equalsIgnoreCase(id)){
                return true;
            }
            actual = actual.getAnterior();
        }
        return false;

    }
    public void desplegarID(String id) {
        NodoDoble actual = this.cola;
        while (actual != null) {
            if (actual.getPokemon().getId().equalsIgnoreCase(id)) {
                despliegue(actual.getPokemon());
            }
            actual = actual.getAnterior();
        }

    }
    public boolean contieneNombre(String nombre) {
        NodoDoble actual = this.cola;

        while(actual != null){
            if (actual.getPokemon().getNombre().equalsIgnoreCase(nombre)){
                return true;
            }
            actual = actual.getAnterior();
        }
        return false;

    }

}
