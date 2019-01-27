import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
class TopBar extends JPanel{
    String[] buttonNames = {"New", "Open", "Save", "Export to HTML", "Help"};
    ArrayList<JButton> buttons = new ArrayList<>();
    Dimension buttonDim, dim;
    MainFrame mf;
    TopBar(MainFrame mframe) {
        mf=mframe;
        buttonDim = new Dimension(100, 30);
        dim = new Dimension(mf.dim.width,buttonDim.height);
        setLayout(new GridLayout(1,buttonNames.length));
        for(String s : buttonNames)
            buttons.add(new JButton(s));
        for(JButton jb: buttons) {
            add(jb);
            jb.addActionListener(mf.bl);
        }
        setBounds(0, 0, dim.width,dim.height);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }
}
