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

import java.io.*;

/**
 * Clase Leer: Contiene metodos para realizar la lectura de datos ingresados por
 * el usuario
 *
 * @author Anonimo(clase "leer") e Ismael Concha Villaseca
 * @version 1.2.15.06.09
 */
public class Leer {

    /**
     * Metodo leer: Realiza la lectura de una cadena ingresada por el usuario
     *
     * @return Retorna la cadena ingresada por el usuario
     */
    public static String string() {
        String sdato = "";
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader flujoe = new BufferedReader(isr);
            sdato = flujoe.readLine();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        if(sdato.compareToIgnoreCase("#TERMINATE#")==0)
            System.exit(-1);
        return (sdato);
    }

    /**
     * Metodo Char: Realiza la lectura de un caracter ingresado por el usuario,
     * si existe algun error lanzara una excepcion de tipo
     * IndexOutOfBoundsException
     *
     * @return Retorna el caracter ingresado por el usuario
     * @throws IndexOutOfBoundsException Lanza esta excepcion cuando el usuario
     * no ingresa ningun caracter
     *
     */
    public static char Char() throws IndexOutOfBoundsException {
        return string().charAt(0);
    }

    /**
     * Metodo Int: Realiza la lectura de un entero ingresado por el usuario, si
     * el numero tiene mal formato, lanzara una excepcion de tipo
     * NumberFormatException
     *
     * @return Retorna el entero ingresado por el usuario
     * @throws NumberFormatException Lanza esta excepcion cuando el numero
     * ingresado no corresponde a un numero valido
     */
    public static int Int() throws NumberFormatException {
        return (Integer.parseInt(string()));
    }

    /**
     * Metodo Byte: Realiza la lectura de un byte ingresado por el usuario, si
     * el numero tiene mal formato, lanzara una excepcion de tipo
     * NumberFormatException
     *
     * @return Retorna el byte ingresado por el usuario
     * @throws NumberFormatException Lanza esta excepcion cuando el numero
     * ingresado no corresponde a un numero valido
     */
    public static byte Byte() throws NumberFormatException {
        return (Byte.parseByte(string()));
    }

    /**
     * Metodo Boolean: Realiza la lectura de un boolean ingresado por el
     * usuario, si el booleano tiene mal formato, retorna false, se considera
     * como valido las palabras true o false
     *
     * @return Retorna el booleano ingresado por el usuario
     */
    public static boolean Boolean() {
        return (Boolean.parseBoolean(string()));
    }

    /**
     * Metodo Boolean: Realiza la lectura de un boolean ingresado por el
     * usuario, si el booleano tiene mal formato, retorna false, se considera
     * como valido los caracteres de verdadero y falso
     *
     * @param verdadero Caracter valido para true
     * @param falso Caracte valido para false
     * @return Retorna el booleano ingresado por el usuario
     */
    public static boolean Boolean(char verdadero, char falso) {
        char lectura = Character.toUpperCase(Char());
        if (lectura == Character.toUpperCase(verdadero)) {
            return true;
        } else if (lectura == Character.toUpperCase(falso)) {
            return false;
        }
        return false;
    }

    /**
     * Metodo Boolean: Realiza la lectura de un boolean ingresado por el
     * usuario, si el booleano tiene mal formato, retorna false, se considera
     * como valido las cadenas de verdadero y falso
     *
     * @param verdadero Cadena valida para true
     * @param falso Cadena valida para false
     * @return Retorna el booleano ingresado por el usuario
     */
    public static boolean Boolean(String verdadero, String falso) {
        String lectura = string().toUpperCase();
        if (lectura.equals(verdadero.toUpperCase())) {
            return true;
        } else if (lectura.equals(falso.toUpperCase())) {
            return false;
        }
        return false;
    }

    /**
     * Metodo Short: Realiza la lectura de un valor de tipo short, si el numero
     * tiene mal formato, lanzara una excepcion de tipo NumberFormatException
     *
     * @return Retorna el short ingresado por usuario
     * @throws NumberFormatException Lanza esta excepcion cuando el numero
     * ingresado no corresponde a un numero valido
     */
    public static short Short() throws NumberFormatException {
        return (Short.parseShort(string()));
    }

    /**
     * Metodo Long: Realiza la lectura de un valor de tipo Long, si el numero
     * tiene mal formato, lanzara una excepcion de tipo NumberFormatException
     *
     * @return Retorna el short ingresado por usuario
     * @throws NumberFormatException Lanza esta excepcion cuando el numero
     * ingresado no corresponde a un numero valido
     */
    public static long Long() throws NumberFormatException {
        return (Long.parseLong(string()));
    }

    /**
     * Metodo Float: Realiza la lectura de un valor de tipo coma flotante, si el
     * numero tiene mal formato, lanzara un excepcion de tipo
     * NumberFormatException
     *
     * @return Retorna el float ingresado por usuario
     * @throws NumberFormatException Lanza esta excepcion cuando el numero
     * ingresado no corresponde a un numero valido
     */
    public static float Float() throws NumberFormatException {
        return new Float(string());
    }

    /**
     * Metodo Double: Realiza la lectura de un valor de tipo doble, si el numero
     * tiene mal formato, lanzara una excepcion de tipo NumberFormatException
     *
     * @return Retorna el double ingresado por usuario
     * @throws NumberFormatException Lanza esta excepcion cuando el numero
     * ingresado no corresponde a un numero valido
     */
    public static double Double() throws NumberFormatException {
        return new Double(string());
    }
}
