package com.javaforever.membooking.facade;

import com.javaforever.membooking.domain.Booking;
import com.javaforever.membooking.service.BookingService;
import com.javaforever.membooking.serviceimpl.BookingServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

public class UpdateBookingFacade extends HttpServlet{

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		processRequest(request, response);
	}

	public String getServletInfo(){
		return "Powered by Mind Rules.";
	}

	protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		Map<String,Object> result = new TreeMap<String,Object>();
		try {
			Booking booking = new Booking();
			booking.setRoomId(null);
			booking.setOccuDate(request.getParameter("occuDate"));
			booking.setDescription(request.getParameter("description"));
			booking.setBookingName(request.getParameter("bookingName"));
			booking.setActive(Boolean.parseBoolean(request.getParameter("active")));
			booking.setId(Long.parseLong(request.getParameter("id")));
			BookingService service = new BookingServiceImpl();
			boolean success = service.updateBooking(booking);
			result.put("success",success);
			result.put("data",null);
			out.print(JSONObject.fromObject(result).toString());
		} catch (Exception e){
			e.printStackTrace();
			result.put("success",false);
			result.put("reason",e.getMessage());
			out.print(JSONObject.fromObject(result).toString());
		} finally {
			out.close();
		}
	}

}
