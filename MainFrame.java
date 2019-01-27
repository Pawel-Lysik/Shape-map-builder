import javax.swing.*;
import java.awt.*;

class MainFrame extends JFrame {
    Dimension dim = new Dimension(800, 600), screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    TopBar tb;
    PaintingTable pt;
    ButtonListener bl;
    IOmanager iom;
    MainFrame() {
        super("Shape map builder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(screenSize.width/2-dim.width/2, screenSize.height*2/5-dim.height/2);
        setSize(dim.width, dim.height);
        bl = new ButtonListener(this);
        tb = new TopBar(this);
        iom = new IOmanager(this);
        pt = new PaintingTable(this);
        add(tb);
        add(pt);
        setVisible(true);
    }
}
