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
 * Clase Pregunta, realiza una impresion de una pregunta, y retorna el dato
 * ingresado por el usuario
 *
 * @author Ismael Concha Villaseca
 * @version 1.1.14.01.25
 * @see consola.Leer
 * @see consola.Salida
 */
public class Preguntar {

    /**
     * Metodo string: Realiza una pregunta para luego leer una respuesta de tipo
     * cadena
     *
     * @param pregunta Pregunta a mostrar en pantalla
     * @return Retorna la cadena ingresada por el usuario
     */
    public static String string(String pregunta) {
        Salida.escribirSinSaltar(pregunta);
        return Leer.string();
    }

    /**
     * Metodo Char: Realiza una pregunta para luego leer una respuesta de tipo
     * caracter
     *
     * @param pregunta Pregunta a mostrar en pantalla
     * @return Retorna el caracter ingresado por el usuario
     */
    public static char Char(String pregunta) {
        Salida.escribirSinSaltar(pregunta);
        return Leer.Char();
    }

    /**
     * Metodo Int: Realiza una pregunta para luego leer una respuesta de tipo
     * numerico entero
     *
     * @param pregunta Pregunta a mostrar en pantalla
     * @return Retorna el entero ingresado por el usuario
     */
    public static int Int(String pregunta) {
        Salida.escribirSinSaltar(pregunta);
        return Leer.Int();
    }

    /**
     * Metodo Double: Realiza una pregunta para luego leer una respuesta de tipo
     * numerico double
     *
     * @param pregunta Pregunta a mostrar en pantalla
     * @return Retorna el double ingresado por el usuario
     */
    public static double Double(String pregunta) {
        Salida.escribirSinSaltar(pregunta);
        return Leer.Double();
    }

    /**
     * Metodo Float: Realiza una pregunta para luego leer una respuesta de tipo
     * numerico de punto flotante
     *
     * @param pregunta Pregunta a mostrar en pantalla
     * @return Retorna el float ingresada por el usuario
     */
    public static float Float(String pregunta) {
        Salida.escribirSinSaltar(pregunta);
        return Leer.Float();
    }

    /**
     * Metodo Long: Realiza una pregunta para luego leer una respuesta de tipo
     * long
     *
     * @param pregunta Pregunta a mostrar en pantalla
     * @return Retorna el long ingresado por el usuario
     */
    public static long Long(String pregunta) {
        Salida.escribirSinSaltar(pregunta);
        return Leer.Long();
    }

    /**
     * Metodo Short: Realiza una pregunta para luego leer una respuesta de tipo
     * short
     *
     * @param pregunta Pregunta a mostrar en pantalla
     * @return Retorna el short ingresado por el usuario
     */
    public static short Short(String pregunta) {
        Salida.escribirSinSaltar(pregunta);
        return Leer.Short();
    }

    public static byte Byte(String pregunta) {
        Salida.escribirSinSaltar(pregunta);
        return Leer.Byte();
    }

    /**
     * Metodo Boolean: Realiza una pregunta para luego leer una respuesta de
     * tipo booleano. se tomaran como validas las cadenas true o false, en caso
     * contrario retornara false
     *
     * @param pregunta Pregunta a mostrar en pantalla
     * @return Retorna el booleano ingresado por el usuario
     */
    public static boolean Boolean(String pregunta) {
        Salida.escribirSinSaltar(pregunta);
        return Leer.Boolean();
    }

    /**
     * Metodo Boolean: Realiza una pregunta para luego leer una respuesta de
     * tipo booleano. se tomaran como validos los caracteres de v y de f, en
     * caso contrario retornara false
     *
     * @param pregunta Pregunta a mostrar en pantalla
     * @param v Caracter valido para true
     * @param f Caracter valido para false
     * @return Retorna el booleano ingresado por el usuario
     */
    public static boolean Boolean(String pregunta, char v, char f) {
        Salida.escribirSinSaltar(pregunta);
        return Leer.Boolean(v, f);
    }

    /**
     * Metodo Boolean: Realiza una pregunta para luego leer una respuesta de
     * tipo booleano. se tomaran como validos las cadenas de v y de f, en caso
     * contrario retornara false
     *
     * @param pregunta Pregunta a mostrar en pantalla
     * @param v Cadena valida para true
     * @param f Cadena valida para false
     * @return Retorna el booleano ingresado por el usuario
     */
    public static boolean Boolean(String pregunta, String v, String f) {
        Salida.escribirSinSaltar(pregunta);
        return Leer.Boolean(v, f);
    }

}
