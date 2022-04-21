package com.javaforever.membooking.facade;

import com.javaforever.membooking.domain.Booking;
import com.javaforever.membooking.service.BookingService;
import com.javaforever.membooking.serviceimpl.BookingServiceImpl;
import com.javaforever.membooking.utils.BooleanUtil;
import com.javaforever.membooking.utils.StringUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

@WebServlet(name = "searchBookingsByFieldsByPageFacade",urlPatterns = "/facade/searchBookingsByFieldsByPageFacade")
public class SearchBookingsByFieldsByPageFacade extends HttpServlet{

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
			Boolean active = StringUtil.isBlank(request.getParameter("active"))?null:BooleanUtil.parseBoolean(request.getParameter("active"));
			String bookingName = StringUtil.isBlank(request.getParameter("bookingName"))?null:request.getParameter("bookingName");
			String description = StringUtil.isBlank(request.getParameter("description"))?null:request.getParameter("description");
			String occuDate = StringUtil.isBlank(request.getParameter("occuDate"))?null:request.getParameter("occuDate");
			
			Long pagenum = StringUtil.isBlank(request.getParameter("page"))?1: Long.parseLong(request.getParameter("page"));
			Long pagesize = StringUtil.isBlank(request.getParameter("rows"))?10: Long.parseLong(request.getParameter("rows"));
			Boolean lastFlagBool = StringUtil.isBlank(request.getParameter("last"))?false: BooleanUtil.parseBoolean(request.getParameter("last"));
			
			booking.setActive(active);
			booking.setBookingName(bookingName);
			booking.setDescription(description);
			booking.setOccuDate(occuDate);
			//booking.setRoomId(roomId);
			BookingService service = new BookingServiceImpl();
			Long recordCount = service.countSearchBookingsByFieldsRecords(booking);
			Long pageCount = (long)Math.ceil((double)recordCount/pagesize);
			if (pageCount <= 1) pageCount = 1L;
			if (pagenum==null || pagenum <= 1) pagenum = 1L;
			if (pagenum >= pageCount) pagenum = pageCount;
			if (lastFlagBool) pagenum = pageCount;
			List<Booking> list = service.searchBookingsByFieldsByPage(booking,pagenum,pagesize);
			result.put("success",true);
			result.put("rows",list);
			result.put("pagesize",pagesize);
			result.put("page",pagenum);
			result.put("total",recordCount);
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
