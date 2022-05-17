package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Diccionario {

    private String[] diccionarioOrdenado;
    private String ruta;

    /**
     * Constructor que recibe la ruta del diccionario como parámetro. Para luego
     * leer las palabras en dicho archivo y ordenarlo
     */
    Diccionario(String ruta) {
        this.ruta = ruta;
        leerDiccionario();
        ordenarDiccionario();
    }

    /**
     * Lee el archivo usando la ruta enviada al constructor Los datos leídos se
     * pasan a un segundo arreglo, para luego ser ordenado
     */
    public void leerDiccionario() {
        ArrayList<String> diccionario = new ArrayList<>();
        try {

            Scanner in = new Scanner(new File(ruta));

            while (in.hasNext()) {
                String s = in.nextLine();
                diccionario.add(s);

            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo indicado no se encuentra");
        }
        diccionarioOrdenado = new String[diccionario.size()];
        diccionarioOrdenado = diccionario.toArray(diccionarioOrdenado);
    }

    /**
     * Ordena el diccionario usando el método de la burbuja
     */
    public void ordenarDiccionario() {
        for (int i = 0; i < 350; i++) {
            for (int j = 0; j < diccionarioOrdenado.length - 1; j++) {
                if (diccionarioOrdenado[j].compareToIgnoreCase(diccionarioOrdenado[j + 1]) > 0) {
                    String aux = diccionarioOrdenado[j];
                    diccionarioOrdenado[j] = diccionarioOrdenado[j + 1];
                    diccionarioOrdenado[j + 1] = aux;
                }
            }
        }
    }

    /**
     * Busca la palabra enviada como parámetro en el diccionario previamente
     * ordenado La busqueda se hace de forma binaria
     *
     * @param palabraBuscada
     * @return boolean
     */
    public boolean buscarPalabras(String palabraBuscada) {
        int inicio = 0;
        int fin = diccionarioOrdenado.length - 1;
        int medio = (inicio + fin) / 2;
        int aux = -1;
        int code;
        while (aux != medio) {
            aux = medio;
            code = diccionarioOrdenado[medio].compareToIgnoreCase(palabraBuscada);
            if (code == 0) {
                return true;
            } else if (code > 0) {
                fin = medio;
            } else {
                inicio = medio;
            }
            medio = (inicio + fin) / 2;
        }
        return false;
    }
}
