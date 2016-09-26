package menus;
import consola.Preguntar;
import consola.Salida;
import consola.Util;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 *Clase DinamicMenu: esta clase permite crear menus "dinamicos".Clase Experimental, podria fallar
 * Modo de uso:
 * Creamos un objeto de tipo DinamicMenu, enviandole un objeto de la clase donde esta el metodo main, o de la clase donde se desea mostrar un menu
 * ademas de enviar las opciones, puede ser un arreglo de String o enviarlas por separado ej: DinamicMenu m = new DinamicMenu(new Main(),"Opcion 1","Opcion 2")
 * luego de eso debemos crear un loop con la instruccion Show ej while(m.Show())
 * @author Ismael Concha Villaseca
 * @version 0.2.14.01.25
 */
public class DinamicMenu { 
    private Object object;
    private List<String> menu=new ArrayList(),menu1=new ArrayList();
    private List<Character> chars=new ArrayList();
    private List<Method> methods=new ArrayList();
    private boolean isCharEnabled=true;
    /**
     *Constructor Principal y unico de esta Clase.
     * <p>Nota: la Opcion "Salir" se agrega automaticamente, al igual que la letra 's' esta reservada para la misma
     * <p>Nota 2: se debe declarar el metodo de tipo void start() en la clase, ya que este se llama como preparacion o para que se puedan agregar
     * instrucciones antes de ejecutar el menu, no hacerlo provocaria una Excepcion en tiempo de ejecucion, pero la ejecucion no se detendra
     * @param main Debe ser un objeto de la misma clase donde se llama a este constructor, recomiendo enviar por parametro esta instruccion "new Main()" donde Main es el nombre la clase
     * @param opcs este parametro recibe un arreglo con las opciones del menu o por separado
     */
    public DinamicMenu(Object main,String... opcs)
    {
        this.object=main;
        for(String var : opcs)
            menu.add(OpcChar(var));
        menu.add("[S]alir");
        for (String opc : opcs) {
            menu1.add(getMethodName(opc));
        }
        for(int i=0;i<opcs.length;i++)
            methods.add(getMethod(menu1.get(i)));
        chars.add('s');
    }
    public DinamicMenu(Object main,boolean isCharEnabled,String... opcs)
    {
        if(isCharEnabled){
            this.object=main;
            for(String var : opcs)
                menu.add(OpcChar(var));
            menu.add("[S]alir");
            for (String opc : opcs) {
                menu1.add(getMethodName(opc));
            }
            for(int i=0;i<opcs.length;i++)
                methods.add(getMethod(menu1.get(i)));
            chars.add('s');
        }
        else
        {
            this.object=main;
            menu.addAll(Arrays.asList(opcs));
            menu.add("Salir");
            for (String opc : opcs) {
                menu1.add(getMethodName(opc));
            }
            for(int i=0;i<opcs.length;i++)
                methods.add(getMethod(menu1.get(i)));
            this.isCharEnabled=false;
        }
    }
    
    public final String getMethodName(String opc)
    {
        ArrayList<String> var = new ArrayList();
        String ret = "";
        var.addAll(Arrays.asList(opc.split(" ")));
        ret = var.stream().map((v) -> Util.mayusculas(v.charAt(0))+v.substring(1)).reduce(ret, String::concat);
        return ret;
    }
    
