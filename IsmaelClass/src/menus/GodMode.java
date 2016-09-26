package menus;
import consola.Preguntar;
import consola.Salida;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * [Clase experimental]
 * Clase GodMode: permite acceder a todo en el objeto que reciba mediante el metodo estatico load() en cualquiera de sus versiones.
 * <p>No se debe crear un objeto de esta, se debe llamar al metodo load() y este se encargara de todo</p>
 * <p>Permite acceder/modificar a atributos public/private/        /protected</p>
 * <p>Permite acceder/ejecutar metodos public/private/        /protected de objeto o estaticos</p>
 * <p>Permite reemplazar atributos de objeto con nuevos objetos, sin embargo solo si sus parametros son de tipo primitivo
 * <p>Permite llamar a metodos que tengan argumentos desde la 2.0.14.01.25
 * <p>Permite leer arreglos</p>
 * <p>Permite Modificar el tamaño de un arreglo</p>
 * <p>No Permite llenar un arreglo</p>
 * @author Ismael Concha Villaseca
 * @version 0.2.14.01.25
 */
public class GodMode extends Main {

    
    private Object main;
    private Field[] fields;
    private Method[] methods;
    private boolean[] fields_bckup,methods_bckup;
    public GodMode(Object main)
    {
        menuopc.addAll(Arrays.asList(new String[]{"Mostar Atributos","Modificar Atributos","Ejecutar a X metodo","Modificar un atributo de Objeto"}));
        args="isCharEnabled=true;isGodMode=true;name=root@"+main.getClass().getName()+":~;design=#;";
        this.main=main;
        fields = main.getClass().getDeclaredFields();
        fields_bckup=new boolean[fields.length];
        for(int i=0;i<fields.length;i++){
            fields_bckup[i]=fields[i].isAccessible();
            fields[i].setAccessible(true);
        }
        methods = main.getClass().getDeclaredMethods();
        methods_bckup=new boolean[methods.length];
        for(int i=0;i<methods.length;i++){
            methods_bckup[i]=methods[i].isAccessible();
            methods[i].setAccessible(true);
        }
        System.out.println("Accesadores de atributos y metodos: respaldados");
    }
    public static void load(Object main) {
        Menu m=new Menu(new GodMode(main));
        m.Run();
    }
    public void Start(){
       
    }
    public void Opc1(){
        try {
            for (Field field : fields) {
                if (field.get(main) != null) {
                    if (field.getType().isArray() || field.getType() == ArrayList.class) {
                        Object[] array = getArray(field.get(main));
                        Salida.escribir(field.getType().getSimpleName() + " " + field.getName() + " length=" + array.length);
                        Salida.impresion(array);
                    } else {
                        Salida.escribir(field.getType().getSimpleName() + " " + field.getName() + "=" + ((Object) field.get(main)).toString());
                    }
                } else {
                    Salida.escribir(field.getType().getSimpleName() + " " + field.getName() + "=null");
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Salida.err(ex.getMessage());
        }
    }
    public void Opc2(){
        try {
            for(int i=0;i<fields.length;i++)
                if(fields[i].get(main)!=null&&fields[i].getType().isArray())
                    Salida.escribir(((i+1)+".-"+fields[i].getName()+":"+fields[i].get(main).getClass().getSimpleName()));
                else if(fields[i].get(main)!=null&&fields[i].getType()!=String.class)
                    Salida.escribir((i+1)+".-"+fields[i].getName()+":"+fields[i].get(main).getClass().getSimpleName()+"="+fields[i].get(main));
                else if(fields[i].get(main)!=null&&fields[i].getType()==String.class)
                    Salida.escribir((i+1)+".-"+fields[i].getName()+":"+fields[i].get(main).getClass().getSimpleName()+"=\""+fields[i].get(main)+"\"");
                else if(fields[i].getType()==String.class){
                    fields[i].set(main, "");
                    Salida.escribir((i+1)+".-"+fields[i].getName()+":String=\"\"");
                }
                else Salida.escribir((i+1)+".-"+fields[i].getName()+":"+fields[i].getType().getSimpleName()+"=null");
            int opc=(consola.Validar.Int("Que atributo desea modificar: ", 1,fields.length)-1);
            fields[opc].set(main, setAttribute(fields[opc].getType()));
        } catch (IllegalArgumentException | IllegalAccessException | UnsupportedOperationException | InstantiationException | InvocationTargetException ex) {
            Salida.err(ex.getMessage());
        }
    }
    public void Opc3(){
        try {
            for(int i=0;i<methods.length;i++)
                System.out.println((i+1)+".- "+methods[i].toGenericString());
            int opc=(consola.Validar.Int("Que metodo desea llamar: ", 1,methods.length)-1);
            if(methods[opc].getParameterTypes().length==0)
            {
                System.out.println("\tLlamada a Metodo "+methods[opc].getName());
                if(!methods[opc].getReturnType().isArray())
                    System.out.println(methods[opc].invoke(main));
                else
                    Salida.impresion(getArray(methods[opc].invoke(main)));
                System.out.println("\tFin llamada");
            }
            else
            {
                Object[] params=getParams(methods[opc]);
                System.out.println("\tLlamada a Metodo "+methods[opc].getName());
                if(!methods[opc].getReturnType().isArray())
                    System.out.println(methods[opc].invoke(main));
                else
                    Salida.impresion(getArray(methods[opc].invoke(main,params)));
                System.out.println("\tFin llamada");
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | UnsupportedOperationException | InstantiationException ex){
            Salida.err(ex.getMessage());
        }
    }
    public void Opc4(){
        try {
            for(int i=0;i<fields.length;i++)
                    if(fields[i].get(main)!=null&&fields[i].getType().isArray())
                        Salida.escribir(((i+1)+".-"+fields[i].getName()+":"+fields[i].get(main).getClass().getSimpleName()));
                    else if(fields[i].get(main)!=null&&fields[i].getType()!=String.class)
                        Salida.escribir((i+1)+".-"+fields[i].getName()+":"+fields[i].get(main).getClass().getSimpleName()+"="+fields[i].get(main));
                    else if(fields[i].get(main)!=null&&fields[i].getType()==String.class)
                        Salida.escribir((i+1)+".-"+fields[i].getName()+":"+fields[i].get(main).getClass().getSimpleName()+"=\""+fields[i].get(main)+"\"");
                    else if(fields[i].getType()==String.class){
                        Salida.escribir((i+1)+".-"+fields[i].getName()+":String=\"\"");
                    }
                    else Salida.escribir((i+1)+".-"+fields[i].getName()+":"+fields[i].getType().getSimpleName()+"=null");
                int opc=(consola.Validar.Int("Que atributo desea modificar: ", 1,fields.length)-1);
                load(fields[opc].get(main));
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Salida.err(ex.getMessage());
        }
    }
    public void Exit() {
        for(int i=0;i<methods.length;i++)
            methods[i].setAccessible(methods_bckup[i]);
        for(int i=0;i<fields.length;i++)
            fields[i].setAccessible(fields_bckup[i]);
        System.out.println("Accesadores de atributos y metodos: restaurados");
    }
    private Object newObject(Class object) throws InstantiationException, IllegalAccessException, InvocationTargetException
    {
        Constructor[] constr = object.getDeclaredConstructors();
        for(int i=0;i<constr.length;i++)
            System.out.println((i+1)+": "+constr[i]);
        int var=consola.Validar.Int("Que constructor desea Llamar: ", 1, constr.length)-1;
        Class[] arg=constr[var].getParameterTypes();Object[] param= new Object[arg.length];
        for(int i=0;i<arg.length;i++)
            if(arg[i]==String.class)
                param[i]=Preguntar.string("Parametro de tipo String:");
            else if(arg[i]==Boolean.class|arg[i]==boolean.class)
                param[i]=Preguntar.Boolean("Parametro de tipo Boolean:");
            else if(arg[i]==Character.class|arg[i]==char.class)
                param[i]=Preguntar.Char("Parametro de tipo Char:");
            else if(arg[i]==Integer.class|arg[i]==int.class)
                param[i]=Preguntar.Int("Parametro de tipo Integer:");
            else if(arg[i]==Long.class|arg[i]==long.class)
                param[i]=Preguntar.Long("Parametro de tipo Long:");
            else if(arg[i]==Short.class|arg[i]==short.class)
                param[i]=Preguntar.Short("Parametro de tipo Short:");
            else if(arg[i]==Float.class|arg[i]==float.class)
                param[i]=Preguntar.Float("Parametro de tipo Float:");
            else if(arg[i]==Double.class|arg[i]==double.class)
                param[i]=Preguntar.Double("Parametro de tipo Double:");
            else if(arg[i].isArray())
                param[i]=Array.newInstance(arg[i].getComponentType(),Preguntar.Int("Capacidad: "));
            else newObject(arg[i]);
        return constr[var].newInstance(param);
    }
    private Object setAttribute(Class object) throws InstantiationException, IllegalAccessException, InvocationTargetException
    {
            if(object==Integer.class|object==int.class)
                return Preguntar.Int("Nuevo Valor: ");
            else if(object==Double.class|object==double.class)
                return Preguntar.Double("Nuevo Valor: ");
            else if(object==Float.class|object==float.class)
                return Preguntar.Float("Nuevo Valor: ");
            else if(object==Short.class|object==short.class)
                return Preguntar.Short("Nuevo Valor: ");
            else if(object==Long.class|object==long.class)
                return Preguntar.Long("Nuevo Valor: ");
            else if(object==Boolean.class|object==boolean.class)
                return Preguntar.Boolean("Nuevo Valor: ");
            else if(object==Character.class|object==char.class)
                return Preguntar.Char("Nuevo Valor: ");
            else if(object==String.class)
                return Preguntar.string("Nuevo Valor: ");
            else if(object.isArray())
                return Array.newInstance(object.getComponentType(),Preguntar.Int("Nueva Capacidad: "));
            else return newObject(object);
    }
    private Object[] getArray(Object object)
    {
        Class clase = object.getClass();
        if(clase==ArrayList.class)
            return ((ArrayList)object).toArray();
        clase=clase.getComponentType();
        if(clase.isPrimitive())
        {    if(clase==int.class)
            {
                int[] array=(int[])object;
                Integer[] ret = new Integer[array.length];
                for(int i=0;i<array.length;i++)
                    ret[i]=array[i];
                return ret;
            }
            else if(clase==short.class)
            {
                short[] array=(short[])object;
                Short[] ret = new Short[array.length];
                for(int i=0;i<array.length;i++)
                    ret[i]=array[i];
                return ret;
            }
            else if(clase==long.class)
            {
                long[] array=(long[])object;
                Long[] ret = new Long[array.length];
                for(int i=0;i<array.length;i++)
                    ret[i]=array[i];
                return ret;
            }
            else if(clase==double.class)
            {
                double[] array=(double[])object;
                Double[] ret = new Double[array.length];
                for(int i=0;i<array.length;i++)
                    ret[i]=array[i];
                return ret;
            }
            else if(clase==float.class)
            {
                float[] array=(float[])object;
                Float[] ret = new Float[array.length];
                for(int i=0;i<array.length;i++)
                    ret[i]=array[i];
                return ret;
            }
            else if(clase==byte.class)
            {
                byte[] array=(byte[])object;
                Byte[] ret = new Byte[array.length];
                for(int i=0;i<array.length;i++)
                    ret[i]=array[i];
                return ret;
            }
            else if(clase==boolean.class)
            {
                boolean[] array=(boolean[])object;
                Boolean[] ret = new Boolean[array.length];
                for(int i=0;i<array.length;i++)
                    ret[i]=array[i];
                return ret;
            }
            else if(clase==char.class)
            {
                char[] array=(char[])object;
                Character[] ret = new Character[array.length];
                for(int i=0;i<array.length;i++)
                    ret[i]=array[i];
                return ret;
            }
            return null;
        }
        else return (Object[])object;
    }
    private Object[] getParams(Method method) throws InstantiationException, IllegalAccessException, InvocationTargetException
    {
        Class[] arg=method.getParameterTypes();Object[] param= new Object[arg.length];
        for(int i=0;i<arg.length;i++)
            if(arg[i]==String.class)
                param[i]=Preguntar.string("Parametro de tipo String:");
            else if(arg[i]==Boolean.class|arg[i]==boolean.class)
                param[i]=Preguntar.Boolean("Parametro de tipo Boolean:");
            else if(arg[i]==Character.class|arg[i]==char.class)
                param[i]=Preguntar.Char("Parametro de tipo Char:");
            else if(arg[i]==Integer.class|arg[i]==int.class)
                param[i]=Preguntar.Int("Parametro de tipo Integer:");
            else if(arg[i]==Long.class|arg[i]==long.class)
                param[i]=Preguntar.Long("Parametro de tipo Long:");
            else if(arg[i]==Short.class|arg[i]==short.class)
                param[i]=Preguntar.Short("Parametro de tipo Short:");
            else if(arg[i]==Float.class|arg[i]==float.class)
                param[i]=Preguntar.Float("Parametro de tipo Float:");
            else if(arg[i]==Double.class|arg[i]==double.class)
                param[i]=Preguntar.Double("Parametro de tipo Double:");
            else if(arg[i].isArray())
                param[i]=Array.newInstance(arg[i].getComponentType(),Preguntar.Int("Capacidad: "));
            else newObject(arg[i]);
        return param;
    }
}
