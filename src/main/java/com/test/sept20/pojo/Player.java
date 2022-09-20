package com.test.sept20.pojo;

public class Player implements Comparable<Player>{
	
	int id;
	String name;
	int matchesPlayed;
	int totRuns;
	int highScore;
	int wktsTaken;
	int outOnZero;
	String type;
	double avgScore;
	
	public Player() {
		super();
	}
	public Player(String name, int matchesPlayed, int totRuns, int highScore, int wktsTaken, int outOnZero, String type,
			double avgScore) {
		super();
		this.name = name;
		this.matchesPlayed = matchesPlayed;
		this.totRuns = totRuns;
		this.highScore = highScore;
		this.wktsTaken = wktsTaken;
		this.outOnZero = outOnZero;
		this.type = type;
		this.avgScore = avgScore;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMatchesPlayed() {
		return matchesPlayed;
	}
	public void setMatchesPlayed(int matchesPlayed) {
		this.matchesPlayed = matchesPlayed;
	}
	public int getTotRuns() {
		return totRuns;
	}
	public void setTotRuns(int totRuns) {
		this.totRuns = totRuns;
	}
	public int getHighScore() {
		return highScore;
	}
	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
	public int getWktsTaken() {
		return wktsTaken;
	}
	public void setWktsTaken(int wktsTaken) {
		this.wktsTaken = wktsTaken;
	}
	public int getOutOnZero() {
		return outOnZero;
	}
	public void setOutOnZero(int outOnZero) {
		this.outOnZero = outOnZero;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getAvgScore() {
		return avgScore;
	}
	public void setAvgScore(double avgScore) {
		this.avgScore = avgScore;
	}
	
	@Override
	public int compareTo(Player o) {
		return (int) (o.getAvgScore() - this.getAvgScore());
	}

}
