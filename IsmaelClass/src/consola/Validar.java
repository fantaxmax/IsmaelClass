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
 * Clase para Validar Ingreso de datos
 * 
 * @author Ismael Concha Villaseca
 * @version 2.0.15.08.10
 * @see consola.Preguntar
 * @see consola.Salida
 */
public class Validar
{
    /**
     * Metodo que retorna el numero ingresado por el usuario despues de ser validado.
     * Toma como valores validos los numeros comprendidos entre min y max
     * 
     * @param min Es el minimo numero que se puede ingresar
     * @param max Es el maximo numero que se puede ingresar
     * @param pregunta Es el mensaje a mostrar pidiendo el ingreso de un numero
     * @return Retorna el numero ingresado por el usuario
     */
    public static int Int(String pregunta, int min, int max)
    {
        Integer resp=null;
        do
        {
            try
            {
                resp = Preguntar.Int(pregunta);
                if(resp<min || resp > max)
                    Salida.escribir("Error: Indice fuera de rango min: "+min+" max: "+max);
            }
            catch(NumberFormatException error)
            {
                Salida.err("El numero ingresado no es valido");
                resp=null;
            }
        }while(resp==null||resp<min || resp > max);
        return resp;
    }
    
    public static double Double(String pregunta, double min, double max)
    {
        Double resp=null;
        do
        {
            try
            {
                resp = Preguntar.Double(pregunta);
                if(resp<min || resp > max)
                    Salida.escribir("Error: Indice fuera de rango min: "+min+" max: "+max);
            }
            catch(NumberFormatException error)
            {
                Salida.err("El numero ingresado no es valido");
                resp=null;
            }
        }while(resp==null||resp<min || resp > max);
        return resp;
    }
    
    public static float Float(String pregunta, float min, float max)
    {
        Float resp=null;
        do
        {
            try
            {
                resp = Preguntar.Float(pregunta);
                if(resp<min || resp > max)
                    Salida.escribir("Error: Indice fuera de rango min: "+min+" max: "+max);
            }
            catch(NumberFormatException error)
            {
                Salida.err("El numero ingresado no es valido");
                resp=null;
            }
        }while(resp==null||resp<min || resp > max);
        return resp;
    }
    
    public static long Long(String pregunta, long min, long max)
    {
        Long resp=null;
        do
        {
            try
            {
                resp = Preguntar.Long(pregunta);
                if(resp<min || resp > max)
                    Salida.escribir("Error: Indice fuera de rango min: "+min+" max: "+max);
            }
            catch(NumberFormatException error)
            {
                Salida.err("El numero ingresado no es valido");
                resp=null;
            }
        }while(resp==null||resp<min || resp > max);
        return resp;
    }
    
    public static byte Byte(String pregunta, byte min, byte max)
    {
        Byte resp=null;
        do
        {
            try
            {
                resp = Preguntar.Byte(pregunta);
                if(resp<min || resp > max)
                    Salida.escribir("Error: Indice fuera de rango min: "+min+" max: "+max);
            }
            catch(NumberFormatException error)
            {
                Salida.err("El numero ingresado no es valido");
                resp=null;
            }
        }while(resp==null||resp<min || resp > max);
        return resp;
    }
    
