import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class ButtonListener implements ActionListener {
    MainFrame mf;
    JFileChooser jfc = new JFileChooser();
    int choice;
    ButtonListener(MainFrame mainFrame) {
        mf = mainFrame;
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().getClass().equals(JButton.class)) {
            String name = ((JButton) e.getSource()).getText();
            if (name.equals("New")) {
                mf.pt.clearTable();
            }
            if (name.equals("Open")) {
                choice = jfc.showOpenDialog(mf);
                if(choice == JFileChooser.APPROVE_OPTION) {
                    System.out.println(jfc.getCurrentDirectory().toString()+"/"+jfc.getSelectedFile().getName());
                    mf.iom.open(jfc.getCurrentDirectory().toString()+"/"+jfc.getSelectedFile().getName());
                }
            }
            if (name.equals("Save")) {
                choice = jfc.showSaveDialog(mf);
                if(choice == JFileChooser.APPROVE_OPTION) {
                    System.out.println(jfc.getCurrentDirectory().toString()+"/"+jfc.getSelectedFile().getName());
                    mf.iom.save(jfc.getCurrentDirectory().toString()+"/"+jfc.getSelectedFile().getName());
                }
            }
            if (name.equals("Export to HTML")) {
                choice = jfc.showSaveDialog(mf);
                if(choice == JFileChooser.APPROVE_OPTION) {
                    mf.iom.toHTML(jfc.getCurrentDirectory().toString()+"/"+jfc.getSelectedFile().getName());
                }
            }
            if (name.equals("Help")) {
                JOptionPane.showMessageDialog(mf, "Description of functionalities\n\n"+
                        "<html><b>Shapes</b></html>\nTo create a shape you have to click on white board\n"+
                        "To finish you have to click first vertex of shape\n To cancel click right mouse button\n" +
                        "Vertices stick to another to avoid situation, \n"+
                        "when we think, that we have more vertices than in reality\n\n"+
                        "Export to HTML\nTo export file to HTML with SVG click \"Export to HTML\" button\n"+
                        "this allows to show shape map someone who do not use Shape map builder\n\n" +
                        "Save/Open\nShape map builder saving files with *.smb extension",
                        "Help", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}