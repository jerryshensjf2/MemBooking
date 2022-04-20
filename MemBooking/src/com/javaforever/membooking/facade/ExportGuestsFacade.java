package com.javaforever.membooking.facade;

import com.javaforever.membooking.domain.Guest;
import com.javaforever.membooking.service.GuestService;
import com.javaforever.membooking.serviceimpl.GuestServiceImpl;
import com.javaforever.membooking.utils.POIExcelUtil;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;

public class ExportGuestsFacade extends HttpServlet{

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
		try(OutputStream out = response.getOutputStream()){
			try{
				GuestService service  = new GuestServiceImpl();
				response.addHeader("Content-Disposition", "attachment;filename=Guests.xls");
				List<Guest> list = service.listAllGuests();
				List<List<String>> contents = new ArrayList<>();
				String sheetName = "Guest信息表";
				String [] headers = {"Gender","description","Guest Name","Active","ID"};
				
				for (Guest guest:list) {
					String [] row = {guest.getGender(),guest.getDescription(),guest.getGuestName(),guest.getActive().toString(),guest.getId().toString()};
					contents.add(Arrays.asList(row));
				}
				
				POIExcelUtil.exportExcelSheet(out, sheetName, Arrays.asList(headers), contents);
			} catch (Exception e) {
				ServletException se = new ServletException();
				se.setStackTrace(e.getStackTrace());
				throw se;
			}
		}
	}

}
