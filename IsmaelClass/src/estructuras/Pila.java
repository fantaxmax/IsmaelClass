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
public class Pila<E> implements Iterable<E> {

    private Base<E> pila;

    public Pila() {
        this.pila = null;
    }

    public void push(E object) {
        if (pila == null) {
            pila = new Base<>(object, null);
        } else {
            pila = new Base<>(object, pila);
        }
    }

    public E pop() {
        Base<E> var = pila;
        pila = pila.getNext();
        return var.getObject();
    }

    public E peek() {
        return pila.getObject();
    }

    private E get(int i) {
        Base<E> var = pila;
        for (int i1 = 0; i1 < i; i1++) {
            var = var.getNext();
        }
        return var.getObject();
    }

    public int count() {
        int ret = 0;
        for (E var : this) {
            ret++;
        }
        return ret;
    }

    public E[] toArray() {
        E[] ret = (E[]) (new Object[this.count()]);
        for (int i = 0; i < this.count(); i++) {
            ret[i] = get(i);
        }
        return ret;
    }

    public boolean remove(E object) {
        Pila<E> var = new Pila<>();
        while(true) {
            if(this.peek().equals(object)) {
                this.pop();
            } else {
                var.push(this.pop());
            }
            if(this.count()==0) {
                break;
            }
        }
        while(true) {
            this.push(var.pop());
            if(var.count()==0) {
                break;
            }
        }
        return true;
    }
    
    public Pila<E> reverse() {
        Pila<E> pilas = new Pila<>();
        while(pila!=null) {
            pilas.push(pop());
        }
        return pilas;
    }

    public Iterator<E> iterator() {
        return new PilaIterator<>();
    }

    private class PilaIterator<T> implements Iterator<E> {

        private Base<E> puntero;

        public PilaIterator() {
            puntero = pila;
        }

        public boolean hasNext() {
            return puntero.getNext()!=null;
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
