//nota personal: actualizar Documentacion, optimizar codigo, retirar variables sin uso
package menus;
import consola.Preguntar;
import consola.Salida;
import consola.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Clase Menu, Evolucion del metodo menu de la clase Output.
 *<p> Modo de uso:
 *<p> 1.- se debe crear un objeto de tipo Menu, enviando los parametros necesarios
 *<p> 2.- llamar al metodo Load() si es necesario realizar acciones antes de ejecutar el menu
 *<p> 3.- entrar a un loop con la instruccion Show(), con esto el menu se mostrara hasta que se ingrese la opcion Salir
 *<p> 4.- si se debe realizar algo mas antes de salir, se llamara al metodo Exit() del Main automaticamente
 * 
 * <p>PD: esta clase debe recibir un objeto de tipo Main, en su App debe implementar la extender el objeto Main de este mismo Package, para que esta clase funcione correctamente
 * 
 * @author Ismael Concha Villaseca
 * @version 2.0.15.06.09
 */
public class Menu {
    private Main main;
    private List menuopc=new ArrayList();
    private List menuchar=new ArrayList();
    private Map args = new HashMap();
    public Menu(Main main)
    {
        this.main=main;
        arg_init();
        if(args.get("isCharEnabled").toString().equals("true"))
        {
            this.main.menuopc.stream().forEach((s) -> {
                menuopc.add(OpcChar(s));
            });
            menuopc.add(OpcChar("Salir"));
        }
        else
        { 
            menuopc=this.main.menuopc;
            menuopc.add("Salir");
        }
        
    }
    
    public Menu(Main main,char[] chars)
    {
        this.main=main;
        arg_init();
        args.replace("isCharEnabled", "true");
        for(int i=0;i<=this.main.menuopc.size();i++)
            menuopc.add(OpcChar(new String[]{this.main.menuopc.get(i),chars[i]+""}));
        menuopc.add("Salir");
    }
    
    private void arg_init()
    {
        for(String s : this.main.args.split(";"))
            if(!s.isEmpty())
                args.put(s.split("=")[0], s.split("=")[1]);
        if(!args.containsKey("isCharEnabled"))
            args.put("isCharEnabled","true");
        if(!args.containsKey("name"))
            args.put("name", "");
        if(!args.containsKey("design"))
            args.put("design", "-");
        if(!args.containsKey("isGodMode"))
            args.put("isGodMode", "false");
    }
    /**
     * Metodo que muestra el menu de usuario, este metodo acepta el numero de opcion como el caracter seleccionado en caso de existir
     * (para que funcione con caracteres, debe enviar un arreglo Bidimensional por parametro al constructor)
     * @return retorna true en caso que se deba volver a ejecutar el menu, false en caso de que el usuario elija salir.
     */
    public boolean Show()
    {
        try
        {
            int esc_int=menuopc.size(); 
            System.out.println("\n\t\t"+args.get("name"));
            for(int i=0;i<40;i++)
                System.out.print(args.get("design"));
            System.out.println();
            for(int i=0;i!=menuopc.size();i++)
                System.out.println((i+1)+" - "+menuopc.get(i));
            int var = 0;String var1;
            do
            {
                boolean flag=true;
                do{

                    for(int i=0;i<15;i++)
                        System.out.print(args.get("design"));
                    var1 = Preguntar.string("Ingrese Opcion: ");
                    if(var1.length()==0)
                        Salida.err("Error: Debe Ingresar al menos un caracter.");
                }while(var1.length()==0);
                if(var1.equalsIgnoreCase("godmode")|var1.equalsIgnoreCase("root"))
                {
                    var=-1;
                    break;
                }
                if(args.get("isCharEnabled").toString().equals("true"))
                    for(int i=0;i<menuchar.size();i++)
                        if(Util.comparar(var1.charAt(0), (char)menuchar.get(i)))
                            var1=""+(i+1);
                try{var=Integer.parseInt(var1);}
                catch(NumberFormatException e)
                {
                    if(var1.length()==1)
                        Salida.err("Error: Ingreso un Caracter no Valido.");
                    if(var1.length()>1)
                        Salida.err("Error: Solo se acepta un caracter.");
                    flag=false;
                }
                if(flag&&(var<1 || var > menuopc.size()))
                    Salida.err("Error: Indice fuera de rango min: "+1+" max: "+menuopc.size()+".");
            }while(var<1 || var > menuopc.size());
            if(var!=esc_int)
            {
                switch(var)
                {
                    case 1:
                        main.Opc1();
                        break;
                    case 2:
                        main.Opc2();
                        break;
                    case 3:
                        main.Opc3();
                        break;
                    case 4:
                        main.Opc4();
                        break;
                    case 5:
                        main.Opc5();
                        break;
                    case 6:
                        main.Opc6();
                        break;
                    case 7:
                        main.Opc7();
                        break;
                    case 8:
                        main.Opc8();
                        break;
                    case -1:
                        if(args.get("isGodMode").toString().equals("false"))
                            GodMode.load(main);
                        else
                            Salida.err("Ya estas en modo Root");
                        break;
                }
                return true;
            }
            main.Exit();
            return false;
        }
        catch(Exception error)
        {
            Salida.err(error.toString());
        }
        return true;
    }
    /**
     * Este metodo puede ser llamado si en la aplicacion se debe hacer algo antes de mostrar el menu.
     */
    public void Load()
    {
        main.Start();
    }
    
    public void Run()
    {
        Load();
        while(Show());
    }
    /**
     * Metodo privado por tanto, no puede ser llamado, sin embargo, asigna los caracteres a detectar por el metodo Menu.Show()
     * @param actual arreglo que contiene el nombre de la opcion y el caracter que lo representa
     * @return retorna el nuevo nombre de la opcion con el caracter representativo resaltada
     */
    private String OpcChar(String[] actual)
    {
        menuchar.add(actual[1].charAt(0));
        for(int i=0;i<actual[0].length();i++)
            if(consola.Util.comparar(actual[0].charAt(i), actual[1].charAt(0)))
                return actual[0].substring(0, i)+"["+actual[1]+"]"+actual[0].substring(i+1);
        menuchar.remove(actual[1].charAt(0));
        return actual[0];
    }
    private String OpcChar(String actual)
    {
        if(menuchar.isEmpty())
        {
            menuchar.add(actual.charAt(0));
            return "["+Util.mayusculas(actual.charAt(0))+"]"+actual.substring(1);
        }
        for(int index=0;index<actual.length();index++)
        {
            if(isChar(actual.charAt(index)))
            {
                menuchar.add(Util.mayusculas(actual.charAt(index)));
                return actual.substring(0,index)+"["+Util.mayusculas(actual.charAt(index))+"]"+actual.substring(index+1);
            }
        }
        return actual; 
    }
    private boolean isChar(char c)
    {
        return menuchar.stream().noneMatch((c1) -> (Util.comparar(c, (char) c1)));
    }
}
