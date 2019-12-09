package myMath;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

public class Functions_GUI implements functions {
    private ArrayList<function> ls;
    @Override
    public void initFromFile(String file) throws IOException {//TODO
    }

    @Override
    public void saveToFile(String file) throws IOException {//TODO

    }

    @Override
    public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
        Random rand = new Random();
        StdDraw.setCanvasSize(width, height);
        for (function f : ls) {
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            Color randomColor = new Color(r, g, b);
            StdDraw.setPenColor(randomColor);
            double[] x = new double[resolution + 1];
            double[] y = new double[resolution + 1];
            for (int i = 0; i <= resolution; i++) {
                x[i] = i;
                y[i] = f.f(i);
            }
            StdDraw.setXscale(rx.get_min(), rx.get_max());
            StdDraw.setYscale(ry.get_min(), ry.get_max());
            for (int i = 0; i < resolution; i++) {
                StdDraw.line(x[i], y[i], x[i + 1], y[i + 1]);
            }
        }
    }

    @Override
    public void drawFunctions(String json_file) {//TODO
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
