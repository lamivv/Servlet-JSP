package com.yedam.dao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 
public interface Control {
	// 기억이 가물가물 가물치...
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	// 구현부분없이 추상메소드만 선언
}
