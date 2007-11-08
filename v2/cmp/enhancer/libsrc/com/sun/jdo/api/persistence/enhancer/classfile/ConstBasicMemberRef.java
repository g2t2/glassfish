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


package com.sun.jdo.api.persistence.enhancer.classfile;

import java.io.*;

/**
 * The abstract base class used to represent the various type of
 * references to members (fields/methods) within the constant pool. 
 */

public abstract class ConstBasicMemberRef extends ConstBasic {
  /* The name of the class on which the member is defined */
  protected ConstClass theClassName;

  /* The index of the class on which the member is defined
   *   - used temporarily while reading from a class file */
  protected int theClassNameIndex;

  /* The name and type of the member */
  protected ConstNameAndType theNameAndType;

  /* The index of the name and type of the member
   *   - used temporarily while reading from a class file */
  protected int theNameAndTypeIndex;

  /* public accessors */

  /**
   * Return the name of the class defining the member 
   */
  public ConstClass className() {
    return theClassName;
  }

  /**
   * Return the name and type of the member 
   */
  public ConstNameAndType nameAndType() {
    return theNameAndType;
  }

  public String toString () {
      return "className(" + theClassName.toString() + ")" +//NOI18N
          " nameAndType(" + theNameAndType.toString() + ")";//NOI18N
  }

  /* package local methods */

  /**
   * Constructor for "from scratch" creation
   */
  ConstBasicMemberRef (ConstClass cname, ConstNameAndType NT) {
    theClassName = cname;
    theNameAndType = NT;
  }

  /**
   * Constructor for reading from a class file
   */
  ConstBasicMemberRef (int cnameIndex, int NT_index) {
    theClassNameIndex = cnameIndex;
    theNameAndTypeIndex = NT_index;
  }

  void formatData (DataOutputStream b) throws IOException {
    b.writeShort(theClassName.getIndex());
    b.writeShort(theNameAndType.getIndex());
  }
  void resolve (ConstantPool p) {
    theClassName = (ConstClass) p.constantAt(theClassNameIndex);
    theNameAndType = (ConstNameAndType) p.constantAt(theNameAndTypeIndex);
  }
}

