package com.surveyApp.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.surveyApp.database.Database;
import com.surveyApp.entity.Feedback;
import com.surveyApp.entity.Survey;

public class SurveyDAOImpl {
	
	public void save(Survey survey) {		
		add(survey, true);	   
	}

	public void update(Survey survey) {		
		add(survey, false);	
	}
	
	private void add(Survey survey, boolean isCreate) {	
		
		try {
			Database database = new Database();
			Connection connection = database.Get_Connection();
			String sqlStatement;
			int surveyId = isCreate ? survey.getSurveyId() + 1 : survey.getSurveyId();
			
			if(isCreate) {
				sqlStatement = "INSERT INTO tblSurvey"
						+ "(userId, description, surveyId) VALUES"
						+ "(?,?,?)";
			}else {
				sqlStatement = "UPDATE tblSurvey SET userId = ? , description = ?"
						+ " WHERE surveyId = ?";
			}
			
			PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
			
			preparedStatement.setInt(1, survey.getUserId());
			preparedStatement.setString(2, survey.getDescription());
			preparedStatement.setInt(3, surveyId);			

			preparedStatement .executeUpdate();			
			
			preparedStatement.close();	
			connection.close();						
			
		}catch(Exception e) {
			System.out.println("Exception Errors 2dfdfdf " + e);
		}
		
	}

	public void delete(int surveyId) {
		
		try {			
			Database database = new Database();
			Connection connection = database.Get_Connection();			
			PreparedStatement ps = connection.prepareStatement("DELETE FROM tblsurvey WHERE surveyId = ?");
			ps.setInt(1, surveyId);
			
			ps.executeUpdate();
			
			ps.close();
			
		}catch(Exception e) {
			System.out.println("Exception Errors 2 " + e);
		}
	}
	
	public List<Survey> getAllSurvey() {
		ArrayList<Survey> surveyList = new ArrayList<Survey>();
		
		try {
			Database database = new Database();
			Connection connection = database.Get_Connection();			
			PreparedStatement ps = connection.prepareStatement("SELECT surveyId, userId, description FROM tblSurvey ORDER BY id ASC");
			
			ResultSet rs = ps.executeQuery();			
			
			while(rs.next()) {
				
				Survey survey = new Survey();				
				survey.setSurveyId(rs.getInt("surveyId"));				
				survey.setUserId(rs.getInt("userId"));				
				survey.setDescription(rs.getString("description"));				
				surveyList.add(survey);	
				
			}	
			
			rs.close();
			ps.close();	
			connection.close();
		}catch(Exception e) {
			System.out.println("Exception Errors 2 " + e);
		}
		
		return surveyList;
	}

	public List<Survey> getAllSurvey(int surveyid) {		
		ArrayList<Survey> surveyList = new ArrayList<Survey>();
		
		try {
			Database database = new Database();
			Connection connection = database.Get_Connection();			
			PreparedStatement ps = connection.prepareStatement("SELECT surveyId, userId, description FROM tblSurvey WHERE surveyId = " + surveyid + " ORDER BY id ASC");
			
			ResultSet rs = ps.executeQuery();			
			
			while(rs.next()) {
				
				Survey survey = new Survey();				
				survey.setSurveyId(rs.getInt("surveyId"));				
				survey.setUserId(rs.getInt("userId"));				
				survey.setDescription(rs.getString("description"));				
				surveyList.add(survey);					
			}			
			rs.close();
			ps.close();	
			connection.close();
			
		}catch(Exception e) {
			System.out.println("Exception Errors 2 " + e);
		}
		
		return surveyList;
	}
	
	public List<Feedback> getAllFeedback() {		
		ArrayList<Feedback> feedbackList = new ArrayList<Feedback>();
		
		try {
			Database database = new Database();
			Connection connection = database.Get_Connection();			
			PreparedStatement ps = connection.prepareStatement("SELECT feedbackId, surveyId, userId, description FROM tblFeedback ORDER BY id ASC");

			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Feedback feedback = new Feedback();
				feedback.setFeedbackId(rs.getInt("feedbackId"));
				feedback.setSurveyId(rs.getInt("surveyId"));
				feedback.setUserId(rs.getInt("userId"));
				feedback.setDescription(rs.getString("description"));
				feedbackList.add(feedback);
				
			}
			
			rs.close();
			ps.close();	
			connection.close();
		}catch(Exception e) {
			System.out.println("Exception Errors 2 " + e);
		}
		
		return feedbackList;		
	}
	
	public List<Feedback> getAllFeedback(int surveyid) {		
		ArrayList<Feedback> feedbackList = new ArrayList<Feedback>();
		
		try {
			Database database = new Database();
			Connection connection = database.Get_Connection();			
			PreparedStatement ps = connection.prepareStatement("SELECT feedbackId, surveyId, userId, description FROM tblFeedback WHERE surveyId = " + surveyid + " ORDER BY id ASC");
			
			ResultSet rs = ps.executeQuery();			
			
			while(rs.next()) {
				
				Feedback feedback = new Feedback();
				feedback.setFeedbackId(rs.getInt("feedbackId"));
				feedback.setSurveyId(rs.getInt("surveyId"));
				feedback.setUserId(rs.getInt("userId"));
				feedback.setDescription(rs.getString("description"));
				feedbackList.add(feedback);
				
			}
			
			rs.close();
			ps.close();	
			connection.close();
		}catch(Exception e) {
			System.out.println("Exception Errors 2adcadcadc " + e);
		}
		
		return feedbackList;
		
	}

}
