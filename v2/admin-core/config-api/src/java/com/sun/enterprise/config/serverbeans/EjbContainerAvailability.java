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
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 */
 
/**
 *	This generated bean class EjbContainerAvailability matches the DTD element ejb-container-availability
 *
 */

package com.sun.enterprise.config.serverbeans;

import org.w3c.dom.*;
import org.netbeans.modules.schema2beans.*;
import java.beans.*;
import java.util.*;
import java.io.Serializable;
import com.sun.enterprise.config.ConfigBean;
import com.sun.enterprise.config.ConfigException;
import com.sun.enterprise.config.StaleWriteConfigException;
import com.sun.enterprise.util.i18n.StringManager;

// BEGIN_NOI18N

public class EjbContainerAvailability extends ConfigBean implements Serializable
{

	static Vector comparators = new Vector();
	private static final org.netbeans.modules.schema2beans.Version runtimeVersion = new org.netbeans.modules.schema2beans.Version(4, 2, 0);

	static public final String ELEMENT_PROPERTY = "ElementProperty";

	public EjbContainerAvailability() {
		this(Common.USE_DEFAULT_VALUES);
	}

	public EjbContainerAvailability(int options)
	{
		super(comparators, runtimeVersion);
		// Properties (see root bean comments for the bean graph)
		initPropertyTables(1);
		this.createProperty("property", ELEMENT_PROPERTY, 
			Common.TYPE_0_N | Common.TYPE_BEAN | Common.TYPE_KEY, 
			ElementProperty.class);
		this.createAttribute(ELEMENT_PROPERTY, "name", "Name", 
						AttrProp.CDATA | AttrProp.REQUIRED,
						null, null);
		this.createAttribute(ELEMENT_PROPERTY, "value", "Value", 
						AttrProp.CDATA | AttrProp.REQUIRED,
						null, null);
		this.initialize(options);
	}

	// Setting the default values of the properties
	void initialize(int options) {

	}

	// Get Method
	public ElementProperty getElementProperty(int index) {
		return (ElementProperty)this.getValue(ELEMENT_PROPERTY, index);
	}

	// This attribute is an array, possibly empty
	public void setElementProperty(ElementProperty[] value) {
		this.setValue(ELEMENT_PROPERTY, value);
	}

	// Getter Method
	public ElementProperty[] getElementProperty() {
		return (ElementProperty[])this.getValues(ELEMENT_PROPERTY);
	}

	// Return the number of properties
	public int sizeElementProperty() {
		return this.size(ELEMENT_PROPERTY);
	}

	// Add a new element returning its index in the list
	public int addElementProperty(ElementProperty value)
			throws ConfigException{
		return addElementProperty(value, true);
	}

	// Add a new element returning its index in the list with a boolean flag
	public int addElementProperty(ElementProperty value, boolean overwrite)
			throws ConfigException{
		ElementProperty old = getElementPropertyByName(value.getName());
		if(old != null) {
			throw new ConfigException(StringManager.getManager(EjbContainerAvailability.class).getString("cannotAddDuplicate",  "ElementProperty"));
		}
		return this.addValue(ELEMENT_PROPERTY, value, overwrite);
	}

	//
	// Remove an element using its reference
	// Returns the index the element had in the list
	//
	public int removeElementProperty(ElementProperty value){
		return this.removeValue(ELEMENT_PROPERTY, value);
	}

	//
	// Remove an element using its reference
	// Returns the index the element had in the list
	// with boolean overwrite
	//
	public int removeElementProperty(ElementProperty value, boolean overwrite)
			throws StaleWriteConfigException{
		return this.removeValue(ELEMENT_PROPERTY, value, overwrite);
	}

