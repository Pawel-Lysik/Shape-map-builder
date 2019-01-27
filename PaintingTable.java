import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
public class PaintingTable extends JComponent{
    Dimension boardDim;
    int leftMargin, rightMargin, upMargin, downMargin;
    Rectangle2D.Double board;
    ArrayList<ArrayList<Point>> polygons = new ArrayList<>();
    ArrayList<Polygon> fillings = new ArrayList<>();
    Mouse mouse = new Mouse(this);
    MainFrame mf;
    int r = 5;
    PaintingTable(MainFrame mframe) {
        mf=mframe;
        polygons.add(new ArrayList<>());
        leftMargin=10; rightMargin=10; upMargin=10+mf.tb.dim.height; downMargin=10;
        boardDim = new Dimension(mf.dim.width-leftMargin-rightMargin, mf.dim.height-upMargin-downMargin);
        board = new Rectangle2D.Double(leftMargin, upMargin, boardDim.width, boardDim.height);
        addMouseListener(mouse);
    }
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(Color.WHITE);
        g2.fill(board);
        g2.setColor(Color.GREEN);
        for(Polygon poly : fillings)
            g2.fillPolygon(poly);
        g2.setColor(Color.BLACK);
        for(ArrayList<Point> points : polygons) {
            for (Point p : points)
                g2.fillOval(p.x - r, p.y - r, 2 * r, 2 * r);
            for (int i = 1; i < points.size(); i++)
                g2.drawLine(points.get(i - 1).x, points.get(i - 1).y, points.get(i).x, points.get(i).y);
        }
    }
    void newPolygon(ArrayList<Point> points) {
        Polygon poly = new Polygon();
        for(int i=0; i<points.size()-1; i++)
            poly.addPoint(points.get(i).x, points.get(i).y);
        fillings.add(poly);
    }
    void newPolygons(){
        for(ArrayList<Point> poly : polygons)
            newPolygon(poly);
    }
    void clearTable() {
        polygons.clear();
        fillings.clear();
        polygons.add(new ArrayList<>());
        repaint();
    }
}