    /**
     *Metodo Show: se encarga de mostrar, leer y ejecutar el menu.
     * <p>Se deben declarar los metodos en la clase que ejecuta el menu "dinamico" con las mismas palabras que en el parametro opcs del constructor
     * pero reemplazando mayusculas por minusculas, y espacios por guiones bajos, tambien permite ingresar a las opciones mediante el caracter que esta entre
     * corchetes, estos caracteres se asignan automaticamente, y pueden ingresarse en mayuscula como minuscula
     * <p>Ej: si creamos un objeto asi DinamicMenu m=new DinamicMenu(new Main(),"Opcion 1","Opcion 2","Opcion 3")
     * mostraria esto
     * <p>----------------------------------
     * <p>1 - [O]pcion 1
     * <p>2 - O[P]cion 2
     * <p>3 - Op[C]ion 3
     * <p>4 - [S]alir
     * <p>---------------Ingrese Opcion: 
     * <p>y llamaria estos metodos opcion_1(),opcion_2(),opcion_3() segun la opcion ingresada
     * si uno de los metodos no esta declarado se lanzara una excepcion y la ejecucion se detendra
     * <p>Nota: se debe declara el metodo de tipo void exit() ya que este se llama al salir del menu, si no es declarado se lanzara una excepcion
     * y la ejecucion se detendra de igual manera
     * <p>Nota 2: los metodos de las opciones deben ser publicos
     * @return retorna true en caso que no se haya ingresado la opcion salir, false en caso de que se ingrese salir o se lance una excepcion
     */
    public boolean Show()
    {
        try {
            System.out.println("\n----------------------------------------");
            for(int i=0;i!=menu.size();i++)
                System.out.println((i+1)+" - "+menu.get(i));
            int var = 0;String var1;
            do
            {
                boolean flag=true;
                do{
                    System.out.print("----------------");
                    var1 = Preguntar.string("Ingrese Opcion: ");
                    if(var1.length()==0&&isCharEnabled)
                        Salida.err("Error: Debe Ingresar al menos un caracter.");
                }while(var1.length()==0);
                if(var1.equalsIgnoreCase("godmode")||var1.equalsIgnoreCase("root"))
                {
                    GodMode.load(object);
                    return true;
                }
                if(isCharEnabled){
                    for(int i=0;i<chars.size();i++)
                        if(Util.comparar(var1.charAt(0), chars.get(i)))
                            var1=""+(i+1);
                }
                try{
                    var=Integer.parseInt(var1);
                }
                catch(NumberFormatException e)
                {
                    if(var1.length()==1&&isCharEnabled)
                        Salida.err("Error: Ingreso un Caracter no Valido.");
                    if(var1.length()>1&&isCharEnabled)
                        Salida.err("Error: Solo se acepta un caracter.");
                    if(!isCharEnabled)
                        Salida.err("Error: Los caracteres estan desactivados.");
                    flag=false;
                }
                if(flag&&(var<1 || var > menu.size()))
                    Salida.err("Error: Indice fuera de rango min: "+1+" max: "+menu.size()+".");
            }while(var<1 || var > menu.size());
            if(var==menu.size())
                return false;
            methods.get(var-1).invoke(object);
            return true;
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Salida.err("Error: "+ex.getMessage());
        }
        return false;
    }
    
    public void Run()
    {
        while(Show());
    }
    private Method getMethod(String simpleName)//metodo privado no podra ser llamado
    {
        try {
            Method method=object.getClass().getDeclaredMethod(simpleName);
            return method;
        } catch (NoSuchMethodException | SecurityException ex) {
            Salida.err("Error: "+ex.getMessage());
        }
        return null;
    }
    private String OpcChar(String actual)//metodo privado no podra ser llamado
    {
        if(chars.isEmpty()&Util.mayusculas(actual.charAt(0))!='S')
        {
            chars.add(actual.charAt(0));
            return "["+Util.mayusculas(actual.charAt(0))+"]"+actual.substring(1);
        }
        for(int index=0;index<actual.length();index++)
        {
            if(isChar(actual.charAt(index))&Util.mayusculas(actual.charAt(index))!='S'&Util.mayusculas(actual.charAt(index))!=' ')
            {
                chars.add(Util.mayusculas(actual.charAt(index)));
                return actual.substring(0,index)+"["+Util.mayusculas(actual.charAt(index))+"]"+actual.substring(index+1);
            }
        }
        return actual; 
    }
    private boolean isChar(char c)//metodo privado no podra ser llamado
    {
        return chars.stream().noneMatch((char1) -> (Util.comparar(c, char1)));
    }
}
