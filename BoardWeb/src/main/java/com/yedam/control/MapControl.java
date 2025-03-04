package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.dao.Control;

public class MapControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.getRequestDispatcher("chart/api.tiles").forward(req, resp);
		req.getRequestDispatcher("WEB-INF/views/chart/map.jsp").forward(req, resp);
	}

}
