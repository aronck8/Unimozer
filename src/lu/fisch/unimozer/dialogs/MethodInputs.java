/*
    Unimozer
    Unimozer intends to be a universal modelizer for Java™. It allows the user
    to draw UML diagrams and generates the relative Java™ code automatically
    and vice-versa.

    Copyright (C) 2009  Bob Fisch

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or any
    later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package lu.fisch.unimozer.dialogs;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedHashMap;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JTextField;
import lu.fisch.unimozer.Unimozer;
import lu.fisch.unimozer.utils.StringList;

/**
 *
 * @author robertfisch
 */
public class MethodInputs extends javax.swing.JDialog implements KeyListener
{

    public boolean OK = false;
    private LinkedHashMap<String,JTextField> edits = new LinkedHashMap();

    public MethodInputs(Frame frame, LinkedHashMap<String,String> inputs, String title, String comment)
    {
        super(frame);
        initComponents();
        Unimozer.switchButtons(btnOK, btnCancel);
        this.setTitle(title);
        this.setAlwaysOnTop(true);

        String jc = new String(comment.trim());
        StringList sl = StringList.explode(jc,"\n");
        //System.out.println(sl.getCommaText());
        jc = "";
        for(int i=0;i<sl.count();i++)
        {
            String ele = sl.get(i);
            if(ele.trim().startsWith("*")) ele=ele.trim().substring(1).trim();
            jc+=ele+"<br>";
        }
        if(sl.count()==1) if(sl.get(0).equals("")) jc="";
        if(!jc.equals("")) jc="<pre>"+jc+"</pre>";
        lblJavaDoc.setText("<html><blockquote>"+jc+"<br><b>"+title+"</b></blockquote><hr><html>");

        Object [] keys = inputs.keySet().toArray();
        this.setSize(this.getWidth(), keys.length*32+64);
        this.validate();
        for(int i=0;i<keys.length;i++)
        {
            String name = (String) keys[i];
            String type = inputs.get(name);

            JLabel label = new JLabel(name+" ("+type+") =");
            if(type.trim().equals("")) label = new JLabel(name+" =");
            label.setHorizontalAlignment(JLabel.RIGHT);
            pnlInputs.add(label);
            JTextField field = new JTextField();
            field.setName(type);
            edits.put(name,field);
            pnlInputs.add(field);
            field.addKeyListener(this);
            if(i==0)
            {
                field.requestFocusInWindow();
                field.requestFocus();
            }
        }
        this.pack();
        this.setModal(true);
        this.setResizable(false);
        this.setLocationRelativeTo(frame);
        this.setVisible(true);
    }

    public String getTypeFor(String name)
    {
        return edits.get(name).getName();
    }

    public String getValueFor(String name)
    {
        String ret = edits.get(name).getText();
        if(edits.get(name).getName().equals("String"))
        {
            try
            {
                lu.fisch.unimozer.Runtime5.getInstance().executeCommand("String aNaMeTaHnEveReVeRWiLlBeTaKen = "+ret);
            }
            catch(Exception ex)
            {
                ret = "\""+ret+"\"";
            }
        }
        return ret;
    }

    /** Creates new form MethodInputs 
    public MethodInputs() {
        initComponents();
        Unimozer.switchButtons(btnOK, btnCancel);
    }*/

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnOK = new javax.swing.JButton();
        pnlInputs = new javax.swing.JPanel();
        btnCancel = new javax.swing.JButton();
        lblJavaDoc = new javax.swing.JLabel();

        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        pnlInputs.setBackground(new java.awt.Color(204, 255, 51));
        pnlInputs.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 8, 8, 8));
        pnlInputs.setOpaque(false);
        pnlInputs.setLayout(new java.awt.GridLayout(0, 2, 8, 4));

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        lblJavaDoc.setBackground(new java.awt.Color(255, 153, 153));
        lblJavaDoc.setFont(new java.awt.Font("Lucida Grande", 2, 13)); // NOI18N
        lblJavaDoc.setText("jLabel1");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(148, 148, 148)
                        .add(btnCancel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btnOK))
                    .add(pnlInputs, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                    .add(lblJavaDoc))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(lblJavaDoc)
                .add(2, 2, 2)
                .add(pnlInputs, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnOK)
                    .add(btnCancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnOKActionPerformed
    {//GEN-HEADEREND:event_btnOKActionPerformed
        OK = true;
        this.setVisible(false);
}//GEN-LAST:event_btnOKActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnCancelActionPerformed
    {//GEN-HEADEREND:event_btnCancelActionPerformed
        OK = false;
        this.setVisible(false);
}//GEN-LAST:event_btnCancelActionPerformed

    /**
    * @param args the command line arguments
    */
    /*
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MethodInputs().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOK;
    private javax.swing.JLabel lblJavaDoc;
    private javax.swing.JPanel pnlInputs;
    // End of variables declaration//GEN-END:variables


    public void keyTyped(KeyEvent e)
    {
    }

    public void keyPressed(KeyEvent evt)
    {
        // if the escape key is pressed
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
            // close the dialog
			OK=false;
			setVisible(false);
		}
		else if(evt.getKeyCode() == KeyEvent.VK_ENTER) // && (evt.isShiftDown() || evt.isControlDown()))
		{
            // get the textfields into  vector
            Vector editi = new Vector(edits.values());
            // if the enter key has been hit on the last one
            if(editi.indexOf(evt.getSource())==editi.size()-1)
            {
                // close the dialog
                OK=true;
                setVisible(false);
            }
            else
            {
                // focus the next one
                ((JTextField) editi.get(editi.indexOf(evt.getSource())+1)).requestFocus();
            }
		}
    }

    public void keyReleased(KeyEvent e)
    {
    }

}
