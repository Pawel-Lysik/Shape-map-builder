import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

class IOmanager {
    MainFrame mf;
    int numberOfPolygons, numberOfPoints;
    IOmanager(MainFrame mframe) {
        mf=mframe;
    }
    void save(String file) {
        try {
            if(!file.contains(".smb"))file+=".smb";
            PrintWriter pw = new PrintWriter(file);
            numberOfPolygons=mf.pt.polygons.size();
            numberOfPoints=0;
            if(mf.pt.polygons.get(mf.pt.polygons.size()-1).isEmpty())
                numberOfPolygons--;
            for(ArrayList<Point> points : mf.pt.polygons)
                for(Point p : points)numberOfPoints++;
            pw.println(numberOfPoints+" "+numberOfPolygons);
            for(int i=0; i<mf.pt.polygons.size(); i++)
                for(Point p : mf.pt.polygons.get(i))
                    pw.println(p.x + " " + p.y + " " + i);
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(mf, "Something was wrong, please try again",
                    "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        JOptionPane.showMessageDialog(mf, "Succesfully saving",
                "Succes", JOptionPane.INFORMATION_MESSAGE);
    }
    void open(String file) {
        mf.pt.clearTable();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String[] stab = br.readLine().split(" ");
            numberOfPoints = Integer.valueOf(stab[0]);
            numberOfPolygons = Integer.valueOf(stab[1]);
            int x, y, poly;
            for(int i=0; i<numberOfPolygons; i++)
                mf.pt.polygons.add(new ArrayList<>());
            for(int i=0; i<numberOfPoints; i++) {
                stab = br.readLine().split(" ");
                x = Integer.valueOf(stab[0]);
                y = Integer.valueOf(stab[1]);
                poly = Integer.valueOf(stab[2]);
                mf.pt.polygons.get(poly).add(new Point(x, y));
            }
            mf.pt.newPolygons();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(mf, "Something was wrong, please try again",
                    "Error", JOptionPane.INFORMATION_MESSAGE);
        }

    }
    void toHTML(String file) {
        try {
            PrintWriter pw = new PrintWriter(file+".html");
            int leftMargin = 100, UpMargin = 100;
            pw.println("<html>\n<body bgcolor=\"white\">\n<svg width=\"1800\" height=\"1000\" > \n");
            for(int i=0; i<mf.pt.polygons.size(); i++) {
                pw.print("<polygon points=\"");
                for (Point p : mf.pt.polygons.get(i))
                    pw.print((p.x + leftMargin) + "," + (p.y + UpMargin) + " ");
                pw.print("\" style=\"fill:lime;stroke:green;stroke-width:1\" /> \n");
                for (Point p : mf.pt.polygons.get(i)) {
                    pw.print("<circle cx=\"" + (p.x + leftMargin) + "\" cy=\"" + (p.y + UpMargin) + "\" ");
                    pw.print("r=\"" + 4 + "\" fill=\"black\" /> \n");
                }
            }
            pw.print("</svg>\n</body>\n</html>\n");
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(mf, "Something was wrong, please try again",
                    "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        JOptionPane.showMessageDialog(mf, "Succesfully exporting",
                "Succes", JOptionPane.INFORMATION_MESSAGE);
    }
}
