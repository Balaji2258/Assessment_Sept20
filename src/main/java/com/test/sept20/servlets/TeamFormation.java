package com.test.sept20.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.protocol.Resultset;
import com.test.sept20.dao.PlayerDao;
import com.test.sept20.pojo.Player;
import com.test.sept20.util.Util;

@WebServlet("/TeamFormation")
public class TeamFormation extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out=response.getWriter();  
        try {
//	        out.println("<a href='AddPlayer.html'>Add New Player</a><br>");
//	        out.println("<a href='ViewPlayers'>View all players</a>");
//	        
	        int noBowlers;
	//		int playerCount = AddPlayer.playerCount;
	        String sql = "select count(*) as count from player where ptype = 'bowler'";
	        Connection con = Util.getConnection();
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);
	        
	        int bowlerCount = 0;
	        if(rs.next())
	        	bowlerCount = rs.getInt("count");
	
			noBowlers = Integer.parseInt(request.getParameter("reqBowlers"));
			if(noBowlers > bowlerCount) {
				out.println("<center><font color = 'red'; weight = bolder;>No. of bowlers exceeds actual bowler count!</font></center>");
				RequestDispatcher rd = request.getRequestDispatcher("TeamFormation.html");
				rd.include(request, response);
			} else {
		        List<Player> list = PlayerDao.formTeam(noBowlers);
		        
		        out.println("""
		        		<style type="text/css">
		        		    body {
		        		    	background: black;
		        		    }
		        		    
		        		    a {
		        		    	background: beige;
		        		    	color: black;
		        		    	padding: 5px;
		        		    }
		        		    
		        		    a:hover {
		        		    	background: grey;
		        		    	color: beige;
		        		    }
		        		    
		        		    h1 {
		        		    	color: beige;
		        		    }
		        		    
							table {
								background: bisque;
							}
							
							th, td {
								padding: 20px 10px;
								text-align: center;
							}
							
							.nc {
								text-align: left;
							}
						</style>
		        		""");
		        
		        out.println("<header>");
		        out.println("<a href='AddPlayer.html'>Add new player</a>&nbsp;&nbsp;");
		        out.println("<a href='ViewPlayers'>View all players</a>");
		        out.println("<h1>Final Team</h1>"); 
		        out.println("</header>");
		          
		        out.print("<table border='1' width='100%' style='background: bisque'");  
		        out.print("<tr><th>Sl No.</th><th>ID</th><th>Name</th><th>Matches Played</th><th>Total Runs Scored</th><th>Highest Score</th>"
		        		+ "<th>Wickets Taken</th><th>Ducked Out Matches</th><th>Player Type</th><th>Average Score</th></tr>");  
		        
		        int slno = 1;
		        for(Player p : list){  
		        	out.print("<tr><td>"  + (slno++) + "</td><td>"+p.getId()+"</td><td class='nc'>"+p.getName()+"</td><td>"+p.getMatchesPlayed()
		         		+ "</td><td>"+p.getTotRuns()+"</td><td>"+p.getHighScore()+"</td><td>"+p.getWktsTaken()
		         		+ "</td><td>"+p.getOutOnZero()+"</td><td class='nc'>"+p.getType()+"</td><td>"+p.getAvgScore());  
		        }  
		        out.print("</table>");
			}
        } catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

}
