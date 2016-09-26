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
 * Clase para realizar la salida de texto por consola.
 *
 * @author Ismael Concha Villaseca
 * @version 1.1.15.06.09
 */
public class Salida {

    /**
     * Metodo escribir: Realiza una impresion en pantalla del texto recibido por
     * parametro, pasando a la siguiente linea inmediatamente terminado el
     * texto.
     *
     * @param texto Es el texto a imprimir en la pantalla
     */
    public static void escribir(Object texto) {
        System.out.println(texto.toString());
    }

    /**
     * Metodo escribir: Realiza un salto de linea
     */
    public static void escribir() {
        System.out.println();
    }

    /**
     * Metodo escribirSinSaltar: Realiza una impresion en pantalla del texto
     * recibido por parametro, una vez terminado el texto no realiza un salto de
     * linea.
     *
     * @param texto Es el texto a imprimir en la pantalla
     */
    public static void escribirSinSaltar(String texto) {
        System.out.print(texto);
    }

    public static void err(String texto) {
        System.err.println("Error: " + texto);
    }

    /**
     * Metodo impresion: Similar a menu, pero esta orientado a mostrar datos
     *
     * @param nombres Datos a mostrar
     */
    public static void impresion(Object[] nombres) {
        System.out.println("\n--------------------------------------------");
        for (Object nombre : nombres) {
            System.out.println(nombre);
        }
        System.out.println("--------------------------------------------\n\n");
    }

}
