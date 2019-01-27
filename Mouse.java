import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

class Mouse implements MouseListener {
    PaintingTable pt;
    Mouse(PaintingTable ptab) { pt=ptab; }
    boolean near(Point p1, Point p2) {
        return (p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y)<100;
    }
    boolean inArea(Point p) {
        System.out.println(p.x+" "+p.y);
        return p.x<pt.boardDim.width+pt.leftMargin && p.x>pt.leftMargin && p.y<pt.boardDim.height+pt.upMargin && p.y>pt.upMargin;
    }
    boolean restricted(Point A) {
        for(Polygon fill : pt.fillings)if(fill.contains(A))return true;
        if(pt.polygons.get(pt.polygons.size()-1).isEmpty())return false;
        else {
            ArrayList<Point> currentPoly = pt.polygons.get(pt.polygons.size() - 1);
            Point D = currentPoly.get(currentPoly.size() - 1);
            for (ArrayList<Point> points : pt.polygons) {
                for (int i = 1; i < points.size(); i++) {
                    Point B = points.get(i - 1), C = points.get(i);
                    double a = D.x - A.x, b = D.y - A.y;
                    double nominator = (B.x-A.x)*b-(B.y-A.y)*a;
                    double denominator = (C.y - B.y) * a - (C.x - B.x) * b;
                    double s = nominator / denominator, t = (B.y - A.y + (C.y - B.y) * s) / b;
                    if (s > 0.0 && s < 1.0 && t > 0.0 && t < 1.0) return true;
                }
            }
            return false;
        }
    }
    boolean inPoly(ArrayList<Point> points) {
        Polygon poly = new Polygon();
        for(Point p : points)
            poly.addPoint(p.x, p.y);
        for(ArrayList<Point> polygon : pt.polygons)
            if(poly.contains(polygon.get(0)))return true;
        return false;
    }
    public void mouseClicked(MouseEvent evt) {
        int mouseButton = evt.getButton();
        ArrayList<Point> points = pt.polygons.get(pt.polygons.size() - 1);
        Point pos = new Point(evt.getX(), evt.getY());
        if(mouseButton==1 && inArea(pos)) {
            for (ArrayList<Point> poly : pt.polygons)
                for (Point p : poly)
                    if (near(p, pos)) pos = p;
            if (!restricted(pos)) {
                if (!points.isEmpty() && near(pos, points.get(0))) {
                    if(inPoly(points)) {
                        JOptionPane.showMessageDialog(pt.mf, "The shape can not be in another shape",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        points.add(points.get(0));
                        pt.newPolygon(pt.polygons.get(pt.polygons.size()-1));
                        pt.polygons.add(new ArrayList<>());
                    }
                } else {
                    points.add(pos);
                }
                pt.repaint();
            }
        }
        if(mouseButton==3) {
            points.clear();
            pt.repaint();
        }
    }
    public void mousePressed(MouseEvent evt) {}
    public void mouseReleased(MouseEvent evt) {}
    public void mouseEntered(MouseEvent evt) {}
    public void mouseExited(MouseEvent evt) {}
}