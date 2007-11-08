/**
 *	This generated bean class Webservices matches the schema element webservices
 *
 *	===============================================================
 *	
 *	
 *		This group keeps the usage of the contained description related
 *		elements consistent across J2EE deployment descriptors.
 *	
 *		All elements may occur multiple times with different languages,
 *		to support localization of the content.
 *	
 *	      
 *	===============================================================
 *	Generated on Fri Apr 22 15:42:52 PDT 2005
 *
 *	This class matches the root element of the XML Schema,
 *	and is the root of the following bean graph:
 *
 *	webservices : Webservices
 *		[attr: version CDATA #FIXED 1.1 : java.math.BigDecimal]
 *		[attr: id CDATA #IMPLIED  : java.lang.String]
 *		description : java.lang.String[0,n]
 *			[attr: id CDATA #IMPLIED  : java.lang.String]
 *			[attr: xml:lang CDATA #IMPLIED  : java.lang.String]
 *		display-name : java.lang.String[0,n]
 *			[attr: id CDATA #IMPLIED  : java.lang.String]
 *			[attr: xml:lang CDATA #IMPLIED  : java.lang.String]
 *		icon : IconType[0,n]
 *			[attr: xml:lang CDATA #IMPLIED  : java.lang.String]
 *			[attr: id CDATA #IMPLIED  : java.lang.String]
 *			small-icon : java.lang.String?
 *			large-icon : java.lang.String?
 *		webservice-description : WebserviceDescriptionType[1,n]
 *			[attr: id CDATA #IMPLIED  : java.lang.String]
 *			description : java.lang.String?
 *				[attr: id CDATA #IMPLIED  : java.lang.String]
 *				[attr: xml:lang CDATA #IMPLIED  : java.lang.String]
 *			display-name : java.lang.String?
 *				[attr: id CDATA #IMPLIED  : java.lang.String]
 *				[attr: xml:lang CDATA #IMPLIED  : java.lang.String]
 *			icon : IconType?
 *				[attr: xml:lang CDATA #IMPLIED  : java.lang.String]
 *				[attr: id CDATA #IMPLIED  : java.lang.String]
 *				small-icon : java.lang.String?
 *				large-icon : java.lang.String?
 *			webservice-description-name : java.lang.String
 *				[attr: id CDATA #IMPLIED  : java.lang.String]
 *			wsdl-file : java.lang.String
 *			jaxrpc-mapping-file : java.lang.String
 *			port-component : PortComponentType[1,n]
 *				[attr: id CDATA #IMPLIED  : java.lang.String]
 *				description : java.lang.String?
 *					[attr: id CDATA #IMPLIED  : java.lang.String]
 *					[attr: xml:lang CDATA #IMPLIED  : java.lang.String]
 *				display-name : java.lang.String?
 *					[attr: id CDATA #IMPLIED  : java.lang.String]
 *					[attr: xml:lang CDATA #IMPLIED  : java.lang.String]
 *				icon : IconType?
 *					[attr: xml:lang CDATA #IMPLIED  : java.lang.String]
 *					[attr: id CDATA #IMPLIED  : java.lang.String]
 *					small-icon : java.lang.String?
 *					large-icon : java.lang.String?
 *				port-component-name : java.lang.String
 *					[attr: id CDATA #IMPLIED  : java.lang.String]
 *				wsdl-port : javax.xml.namespace.QName
 *					[attr: id CDATA #IMPLIED  : java.lang.String]
 *				service-endpoint-interface : java.lang.String
 *				service-impl-bean : ServiceImplBeanType
 *					[attr: id CDATA #IMPLIED  : java.lang.String]
 *					| ejb-link : java.lang.String
 *					| servlet-link : java.lang.String
 *				handler : PortComponentHandlerType[0,n]
 *					[attr: id CDATA #IMPLIED  : java.lang.String]
 *					description : java.lang.String[0,n]
 *						[attr: id CDATA #IMPLIED  : java.lang.String]
 *						[attr: xml:lang CDATA #IMPLIED  : java.lang.String]
 *					display-name : java.lang.String[0,n]
 *						[attr: id CDATA #IMPLIED  : java.lang.String]
 *						[attr: xml:lang CDATA #IMPLIED  : java.lang.String]
 *					icon : IconType[0,n]
 *						[attr: xml:lang CDATA #IMPLIED  : java.lang.String]
 *						[attr: id CDATA #IMPLIED  : java.lang.String]
 *						small-icon : java.lang.String?
 *						large-icon : java.lang.String?
 *					handler-name : java.lang.String
 *						[attr: id CDATA #IMPLIED  : java.lang.String]
 *					handler-class : java.lang.String
 *					init-param : ParamValueType[0,n]
 *						[attr: id CDATA #IMPLIED  : java.lang.String]
 *						description : java.lang.String[0,n]
 *							[attr: id CDATA #IMPLIED  : java.lang.String]
 *							[attr: xml:lang CDATA #IMPLIED  : java.lang.String]
 *						param-name : java.lang.String
 *							[attr: id CDATA #IMPLIED  : java.lang.String]
 *						param-value : java.lang.String
 *							[attr: id CDATA #IMPLIED  : java.lang.String]
 *					soap-header : javax.xml.namespace.QName[0,n]
 *						[attr: id CDATA #IMPLIED  : java.lang.String]
 *					soap-role : java.lang.String[0,n]
 *						[attr: id CDATA #IMPLIED  : java.lang.String]
 *
 */

