/*
* The contents of this file are subject to the terms 
* of the Common Development and Distribution License 
* (the License).  You may not use this file except in
* compliance with the License.
* 
* You can obtain a copy of the license at 
* https://glassfish.dev.java.net/public/CDDLv1.0.html or
* glassfish/bootstrap/legal/CDDLv1.0.txt.
* See the License for the specific language governing 
* permissions and limitations under the License.
* 
* When distributing Covered Code, include this CDDL 
* Header Notice in each file and include the License file 
* at glassfish/bootstrap/legal/CDDLv1.0.txt.  
* If applicable, add the following below the CDDL Header, 
* with the fields enclosed by brackets [] replaced by
* you own identifying information: 
* "Portions Copyrighted [year] [name of copyright owner]"
* 
* Copyright 2007 Sun Microsystems, Inc. All rights reserved.
*/


//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-3010 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2005.11.29 at 04:18:06 PM IST 
//


package com.sun.xml.registry.uddi.bindings_v2_2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.registry.uddi.bindings_v2_2.AccessPoint;
import com.sun.xml.registry.uddi.bindings_v2_2.BindingTemplate;
import com.sun.xml.registry.uddi.bindings_v2_2.Description;
import com.sun.xml.registry.uddi.bindings_v2_2.HostingRedirector;
import com.sun.xml.registry.uddi.bindings_v2_2.TModelInstanceDetails;


/**
 * <p>Java class for bindingTemplate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bindingTemplate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:uddi-org:api_v2}description" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element ref="{urn:uddi-org:api_v2}accessPoint"/>
 *           &lt;element ref="{urn:uddi-org:api_v2}hostingRedirector"/>
 *         &lt;/choice>
 *         &lt;element ref="{urn:uddi-org:api_v2}tModelInstanceDetails"/>
 *       &lt;/sequence>
 *       &lt;attribute name="bindingKey" use="required" type="{urn:uddi-org:api_v2}bindingKey" />
 *       &lt;attribute name="serviceKey" type="{urn:uddi-org:api_v2}serviceKey" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bindingTemplate", propOrder = {
    "description",
    "accessPoint",
    "hostingRedirector",
    "tModelInstanceDetails"
})
public class BindingTemplate {

    @XmlElement(namespace = "urn:uddi-org:api_v2")
    protected List<Description> description;
    @XmlElement(namespace = "urn:uddi-org:api_v2")
    protected AccessPoint accessPoint;
    @XmlElement(namespace = "urn:uddi-org:api_v2")
    protected HostingRedirector hostingRedirector;
    @XmlElement(namespace = "urn:uddi-org:api_v2")
    protected TModelInstanceDetails tModelInstanceDetails;
    @XmlAttribute(required = true)
    protected String bindingKey;
    @XmlAttribute
    protected String serviceKey;

    /**
     * Gets the value of the description property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the description property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Description }
     * 
     * 
     */
    public List<Description> getDescription() {
        if (description == null) {
            description = new ArrayList<Description>();
        }
        return this.description;
    }

    /**
     * Gets the value of the accessPoint property.
     * 
     * @return
     *     possible object is
     *     {@link AccessPoint }
     *     
     */
    public AccessPoint getAccessPoint() {
        return accessPoint;
    }

    /**
     * Sets the value of the accessPoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccessPoint }
     *     
     */
    public void setAccessPoint(AccessPoint value) {
        this.accessPoint = value;
    }

    /**
     * Gets the value of the hostingRedirector property.
     * 
     * @return
     *     possible object is
     *     {@link HostingRedirector }
     *     
     */
    public HostingRedirector getHostingRedirector() {
        return hostingRedirector;
    }

    /**
     * Sets the value of the hostingRedirector property.
     * 
     * @param value
     *     allowed object is
     *     {@link HostingRedirector }
     *     
     */
    public void setHostingRedirector(HostingRedirector value) {
        this.hostingRedirector = value;
    }

    /**
     * Gets the value of the tModelInstanceDetails property.
     * 
     * @return
     *     possible object is
     *     {@link TModelInstanceDetails }
     *     
     */
    public TModelInstanceDetails getTModelInstanceDetails() {
        return tModelInstanceDetails;
    }

    /**
     * Sets the value of the tModelInstanceDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link TModelInstanceDetails }
     *     
     */
    public void setTModelInstanceDetails(TModelInstanceDetails value) {
        this.tModelInstanceDetails = value;
    }

    /**
     * Gets the value of the bindingKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBindingKey() {
        return bindingKey;
    }

    /**
     * Sets the value of the bindingKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBindingKey(String value) {
        this.bindingKey = value;
    }

    /**
     * Gets the value of the serviceKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceKey() {
        return serviceKey;
    }

    /**
     * Sets the value of the serviceKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceKey(String value) {
        this.serviceKey = value;
    }

}
