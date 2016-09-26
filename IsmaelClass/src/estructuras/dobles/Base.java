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
package estructuras.dobles;

/**
 *
 * @author Ismael
 */
class Base<T>{
    private Base<T> before;
    private T object;
    private Base<T> next;

    public Base(Base<T> before,T object, Base<T> next) {
        this.before = before;
        this.object = object;        
        this.next = next;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public Base<T> getNext() {
        return next;
    }

    public void setNext(Base<T> next) {
        this.next = next;
    }

    public Base<T> getBefore() {
        return before;
    }

    public void setBefore(Base<T> before) {
        this.before = before;
    }
    
    public String toString() {
        return  "Debug Info" + (before == null ? "null" : before.hashCode()) + " - " + (hashCode()) + " - " + (next == null ? "null" : next.hashCode());
    }
}
