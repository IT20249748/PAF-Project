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

@Path("/payment")
public class Payment {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String add(String j_text)
	{ 
		paymentModel payments =new paymentModel();
		JsonObject jdata = new JsonParser().parse(j_text).getAsJsonObject();

		if(jdata.get("bill_id").getAsString()!=""&&jdata.get("card_number").getAsString()!=""&&jdata.get("card_type").getAsString()!=""&&jdata.get("amount").getAsString()!="") {

			payments.addPayment(Integer.parseInt(jdata.get("bill_id").getAsString()),jdata.get("card_number").getAsString(),jdata.get("card_type").getAsString(),Double.parseDouble(jdata.get("amount").getAsString()));
			
			JSONObject js_obj = new JSONObject();
			js_obj.put("success", payments.getSuccessful());
			
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

		paymentModel payments =new paymentModel();
		JsonObject jdata = new JsonParser().parse(j_text).getAsJsonObject();

		if(jdata.get("id").getAsString()!=""&&jdata.get("bill_id").getAsString()!=""&&jdata.get("card_number").getAsString()!=""&&jdata.get("card_type").getAsString()!=""&&jdata.get("amount").getAsString()!="") {

			payments.editPayment(Integer.parseInt(jdata.get("id").getAsString()),Integer.parseInt(jdata.get("bill_id").getAsString()),jdata.get("card_number").getAsString(),jdata.get("card_type").getAsString(),Double.parseDouble(jdata.get("amount").getAsString()));
			
			JSONObject js_obj = new JSONObject();
			js_obj.put("success", payments.getSuccessful());
	
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

		paymentModel payments =new paymentModel();
		JsonObject jdata = new JsonParser().parse(j_text).getAsJsonObject();
		if(jdata.get("id").getAsString()!="") {
	
			payments.deletePayment(Integer.parseInt(jdata.get("id").getAsString()));
			
			JSONObject js_obj = new JSONObject();
			js_obj.put("success", payments.getSuccessful());
	
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
		paymentModel payments =new paymentModel();
		return payments.getPayment();
	}
	
}
