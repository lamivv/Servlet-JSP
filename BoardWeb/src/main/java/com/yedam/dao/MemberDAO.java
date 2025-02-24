package com.yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.vo.MemberVO;

public class MemberDAO extends DAO {
	
	// 삭제
	public boolean isOk(String mid) {
		String sql = "delete from tbl_member ";
		sql += "where member_id = ?";
		
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setString(1, mid);
			int r = psmt.executeUpdate();

			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	// 회원목록
	public List<MemberVO> members() {
		String sql = "select member_id " //
				+ "         ,passwd " //
				+ "         ,member_name " //
				+ "         ,responsibility " //
				+ "   from  tbl_member "; //
		List<MemberVO> list = new ArrayList<>();
		// 조회
		try {
			psmt = getConnect().prepareStatement(sql);
			rs = psmt.executeQuery(); // 쿼리실행
			
			while (rs.next()) { // 조회된 결과가 있으면 
				MemberVO mvo = new MemberVO();
				mvo.setMemberId(rs.getString("member_id"));
				mvo.setPasswd(rs.getString("passwd"));
				mvo.setMamberName(rs.getString("member_name"));
				mvo.setResponsibility(rs.getString("responsibility"));
				
				list.add(mvo); // 건수만큼 list추가
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return list;
	}
	
	public MemberVO login(String id, String pw) {
		String sql = "select member_id " //
				+ "         ,passwd " //
				+ "         ,member_name " //
				+ "         ,responsibility " //
				+ "   from  tbl_member " //
				+ "   where member_id = ? " //
				+ "   and   passwd = ? ";
		// 조회
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			
			rs = psmt.executeQuery(); // 쿼리실행
			
			if (rs.next()) { // 조회된 결과가 있으면 
				MemberVO mvo = new MemberVO();
				mvo.setMemberId(rs.getString("member_id"));
				mvo.setPasswd(rs.getString("passwd"));
				mvo.setMamberName(rs.getString("member_name"));
				mvo.setResponsibility(rs.getString("responsibility"));
				return mvo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return null;
	}
	

}
