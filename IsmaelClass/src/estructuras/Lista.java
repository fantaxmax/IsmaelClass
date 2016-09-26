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
 * ----añadir metodo para agregar en x lugar
 * @author Ismael
 */
public class Lista<E> implements Iterable<E> {

    private Base<E> lista;

    public Lista() {
        this.lista = null;
    }

    public void add(E object) {
        if (lista == null) {
            lista = new Base<>(object, null);
        } else {
            Base<E> punt = lista;
            while (true) {
                if (punt.getNext() == null) {
                    punt.setNext(new Base<>(object, null));
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
        for (E var : this) {
            ret++;
        }
        return ret;
    }

    public boolean remove(E object) {
        Base<E> var = lista;
        if(lista.getObject().equals(object)) {
            lista=lista.getNext();
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

    public E[] toArray() {
        E[] ret = (E[]) (new Object[this.size()]);
        for (int i = 0; i < this.size(); i++) {
            ret[i] = get(i);
        }
        return ret;
    }

    public Iterator<E> iterator() {
        return new ListaIterator<>();
    }

    private class ListaIterator<T> implements Iterator<E> {

        private Base<E> puntero;

        public ListaIterator() {
            puntero = lista;
        }

        public boolean hasNext() {
            return puntero != null;
        }

        public E next() {
            Base<E> var = puntero;
            puntero = puntero.getNext();
            return var.getObject();
        }

        public void remove() {
            throw new UnsupportedOperationException("No Soportado");
        }
    }
}
