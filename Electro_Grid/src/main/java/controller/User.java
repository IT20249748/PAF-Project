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

@Path("/user")
public class User {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String add(String j_text)
	{
		userModel users =new userModel();
		JsonObject jdata = new JsonParser().parse(j_text).getAsJsonObject();

		if(jdata.get("name").getAsString()!=""&&jdata.get("nic").getAsString()!=""&&jdata.get("address").getAsString()!=""&&jdata.get("email").getAsString()!=""&&jdata.get("phone").getAsString()!="") {

			users.addUsers(jdata.get("name").getAsString(),jdata.get("nic").getAsString(),jdata.get("address").getAsString(),jdata.get("email").getAsString(),jdata.get("phone").getAsString());
			
			JSONObject js_obj = new JSONObject();
			js_obj.put("success", users.getSuccessful());
			
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

		userModel users =new userModel();
		JsonObject jdata = new JsonParser().parse(j_text).getAsJsonObject();

		if(jdata.get("id").getAsString()!=""&&jdata.get("name").getAsString()!=""&&jdata.get("nic").getAsString()!=""&&jdata.get("address").getAsString()!=""&&jdata.get("email").getAsString()!=""&&jdata.get("phone").getAsString()!="") {

			users.editUsers(Integer.parseInt(jdata.get("id").getAsString()),jdata.get("name").getAsString(),jdata.get("nic").getAsString(),jdata.get("address").getAsString(),jdata.get("email").getAsString(),jdata.get("phone").getAsString());
			
			JSONObject js_obj = new JSONObject();
			js_obj.put("success", users.getSuccessful());
	
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

		userModel users =new userModel();
		JsonObject jdata = new JsonParser().parse(j_text).getAsJsonObject();
		if(jdata.get("id").getAsString()!="") {
	
			users.deleteUsers(Integer.parseInt(jdata.get("id").getAsString()));
			
			JSONObject js_obj = new JSONObject();
			js_obj.put("success", users.getSuccessful());
	
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
		userModel users =new userModel();
		return users.getUsers();
	}
	
}
