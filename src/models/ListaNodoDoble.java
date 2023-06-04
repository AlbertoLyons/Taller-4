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
    
    public void despliegue(){

        for (NodoDoble aux = this.cabeza; aux != null; aux = aux.getSiguiente()){
            int contador = 1;

            StdOut.println("////// POKEMON " + contador +" //////");
            Pokemon pokemon = aux.getPokemon();
            StdOut.println("ID: " + pokemon.getId());
            StdOut.println("NOMBRE: " + pokemon.getNombre());
            
            if (pokemon instanceof Basico){
                StdOut.println("PRIMER EVOLUCION: " + ((Basico) pokemon).getPrimerEvolucion());

                if (pokemon.getPrimerTipo().equals(pokemon.getSegundoTipo())){
                    StdOut.println("TIPO: " + pokemon.getPrimerTipo());
                }else {
                    StdOut.println("PRIMER TIPO: " + pokemon.getPrimerTipo());
                    StdOut.println("SEGUNDO TIPO: " + pokemon.getSegundoTipo());
                }
            } else if (pokemon instanceof PrimeraEvolucion) {
                StdOut.println("SEGUNDA EVOLUCION: " + ((PrimeraEvolucion) pokemon).getSegundaEvolucion());
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
    }

}
