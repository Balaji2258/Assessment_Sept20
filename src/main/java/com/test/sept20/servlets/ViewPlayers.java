package com.test.sept20.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.sept20.dao.PlayerDao;
import com.test.sept20.pojo.Player;

@WebServlet("/ViewPlayers")
public class ViewPlayers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out=response.getWriter();
          
        List<Player> list = PlayerDao.getAllPlayers();
        
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
						padding: 12px;
						text-align: center;
					}
					
					.nc {
						text-align: left;
					}
				</style>
        		""");
        
        out.println("<header>");
        out.println("<a href='AddPlayer.html'>Add new player</a>&nbsp;&nbsp;");
        out.println("<a href='TeamFormation.html'>View final team</a>");
        out.println("<h1>Player List</h1>"); 
        out.println("</header>");
          
        out.print("<table border='1' width='100%'");  
        out.print("<tr><th>Sl No.</th><th>ID</th><th>Name</th><th>Matches Played</th><th>Total Runs Scored</th><th>Highest Score</th>"
        		+ "<th>Wickets Taken</th><th>Ducked Out Matches</th><th>Player Type</th><th>Average Score</th>"
        		+ "<th>Edit</th><th>Delete</th></tr>");  
        int slno = 1;
        for(Player p : list){  
        	out.print("<tr><td>" + (slno++) + "</td><td>"+p.getId()+"</td><td class='nc'>"+p.getName()+"</td><td>"+p.getMatchesPlayed()
         		+ "</td><td>"+p.getTotRuns()+"</td><td>"+p.getHighScore()+"</td><td>"+p.getWktsTaken()
         		+ "</td><td>"+p.getOutOnZero()+"</td><td class='nc'>"+p.getType()+"</td><td>"+p.getAvgScore()
         		+ "</td><td><a href='UpdatePlayer?id="+p.getId()+"'>Update</a>"
         		+ "</td><td><a href='DeletePlayer?id="+p.getId()+"'>Delete</a></td></tr>");  
        }  
        out.print("</table>");  
          
        out.close();  
	}

}