    public static short Short(String pregunta, short min, short max)
    {
        Short resp=null;
        do
        {
            try
            {
                resp = Preguntar.Short(pregunta);
                if(resp<min || resp > max)
                    Salida.escribir("Error: Indice fuera de rango min: "+min+" max: "+max);
            }
            catch(NumberFormatException error)
            {
                Salida.err("El numero ingresado no es valido");
                resp=null;
            }
        }while(resp==null||resp<min || resp > max);
        return resp;
    }
    /**
     * Metodo que retorna el caracter ingresado por el usuario despues de ser validado.
     * Toma como caracteres validos, todos aquellos que esten en la matriz que recibe
     * 
     * @param opcs Matriz de caracteres validos
     * @param pregunta Es el mensaje a mostrar pidiendo el ingreso de un caracter
     * @return Retorna el caracter, en mayuscula, ingresado por el usuario
     */
    public static char Char(String pregunta, Character... opcs)
    {
        char resp;
        do
        {
            resp = Util.mayusculas(Preguntar.Char(pregunta));
            for(char a : opcs)
            {
                if(Util.mayusculas(a)==resp)
                    return resp;
            }
            Salida.escribir("Error: Respuesta invalida");
        }while(true);
    }
    /**
     * Metodo que retorna la cadena ingresada por el usuario despues de ser validada.
     * Toma como respuesta valida, todas las cadenas de la matriz
     * 
     * @param opcs Matriz de cadenas validas
     * @param pregunta Es el mensaje a mostrar pidiendo el ingreso de una cadena
     * @return Retorna la cadena ingresada por el usuario, pero en mayuscula
     */
    public static String string(String pregunta,Object... opcs)
    {
        String resp;
        do
        {
            resp = Util.mayusculas(Preguntar.string(pregunta));
            for(Object a : opcs)
            {
                if(Util.mayusculas(a.toString()).equals(resp))
                    return resp;
            }
            Salida.escribir("Error: Respuesta invalida");
        }while(true);
    }
    /**
     * Metodo que retorna un booleano ingresado por el usuario despues de ser validado.
     * Toma como caracteres validos, el caracter de v y el de f
     * 
     * @param v Caracter que representa Verdadero
     * @param f Caracter que representa Falso
     * @param pregunta Es el mensaje a mostrar pidiendo el ingreso de una respuesta
     * @return Retorna true o false, según lo ingresado por el usuario
     */
    public static boolean bool(String pregunta, char v, char f)
    {
        char resp;
        do
        {
            resp = Util.mayusculas(Preguntar.Char(pregunta));
            if(resp==Util.mayusculas(v))
                return true;
            else if(resp==Util.mayusculas(f))
                return false;
            else
                Salida.escribir("Error: Respuesta invalida");
        }while(true);
    }
     /**
     * Metodo que retorna un booleano ingresado por el usuario despues de ser validado.
     * Toma como caracteres validos, las cadenas v y f
     * 
     * @param v Cadena que representa Verdadero
     * @param f Cadena que representa Falso
     * @param pregunta Es el mensaje a mostrar pidiendo el ingreso de una respuesta
     * @return Retorna true o false, según lo ingresado por el usuario
     */
    public static boolean bool(String pregunta, String v, String f)
    {
        String resp;
        do
        {
            resp = Util.mayusculas(Preguntar.string(pregunta));
            if(resp.equals(Util.mayusculas(v)))
                return true;
            else if(resp.equals(Util.mayusculas(f)))
                return false;
            else 
                Salida.escribir("Error: Respuesta invalida");
        }while(true);
    }
     /**
     * Metodo que retorna una cadena ingresada por el usuario despues de ser validada.
     * La cadena es valida siempre que la longitud de la misma sea igual o mayor que el parametro min
     * 
     * @param min Parametro que indica el largo minimo de la cadena que debe ser ingresada
     * @param pregunta Es el mensaje a mostrar pidiendo el ingreso de una cadena
     * @return Retorna la cadena ingresada por el usuario, en mayucula
     */
    public static String longitud(String pregunta, int min)
    {
        String resp;
        do
        {
            resp = Util.mayusculas(Preguntar.string(pregunta));
            if(resp.length()>=min)
                return resp;
            else
                Salida.escribir("Error: Longitud minima no alcanzada");
        }while(true);
    }
    /**
     * Metodo que valida un R.U.T, en formato de cadena.
     * Este debe estar en el rango de 100.000-X y 99.999.999-X, sin puntos y con guion
     * 
     * @param pregunta Es el mensaje a mostrar pidiendo el ingreso del R.U.T.
     * @return Retorna el R.U.T. ingresado por el usuario
     */
    public static String rut(String pregunta)
    {
        String resp;
        do
        {
            resp = Preguntar.string(pregunta);
            try
            {
                Integer.parseInt(resp.substring(0,resp.length()-3));
                if((resp.length()<=10&&resp.length()>=8)&&resp.charAt(resp.length()-2)=='-')
                    return resp;
                else
                    Salida.escribir("Error: Formato invalido");
            }
            catch(NumberFormatException e)
            {
                Salida.escribir("Error: Formato invalido");
            }
        }while(true);
    }
    /**
     * Metodo que valida que se ingresen al menos tres palabras
     * @param pregunta mensaje a mostrar cuando se llame al metodo
     * @return retorna el dato ingresado por el usuario
     */
    public static String nombres(String pregunta)
    {
        String resp;
        do
        {
            resp = Preguntar.string(pregunta);
            if(resp.split(" ").length>=3)
                return resp;
            Salida.escribir("Error: El nombre debe tener al menos 3 palabras");
        }while(true);
    }
}