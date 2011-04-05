package org.ow2.aspirerfid.tracking;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.PropertyConfigurator;

/**
 * @author Kostas Mourtzoukos (komo) e-mail: komo@ait.edu.gr
 *
 */

/**
 * Servlet implementation class manage
 */
public class manage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public manage() {
        super();
    }
    
    
    /**
     * Initialise the 'manage' servlet. Also start the thread that periodicaly pols the 
     * EPCIS repository for new events
     * @param config
     * @throws ServletException
     */
    public void init(ServletConfig config)throws ServletException
    {
    	super.init(config);
    	PropertyConfigurator.configure(manage.class.getResource("/log4j.properties"));
    	// Start the thread that periodically polls the Local EPCIS for new data
    	// and reports them accordingly
    	GetAndReport garep = new GetAndReport();
    	Thread thread = new Thread(garep);
    	thread.start();
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO Add future functionality
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO Add future functionality
	}

}
