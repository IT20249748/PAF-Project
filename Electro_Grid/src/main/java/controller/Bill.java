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

@Path("/bill")
public class Bill {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String add(String j_text)
	{
		billModel bills =new billModel();
		JsonObject jdata = new JsonParser().parse(j_text).getAsJsonObject();

		if(jdata.get("user_id").getAsString()!=""&&jdata.get("unit_usage").getAsString()!=""&&jdata.get("date").getAsString()!=""&&jdata.get("unit_price").getAsString()!=""&&jdata.get("total").getAsString()!="") {

			bills.addBill(Integer.parseInt(jdata.get("user_id").getAsString()),Integer.parseInt(jdata.get("unit_usage").getAsString()),jdata.get("date").getAsString(),Double.parseDouble(jdata.get("unit_price").getAsString()),Double.parseDouble(jdata.get("total").getAsString()));
			
			JSONObject js_obj = new JSONObject();
			js_obj.put("success", bills.getSuccessful());
			
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

		billModel bills =new billModel();
		JsonObject jdata = new JsonParser().parse(j_text).getAsJsonObject();

		if(jdata.get("id").getAsString()!=""&&jdata.get("user_id").getAsString()!=""&&jdata.get("unit_usage").getAsString()!=""&&jdata.get("date").getAsString()!=""&&jdata.get("unit_price").getAsString()!=""&&jdata.get("total").getAsString()!="") {

			bills.editBill(Integer.parseInt(jdata.get("id").getAsString()),Integer.parseInt(jdata.get("user_id").getAsString()),Integer.parseInt(jdata.get("unit_usage").getAsString()),jdata.get("date").getAsString(),Double.parseDouble(jdata.get("unit_price").getAsString()),Double.parseDouble(jdata.get("total").getAsString()));
			
			JSONObject js_obj = new JSONObject();
			js_obj.put("success", bills.getSuccessful());
	
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

		billModel bills =new billModel();
		JsonObject jdata = new JsonParser().parse(j_text).getAsJsonObject();
		if(jdata.get("id").getAsString()!="") {
	
			bills.deleteBill(Integer.parseInt(jdata.get("id").getAsString()));
			
			JSONObject js_obj = new JSONObject();
			js_obj.put("success", bills.getSuccessful());
	
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
		billModel bills =new billModel();
		return bills.getBill();
	}
	
}
