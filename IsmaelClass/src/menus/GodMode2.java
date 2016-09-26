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
package menus;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import consola.*;
/**
 * En Construccion...
 * @author Ismael
 */
public class GodMode2 extends Main {
    private Object object;
    private ArrayList<Field> fields = new ArrayList<>();
    private ArrayList<Method> methods = new ArrayList<>();
    
    public GodMode2(Object object)
    {
        menuopc.addAll(Arrays.asList(new String[]{"Mostar Atributos","Modificar Atributos","Ejecutar a X metodo","Modificar un atributo de Objeto"}));
        args="isCharEnabled=true;isGodMode=true;name=user@"+object.getClass().getName()+":~;design=#;";
        this.object = object;
        fields.addAll(Arrays.asList(this.object.getClass().getFields()));
        methods.addAll(Arrays.asList(this.object.getClass().getMethods()));
        
    }
    
    public static void load(Object obj) {
        Menu m = new Menu(new GodMode2(obj));
        m.Run();
    }
    
    public void Opc1()//Mostrar Atributos
    {
        if(fields.isEmpty()){
            Salida.escribir("No existen Atributos Visibles");
        } else {
            Salida.escribir("\n---------------------------------");
            fields.forEach((Field field) -> {
                try {
                    Object obj = field.get(object);
                    if(obj==null){
                        Salida.escribir(field.getName()+"=null");
                    } else if(field.getType().isArray() || field.getType() == ArrayList.class) {
                        
                    }
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    
                }
            });
        }
            
    }
   
}
