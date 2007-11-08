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

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.sun.persistence.api.deployment.DescriptorNode;

@XmlAccessorType(value = javax.xml.bind.annotation.AccessType.FIELD)
@XmlType(name = "embeddable", namespace = "http://java.sun.com/xml/ns/persistence_ORM")
public class EmbeddableDescriptor
    extends DescriptorNode
{

    @XmlElement(defaultValue = "PROPERTY", name = "access", namespace = "http://java.sun.com/xml/ns/persistence_ORM", type = com.sun.persistence.api.deployment.AccessType.class)
    protected com.sun.persistence.api.deployment.AccessType access;

    /**
     * Gets the value of the access property.
     * 
     * @return
     *     possible object is
     *     {@link com.sun.persistence.api.deployment.AccessType}
     */
    public com.sun.persistence.api.deployment.AccessType getAccess() {
        return access;
    }

    /**
     * Sets the value of the access property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.sun.persistence.api.deployment.AccessType}
     */
    public void setAccess(com.sun.persistence.api.deployment.AccessType value) {
        this.access = value;
    }

    public boolean isSetAccess() {
        return (this.access!= null);
    }

    public void unsetAccess() {
        this.access = null;
    }

    //Added code

    EmbeddableDescriptor() {
    }

    public void accept(Visitor v) throws DeploymentException {
        v.visitEmbeddableDescriptor(this);
    }

    @Override public ClassDescriptor parent(){
        return ClassDescriptor.class.cast(super.parent());
    }

}
