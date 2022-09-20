package com.test.sept20.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.sept20.dao.PlayerDao;
import com.test.sept20.pojo.Player;
import com.test.sept20.util.Util;

@WebServlet("/UpdatePlayer")
public class UpdatePlayer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Player player = PlayerDao.getPlayerById(id);
			if(player == null) {
				out.println("<center><font color = 'red'; weight = bolder;>Player not found!</font></center>");
				RequestDispatcher rd = request.getRequestDispatcher("UpdatePlayer.html");
				rd.include(request, response);
			} else {
				out.println("""
						<style type="text/css">
							body {
							   	background: black;
							}
								        		    
							.button {
		        		    	background: beige;
		        		    	color: black;
		        		    	padding: 5px;
		        		    	height: 40px;
		        		    	width: 150px;
		        		    }
		        		    
		        		    .button:hover {
		        		    	background: grey;
		        		    	color: beige;
		        		    }
		        		    
		        		    h1 {
		        		    	text-align: center;
								padding: 10px;
							   	color: beige;
		        		    }
								        		    
							form {
								margin-left: auto;
								margin-right: auto;
								background: bisque;
								width: 30%;
								align-self: center;
								border-style: double;
								border-width: thick;
								border-color: olive;
							}
							
							table {
								padding: 20px 30px 20px 40px;
							}
													
							th, td {
								padding: 20px 10px;
							}
													
							.nc {
								text-align: left;
							}
							
							select, input {
								width: 200px;
								height: 25px;
							}
							
							.btn {
								width: 150px;
								background: beige;
								font-weight: bolder;
							}
							
							.btn:hover {
								background: black;
								color: beige;
							}
						</style>
						""");
				out.println("<header>");
		        out.println("<a href='AddPlayer.html'><input type='button' class='button' value='Add new player'></a>&nbsp;&nbsp;");
		        out.println("<a href='ViewPlayers'><input type='button' class='button' value='View all players'></a>");
		        out.println("<h1>UPDATE PLAYER</h1>"); 
		        out.println("</header>");
				out.println("<form action='Update' method='post'>\n"
						+ "	<table>"
						+ "	<tr><td>ID : </td><td><input type='int' name='id' readonly='readonly' value=" + player.getId() + "></td></tr>"
						+ "	<tr><td>Name : </td><td><input type='text' name='name' required='required' readonly='readonly' value='" + player.getName() + "'></td></tr>"
						+ "	<tr><td>No. of matches played : </td><td><input type='int' name='matchesPlayed' required='required' value=" + player.getMatchesPlayed() + "></td></tr>"
						+ "	<tr><td>Total runs scored : </td><td><input type='int' name='totRuns' required='required' value=" + player.getTotRuns() + "></td></tr>"
						+ "	<tr><td>Highest score : </td><td><input type='int' name='highScore' value=" + player.getHighScore() + "></td></tr>"
						+ "	<tr><td>No. of wickets taken : </td><td><input type='int' name='wktsTaken' value=" + player.getWktsTaken() + "></td></tr>"
						+ "	<tr><td>No. of ducked out matches : </td><td><input type='int' name='outOnZero' value=" + player.getOutOnZero() + "></td></tr>"
						+ "	<tr><td>Player type : </td><td><input type='text' name='type' required='required' readonly='readonly' value='" + player.getType() + "'></td></tr>"
						+ "	<tr><td>Average Score : </td><td><input type='double' name='avgScore' readonly='readonly' value=" + player.getAvgScore() + "></td></tr>"
						+ "	<tr><td colspan='2' align='center'><input type='submit' value='UPDATE' class='btn'></td></tr>\n"
						+ "	</table>\n"
						+ "	</form>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