package com.sun.enterprise.tools.common.dd.webservice;

import org.w3c.dom.*;
import org.netbeans.modules.schema2beans.*;
import java.beans.*;
import java.util.*;
import java.io.*;

// BEGIN_NOI18N

public class Webservices extends org.netbeans.modules.schema2beans.BaseBean
{

	static Vector comparators = new Vector();

	static public final String DESCRIPTION = "Description";	// NOI18N
	static public final String DISPLAY_NAME = "DisplayName";	// NOI18N
	static public final String ICON = "Icon";	// NOI18N
	static public final String WEBSERVICE_DESCRIPTION = "WebserviceDescription";	// NOI18N

	public Webservices() {
		this(null, Common.USE_DEFAULT_VALUES);
	}

	public Webservices(org.w3c.dom.Node doc, int options) {
		this(Common.NO_DEFAULT_VALUES);
		try {
			initFromNode(doc, options);
		}
		catch (Schema2BeansException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	protected void initFromNode(org.w3c.dom.Node doc, int options) throws Schema2BeansException
	{
		if (doc == null)
		{
			doc = GraphManager.createRootElementNode("webservices");	// NOI18N
			if (doc == null)
				throw new Schema2BeansException("Cannot create DOM root");	// NOI18N
		}
		Node n = GraphManager.getElementNode("webservices", doc);	// NOI18N
		if (n == null)
			throw new Schema2BeansException("Doc root not in the DOM graph");	// NOI18N
		;

		this.graphManager.setXmlDocument(doc);

		// Entry point of the createBeans() recursive calls
		this.createBean(n, this.graphManager());
		this.initialize(options);
	}
	public Webservices(int options)
	{
		super(comparators, new org.netbeans.modules.schema2beans.Version(1, 2, 0));
		initOptions(options);
	}
	protected void initOptions(int options)
	{
		// The graph manager is allocated in the bean root
		this.graphManager = new GraphManager(this);
		this.createRoot("webservices", "Webservices",	// NOI18N
			Common.TYPE_1 | Common.TYPE_BEAN, Webservices.class);

		// Properties (see root bean comments for the bean graph)
		this.createProperty("description", 	// NOI18N
			DESCRIPTION, 
			Common.TYPE_0_N | Common.TYPE_STRING | Common.TYPE_KEY, 
			java.lang.String.class);
		this.createAttribute(DESCRIPTION, "id", "Id", 
						AttrProp.CDATA | AttrProp.IMPLIED,
						null, null);
		this.createAttribute(DESCRIPTION, "xml:lang", "XmlLang", 
						AttrProp.CDATA | AttrProp.IMPLIED,
						null, null);
		this.createProperty("display-name", 	// NOI18N
			DISPLAY_NAME, 
			Common.TYPE_0_N | Common.TYPE_STRING | Common.TYPE_KEY, 
			java.lang.String.class);
		this.createAttribute(DISPLAY_NAME, "id", "Id", 
						AttrProp.CDATA | AttrProp.IMPLIED,
						null, null);
		this.createAttribute(DISPLAY_NAME, "xml:lang", "XmlLang", 
						AttrProp.CDATA | AttrProp.IMPLIED,
						null, null);
		this.createProperty("icon", 	// NOI18N
			ICON, 
			Common.TYPE_0_N | Common.TYPE_BEAN | Common.TYPE_KEY, 
			IconType.class);
		this.createAttribute(ICON, "xml:lang", "XmlLang", 
						AttrProp.CDATA | AttrProp.IMPLIED,
						null, null);
		this.createAttribute(ICON, "id", "Id", 
						AttrProp.CDATA | AttrProp.IMPLIED,
						null, null);
		this.createProperty("webservice-description", 	// NOI18N
			WEBSERVICE_DESCRIPTION, 
			Common.TYPE_1_N | Common.TYPE_BEAN | Common.TYPE_KEY, 
			WebserviceDescriptionType.class);
		this.createAttribute(WEBSERVICE_DESCRIPTION, "id", "Id", 
						AttrProp.CDATA | AttrProp.IMPLIED,
						null, null);
		this.createAttribute("version", "Version", 
						AttrProp.CDATA | AttrProp.FIXED,
						null, "1.1");
		this.createAttribute("id", "Id", 
						AttrProp.CDATA | AttrProp.IMPLIED,
						null, null);
		this.initialize(options);
	}

	// Setting the default values of the properties
	void initialize(int options)
	{
		setDefaultNamespace("http://java.sun.com/xml/ns/j2ee");

	}

	// This attribute is an array, possibly empty
	public void setDescription(int index, java.lang.String value) {
		this.setValue(DESCRIPTION, index, value);
	}

	//
	public java.lang.String getDescription(int index) {
		return (java.lang.String)this.getValue(DESCRIPTION, index);
	}

	// This attribute is an array, possibly empty
	public void setDescription(java.lang.String[] value) {
		this.setValue(DESCRIPTION, value);
	}

	//
	public java.lang.String[] getDescription() {
		return (java.lang.String[])this.getValues(DESCRIPTION);
	}

	// Return the number of properties
	public int sizeDescription() {
		return this.size(DESCRIPTION);
	}

	// Add a new element returning its index in the list
	public int addDescription(java.lang.String value) {
		return this.addValue(DESCRIPTION, value);
	}

	//
	// Remove an element using its reference
	// Returns the index the element had in the list
	//
	public int removeDescription(java.lang.String value) {
		return this.removeValue(DESCRIPTION, value);
	}

	// This attribute is an array, possibly empty
	public void setDisplayName(int index, java.lang.String value) {
		this.setValue(DISPLAY_NAME, index, value);
	}

	//
	public java.lang.String getDisplayName(int index) {
		return (java.lang.String)this.getValue(DISPLAY_NAME, index);
	}

	// This attribute is an array, possibly empty
	public void setDisplayName(java.lang.String[] value) {
		this.setValue(DISPLAY_NAME, value);
	}

	//
	public java.lang.String[] getDisplayName() {
		return (java.lang.String[])this.getValues(DISPLAY_NAME);
	}

	// Return the number of properties
	public int sizeDisplayName() {
		return this.size(DISPLAY_NAME);
	}

	// Add a new element returning its index in the list
	public int addDisplayName(java.lang.String value) {
		return this.addValue(DISPLAY_NAME, value);
	}

	//
	// Remove an element using its reference
	// Returns the index the element had in the list
	//
	public int removeDisplayName(java.lang.String value) {
		return this.removeValue(DISPLAY_NAME, value);
	}

	// This attribute is an array, possibly empty
	public void setIcon(int index, IconType value) {
		this.setValue(ICON, index, value);
	}

	//
	public IconType getIcon(int index) {
		return (IconType)this.getValue(ICON, index);
	}

	// This attribute is an array, possibly empty
	public void setIcon(IconType[] value) {
		this.setValue(ICON, value);
	}

	//
	public IconType[] getIcon() {
		return (IconType[])this.getValues(ICON);
	}

	// Return the number of properties
	public int sizeIcon() {
		return this.size(ICON);
	}

	// Add a new element returning its index in the list
	public int addIcon(com.sun.enterprise.tools.common.dd.webservice.IconType value) {
		return this.addValue(ICON, value);
	}

	//
	// Remove an element using its reference
	// Returns the index the element had in the list
	//
	public int removeIcon(com.sun.enterprise.tools.common.dd.webservice.IconType value) {
		return this.removeValue(ICON, value);
	}

	// This attribute is an array containing at least one element
	public void setWebserviceDescription(int index, WebserviceDescriptionType value) {
		this.setValue(WEBSERVICE_DESCRIPTION, index, value);
	}

	//
	public WebserviceDescriptionType getWebserviceDescription(int index) {
		return (WebserviceDescriptionType)this.getValue(WEBSERVICE_DESCRIPTION, index);
	}

	// This attribute is an array containing at least one element
	public void setWebserviceDescription(WebserviceDescriptionType[] value) {
		this.setValue(WEBSERVICE_DESCRIPTION, value);
	}

	//
	public WebserviceDescriptionType[] getWebserviceDescription() {
		return (WebserviceDescriptionType[])this.getValues(WEBSERVICE_DESCRIPTION);
	}

	// Return the number of properties
	public int sizeWebserviceDescription() {
		return this.size(WEBSERVICE_DESCRIPTION);
	}

	// Add a new element returning its index in the list
	public int addWebserviceDescription(com.sun.enterprise.tools.common.dd.webservice.WebserviceDescriptionType value) {
		return this.addValue(WEBSERVICE_DESCRIPTION, value);
	}

	//
	// Remove an element using its reference
	// Returns the index the element had in the list
	//
	public int removeWebserviceDescription(com.sun.enterprise.tools.common.dd.webservice.WebserviceDescriptionType value) {
		return this.removeValue(WEBSERVICE_DESCRIPTION, value);
	}

	//
	public static void addComparator(org.netbeans.modules.schema2beans.BeanComparator c) {
		comparators.add(c);
	}

	//
	public static void removeComparator(org.netbeans.modules.schema2beans.BeanComparator c) {
		comparators.remove(c);
	}
	//
	// This method returns the root of the bean graph
	// Each call creates a new bean graph from the specified DOM graph
	//
	public static Webservices createGraph(org.w3c.dom.Node doc) {
		return new Webservices(doc, Common.NO_DEFAULT_VALUES);
	}

	public static Webservices createGraph(java.io.InputStream in) {
		return createGraph(in, false);
	}

	public static Webservices createGraph(java.io.InputStream in, boolean validate) {
		try {
			Document doc = GraphManager.createXmlDocument(in, validate);
			return createGraph(doc);
		}
		catch (Exception t) {
			t.printStackTrace();
			throw new RuntimeException("DOM graph creation failed");	// NOI18N
		}
	}

	//
	// This method returns the root for a new empty bean graph
	//
	public static Webservices createGraph() {
		return new Webservices();
	}

	public void validate() throws org.netbeans.modules.schema2beans.ValidateException {
	}

	// Special serializer: output XML as serialization
	private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		write(baos);
		String str = baos.toString();;
		// System.out.println("str='"+str+"'");
		out.writeUTF(str);
	}
	// Special deserializer: read XML as deserialization
	private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException{
		try{
			init(comparators, new org.netbeans.modules.schema2beans.Version(1, 2, 0));
			String strDocument = in.readUTF();
			// System.out.println("strDocument='"+strDocument+"'");
			ByteArrayInputStream bais = new ByteArrayInputStream(strDocument.getBytes());
			Document doc = GraphManager.createXmlDocument(bais, false);
			initOptions(Common.NO_DEFAULT_VALUES);
			initFromNode(doc, Common.NO_DEFAULT_VALUES);
		}
		catch (Schema2BeansException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	// Dump the content of this bean returning it as a String
	public void dump(StringBuffer str, String indent){
		String s;
		Object o;
		org.netbeans.modules.schema2beans.BaseBean n;
		str.append(indent);
		str.append("Description["+this.sizeDescription()+"]");	// NOI18N
		for(int i=0; i<this.sizeDescription(); i++)
		{
			str.append(indent+"\t");
			str.append("#"+i+":");
			str.append(indent+"\t");	// NOI18N
			str.append("<");	// NOI18N
			s = this.getDescription(i);
			str.append((s==null?"null":s.trim()));	// NOI18N
			str.append(">\n");	// NOI18N
			this.dumpAttributes(DESCRIPTION, i, str, indent);
		}

		str.append(indent);
		str.append("DisplayName["+this.sizeDisplayName()+"]");	// NOI18N
		for(int i=0; i<this.sizeDisplayName(); i++)
		{
			str.append(indent+"\t");
			str.append("#"+i+":");
			str.append(indent+"\t");	// NOI18N
			str.append("<");	// NOI18N
			s = this.getDisplayName(i);
			str.append((s==null?"null":s.trim()));	// NOI18N
			str.append(">\n");	// NOI18N
			this.dumpAttributes(DISPLAY_NAME, i, str, indent);
		}

		str.append(indent);
		str.append("Icon["+this.sizeIcon()+"]");	// NOI18N
		for(int i=0; i<this.sizeIcon(); i++)
		{
			str.append(indent+"\t");
			str.append("#"+i+":");
			n = (org.netbeans.modules.schema2beans.BaseBean) this.getIcon(i);
			if (n != null)
				n.dump(str, indent + "\t");	// NOI18N
			else
				str.append(indent+"\tnull");	// NOI18N
			this.dumpAttributes(ICON, i, str, indent);
		}

		str.append(indent);
		str.append("WebserviceDescription["+this.sizeWebserviceDescription()+"]");	// NOI18N
		for(int i=0; i<this.sizeWebserviceDescription(); i++)
		{
			str.append(indent+"\t");
			str.append("#"+i+":");
			n = (org.netbeans.modules.schema2beans.BaseBean) this.getWebserviceDescription(i);
			if (n != null)
				n.dump(str, indent + "\t");	// NOI18N
			else
				str.append(indent+"\tnull");	// NOI18N
			this.dumpAttributes(WEBSERVICE_DESCRIPTION, i, str, indent);
		}

	}
	public String dumpBeanNode(){
		StringBuffer str = new StringBuffer();
		str.append("Webservices\n");	// NOI18N
		this.dump(str, "\n  ");	// NOI18N
		return str.toString();
	}}

// END_NOI18N


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
