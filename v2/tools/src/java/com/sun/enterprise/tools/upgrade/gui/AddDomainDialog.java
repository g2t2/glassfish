/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 * 
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License. You can obtain
 * a copy of the License at https://glassfish.dev.java.net/public/CDDL+GPL.html
 * or glassfish/bootstrap/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at glassfish/bootstrap/legal/LICENSE.txt.
 * Sun designates this particular file as subject to the "Classpath" exception
 * as provided by Sun in the GPL Version 2 section of the License file that
 * accompanied this code.  If applicable, add the following below the License
 * Header, with the fields enclosed by brackets [] replaced by your own
 * identifying information: "Portions Copyrighted [year]
 * [name of copyright owner]"
 * 
 * Contributor(s):
 * 
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

/*
 * AddDomainDialog.java
 *
 * Created on September 29, 2003, 3:57 PM
 */

package com.sun.enterprise.tools.upgrade.gui;

/**
 *
 * @author  prakash
 */
import java.util.*;
import com.sun.enterprise.tools.upgrade.gui.util.*;
import java.util.logging.*;
import com.sun.enterprise.util.i18n.StringManager;
import com.sun.enterprise.tools.upgrade.logging.*;
import javax.swing.*;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import com.sun.enterprise.tools.upgrade.common.DomainInfo;
import com.sun.enterprise.tools.upgrade.common.CommonInfoModel;
import com.sun.enterprise.tools.upgrade.common.UpgradeConstants;

public class AddDomainDialog extends javax.swing.JDialog {
    
    private javax.swing.JComboBox domainsComboBox;
    private javax.swing.JComboBox instancesComboBox; // used during migrations from 7.x
    private javax.swing.JPasswordField targetKeyDBPwdField;
    private javax.swing.JPasswordField sourceCertPwdField;
    private javax.swing.JPasswordField targetCertAuthPwdField;
    
    public final static int JKStoJKS = 1;
    public final static int NSStoNSS = 2;
    public final static int NSStoJKS = 3;
    public final static int JKStoNSS = 4;
    private int currentSourceToVersion;
    
    private StringManager stringManager = StringManager.getManager("com.sun.enterprise.tools.upgrade.gui");
    private Logger logger = com.sun.enterprise.tools.upgrade.common.CommonInfoModel.getDefaultLogger();
    
    private java.util.List domainList;
    private java.util.List instanceList;
    private Hashtable domainMap;
    private CommonInfoModel commonInfo;
    
    private Vector dialogListeners = new Vector();
    public void addDialogListener(DialogListener listener){
        this.dialogListeners.addElement(listener);
    }
    public void removeDialogListener(DialogListener listener){
        this.dialogListeners.removeElement(listener);
    }
    
