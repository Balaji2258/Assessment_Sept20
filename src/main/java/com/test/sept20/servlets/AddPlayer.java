package com.test.sept20.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.sept20.dao.PlayerDao;
import com.test.sept20.pojo.Player;

@WebServlet("/AddPlayer")
public class AddPlayer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static int playerCount = 0;
	public static int bowlerCount = 0;
	private static boolean wktKeeper = false;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String name;
		int matchesPlayed = 0;
		int totRuns = 0;
		int highScore = 0;
		int wktsTaken = 0;
		int outOnZero = 0;
		String pType;
		double avgScore = 0.0;
		int succ=0;
		
		try {
			name = request.getParameter("name");
			
			matchesPlayed = Integer.parseInt(request.getParameter("matchesPlayed"));
			
			totRuns = Integer.parseInt(request.getParameter("totRuns"));
			
			String hs = request.getParameter("highScore");
			if(hs != null)
				highScore = Integer.parseInt(hs);
			
			String wt = request.getParameter("wktsTaken");
			if(wt != null)
				wktsTaken = Integer.parseInt(wt);
			
			String oz = request.getParameter("outOnZero");
			if(oz != null)
				outOnZero = Integer.parseInt(oz);
			
			pType = request.getParameter("type");
			
			if(matchesPlayed == 0) {
				out.println("<center><font color = 'red'; weight = bolder;>Matches played cannot be 0!</font></center>");
//				RequestDispatcher rd = request.getRequestDispatcher("ViewPlayers");
//				rd.include(request, response);
				response.sendRedirect("ViewPlayers");
			}
			
			avgScore = (double) totRuns / matchesPlayed;
			
			if(pType.equalsIgnoreCase("wicketkeeper")) {
				if(!wktKeeper) {
					wktKeeper = true;
				}
				else {
					out.println("<center><font color = 'red'; weight = bolder;>You already have a wicket keeper!</font></center>");
					RequestDispatcher rd = request.getRequestDispatcher("AddPlayer.html");
					rd.include(request, response);
				}
			}
			else if(pType.equalsIgnoreCase("bowler"))
				bowlerCount++;
			
//			response.sendRedirect("ViewPlayers");
			
			Player player = new Player(name, matchesPlayed, totRuns, highScore, wktsTaken, outOnZero, pType, avgScore);
			succ = PlayerDao.addPlayer(player);
			
			if(playerCount > 11 && bowlerCount < 3) {
				out.println("<center><font color = 'red'; weight = bolder;>No. of bowlers is less than 3.. Please add another bowler!</font></center>");
//				RequestDispatcher rd = request.getRequestDispatcher("AddPlayer.html");
//				rd.include(request, response);
//				response.sendRedirect("ViewPlayers");
			}
			
			if(succ > 0) {
				out.println("<center><font color = 'green'; weight = bolder;>Player added successfully!</font></center>");
				RequestDispatcher rd = request.getRequestDispatcher("ViewPlayers");
				rd.include(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
