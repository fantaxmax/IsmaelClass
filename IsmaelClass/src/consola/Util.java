/*
 * Copyright (C) 2015 Ismael
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package consola;

/**
 * Clase Utils: Contiene metodos aleatorios, que aun no tienen una categoria
 * definida
 *
 * @author Ismael Concha Villaseca
 * @version 1.2.15.06.09
 */

public class Util {

    /**
     * Metodo mayusculas: Convierte a mayusculas el texto recibido y luego lo
     * retorna
     *
     * @param texto Texto a convertir a mayusculas
     * @return Retorna el texto convertido a mayusculas
     */
    public static String mayusculas(String texto) {
        return texto.toUpperCase();
    }

    /**
     * Metodo mayusculas: Convierte a mayusculas el caracter recibido y luego lo
     * retorna
     *
     * @param c Caracter a convertir a mayusculas
     * @return Retorna el caracter convertido a mayusculas
     */
    public static char mayusculas(char c) {
        return Character.toUpperCase(c);
    }

    /**
     * Metodo minusculas: Convierte a minusculas el texto recibido y luego lo
     * retorna
     *
     * @param texto Texto a convertir a minusculas
     * @return Retorna el texto convertido a minusculas
     */
    public static String minusculas(String texto) {
        return texto.toLowerCase();
    }

    /**
     * Metodo minusculas: Convierte a minusculas el caracter recibido y luego lo
     * retorna
     *
     * @param c Caracter a convertir a minusculas
     * @return Retorna el caracter convertido a minusculas
     */
    public static char minusculas(char c) {
        return Character.toLowerCase(c);
    }

    /**
     * Metodo azar: genera un numero aleatorio positivo entre 0 y max
     *
     * @param max maximo numero que se puede generar
     * @return Retorna el numero generado
     */
    public static int azar(int max) {
        return (int) ((max + 1) * Math.random());
    }

    /**
     * Metodo azar: genera un numero aleatorio positivo entre min y max
     *
     * @param min minimo numero generado
     * @param max maximo numero que se puede generar
     * @return Retorna el numero generado
     */
    public static int azar(int min, int max) {
        return (int) ((max - min + 1) * Math.random() + min);
    }

    /**
     * Metodo Comparar: realiza una comparacion entre la cadena a y la cadena b,
     * retornando true en caso de que sean iguales, en cualquier otro caso
     * retorna false
     *
     * @param a Cadena a comparar
     * @param b Cadena a comparar
     * @return true si son iguales, false en cualquier otro caso
     */
    public static boolean comparar(String a, String b) {
        return mayusculas(a).equals(mayusculas(b));
    }

    /**
     * Metodo Comparar: realiza una comparacion entre el caracter a y el
     * caracter b, retornando true en caso de que sean iguales, en cualquier
     * otro caso retorna false
     *
     * @param a Caracter a comparar
     * @param b Caracter a comparar
     * @return true si son iguales, false en cualquier otro caso
     */
    public static boolean comparar(char a, char b) {
        return mayusculas(a) == mayusculas(b);
    }

    /**
     * Metodo que se encarga de invertir el orden de los datos en el arreglo que
     * recibe por parametro.
     *
     * Ej: import Console.Utils; public static void main(String[] args) { char[]
     * data= new char[]{'a','e','i','o','u'}; //declaramos un arreglo de tipo
     * char, e inicializamos el mismo con a,e,i,o,u.
     * data=Utils.reverse(data);//llamamos al metodo reverse y asignamos el
     * valor devuelto a nuestro arreglo "data". for(char a : data) //recorremos
     * el arreglo "data". System.out.println(a); //imprimimos en pantalla los
     * datos. }
     *
     * @param array Arreglo a invertir.
     * @return retorna el arreglo invertido.
     */
    public static Object[] reverse(Object[] array) {
        Object[] ret = new Object[array.length];
        for (int x = 0, y = array.length - 1; x != array.length && y != -1; x++, y--) {
            ret[x] = array[y];
        }
        return ret;
    }

}
