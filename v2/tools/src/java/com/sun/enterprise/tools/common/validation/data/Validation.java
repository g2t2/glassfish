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

/**
 *	This generated bean class Validation matches the schema element validation
 *
 *	Generated on Wed Aug 20 17:21:25 PDT 2003
 *
 *	This class matches the root element of the DTD,
 *	and is the root of the following bean graph:
 *
 *	validation : Validation
 *		[attr: validate ENUM ( true false ) true]
 *		element : Element[0,n]
 *			name : String
 *			check : Check[0,n]
 *				name : String
 *				parameters : Parameters?
 *					parameter : Parameter[1,n]
 *						name : String
 *						value : String[1,n]
 *
 */

package com.sun.enterprise.tools.common.validation.data;

import org.w3c.dom.*;
import org.netbeans.modules.schema2beans.*;
import java.beans.*;
import java.util.*;
import java.io.*;

// BEGIN_NOI18N

public class Validation extends org.netbeans.modules.schema2beans.BaseBean
{

	static Vector comparators = new Vector();

	static public final String VALIDATE = "Validate";	// NOI18N
	static public final String ELEMENT = "Element";	// NOI18N

	public Validation() throws org.netbeans.modules.schema2beans.Schema2BeansException {
		this(null, Common.USE_DEFAULT_VALUES);
	}

	public Validation(org.w3c.dom.Node doc, int options) throws org.netbeans.modules.schema2beans.Schema2BeansException {
		this(Common.NO_DEFAULT_VALUES);
		initFromNode(doc, options);
	}
	protected void initFromNode(org.w3c.dom.Node doc, int options) throws Schema2BeansException
	{
		if (doc == null)
		{
			doc = GraphManager.createRootElementNode("validation");	// NOI18N
			if (doc == null)
				throw new Schema2BeansException(Common.getMessage(
					"CantCreateDOMRoot_msg", "validation"));
		}
		Node n = GraphManager.getElementNode("validation", doc);	// NOI18N
		if (n == null)
			throw new Schema2BeansException(Common.getMessage(
				"DocRootNotInDOMGraph_msg", "validation", doc.getFirstChild().getNodeName()));

		this.graphManager.setXmlDocument(doc);

		// Entry point of the createBeans() recursive calls
		this.createBean(n, this.graphManager());
		this.initialize(options);
	}
	public Validation(int options)
	{
		super(comparators, new org.netbeans.modules.schema2beans.Version(1, 2, 0));
		initOptions(options);
	}
	protected void initOptions(int options)
	{
		// The graph manager is allocated in the bean root
		this.graphManager = new GraphManager(this);
		this.createRoot("validation", "Validation",	// NOI18N
			Common.TYPE_1 | Common.TYPE_BEAN, Validation.class);

		// Properties (see root bean comments for the bean graph)
		this.createProperty("element", 	// NOI18N
			ELEMENT, 
			Common.TYPE_0_N | Common.TYPE_BEAN | Common.TYPE_KEY, 
			Element.class);
		this.createAttribute("validate", "Validate", 
						AttrProp.ENUM,
						new String[] {
							"true",
							"false"
						}, "true");
		this.initialize(options);
	}

	// Setting the default values of the properties
	void initialize(int options)
	{

	}

	// This attribute is mandatory
	public void setValidate(java.lang.String value) {
		setAttributeValue(VALIDATE, value);
	}

	//
	public java.lang.String getValidate() {
		return getAttributeValue(VALIDATE);
	}

	// This attribute is an array, possibly empty
	public void setElement(int index, Element value) {
		this.setValue(ELEMENT, index, value);
	}

	//
	public Element getElement(int index) {
		return (Element)this.getValue(ELEMENT, index);
	}

	// This attribute is an array, possibly empty
	public void setElement(Element[] value) {
		this.setValue(ELEMENT, value);
	}

	//
	public Element[] getElement() {
		return (Element[])this.getValues(ELEMENT);
	}

	// Return the number of properties
	public int sizeElement() {
		return this.size(ELEMENT);
	}

	// Add a new element returning its index in the list
	public int addElement(com.sun.enterprise.tools.common.validation.data.Element value) {
		return this.addValue(ELEMENT, value);
	}

