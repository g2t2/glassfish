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

import javax.xml.bind.annotation.AccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.sun.persistence.api.deployment.AccessMode;
import com.sun.persistence.api.deployment.DescriptorNode;

@XmlAccessorType(value = AccessType.FIELD)
@XmlType(name = "named-query", namespace = "http://java.sun.com/xml/ns/persistence_ORM")
public class NamedQueryDescriptor
    extends DescriptorNode
{

    @XmlElement(defaultValue = "", name = "name", namespace = "http://java.sun.com/xml/ns/persistence_ORM", type = String.class)
    protected String name;
    @XmlElement(name = "query-string", namespace = "http://java.sun.com/xml/ns/persistence_ORM", type = String.class)
    protected String queryString;
    @XmlElement(defaultValue = "", name = "result-type", namespace = "http://java.sun.com/xml/ns/persistence_ORM", type = String.class)
    protected String resultType;
    @XmlElement(defaultValue = "LOCAL", name = "ejb-interface-type", namespace = "http://java.sun.com/xml/ns/persistence_ORM", type = AccessMode.class)
    protected AccessMode ejbInterfaceType;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    public void setName(String value) {
        this.name = value;
    }

    public boolean isSetName() {
        return (this.name!= null);
    }

    public void unsetName() {
        this.name = null;
    }

    /**
     * Gets the value of the queryString property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    public String getQueryString() {
        return queryString;
    }

    /**
     * Sets the value of the queryString property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    public void setQueryString(String value) {
        this.queryString = value;
    }

    public boolean isSetQueryString() {
        return (this.queryString!= null);
    }

    public void unsetQueryString() {
        this.queryString = null;
    }

    /**
     * Gets the value of the resultType property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    public String getResultType() {
        return resultType;
    }

    /**
     * Sets the value of the resultType property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    public void setResultType(String value) {
        this.resultType = value;
    }

    public boolean isSetResultType() {
        return (this.resultType!= null);
    }

    public void unsetResultType() {
        this.resultType = null;
    }

    /**
     * Gets the value of the ejbInterfaceType property.
     * 
     * @return
     *     possible object is
     *     {@link com.sun.persistence.api.deployment.AccessMode}
     */
    public AccessMode getEjbInterfaceType() {
        return ejbInterfaceType;
    }

    /**
     * Sets the value of the ejbInterfaceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.sun.persistence.api.deployment.AccessMode}
     */
    public void setEjbInterfaceType(AccessMode value) {
        this.ejbInterfaceType = value;
    }

    public boolean isSetEjbInterfaceType() {
        return (this.ejbInterfaceType!= null);
    }

    public void unsetEjbInterfaceType() {
        this.ejbInterfaceType = null;
    }

    //Added code

    public NamedQueryDescriptor() {
    }

    public void accept(Visitor v) throws DeploymentException {
        v.visitNamedQueryDescriptor(this);
    }

}