	public ElementProperty getElementPropertyByName(String id) {
	 if (null != id) { id = id.trim(); }
	ElementProperty[] o = getElementProperty();
	 if (o == null) return null;

	 for (int i=0; i < o.length; i++) {
	     if(o[i].getAttributeValue(Common.convertName(ServerTags.NAME)).equals(id)) {
	         return o[i];
	     }
	 }

		return null;
		
	}
	/**
	* Getter for AvailabilityEnabled of the Element ejb-container-availability
	* @return  the AvailabilityEnabled of the Element ejb-container-availability
	*/
	public String getAvailabilityEnabled() {
			return getAttributeValue(ServerTags.AVAILABILITY_ENABLED);
	}
	/**
	* Modify  the AvailabilityEnabled of the Element ejb-container-availability
	* @param v the new value
	* @throws StaleWriteConfigException if overwrite is false and file changed on disk
	*/
	public void setAvailabilityEnabled(String v, boolean overwrite) throws StaleWriteConfigException {
		setAttributeValue(ServerTags.AVAILABILITY_ENABLED, v, overwrite);
	}
	/**
	* Modify  the AvailabilityEnabled of the Element ejb-container-availability
	* @param v the new value
	*/
	public void setAvailabilityEnabled(String v) {
		setAttributeValue(ServerTags.AVAILABILITY_ENABLED, v);
	}
	/**
	* Getter for SfsbHaPersistenceType of the Element ejb-container-availability
	* @return  the SfsbHaPersistenceType of the Element ejb-container-availability
	*/
	public String getSfsbHaPersistenceType() {
		return getAttributeValue(ServerTags.SFSB_HA_PERSISTENCE_TYPE);
	}
	/**
	* Modify  the SfsbHaPersistenceType of the Element ejb-container-availability
	* @param v the new value
	* @throws StaleWriteConfigException if overwrite is false and file changed on disk
	*/
	public void setSfsbHaPersistenceType(String v, boolean overwrite) throws StaleWriteConfigException {
		setAttributeValue(ServerTags.SFSB_HA_PERSISTENCE_TYPE, v, overwrite);
	}
	/**
	* Modify  the SfsbHaPersistenceType of the Element ejb-container-availability
	* @param v the new value
	*/
	public void setSfsbHaPersistenceType(String v) {
		setAttributeValue(ServerTags.SFSB_HA_PERSISTENCE_TYPE, v);
	}
	/**
	* Get the default value of SfsbHaPersistenceType from dtd
	*/
	public static String getDefaultSfsbHaPersistenceType() {
		return "ha".trim();
	}
	/**
	* Getter for SfsbPersistenceType of the Element ejb-container-availability
	* @return  the SfsbPersistenceType of the Element ejb-container-availability
	*/
	public String getSfsbPersistenceType() {
		return getAttributeValue(ServerTags.SFSB_PERSISTENCE_TYPE);
	}
	/**
	* Modify  the SfsbPersistenceType of the Element ejb-container-availability
	* @param v the new value
	* @throws StaleWriteConfigException if overwrite is false and file changed on disk
	*/
	public void setSfsbPersistenceType(String v, boolean overwrite) throws StaleWriteConfigException {
		setAttributeValue(ServerTags.SFSB_PERSISTENCE_TYPE, v, overwrite);
	}
	/**
	* Modify  the SfsbPersistenceType of the Element ejb-container-availability
	* @param v the new value
	*/
	public void setSfsbPersistenceType(String v) {
		setAttributeValue(ServerTags.SFSB_PERSISTENCE_TYPE, v);
	}
	/**
	* Get the default value of SfsbPersistenceType from dtd
	*/
	public static String getDefaultSfsbPersistenceType() {
		return "file".trim();
	}
	/**
	* Getter for SfsbCheckpointEnabled of the Element ejb-container-availability
	* @return  the SfsbCheckpointEnabled of the Element ejb-container-availability
	*/
	public String getSfsbCheckpointEnabled() {
			return getAttributeValue(ServerTags.SFSB_CHECKPOINT_ENABLED);
	}
	/**
	* Modify  the SfsbCheckpointEnabled of the Element ejb-container-availability
	* @param v the new value
	* @throws StaleWriteConfigException if overwrite is false and file changed on disk
	*/
	public void setSfsbCheckpointEnabled(String v, boolean overwrite) throws StaleWriteConfigException {
		setAttributeValue(ServerTags.SFSB_CHECKPOINT_ENABLED, v, overwrite);
	}
	/**
	* Modify  the SfsbCheckpointEnabled of the Element ejb-container-availability
	* @param v the new value
	*/
	public void setSfsbCheckpointEnabled(String v) {
		setAttributeValue(ServerTags.SFSB_CHECKPOINT_ENABLED, v);
	}
	/**
	* Getter for SfsbQuickCheckpointEnabled of the Element ejb-container-availability
	* @return  the SfsbQuickCheckpointEnabled of the Element ejb-container-availability
	*/
	public String getSfsbQuickCheckpointEnabled() {
			return getAttributeValue(ServerTags.SFSB_QUICK_CHECKPOINT_ENABLED);
	}
	/**
	* Modify  the SfsbQuickCheckpointEnabled of the Element ejb-container-availability
	* @param v the new value
	* @throws StaleWriteConfigException if overwrite is false and file changed on disk
	*/
	public void setSfsbQuickCheckpointEnabled(String v, boolean overwrite) throws StaleWriteConfigException {
		setAttributeValue(ServerTags.SFSB_QUICK_CHECKPOINT_ENABLED, v, overwrite);
	}
	/**
	* Modify  the SfsbQuickCheckpointEnabled of the Element ejb-container-availability
	* @param v the new value
	*/
	public void setSfsbQuickCheckpointEnabled(String v) {
		setAttributeValue(ServerTags.SFSB_QUICK_CHECKPOINT_ENABLED, v);
	}
	/**
	* Getter for SfsbStorePoolName of the Element ejb-container-availability
	* @return  the SfsbStorePoolName of the Element ejb-container-availability
	*/
	public String getSfsbStorePoolName() {
			return getAttributeValue(ServerTags.SFSB_STORE_POOL_NAME);
	}
	/**
	* Modify  the SfsbStorePoolName of the Element ejb-container-availability
	* @param v the new value
	* @throws StaleWriteConfigException if overwrite is false and file changed on disk
	*/
	public void setSfsbStorePoolName(String v, boolean overwrite) throws StaleWriteConfigException {
		setAttributeValue(ServerTags.SFSB_STORE_POOL_NAME, v, overwrite);
	}
	/**
	* Modify  the SfsbStorePoolName of the Element ejb-container-availability
	* @param v the new value
	*/
	public void setSfsbStorePoolName(String v) {
		setAttributeValue(ServerTags.SFSB_STORE_POOL_NAME, v);
	}
	/**
	 * Create a new bean using it's default constructor.
	 * This does not add it to any bean graph.
	 */
	public ElementProperty newElementProperty() {
		return new ElementProperty();
	}