	//
	// Remove an element using its reference
	// Returns the index the element had in the list
	//
	public int removeElement(com.sun.enterprise.tools.common.validation.data.Element value) {
		return this.removeValue(ELEMENT, value);
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
	public static Validation createGraph(org.w3c.dom.Node doc) throws org.netbeans.modules.schema2beans.Schema2BeansException {
		return new Validation(doc, Common.NO_DEFAULT_VALUES);
	}

	public static Validation createGraph(java.io.InputStream in) throws org.netbeans.modules.schema2beans.Schema2BeansException {
		return createGraph(in, false);
	}

	public static Validation createGraph(java.io.InputStream in, boolean validate) throws org.netbeans.modules.schema2beans.Schema2BeansException {
		Document doc = GraphManager.createXmlDocument(in, validate);
		return createGraph(doc);
	}

	//
	// This method returns the root for a new empty bean graph
	//
	public static Validation createGraph() {
		try {
			return new Validation();
		}
		catch (Schema2BeansException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void validate() throws org.netbeans.modules.schema2beans.ValidateException {
		boolean restrictionFailure = false;
		// Validating property validate
		if (getValidate() == null) {
			throw new org.netbeans.modules.schema2beans.ValidateException("getValidate() == null", "validate", this);	// NOI18N
		}
		// Validating property element
		for (int _index = 0; _index < sizeElement(); ++_index) {
			com.sun.enterprise.tools.common.validation.data.Element element = getElement(_index);
			if (element != null) {
				element.validate();
			}
		}
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
		str.append("Element["+this.sizeElement()+"]");	// NOI18N
		for(int i=0; i<this.sizeElement(); i++)
		{
			str.append(indent+"\t");
			str.append("#"+i+":");
			n = (org.netbeans.modules.schema2beans.BaseBean) this.getElement(i);
			if (n != null)
				n.dump(str, indent + "\t");	// NOI18N
			else
				str.append(indent+"\tnull");	// NOI18N
			this.dumpAttributes(ELEMENT, i, str, indent);
		}

	}
	public String dumpBeanNode(){
		StringBuffer str = new StringBuffer();
		str.append("Validation\n");	// NOI18N
		this.dump(str, "\n  ");	// NOI18N
		return str.toString();
	}}

// END_NOI18N


/*
		The following schema file has been used for generation:

<!--
  XML DTD for for validation xml.
  validation.xml is used to specify Constraints to be applied to
  elements.
 
  $Revision: 1.3 $
-->


<!--
This is the root element
-->
<!ELEMENT validation  (element*) >
<!ATTLIST validation
        validate    CDATA        (true | false)     "true">


<!--
This element represents, the set of Constraints to be applied to
the given element.
-->
<!ELEMENT element (name, check*)>


<!--
This element represents, a particular Constraint.
Note : Information about this Constraint must be provided through
corresponding <check-info> object in constraints.xml Sub element
<name> should match with <name> of corresponding <check-info>
element defined in constraints.xml.
-->
<!ELEMENT check (name, parameters?)>


<!--
This element represent, Constraint parameters.
Number of sub elements, <parameter> should match with the number
of <argument> sub elements, of corresponding <arguments> element
in constraints.xml
-->
<!ELEMENT parameters (parameter+)>


<!--
This element represents, a Constraint parameter.
Sub elements <name> should match with the <name> sub element of
corresponding <argument> element in constraints.xml
<value> could be one or more. In case of an variable array
argument, multiple <value> elements will be used.
Example : InConstraint
-->
<!ELEMENT parameter (name, value+)>


<!--
Used in elements : <element>, <check> and <parameter>
In <element> , it represents the name(xpath - complete absolute
name of an element(leaf).
In <check> , it represents name of a Constraint. This is the
linking element for <check> element in validation.xml and
<check-info> element in constraints.xml.
In <parameter>, it represents name of parameter. This is the
linking element for <parameter> element in validation.xml and
<argument> element in constraints.xml.
-->
<!ELEMENT name (#PCDATA)>


<!--
This element represents the value of a parameter.
-->
<!ELEMENT value (#PCDATA)>

*/
