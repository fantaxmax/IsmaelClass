/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus.dynamic;

import consola.Salida;
import estructuras.Lista;

/**
 *
 * @author cetecom
 */
public class Menu {
    private Lista<MenuItem> items;
    private Object main;
    private boolean isCharEnabled;
    private boolean isGodModeEnabled;
    private boolean debug = false;
    private boolean extracalls = false;
    
    public Menu(Object main, String... opcs) {
        this.main = main;
        this.items = new Lista<>();
        for (String opc : opcs) {
            if (opc.startsWith("$")) {
                LoadOptions(opc);
            } else if (!opc.isEmpty()) {
                MenuItem m = LoadString(opc);
                if(m!=null) items.add(m);
            }
        }
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
    
    private MenuItem LoadString(String opc) {
        String chars = "";
        for(MenuItem mi : items) chars += mi.getCharacter();
        for(char c : opc.toCharArray()) {
            if(c!='S'&!chars.contains(""+c)) {
                return new MenuItem(opc,c,items.size()+1,main);
            }
        }
        return null;
    }
    
    public boolean Exec() {
        Salida.impresion(items.toArray());
        String answer = "";
        Lista<MenuItem> calls = new Lista<>();
        
    }
}
