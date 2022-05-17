package Main;

import java.util.ArrayList;

public class Combinar {

    private ArrayList<Character> letras = new ArrayList<Character>();
    private ArrayList<String> palabrasPosibles;
    private int[] indices;
    private int[] puntuaciones;
    private Diccionario diccionario;

    /**
     * Constructor que recibe un diccionario como parámetro, en cuál se buscarán
     * las palabaras luego de obtener las combinaciones.
     */
    Combinar(Diccionario diccionario) {
        this.diccionario = diccionario;
    }

    /**
     * Método encargado de agregar al arreglo letras, cada uno de los caracteres
     * del parámetro letrasEnMano. Luego llama al método combinar con los
     * valores correspondientes, para crear las
     *
     * @param letrasEnMano
     * @return
     */
    public ArrayList<String> crearPalabras(String letrasEnMano) {
        palabrasPosibles = new ArrayList<>();
        letras = new ArrayList<>();
        for (int i = 0; i < letrasEnMano.length(); i++) {
            letras.add(letrasEnMano.charAt(i));
        }
        for (int i = 2; i <= letrasEnMano.length(); i++) {
            String a = "";
            combinar(letras, 1, i, a);
        }
        puntuarPalabras();

        ArrayList<String> c = new ArrayList<>();
        
        for (int i = 0; i < indices.length; i++) {
            if(indices[i] != 0){
                c.add( palabrasPosibles.get(indices[i]-1) );     
            }
            
        }
        
        for (int i = 0; i < indices.length; i++) {
            if(indices[i] != 0){
                c.add(String.valueOf(puntuaciones[indices[i]-1]));
            }
            
        }
        
        return c;

        
    }

    /**
     * Método recursivo. Crea las combinaciones posibles luego de ser llamado
     * desde el método CrearPalabras y verifica si existe dicha combinación en
     * el diccionario
     *
     * @param letras
     * @param cambios
     * @param limite
     * @param a
     */
    public void combinar(ArrayList<Character> letras, int cambios, int limite, String a) {
        if (limite == cambios) {
            for (int i = 0; i < letras.size(); i++) {
                if (!verificarRepeticiones(a + letras.get(i))) {
                    if (diccionario.buscarPalabras(a + letras.get(i))) {
                        palabrasPosibles.add(a + letras.get(i));
                    }
                }

            }
            return;
        }
        for (int i = 0; i < letras.size(); i++) {
            if (cambios == 1) {
                a = "";
            }
            a += letras.get(i);
            ArrayList<Character> letras2 = new ArrayList<>();
            for (int j = 0; j < letras.size(); j++) {
                if (j != i) {
                    letras2.add(letras.get(j));
                }
            }
            combinar(letras2, cambios + 1, limite, a);
            a = a.substring(0, a.length() - 1);
        }

    }

    /**
     * Método que imprime las combinaciones posibles luego de ser obtenidas por
     * el método crearPalabras y el método combinar
     */
    public void imprimirCombinaciones() {
        System.out.println("Palabras posibles: ");
        for (int i = 0; i < palabrasPosibles.size(); i++) {
            System.out.println(palabrasPosibles.get(i));
        }
        /*
        if ( palabrasPosibles.size() > 10) {
            System.out.println("Mejores 10 palabras: ");
            for (int i = 0; i < indices.length; i++) {
                System.out.println(palabrasPosibles.get(indices[i]) );
            }
        }else{
            System.out.println("Palabras posibles: ");
            for (int i = 0; i < palabrasPosibles.size(); i++) {
                System.out.println(palabrasPosibles.get(i) );
            }
        }
         */
    }

    /**
     * Método que da puntos a las palabras que ya han sido verificadas en el
     * diccionario y guarda las posiciones de las 10 palabras con mayor puntaje
     * en un arreglo
     */
    public void puntuarPalabras() {
        puntuaciones = new int[palabrasPosibles.size()];
        for (int i = 0; i < palabrasPosibles.size(); i++) {
            for (int j = 0; j < palabrasPosibles.get(i).length(); j++) {
                switch (palabrasPosibles.get(i).charAt(j)) {
                    case 'd':
                    case 'g':
                        puntuaciones[i] += 2;
                        break;
                    case 'b':
                    case 'c':
                    case 'm':
                    case 'p':
                        puntuaciones[i] += 3;
                        break;
                    case 'f':
                    case 'h':
                    case 'v':
                    case 'y':
                        puntuaciones[i] += 4;
                        break;
                    case 'q':
                        puntuaciones[i] += 5;
                        break;
                    case 'j':
                    case 'ñ':
                    case 'x':
                        puntuaciones[i] += 8;
                        break;
                    case 'z':
                        puntuaciones[i] += 10;
                        break;
                    default:
                        puntuaciones[i] += 1;
                        break;
                }
            }
        }

        //obtiene las posiciones de las 10 palabras con mejor puntaje
        indices = new int[10];
        for (int i = 0; i < puntuaciones.length; i++) {
         //   System.out.print(i);
            for (int j = 0; j < indices.length; j++) {
                if (indices[j] == 0){
                    indices[j] = i+1;
                    break;
                }else if (puntuaciones[i] > puntuaciones[indices[j]-1]) {
                    for (int k = indices.length - 2; k >= j; k--) {
                        indices[k + 1] = indices[k];
                    }
                    indices[j] = i+1;
                    break;
                    
                }

                
            }
        }
        /*
        for(int i = 0; i < indices.length; i++){
            System.out.print(indices[i] + ", ");
        }
        */

    }

    public boolean verificarRepeticiones(String cadena) {
        for (String c : palabrasPosibles) {
            if (c.equals(cadena)) {
                return true;
            }
        }
        return false;
    }
}
