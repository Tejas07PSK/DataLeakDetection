/*  -> Designed for testing and development purposes.
 *  -> Project to design a 'SecurityPatching' prototype.
 *  -> Development Phase -- Intermediate.
 *  -> Project Type -- Educational.
 *  -> Owner/Designer of code file :
 *             @ Name - Palash Sarkar.
 *             @ Email - palashsarkar0007@gmail.com.
 *  -> Copyright Norms - Every piece of code given below has been written by 'Palash Sarkar (Tj07)'©,
 *                       and he holds the rights to the file. Not meant to be
 *                       copied or tampered with, without prior permission from the author.
 *  -> Guide - Rudraprasad Pradhan.
 */

package org.dld.datahandling;

import java.io.Serializable;
import java.sql.*;

public abstract class Rdbms implements Serializable
{

       private static final long serialVersionUID = 1L;

       final private String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
       final private String DB_URL = "jdbc:mysql://" + "mysql" + ":3306/DataLeakDetection";
       final private String USER = "palashsarkaradmin";
       final private String PASS = "tejas!!";

       private static Connection conn = null;

       protected Rdbms () {

           if ( conn == null ) {

                    try {

                        Class.forName( JDBC_DRIVER ).newInstance();
                        System.out.println( "Connecting to database..." );
                        conn = DriverManager.getConnection( DB_URL, USER, PASS );

                    } catch( SQLException se ) {

                        se.printStackTrace();
                        conn = null;

                    } catch( Exception e ) {

                        e.printStackTrace();
                        conn = null;

                    } finally {

                        if (conn != null) { System.out.println( "Connection Successful!!" ); }
                        else { System.out.println( "Connection Unsuccessful!!" ); stopDBConnection(); }

                    }

           }

       }

       protected synchronized static void stopDBConnection () {

           if ( conn != null ) {

                    try {

                        conn.close();

                    } catch( SQLException se ) {

                        se.printStackTrace();

                    } catch ( Exception e ) {

                        e.printStackTrace();

                    } finally { System.out.println( "Connection stopped ... !!" ); conn = null; }

           }

       }

       protected synchronized static Connection getConn () { return ( conn ); }

}