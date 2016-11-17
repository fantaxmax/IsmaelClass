/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus.dynamic;

import estructuras.Lista;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author cetecom
 */
public class MenuItem {
    private Object main;
    private String name;
    private char character;
    private int code;
    private Method method;
    private Field field;
    private Lista<String> questions;

    public MenuItem(String name, char character, int code, Object main) {
        this.name = name;
        this.character = character;
        this.code = code;
        this.main = main;
        method = getMethod(name);
    }

    public MenuItem(String name, char character, int code, Method method, Object main) {
        this.name = name;
        this.character = character;
        this.code = code;
        this.method = method;
        this.main = main;
    }

    /*
    UNDER CONSTRUCTION    
    */
    private MenuItem(String name, char character, int code, Field field, Lista<String> questions, Object main) {
        this.name = name;
        this.character = character;
        this.code = code;
        this.field = field;
        this.questions = questions;
        this.main = main;
    }
    
    private String getMethodName(String name) {
        ArrayList<String> var = new ArrayList();
        String ret = "";
        var.addAll(Arrays.asList(name.split(" ")));
        ret = var.stream()
                .map((v) -> consola.Util.mayusculas(v.charAt(0)) + v.substring(1))
                .reduce(ret, String::concat);
        return ret;
    }
    
    private Method getMethod(String name) {
        try {
            return main.getClass().getDeclaredMethod(getMethodName(name));
        } catch(NoSuchMethodException e) {
            return null;
        }
    }
    
    public String toString() {
        return code + ".- " + name + (method!=null ? "" : (field==null ? " : [Sin Codigo]" : ""));
    }
    
    private String getString(String type) {
        for(String s : questions) {
            if(s.startsWith(type))
                return s.substring(s.indexOf(":"));
        }
        return "";
    }
    
    public void Exec() throws IllegalAccessException, InvocationTargetException {
        if(method != null) {
            method.invoke(main);
        } //else {
//            if(field!=null) {
//                UNDER CONSTRUCTION
//            }
//        }
    }
}
