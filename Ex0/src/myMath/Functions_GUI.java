package myMath;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.awt.*;
import java.io.*;
import java.util.*;
public class Functions_GUI implements functions {
    private ArrayList<function> ls;

    public Functions_GUI() {
        ls = new ArrayList<function>();
    }
    @Override
    public void initFromFile(String file) throws IOException {
        File fl = new File(file);
        if (!file.endsWith(".txt")) throw new IOException("wrong format");
        if (!fl.exists()) throw new IOException("file does not exist");
        Scanner sc = new Scanner(fl);
        if (ls != null) ls = new ArrayList<function>();
        ComplexFunction cf = new ComplexFunction(new Polynom("2"));
        while (sc.hasNextLine()) {
            String a = sc.nextLine();
            System.out.println(a);
            ls.add(cf.initFromString(a));
        }
    }

    @Override
    public void saveToFile(String file) throws IOException {
        File file1 = new File(file);
        if (!file1.canWrite()) throw new IOException("can not be write to");
        if (file1.exists()) file1.delete();
        FileWriter fileWriter = new FileWriter(file1, true);
        Iterator<function> it = ls.iterator();
        while (it.hasNext()) {
            String a = it.next().toString();
            fileWriter.write(a + "\n");
        }
        fileWriter.close();
    }

    @Override
    public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
        Random rand = new Random();
        float r, g, b;
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(rx.get_min(), rx.get_max());
        StdDraw.setYscale(ry.get_min(), ry.get_max());
        StdDraw.setPenColor(Color.black);
        StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);
        StdDraw.line(0, ry.get_min(), 0, ry.get_max());
        StdDraw.setPenColor(Color.LIGHT_GRAY);
        for (double i = rx.get_min(); i <= rx.get_max(); i = i + 10) {
            //StdDraw.line(i, ry.get_min(), i, ry.get_max());
        }
        for (double i = ry.get_min(); i <= ry.get_max(); i = i + 0.5) {//horizontal
            // StdDraw.line(rx.get_min(), i, rx.get_max(), i);
        }
        Iterator<function> it = ls.iterator();
        function f;
        int index = 0;
        while (it.hasNext()) {
            f = it.next();
            r = rand.nextFloat();
            g = rand.nextFloat();
            b = rand.nextFloat();
            Color randomColor = new Color(r, g, b);
            System.out.println(index + ") " + randomColor.toString() + " f(x) = " + f.toString());
            index++;
            StdDraw.setPenColor(randomColor);
            StdDraw.setPenRadius(0.005);
            double[] x = new double[resolution + 1];
            double[] y = new double[resolution + 1];
            double jump = (rx.get_max() - rx.get_min()) / resolution;
            for (int i = 0; i <= resolution; i++) {
                x[i] = i * jump + rx.get_min();
                y[i] = f.f(x[i]);
            }
            for (int i = 0; i < resolution; i++) {
                StdDraw.line(x[i], y[i], x[i + 1], y[i + 1]);
            }
        }
    }

    @Override
    public void drawFunctions(String json_file) {
        Gson gson = new Gson();
        try {

            BufferedReader br = new BufferedReader(
                    new FileReader(json_file));

            //convert the json string back to object
            JsonObject obj = gson.fromJson(br, JsonObject.class);
            int width = obj.get("Width").getAsInt();
            int height = obj.get("Height").getAsInt();
            int resolution = obj.get("Resolution").getAsInt();
            Range rx, ry;
            JsonArray ja = obj.get("Range_X").getAsJsonArray();
            rx = new Range(ja.get(0).getAsInt(), ja.get(1).getAsInt());
            ja = obj.get("Range_Y").getAsJsonArray();
            ry = new Range(ja.get(0).getAsInt(), ja.get(1).getAsInt());
            drawFunctions(width, height, rx, ry, resolution);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public String toString()
    {
        return ls.toString();
    }
}
