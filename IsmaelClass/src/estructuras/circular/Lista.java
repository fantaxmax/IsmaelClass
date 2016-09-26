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
package estructuras.circular;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * @author Ismael
 * @param <E> referencia de tipo de clase a almacenar
 * 
 */
public class Lista<E> implements Iterable<E> {

    private Base<E> lista;

    public Lista() {
        this.lista = null;
    }

    public void add(E object) {
        if (lista == null) {
            lista = new Base<>(null,object, null);
            lista.setNext(lista);
            lista.setBefore(lista);
        } else {
            Base<E> punt = lista;
            while (true) {
                if (punt.getNext() == lista) {
                    punt.setNext(new Base<>(punt,object, lista));
                    lista.setBefore(punt.getNext());
                    break;
                } else {
                    punt = punt.getNext();
                }
            }
        }
    }
    
    public void add(E object,int pos) {
        if(pos==0) {
            Base<E> next = lista;
            Base<E> nu = new Base<>(null,object,null);
            nu.setBefore(next.getBefore());
            nu.setNext(next);
            next.setBefore(nu);
            lista = nu;
        } else if(size()>1) {
            Base<E> punt = lista;
            for(int i = 0;i<size();i++) {
                if (i==pos) {
                    Base<E> next = punt.getNext();
                    Base<E> ann = new Base<>(punt,object,next);
                    next.setBefore(ann);
                    punt.setNext(ann);
                    break;
                } else {
                    punt = punt.getNext();
                }
            }
        }
    }

    public E get(int i) {
        Base<E> var = lista;
        for (int i1 = 0; i1 < i; i1++) {
            var = var.getNext();
        }
        return var.getObject();
    }

    public boolean constainsValue(E value) {
        for (E var : this) {
            if (var.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        int ret = 0;
        for(Base<E> e = lista,ant=null;e!=null & (e!=lista||ant==null);e = e.getNext(),ret++,ant=e);
        return ret;
    }

    public boolean remove(E object) {
        if(size()==1) {
            lista = null;
            return true;
        }
        Base<E> var = lista;
        while(var!=null) {
            if(var.getObject().equals(object)) {
                Base<E> man = var.getNext();
                Base<E> ant = var.getBefore();
                ant.setNext(man);
                man.setBefore(ant);
                return true;
            }
            var = var.getNext();
        }
        return false;
    }
    //corregir get(i)
    public E[] toArray() {
        E[] ret = (E[]) (new Object[this.size()]);
        for (int i = 0; i < this.size(); i++) {
            ret[i] = get(i);
        }
        return ret;
    }
    
    public String[] getDebugInfo() {
        ArrayList<String> ddd = new ArrayList<>();
        Base<E> punt = lista;
        for(int i = 0;i<size();i++) {
            ddd.add(punt.toString());
            punt = punt.getNext();
        }
        String[] ret = new String[size()];
        return ddd.toArray(ret);
    }

    public Iterator<E> iterator() {
        return new ListaIterator<>();
    }

    private class ListaIterator<T> implements Iterator<E> {

        private Base<E> puntero;
        private Base<E> ant;

        public ListaIterator() {
            puntero = lista;
            ant = null;
        }

        public boolean hasNext() {
            return puntero!=null && (ant==null || puntero!=lista);
        }

        public E next() {
            ant = puntero;
            puntero = puntero.getNext();
            return ant.getObject();
        }

        public void remove() {
            throw new UnsupportedOperationException("No Soportado");
        }
    }
}