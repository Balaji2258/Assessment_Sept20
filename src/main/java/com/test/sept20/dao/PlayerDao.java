package com.test.sept20.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.test.sept20.pojo.Player;
import com.test.sept20.util.Util;

public class PlayerDao {

	public static int addPlayer(Player player) {
		Statement stmt = null;
		String sql = null;
		try {
			sql = "insert into player(pname, matchesPlayed, totRuns, highScore, wktsTaken, outOnZero, "
					+ "ptype, avgScore) values("
					+ "'" + player.getName() 
					+ "', " + player.getMatchesPlayed() 
					+ ", " + player.getTotRuns()
					+ ", " + player.getHighScore()
					+ ", " + player.getWktsTaken() 
					+ ", " + player.getOutOnZero() 
					+ ", '" + player.getType() 
					+ "', " + player.getAvgScore() + ");";

			Connection con = Util.getConnection();
			stmt = con.createStatement();
			
			int ret = stmt.executeUpdate(sql);
			System.out.println(ret);
			stmt.close();
			con.close();
			return ret;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static Player getPlayerById(int id) {
		Player player = null;
		try {
			Connection con = Util.getConnection();
			Statement stmt = con.createStatement();
			String sql = "select * from player where id = " + id;
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				player = new Player();
				player.setId(rs.getInt("id"));
				player.setName(rs.getString("pname"));
				player.setMatchesPlayed(rs.getInt("matchesPlayed"));
				player.setTotRuns(rs.getInt("totRuns"));
				player.setHighScore(rs.getInt("highScore"));
				player.setWktsTaken(rs.getInt("wktsTaken"));
				player.setOutOnZero(rs.getInt("outOnZero"));
				player.setType(rs.getString("ptype"));
				player.setAvgScore(rs.getDouble("avgScore"));
			}
			stmt.close();
			con.close();
			return player;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static int updatePlayer(Player player) {
		int ret = 0;
		try {
			Connection con = Util.getConnection();
			Statement stmt = con.createStatement();
			String sql = "update player set"
					+ " matchesPlayed = " + player.getMatchesPlayed() 
					+ ", totRuns = " + player.getTotRuns() 
					+ ", highScore = " + player.getHighScore() 
					+ ", wktsTaken = " + player.getWktsTaken() 
					+ ", outOnZero = " + player.getOutOnZero() 
					+ ", avgScore = " + player.getAvgScore() 
					+ " where id = " + player.getId();
			ret = stmt.executeUpdate(sql);
			stmt.close();
			con.close();
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return ret;
		}
	}

	public static List<Player> getAllPlayers() {
		List<Player> list = new ArrayList<Player>();  

		try{  
			Connection con=Util.getConnection();  
			Statement stmt = con.createStatement();
			String sql = "select * from player order by avgScore desc";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){  
				Player p = new Player();  
				p.setId(rs.getInt("id"));  
				p.setName(rs.getString("pname"));
				p.setMatchesPlayed(rs.getInt("matchesPlayed"));
				p.setTotRuns(rs.getInt("totRuns"));
				p.setHighScore(rs.getInt("highScore"));
				p.setWktsTaken(rs.getInt("wktsTaken"));
				p.setOutOnZero(rs.getInt("outOnZero"));
				p.setType(rs.getString("ptype"));
				p.setAvgScore(rs.getDouble("avgScore"));

				list.add(p);
			}
			stmt.close();
			con.close();  
		} catch(Exception e) {
			e.printStackTrace();
		}  
		return list;  
	}

	public static int delete(int id){  
		int status=0;  
		try{  
			Connection con = Util.getConnection();  
			Statement stmt = con.createStatement();  
			String sql = "delete from player where id = " + id;  
			status = stmt.executeUpdate(sql);  
			con.close();  
		} catch(Exception e) {
			e.printStackTrace();
		}  
		return status;  
	}
	
	public static List<Player> copyTemp(List<Player> playerList) {
		List<Player> tempList = new ArrayList<>();
		if(playerList!=null) {
			for(Player p : playerList) {
				tempList.add(p);
			}
		}
		return tempList;
	}
	
	public static List<Player> formTeam(int noBowlers) {
		List<Player> team = new ArrayList<>();
//		int noBowlers;
//		int playerCount = AddPlayer.playerCount;
//		int bowlerCount = AddPlayer.bowlerCount;
//		while(true) {
//			System.out.println("Enter number of bowlers required in your team (minimum = 3): ");
//			noBowlers = sc.nextInt();
//			if(noBowlers > bowlerCount) {
//				System.out.println("No. of bowlers exceeds actual bowler count!");
//			} else {
//				break;
//			}
//		}
		List<Player> playerList = getAllPlayers();
		List<Player> tempList = copyTemp(playerList);
//		Collections.sort(playerList);
//		Collections.sort(tempList);
		Iterator<Player> itr = playerList.iterator();
		int i = 0;
		while(i < noBowlers && itr.hasNext()) {
			Player pl = itr.next();
			if(pl.getType().equalsIgnoreCase("bowler")) {
				team.add(pl);
				tempList.remove(pl);
			}
			else {
				continue;
			}
			++i;
		}
		
		i = 0;
		Collections.sort(tempList);
		Iterator<Player> itr1 = tempList.listIterator();
		while(i < 11 - noBowlers && itr1.hasNext()) {
			team.add(itr1.next());
			++i;
		}
		Collections.sort(team);
		return team;
	}
}
