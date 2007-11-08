/*
 * The contents of this file are subject to the terms 
 * of the Common Development and Distribution License 
 * (the "License").  You may not use this file except 
 * in compliance with the License.
 * 
 * You can obtain a copy of the license at 
 * glassfish/bootstrap/legal/CDDLv1.0.txt or 
 * https://glassfish.dev.java.net/public/CDDLv1.0.html. 
 * See the License for the specific language governing 
 * permissions and limitations under the License.
 * 
 * When distributing Covered Code, include this CDDL 
 * HEADER in each file and include the License file at 
 * glassfish/bootstrap/legal/CDDLv1.0.txt.  If applicable, 
 * add the following below this CDDL HEADER, with the 
 * fields enclosed by brackets "[]" replaced with your 
 * own identifying information: Portions Copyright [yyyy] 
 * [name of copyright owner]
 */

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-1973 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// This file may need to modified upon recompilation of the source schema. 
// Generated on: 2005.04.20 at 08:27:00 IST 
//


package com.sun.persistence.api.deployment;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.AccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.sun.persistence.api.deployment.DescriptorNode;
import com.sun.persistence.api.deployment.JoinColumnDescriptor;
import com.sun.persistence.api.deployment.TableDescriptor;

@XmlAccessorType(value = AccessType.FIELD)
@XmlType(name = "association-table", namespace = "http://java.sun.com/xml/ns/persistence_ORM")
public class AssociationTableDescriptor
    extends DescriptorNode
{

    @XmlElement(name = "table", namespace = "http://java.sun.com/xml/ns/persistence_ORM", type = TableDescriptor.class)
    protected TableDescriptor table;
    @XmlElement(name = "join-column", namespace = "http://java.sun.com/xml/ns/persistence_ORM", type = JoinColumnDescriptor.class)
    protected List<JoinColumnDescriptor> joinColumn;
    @XmlElement(name = "inverse-join-column", namespace = "http://java.sun.com/xml/ns/persistence_ORM", type = JoinColumnDescriptor.class)
    protected List<JoinColumnDescriptor> inverseJoinColumn;

    /**
     * Gets the value of the table property.
     * 
     * @return
     *     possible object is
     *     {@link com.sun.persistence.api.deployment.TableDescriptor}
     */
    public TableDescriptor getTable() {
        return table;
    }

    /**
     * Sets the value of the table property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.sun.persistence.api.deployment.TableDescriptor}
     */
    public void setTable(TableDescriptor value) {
        this.table = value;
        value.parent(this);
    }

    public boolean isSetTable() {
        return (this.table!= null);
    }

    public void unsetTable() {
        this.table = null;
    }

    protected List<JoinColumnDescriptor> _getJoinColumn() {
        if (joinColumn == null) {
            joinColumn = new ArrayList<JoinColumnDescriptor>();
        }
        return joinColumn;
    }

    /**
     * Gets the value of the joinColumn property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the joinColumn property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getJoinColumn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link com.sun.persistence.api.deployment.JoinColumnDescriptor}
     * 
     */
    public List<JoinColumnDescriptor> getJoinColumn() {
        return this._getJoinColumn();
    }

    public boolean isSetJoinColumn() {
        return (this.joinColumn!= null);
    }

    public void unsetJoinColumn() {
        this.joinColumn = null;
    }

    protected List<JoinColumnDescriptor> _getInverseJoinColumn() {
        if (inverseJoinColumn == null) {
            inverseJoinColumn = new ArrayList<JoinColumnDescriptor>();
        }
        return inverseJoinColumn;
    }

    /**
     * Gets the value of the inverseJoinColumn property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inverseJoinColumn property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInverseJoinColumn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link com.sun.persistence.api.deployment.JoinColumnDescriptor}
     * 
     */
    public List<JoinColumnDescriptor> getInverseJoinColumn() {
        return this._getInverseJoinColumn();
    }

    public boolean isSetInverseJoinColumn() {
        return (this.inverseJoinColumn!= null);
    }

    public void unsetInverseJoinColumn() {
        this.inverseJoinColumn = null;
    }

    // Added code

    public void accept(Visitor v) throws DeploymentException {
        v.visitAssociationTableDescriptor(this);
    }

    AssociationTableDescriptor() {
        joinColumn = new com.sun.persistence.api.deployment.DescriptorNodeList<JoinColumnDescriptor>(this);
        inverseJoinColumn = new com.sun.persistence.api.deployment.DescriptorNodeList<JoinColumnDescriptor>(this);
    }

    @Override public PropertyDescriptor parent(){
        return PropertyDescriptor.class.cast(super.parent());
    }

}
