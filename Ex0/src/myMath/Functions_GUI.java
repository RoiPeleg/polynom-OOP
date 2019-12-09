package myMath;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Functions_GUI implements functions {
    private ArrayList<function> ls;
    @Override
    public void initFromFile(String file) throws IOException {

    }

    @Override
    public void saveToFile(String file) throws IOException {

    }

    @Override
    public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {

    }

    @Override
    public void drawFunctions(String json_file) {

    }

    @Override
    public int size() {
        return ls.size();
    }

    @Override
    public boolean isEmpty() {
        return ls.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return ls.contains(o);
    }

    @Override
    public Iterator<function> iterator() {
        return ls.iterator();
    }

    @Override
    public Object[] toArray() {
        return ls.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return ls.toArray(a);
    }

    @Override
    public boolean add(function function) {
        return ls.add(function);
    }

    @Override
    public boolean remove(Object o) {
        return ls.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return ls.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends function> c) {
        return ls.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return ls.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return ls.removeAll(c);
    }

    @Override
    public void clear() {
        ls.clear();
    }
}
