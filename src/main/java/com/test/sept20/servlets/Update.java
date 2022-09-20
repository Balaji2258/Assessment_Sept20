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

@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String name;
		int matchesPlayed = 0;
		int totRuns = 0;
		int highScore = 0;
		int wktsTaken = 0;
		int outOnZero = 0;
		String type;
		double avgScore = 0.0;
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			
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
			
			String pType = request.getParameter("type");
			
//			double avg = Double.parseDouble(request.getParameter("avgScore"));
			
			if(matchesPlayed == 0) {
				out.println("<center><font color = 'red'; weight = bolder;>Matches played cannot be 0!</font></center>");
				RequestDispatcher rd = request.getRequestDispatcher("AddPlayer.html");
				rd.include(request, response);
			}
			
			avgScore = (double) totRuns / matchesPlayed;
			
			Player player = new Player(name, matchesPlayed, totRuns, highScore, wktsTaken, outOnZero, pType, avgScore);
			player.setId(id);
			int succ = PlayerDao.updatePlayer(player);
			if(succ != 0) {
				out.println("<center><font color = 'green'; weight = bolder;>Player updated successfully!</font></center>");
//				RequestDispatcher rd = request.getRequestDispatcher("ViewPlayers");
//				rd.include(request, response);
				response.sendRedirect("ViewPlayers");
			} else {
				out.println("<center><font color = 'red'; weight = bolder;>Player could not be updated!</font></center>");
//				RequestDispatcher rd = request.getRequestDispatcher("ViewPlayers");
//				rd.include(request, response);
				response.sendRedirect("ViewPlayers");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