	/**
	* get the xpath representation for this element
	* returns something like abc[@name='value'] or abc
	* depending on the type of the bean
	*/
	protected String getRelativeXPath() {
	    String ret = null;
	    ret = "ejb-container-availability";
	    return (null != ret ? ret.trim() : null);
	}

	/*
	* generic method to get default value from dtd
	*/
	public static String getDefaultAttributeValue(String attr) {
		if(attr == null) return null;
		attr = attr.trim();
		if(attr.equals(ServerTags.SFSB_HA_PERSISTENCE_TYPE)) return "ha".trim();
		if(attr.equals(ServerTags.SFSB_PERSISTENCE_TYPE)) return "file".trim();
	return null;
	}
	//
	public static void addComparator(org.netbeans.modules.schema2beans.BeanComparator c) {
		comparators.add(c);
	}

	//
	public static void removeComparator(org.netbeans.modules.schema2beans.BeanComparator c) {
		comparators.remove(c);
	}
	public void validate() throws org.netbeans.modules.schema2beans.ValidateException {
	}

	// Dump the content of this bean returning it as a String
	public void dump(StringBuffer str, String indent){
		String s;
		Object o;
		org.netbeans.modules.schema2beans.BaseBean n;
		str.append(indent);
		str.append("ElementProperty["+this.sizeElementProperty()+"]");	// NOI18N
		for(int i=0; i<this.sizeElementProperty(); i++)
		{
			str.append(indent+"\t");
			str.append("#"+i+":");
			n = (org.netbeans.modules.schema2beans.BaseBean) this.getElementProperty(i);
			if (n != null)
				n.dump(str, indent + "\t");	// NOI18N
			else
				str.append(indent+"\tnull");	// NOI18N
			this.dumpAttributes(ELEMENT_PROPERTY, i, str, indent);
		}

	}
	public String dumpBeanNode(){
		StringBuffer str = new StringBuffer();
		str.append("EjbContainerAvailability\n");	// NOI18N
		this.dump(str, "\n  ");	// NOI18N
		return str.toString();
	}}

// END_NOI18N

