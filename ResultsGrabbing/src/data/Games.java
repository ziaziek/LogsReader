// default package
// Generated 2013-05-27 13:40:06 by Hibernate Tools 3.4.0.CR1
package data;
/**
 * Games generated by hbm2java
 */

import java.util.List;

import data.interfaces.ISample;

public class Games implements java.io.Serializable, ISample {

	private int id;
	private Integer IdMatches;
	private Integer IdPlayers;
	private Integer IdOponents;
	private Integer Age;
	private Integer OponentRank;
	private double AvgPointDiff;
	private GamesResults result;

	public Games() {
	}

	public Games(int id) {
		this.id = id;
	}

	public Games(int id, Integer IdMatches, Integer IdPlayers,
			Integer IdOponents, Integer Age, Integer OponentRank,
			double AvgPointDiff, GamesResults result) {
		this.id = id;
		this.IdMatches = IdMatches;
		this.IdPlayers = IdPlayers;
		this.IdOponents = IdOponents;
		this.Age = Age;
		this.OponentRank = OponentRank;
		this.AvgPointDiff = AvgPointDiff;
		this.result = result;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getIdMatches() {
		return this.IdMatches;
	}

	public void setIdMatches(Integer IdMatches) {
		this.IdMatches = IdMatches;
	}

	public Integer getIdPlayers() {
		return this.IdPlayers;
	}

	public void setIdPlayers(Integer IdPlayers) {
		this.IdPlayers = IdPlayers;
	}

	public Integer getIdOponents() {
		return this.IdOponents;
	}

	public void setIdOponents(Integer IdOponents) {
		this.IdOponents = IdOponents;
	}

	public Integer getAge() {
		return this.Age;
	}

	public void setAge(Integer Age) {
		this.Age = Age;
	}

	public Integer getOponentRank() {
		return this.OponentRank;
	}

	public void setOponentRank(Integer OponentRank) {
		this.OponentRank = OponentRank;
	}

	public double getAvgPointDiff() {
		return this.AvgPointDiff;
	}

	public void setAvgPointDiff(double AvgPointDiff) {
		this.AvgPointDiff = AvgPointDiff;
	}

	public GamesResults getResult() {
		return this.result;
	}

	public void setResult(GamesResults result) {
		this.result = result;
	}

	@Override
	public List<String> getAttributeNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getValue(String attrName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<?> getValueType(String attrName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setValue(String attrName, Object v) {
		// TODO Auto-generated method stub
		return false;
	}

}
