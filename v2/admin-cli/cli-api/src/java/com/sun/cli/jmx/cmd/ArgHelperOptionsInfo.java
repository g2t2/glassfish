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
 * $Header: /cvs/glassfish/admin-cli/cli-api/src/java/com/sun/cli/jmx/cmd/ArgHelperOptionsInfo.java,v 1.4 2006/11/10 21:14:44 dpatil Exp $
 * $Revision: 1.4 $
 * $Date: 2006/11/10 21:14:44 $
 */
package com.sun.cli.jmx.cmd;

import java.util.HashMap;
import java.util.Iterator;
 


public class ArgHelperOptionsInfo implements ArgHelper.OptionsInfo
{
	final HashMap		mOptionDescriptions;
	
		public
	ArgHelperOptionsInfo( )
		throws ArgHelper.IllegalOptionException
	{
		mOptionDescriptions	= new HashMap();
	}
	
		public
	ArgHelperOptionsInfo( String options )
		throws ArgHelper.IllegalOptionException
	{
		mOptionDescriptions	= new HashMap();
		
		addOptions( options );
	}
	
		public String
	tokenToOptionName( String token )
	{
		final int	delimIndex	= token.indexOf( '=' );
		
		String	name	= token;
		
		if ( delimIndex > 0 )
		{
			name	= token.substring( 0, delimIndex );
		}
		
		return( name );
	}
	
		public String
	tokenToOptionData( String token )
	{
		final int	delimIndex	= token.indexOf( '=' );
		
		String	data	= null;	// return null if no data eg "--foo"
		
		if ( delimIndex > 0 )
		{
			// note: form "--foo=" is valid and should result in an empty string
			data	= token.substring( delimIndex + 1, token.length() );
		}
		
		return( data );
	}
	
		private static void
	dm( Object o )
	{
		System.out.println( o.toString() );
	}
	
		OptionDesc
	findOptionDesc( String token )
	{
		final String	optionName	= tokenToOptionName( OptionDesc.mapName( token ) );
		
		return( (OptionDesc)mOptionDescriptions.get( optionName ) );
	}
	
		public boolean
	isLegalOption( String token )
	{
		if ( ! token.startsWith( "-" ) )
		{
			return( false );
		}

		final boolean	isLegal	= (findOptionDesc( token ) != null);
		
		return( isLegal );
	}
	
		public void
	checkLegalOption( String token )
	{
		if ( ! isLegalOption( token ) )
		{
			throw new IllegalArgumentException( "illegal option: " + token );
		}
	}
	
		public int
	getNumValues( String token )
	{
		checkLegalOption( token );
		
		final OptionDesc	info	= findOptionDesc( token );
		
		return( info.mNumValues );
	}
	
		public boolean
	isBoolean( String token )
	{
		checkLegalOption( token );
		
		final OptionDesc	info	= findOptionDesc( token );
		
		return( info.mIsBoolean );
	}
	
		public void
	foundIllegalOption( final String token  )
		throws ArgHelper.IllegalOptionException
	{
		throw new ArgHelper.IllegalOptionException( "illegal option: " + token );
	}
	
		boolean
	exists( final String optionName )
	{
		return( mOptionDescriptions.get( optionName ) != null );
	}
	
		void
	checkExists( final String optionName )
	{
		if ( exists( optionName ) )
		{
			throw new IllegalArgumentException( "can't add same option twice: " + optionName );
		}
	}
	
		void
	add( final OptionDesc desc )
	{
		checkExists( desc.mName );
		
		mOptionDescriptions.put( desc.mName, desc );
	}

		public void
	addBoolean( final String name )
		throws ArgHelper.IllegalOptionException
	{
		add( new OptionDesc( name ) );
	}
	
	
		public void
	addNonBoolean( final String name, final int numValues )
		throws ArgHelper.IllegalOptionException
	{
		add( new OptionDesc( name, numValues ) );
	}
	
	
	public final static char	MULTIPLE_DELIM	= ' ';
	public final static char	NUMVALUES_DELIM	= ',';
	
		public void
	addOptions( String list )
		throws ArgHelper.IllegalOptionException
	{
		final String []	names	= list.split(" ");
		
		for (int i = 0; i < names.length; ++i) {
                     final String[] data = names[i].split("" + NUMVALUES_DELIM);

                     if (data.length == 1) {
                         addBoolean(data[0]);
                     } else {
                         addNonBoolean(data[0],
                                       Integer.valueOf(data[1]).intValue());
                     }
                 }
	}
		
		
	
	public final static class OptionDesc
	{
		public String	mName;	// include "-" or "--" prefix
		public int		mNumValues;
		public boolean	mIsBoolean;
		
			
			boolean
		isLegalOptionNameChar( final char theChar )
		{
			return(	(theChar >= 'a' && theChar <= 'z') ||
					(theChar >= 'A' && theChar <= 'Z') ||
					(theChar >= '0' && theChar <= '9') ||
					( theChar == '-' || theChar == '_' || theChar == '.' )
				);
		}

			void
		validateName( String name )
			throws ArgHelper.IllegalOptionException
		{
			final boolean	isLongOption	= name.startsWith( "--" );
			final boolean	isShortOption	= (! isLongOption) && name.startsWith( "-" );
			final int		length	= name.length();
			
			if ( ! (isLongOption || isShortOption) )
			{
				throw new ArgHelper.IllegalOptionException( "invalid option name: " + name );
			}
			
			if ( isShortOption && length != 2 )
			{
				throw new ArgHelper.IllegalOptionException( "invalid short option name: " + name );
			}
			
			for( int i = 0; i < length; ++i )
			{
				final char	theChar	= name.charAt( i );
				
				if ( ! isLegalOptionNameChar( theChar ) )
				{
					throw new ArgHelper.IllegalOptionException( "invalid character '" +
						theChar + "' in option name: " + name );
				}
			}
		}
		
		OptionDesc( String name, int numValues )
			throws ArgHelper.IllegalOptionException
		{
			mName		= mapName( name );
			
			validateName( mName );
			
			if ( numValues == 0 )
			{
				throw new IllegalArgumentException( "use OptionDesc( name ) for boolean options" );
			}
			
			mNumValues	= numValues;
			mIsBoolean	= false;
		}
		
		OptionDesc( String name )
			throws ArgHelper.IllegalOptionException
		{
			mName		= mapName( name );
			
			validateName( mName );
			
			mIsBoolean	= true;
			mNumValues	= 1;
		}
		
			static String
		mapName( String name )
		{
			String	mappedName	= name;
			
			if ( ! name.startsWith( "-" ) )
			{
				final String	prefix	= (name.length() == 1) ? "-" : "--";
				
				mappedName	= prefix + name;
			}
			return( mappedName );
		}
		
			public String
		toString()
		{
			if ( mIsBoolean )
			{
				return( mName + ":boolean" );
			}
			return( mName + ":" + mNumValues + " values" );
		}
	}

}

