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

package javax.xml.registry;

/**
 * This exception is thrown when the JAXR provider finds a Object that is
 * out-of-place or of the wrong type within the context of a user request.
 * For example a saveOrganizations request in BusinessLifeCycleManager would
 * throw this Exception if it found an Object other than Organization in the
 * Collection of Objects provided by client to be saved by the request.
 *
 * @author Farrukh S. Najmi
 */
public class UnexpectedObjectException extends JAXRException {

	 /**
     * Constructs a <code>JAXRException</code> object with no
     * reason or embedded Throwable.
     */
    public UnexpectedObjectException() {
        super();
		this.cause = null;
    }

    /**
     * Constructs a <code>JAXRException</code> object with the given
     * <code>String</code> as the reason for the exception being thrown.
     *
     * @param reason a description of what caused the exception
     */
    public UnexpectedObjectException(String reason) {
        super(reason);
		this.cause = null;
    }

    /**
     * Constructs a <code>JAXRException</code> object with the given
     * <code>String</code> as the reason for the exception being thrown
     * and the given <code>Throwable</code> object as an embedded
     * Throwable.
     *
     * @param reason a description of what caused the exception
     * @param cause a <code>Throwable</code> object that is to
     *        be embedded in this <code>JAXRException</code> object
     */
    public UnexpectedObjectException(String reason, Throwable cause) {
       super (reason);
       initCause(cause);
    }

    /**
     * Constructs a <code>JAXRException</code> object initialized
     * with the given <code>Throwable</code> object.
     *
     * @param cause the Throwable that caused this Exception
     */
    public UnexpectedObjectException(Throwable cause) {
		super (cause.toString());
		initCause(cause);
    }
}
