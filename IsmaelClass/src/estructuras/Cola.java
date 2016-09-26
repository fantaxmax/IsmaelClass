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
package estructuras;

import java.util.Iterator;

/**
 *
 * @author Ismael
 */
public class Cola<E> {
    private Base<E> cola;
    private Base<E> fifo;
    public Cola()
    {
        this.cola = null;
        this.fifo = null;
    }
    
    public void add(E object)
    {
//        if(cola==null)
//            cola=new Base<>(object,null);
//        else 
//        {
//            Base<E> punt = cola;
//            while(true)
//            {
//                if(punt.getNext()==null)
//                {
//                    punt.setNext(new Base<>(object,null));
//                    break;
//                }
//                else punt = punt.getNext();
//            }
//        }
        cola = new Base<>(object,cola);
    }
    
    private E get(int i)
    {
        Base<E> var = cola;
        for(int i1=0;i1<i;i1++)
            var=var.getNext();
        return var.getObject();
    }
    
    public E pop()
    {
        Base<E> var = fifo;
        fifo = fifo.getNext();
        return var.getObject();
    }
    
    public E peek()
    {
        return fifo.getObject();
    }
    
    public int count()
    {
        Base<E> aux = cola;
        int count = 0;
        while(aux!=null) {
            count++;
            aux = aux.getNext();
        }
        return count;
    }
    
    public E[] toArray()
    {
        E[] ret = (E[])(new Object[this.count()]);
        for(int i=0;i<this.count();i++)
            ret[i]=get(i);
        return ret;
    }
    
    public boolean remove(E object) {
        Base<E> var = cola;
        if(cola.getObject().equals(object)) {
            cola=cola.getNext();
            return true;
        } else {
            while(var!=null) {
                if(var.getNext().getObject().equals(object)) {
                    var.setNext(var.getNext().getNext());
                    return true;
                }
                var = var.getNext();
            }
            return false;
        }
    }
}
