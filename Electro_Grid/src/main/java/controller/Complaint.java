package controller;


import org.apache.tomcat.util.json.JSONParser;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
import java.util.ArrayList;

import com.google.gson.*;
import org.json.simple.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import model.*;

@Path("/complaint")
public class Complaint {
	
	complaintModel complaints =new complaintModel();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String add(String j_text)
	{
		complaintModel complaints =new complaintModel();
		JsonObject jdata = new JsonParser().parse(j_text).getAsJsonObject();

		if(jdata.get("user_id").getAsString()!=""&&jdata.get("type").getAsString()!=""&&jdata.get("complaint").getAsString()!=""&&jdata.get("date").getAsString()!="") {

			complaints.addComplaint(Integer.parseInt(jdata.get("user_id").getAsString()),jdata.get("type").getAsString(),jdata.get("complaint").getAsString(),jdata.get("date").getAsString());
			
			JSONObject js_obj = new JSONObject();
			js_obj.put("success", complaints.getSuccessful());
			
			return js_obj.toString();
			
		}else {
			
			JSONObject js_obj = new JSONObject();
			js_obj.put("success", "required");
			
			return js_obj.toString();
			
		}
			
		
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String edit(String j_text)
	{

		complaintModel complaints =new complaintModel();
		JsonObject jdata = new JsonParser().parse(j_text).getAsJsonObject();

		if(jdata.get("id").getAsString()!=""&&jdata.get("user_id").getAsString()!=""&&jdata.get("type").getAsString()!=""&&jdata.get("complaint").getAsString()!=""&&jdata.get("date").getAsString()!="") {

			complaints.editComplaint(Integer.parseInt(jdata.get("id").getAsString()),Integer.parseInt(jdata.get("user_id").getAsString()),jdata.get("type").getAsString(),jdata.get("complaint").getAsString(),jdata.get("date").getAsString());
			
			JSONObject js_obj = new JSONObject();
			js_obj.put("success", complaints.getSuccessful());
	
			return js_obj.toString();
			
		}else {
			
			JSONObject js_obj = new JSONObject();
			js_obj.put("success", "required");
			
			return js_obj.toString();
			
		}
			
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String delete(String j_text)
	{

		complaintModel complaints =new complaintModel();
		JsonObject jdata = new JsonParser().parse(j_text).getAsJsonObject();
		if(jdata.get("id").getAsString()!="") {
	
			complaints.deleteComplaint(Integer.parseInt(jdata.get("id").getAsString()));
			
			JSONObject js_obj = new JSONObject();
			js_obj.put("success", complaints.getSuccessful());
	
			return js_obj.toString();
			
		}else {
			
			JSONObject js_obj = new JSONObject();
			js_obj.put("success", "required");
			
			return js_obj.toString();
			
		}
		
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String view(String j_text)
	{
		complaintModel complaints =new complaintModel();
		return complaints.getComplaint();
	}
	


@POST
@Path("/get")
@Produces(MediaType.TEXT_HTML)
public String viewOne(String app_text)
{
	JsonObject app = new JsonParser().parse(app_text).getAsJsonObject();
	complaintModel complaints =new complaintModel();
	return complaintModel.getOnecomplaint(Integer.parseInt(app.get("id").getAsString())).toString();
}

}