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

import consola.Preguntar;
import consola.Salida;
import consola.Util;
import estructuras.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @version 1.0
 * @author Ismael
 */
public class DinamicMenu2 {

    private final Object main;
    private final Lista<Lis<Integer, Character, String, Method>> ss;
    private boolean isCharEnabled;
    private boolean isGodModeEnabled;
    private boolean debug = false;
    private boolean extracalls = false;

    public DinamicMenu2(Object main, String... opcs) {
        this.main = main;
        this.ss = new Lista<>();
        for (int i = 0;i<opcs.length;i++) {
            if (opcs[i].startsWith("$")) {
                LoadOptions(opcs[i]);
            } else if (!opcs[i].isEmpty()) {
                LoadString(opcs[i]);
            }
        }
        ss.add(new Lis<>(ss.size() + 1, 'S', "Salir", null));
    }

    private void LoadOptions(String data) {
        if (data.startsWith("$char")) {
            isCharEnabled = data.endsWith(":Enabled");
        }
        if (data.startsWith("$godmode")) {
            isGodModeEnabled = data.endsWith(":Enabled");
        }
        if(data.startsWith("$debug")) {
            debug = data.endsWith(":Enabled");
        }
        if(data.startsWith("$extracalls")) {
            extracalls = data.endsWith(":Enabled");
        }
    }

    private void LoadString(String data) {
        StringBuilder chars = new StringBuilder();
        char c = '.';
        ss.forEach((Lis<Integer, Character, String, Method> v) -> {
            chars.append(Util.mayusculas(v.b));
        });
        if(chars.length()==0) {
            for(char v : data.toCharArray()) {
                if(!Util.comparar(v, 'S')) {
                    c=v;
                    break;
                }
            }
        } else {
            for(char v : data.toCharArray()) {
                if(!Util.comparar(v, 'S')&&!chars.toString().contains(Util.mayusculas(""+v))) {
                    c=v;
                    break;
                }
            }
        }
        try {
            Method m = main.getClass().getDeclaredMethod(getMethodName(data));
            ss.add(new Lis<>(ss.size() + 1, c, data, m));
        } catch (NoSuchMethodException e) {
            ss.add(new Lis<>(ss.size() + 1, c, data, null));
        }
    }

    private String getMethodName(String opc) {
        ArrayList<String> var = new ArrayList();
        String ret = "";
        var.addAll(Arrays.asList(opc.split(" ")));
        ret = var.stream()
                .map((v) -> consola.Util.mayusculas(v.charAt(0)) + v.substring(1))
                .reduce(ret, String::concat);
        return ret;
    }

    public boolean Show() {
        ArrayList<String> validAnswers = new ArrayList<>();
        for (int i = 0; i < 45; i++, Salida.escribirSinSaltar("-"));
        this.ss.forEach((Lis<Integer, Character, String, Method> var) -> {
            validAnswers.add(var.b.toString());
            Salida.escribirSinSaltar(
                    String.format("\n%d.- %s",
                            validAnswers.size(),
                            isCharEnabled
                                    ? var.d != null
                                            ? (var.c.replaceFirst(
                                                    var.b.toString(),
                                                    String.format("[%s]", consola.Util.mayusculas(var.b))))
                                            : (var.c.replaceFirst(
                                                    var.b.toString(),
                                                    String.format("[%s]", consola.Util.mayusculas(var.b)))
                                            .concat(
                                                    consola.Util.comparar(var.b, 'S')
                                                            ? "" : "  :  [Sin Codigo]"))
                                    : var.d != null
                                            ? (var.c) : var.c.concat(
                                                    consola.Util.comparar(var.b, 'S')
                                                            ? "" : "  :  [Sin Codigo]"))
            );
        });
        Salida.escribir();
        for (int i = 0; i < 15; i++, Salida.escribirSinSaltar("-"));
        String ret = Preguntar.string("Ingrese Opcion: ");
        if (consola.Util.comparar(ret, "root")) {
            if (isGodModeEnabled) {
                GodMode.load(main);//GODMODE ENTRY POINT
                return true;
            }
            Salida.err("Esta Aplicacion no permite el uso de este paquete");
            return true;
        }
        ArrayList<Lis<Integer, Character, String, Method>> callback = new ArrayList<>();
        if (isCharEnabled) {
            ss.forEach((Lis<Integer, Character, String, Method> var) -> {
                int respi = -1;
                try {
                    respi = Integer.parseInt(ret);
                    if (var.a == respi ) {
                        callback.add(var);
                    }
                } catch (NumberFormatException e) {
                    for (Character c : ret.toCharArray()) {
                        if (consola.Util.comparar(var.b, c)) {
                            callback.add(var);
                        }
                    }
                }
            });
        } else {
            int respi = -1;
            try {
                respi = Integer.parseInt(ret);
                if (ss.get(respi-1).a == respi) {
                    callback.add(ss.get(respi-1));
                }
            } catch (NumberFormatException e) {
                Salida.err("Solo se admiten numeros");
            }
        }

        for (Lis<Integer, Character, String, Method> v : callback) {
            try {
                if(callback.size()>1) Salida.escribir("Llamada a Opcion: "+v.c);
                v.d.invoke(main);
            } catch (IllegalAccessException e) {
                Salida.err("La Opcion Ingresada no pudo ser Ejecutada");
                if(debug) e.printStackTrace();
            } catch (InvocationTargetException e) {
                Salida.err("La Opcion Ejecutada ha fallado, reintente...");
                if(debug) e.printStackTrace();
            } catch (NullPointerException e) {
                if (consola.Util.comparar(v.b, 'S')) {
                    Method m;
                    try {
                        if(extracalls) {
                            m = main.getClass().getDeclaredMethod("ON_EXIT");
                            m.invoke(main);
                        }
                    } catch (NoSuchMethodException | InvocationTargetException er) {
                        Salida.err("La Opcion Ingresada aun no esta programada");
                        if(debug) er.printStackTrace();
                    } catch (IllegalAccessException er) {
                        Salida.err("No tienes los permisos para ejecutar este codigo");
                        if(debug) er.printStackTrace();
                    }
                    return false;
                } else {
                    Method m;
                    try {
                        if(extracalls) {
                            m = main.getClass().getDeclaredMethod("ON_NULL_METHOD", String.class);
                            m.invoke(main, v.c);
                        }
                    } catch (NoSuchMethodException | InvocationTargetException er) {
                        Salida.err("La Opcion Ingresada aun no esta programada");
                        if(debug) er.printStackTrace();
                    } catch (IllegalAccessException er) {
                        Salida.err("No tienes los permisos para ejecutar este codigo");
                        if(debug) er.printStackTrace();
                    }
                }
            }
        }
        return true;
    }

    public void Run() {
        while (Show());
    }

    private class Lis<A, B, C, D> {

        private A a;
        private B b;
        private C c;
        private D d;

        public Lis(A a, B b, C c, D d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }

        public A getA() {
            return a;
        }

        public void setA(A a) {
            this.a = a;
        }

        public B getB() {
            return b;
        }

        public void setB(B b) {
            this.b = b;
        }

        public C getC() {
            return c;
        }

        public void setC(C c) {
            this.c = c;
        }

        public D getD() {
            return d;
        }

        public void setD(D d) {
            this.d = d;
        }

    }
}