    /** Creates new form AddDomainDialog */
    public AddDomainDialog(java.awt.Frame parent, boolean modal, CommonInfoModel commonInfo, int currentStoT) {
        super(parent, modal);
        this.setTitle(stringManager.getString("upgrade.gui.adddomainpanel.dialogTitle"));
        this.domainList = commonInfo.getDomainList();
        this.domainMap = commonInfo.getDomainMapping();
        this.instanceList = new ArrayList();
        this.currentSourceToVersion = currentStoT;
        this.commonInfo = commonInfo;
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
        
        javax.swing.JLabel domainNameLabel = new javax.swing.JLabel();
        domainsComboBox = new javax.swing.JComboBox();
        instancesComboBox = new javax.swing.JComboBox();
        javax.swing.JPanel passwordPanel = null;
        getContentPane().setLayout(new java.awt.GridBagLayout());
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                cancelButtonActionPerformed();
            }
        });
        
        domainsComboBox.setModel(new javax.swing.DefaultComboBoxModel(domainList.toArray()));
        domainsComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                domainsComboBoxItemStateChanged();
            }
        });

        this.addComponetWithConstraints(domainsComboBox,getContentPane(), 0, 1, 1,1, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 10, 60), 1.0,0.0);
        if(commonInfo.getSourceVersion().equals(UpgradeConstants.VERSION_7X)) {
            domainNameLabel.setText(stringManager.getString("upgrade.gui.adddomainpanel.instanceBoxLabel"));
            DomainInfo dInfo = (DomainInfo)domainMap.get((String)domainsComboBox.getSelectedItem());
            instanceList = dInfo.getInstanceNames();
            instanceList.remove("admin-server");
            instancesComboBox.setModel(new javax.swing.DefaultComboBoxModel(instanceList.toArray()));
            instancesComboBox.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    instancesComboBoxItemStateChanged();
                }
            });
            this.addComponetWithConstraints(instancesComboBox,getContentPane(), 0, 2, 1,1, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 10, 60), 1.0,0.0);
        } else {
            domainNameLabel.setText(stringManager.getString("upgrade.gui.adddomainpanel.listBoxLabel")); 
        }
        this.addComponetWithConstraints(domainNameLabel,getContentPane(), 0, 0, 1,1, GridBagConstraints.HORIZONTAL, new Insets(10,10,5,60), 1.0,0.0);
        if((this.currentSourceToVersion == this.NSStoJKS) || (this.currentSourceToVersion == this.JKStoNSS)){
            passwordPanel = this.getPasswordPanelWith3Fields();
        }else{
            passwordPanel = this.getPasswordPanelWith2Fields();
        }
        this.addComponetWithConstraints(passwordPanel,getContentPane(), 0, 3, 1,1, GridBagConstraints.BOTH, new Insets(5, 10, 10, 60), 1.0,1.0);
        
        this.addComponetWithConstraints(getButtonsPanel(),getContentPane(), 0, 4, 1,1, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0), 1.0,0.0);
        
        this.setSize(400, 350);
    }
    private void addComponetWithConstraints(JComponent compo, java.awt.Container parent, int gx, int gy, int gw, int gh, int fill,java.awt.Insets in,
            double wx, double wy){
        java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = gx; gridBagConstraints.gridy = gy;
        gridBagConstraints.gridwidth = gw; gridBagConstraints.gridheight = gh;
        gridBagConstraints.fill = fill;
        gridBagConstraints.insets = in;
        gridBagConstraints.weightx = wx;
        gridBagConstraints.weighty = wy;
        parent.add(compo, gridBagConstraints);
    }
    private JPanel getPasswordPanelWith3Fields(){
        javax.swing.JPanel passwordPanel = new javax.swing.JPanel();
        javax.swing.JLabel sourceCertPwdLabel = new javax.swing.JLabel();
        sourceCertPwdField = new javax.swing.JPasswordField();
        javax.swing.JLabel targetKeyDBPwdLabel = new javax.swing.JLabel();
        targetKeyDBPwdField = new javax.swing.JPasswordField();
        targetCertAuthPwdField = new javax.swing.JPasswordField();
        javax.swing.JLabel targetCertAuthPwdLabel = new javax.swing.JLabel();
        
        passwordPanel.setLayout(new java.awt.GridBagLayout());
        passwordPanel.setBorder(new javax.swing.border.TitledBorder(stringManager.getString("upgrade.gui.adddomainpanel.certTitleBorder")));
        
        String[] labelAndToolTipStrings = new String[6];
        if(this.currentSourceToVersion == this.NSStoJKS){
            labelAndToolTipStrings[0] = stringManager.getString("upgrade.gui.adddomainpanel.NSStoJKS.sourceCertLabelTooltip");
            labelAndToolTipStrings[1] = stringManager.getString("upgrade.gui.adddomainpanel.NSStoJKS.targetKeyDBTooltip");
            labelAndToolTipStrings[2] = stringManager.getString("upgrade.gui.adddomainpanel.NSStoJKS.targetCATooltip");
            labelAndToolTipStrings[3] = stringManager.getString("upgrade.gui.adddomainpanel.NSStoJKS.sourceCertLabel");
            labelAndToolTipStrings[4] = stringManager.getString("upgrade.gui.adddomainpanel.NSStoJKS.targetKeyDBLabel");
            labelAndToolTipStrings[5] = stringManager.getString("upgrade.gui.adddomainpanel.NSStoJKS.targetCALabel");
        }else{
            labelAndToolTipStrings[0] = stringManager.getString("upgrade.gui.adddomainpanel.JKStoNSS.sourceCertLabelTooltip");
            labelAndToolTipStrings[1] = stringManager.getString("upgrade.gui.adddomainpanel.JKStoNSS.targetKeyDBTooltip");
            labelAndToolTipStrings[2] = stringManager.getString("upgrade.gui.adddomainpanel.JKStoNSS.targetCATooltip");
            labelAndToolTipStrings[3] = stringManager.getString("upgrade.gui.adddomainpanel.JKStoNSS.sourceCertLabel");
            labelAndToolTipStrings[4] = stringManager.getString("upgrade.gui.adddomainpanel.JKStoNSS.targetKeyDBLabel");
            labelAndToolTipStrings[5] = stringManager.getString("upgrade.gui.adddomainpanel.JKStoNSS.targetCALabel");
        }
        sourceCertPwdLabel.setText(labelAndToolTipStrings[3]);
        sourceCertPwdLabel.setToolTipText(labelAndToolTipStrings[0]);
        this.addComponetWithConstraints(sourceCertPwdLabel,passwordPanel, 0, 0, 1,1, GridBagConstraints.HORIZONTAL, new Insets(5, 10, 5, 10), 1.0,0.0);
        
        sourceCertPwdField.setToolTipText(labelAndToolTipStrings[0]);
        this.addComponetWithConstraints(sourceCertPwdField,passwordPanel, 0, 1, 1,1, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 7, 10), 1.0,0.0);
        
        targetKeyDBPwdLabel.setText(labelAndToolTipStrings[4]);
        targetKeyDBPwdLabel.setToolTipText(labelAndToolTipStrings[1]);
        this.addComponetWithConstraints(targetKeyDBPwdLabel,passwordPanel, 0, 2, 1,1, GridBagConstraints.HORIZONTAL, new Insets(5, 10, 5, 10), 1.0,0.0);
        
        targetKeyDBPwdField.setToolTipText(labelAndToolTipStrings[1]);
        this.addComponetWithConstraints(targetKeyDBPwdField,passwordPanel, 0, 3, 1,1, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 7, 10), 1.0,0.0);
        
        targetCertAuthPwdLabel.setText(labelAndToolTipStrings[5]);
        targetCertAuthPwdLabel.setToolTipText(labelAndToolTipStrings[2]);
        this.addComponetWithConstraints(targetCertAuthPwdLabel,passwordPanel, 0, 4, 1,1, GridBagConstraints.HORIZONTAL, new Insets(5, 10, 5, 10), 1.0,0.0);
        
        targetCertAuthPwdField.setToolTipText(labelAndToolTipStrings[2]);
        this.addComponetWithConstraints(targetCertAuthPwdField,passwordPanel, 0, 5, 1,1, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 7, 10), 1.0,0.0);
        
        return passwordPanel;
    }
    private JPanel getPasswordPanelWith2Fields(){
        javax.swing.JPanel passwordPanel = new javax.swing.JPanel();
        javax.swing.JLabel sourceCertPwdLabel = new javax.swing.JLabel();
        sourceCertPwdField = new javax.swing.JPasswordField();
        javax.swing.JLabel targetKeyDBPwdLabel = new javax.swing.JLabel();
        targetKeyDBPwdField = new javax.swing.JPasswordField();
        
        passwordPanel.setLayout(new java.awt.GridBagLayout());
        passwordPanel.setBorder(new javax.swing.border.TitledBorder(stringManager.getString("upgrade.gui.adddomainpanel.certTitleBorder")));
        
        String[] labelAndToolTipStrings = new String[4];
        if(this.currentSourceToVersion == this.JKStoJKS){
            labelAndToolTipStrings[0] = stringManager.getString("upgrade.gui.adddomainpanel.JKStoJKS.sourceCertLabelTooltip");
            labelAndToolTipStrings[1] = stringManager.getString("upgrade.gui.adddomainpanel.JKStoJKS.targetKeyDBTooltip");
            labelAndToolTipStrings[2] = stringManager.getString("upgrade.gui.adddomainpanel.JKStoJKS.sourceCertLabel");
            labelAndToolTipStrings[3] = stringManager.getString("upgrade.gui.adddomainpanel.JKStoJKS.targetKeyDBLabel");
        }else{
            labelAndToolTipStrings[0] = stringManager.getString("upgrade.gui.adddomainpanel.NSStoNSS.sourceCertLabelTooltip");
            labelAndToolTipStrings[1] = stringManager.getString("upgrade.gui.adddomainpanel.NSStoNSS.targetKeyDBTooltip");
            labelAndToolTipStrings[2] = stringManager.getString("upgrade.gui.adddomainpanel.NSStoNSS.sourceCertLabel");
            labelAndToolTipStrings[3] = stringManager.getString("upgrade.gui.adddomainpanel.NSStoNSS.targetKeyDBLabel");
        }
        sourceCertPwdLabel.setText(labelAndToolTipStrings[2]);
        sourceCertPwdLabel.setToolTipText(labelAndToolTipStrings[0]);
        this.addComponetWithConstraints(sourceCertPwdLabel,passwordPanel, 0, 0, 1,1, GridBagConstraints.HORIZONTAL, new Insets(5, 10, 5, 10), 1.0,0.0);
        
        sourceCertPwdField.setToolTipText(labelAndToolTipStrings[0]);
        this.addComponetWithConstraints(sourceCertPwdField,passwordPanel, 0, 1, 1,1, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 7, 10), 1.0,0.0);
        
        targetKeyDBPwdLabel.setText(labelAndToolTipStrings[3]);
        targetKeyDBPwdLabel.setToolTipText(labelAndToolTipStrings[1]);
        this.addComponetWithConstraints(targetKeyDBPwdLabel,passwordPanel, 0, 2, 1,1, GridBagConstraints.HORIZONTAL, new Insets(5, 10, 5, 10), 1.0,0.0);
        
        targetKeyDBPwdField.setToolTipText(labelAndToolTipStrings[1]);
        this.addComponetWithConstraints(targetKeyDBPwdField,passwordPanel, 0, 3, 1,1, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 7, 10), 1.0,0.0);
        
        return passwordPanel;
    }
    
    private javax.swing.JPanel getButtonsPanel(){
        javax.swing.JPanel buttonsPanel = new javax.swing.JPanel();
        javax.swing.JButton okButton = new javax.swing.JButton();
        javax.swing.JButton cancelButton = new javax.swing.JButton();
        javax.swing.JButton helpButton = new javax.swing.JButton();
        buttonsPanel.setLayout(new java.awt.GridBagLayout());
        
        okButton.setText(stringManager.getString("upgrade.gui.adddomainpanel.okButtonText"));
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed();
            }
        });
        this.addComponetWithConstraints(okButton, buttonsPanel, 1, 0, 1,1, GridBagConstraints.NONE, new Insets(5, 5, 10, 10), 0.0,0.0);
        
        cancelButton.setText(stringManager.getString("upgrade.gui.adddomainpanel.cancelButtonText"));
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed();
            }
        });
        this.addComponetWithConstraints(cancelButton, buttonsPanel, 2, 0, 1,1, GridBagConstraints.NONE, new Insets(5, 5, 10, 10), 0.0,0.0);
        
        helpButton.setText(stringManager.getString("upgrade.gui.adddomainpanel.helpButtonText"));
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed();
            }
        });
        this.addComponetWithConstraints(helpButton, buttonsPanel, 3, 0, 1,1, GridBagConstraints.NONE, new Insets(5, 5, 10, 10), 0.0,0.0);
        this.addComponetWithConstraints(new JPanel(), buttonsPanel, 0, 0, 1,1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 10, 10), 1.0,0.0);
        if(Utils.getHelpBroker() != null)
            Utils.getHelpBroker().enableHelpOnButton(helpButton, "ADD_DOMAIN_DIALOG", null);
        return buttonsPanel;
    }
    
    private void helpButtonActionPerformed() {
        DialogEvent de = new DialogEvent(this, DialogEvent.HELP_ACTION);
        for(int i=0 ; i<this.dialogListeners.size(); i++){
            ((DialogListener)dialogListeners.elementAt(i)).dialogProcessed(de);
        }
    }
    
    private void cancelButtonActionPerformed() {
        DialogEvent de = new DialogEvent(this, DialogEvent.CANCEL_ACTION);
        for(int i=0 ; i<this.dialogListeners.size(); i++){
            ((DialogListener)dialogListeners.elementAt(i)).dialogProcessed(de);
        }
    }
    
    private void okButtonActionPerformed() {
        DialogEvent de = new DialogEvent(this, DialogEvent.FINISH_ACTION);
        Vector data = new Vector();
        de.setObject(data);
        data.add(new Integer(this.currentSourceToVersion));
        data.add(domainsComboBox.getSelectedItem());
        if(instanceList.isEmpty()) {
            data.add("server");
        } else {
            data.add(instancesComboBox.getSelectedItem());
        }
        if((sourceCertPwdField.getPassword() != null) && (!("".equals(new String(sourceCertPwdField.getPassword()).trim())))){
            data.add(new String(sourceCertPwdField.getPassword()));
        }
        if((targetKeyDBPwdField.getPassword() != null) && (!("".equals(new String(targetKeyDBPwdField.getPassword()).trim())))){
            data.add(new String(targetKeyDBPwdField.getPassword()));
        }
        if((this.currentSourceToVersion == this.NSStoJKS) || (this.currentSourceToVersion == this.JKStoNSS)){
            if((targetCertAuthPwdField.getPassword() != null) && (!("".equals(new String(targetCertAuthPwdField.getPassword()).trim())))){
                data.add(new String(targetCertAuthPwdField.getPassword()));
            }
        }
        for(int i=0 ; i<this.dialogListeners.size(); i++){
            ((DialogListener)dialogListeners.elementAt(i)).dialogProcessed(de);
        }
    }
    
    private void domainsComboBoxItemStateChanged() {
        // Add your handling code here:
        DomainInfo dInfo = (DomainInfo)domainMap.get((String)domainsComboBox.getSelectedItem());
        instanceList = dInfo.getInstanceNames();
        if(commonInfo.getSourceVersion().equals(UpgradeConstants.VERSION_7X)){
            instanceList.remove("admin-server");
        }
        instancesComboBox.setModel(new javax.swing.DefaultComboBoxModel(instanceList.toArray()));
    }
    
    private void instancesComboBoxItemStateChanged() {
        // Add your handling code here:
    }
    
    public static void main(String args[]) {
        //new AddDomainDialog(new javax.swing.JFrame(), true).show();
    }
    public void clearFields(){
        sourceCertPwdField.setText("");
        targetKeyDBPwdField.setText("");
        if(targetCertAuthPwdField != null)
            targetCertAuthPwdField.setText("");
    }
}
