package com.yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yedam.vo.ReplyVO;

// 댓글목록, 등록, 삭제, 상세조회
public class ReplyDAO extends DAO {
	
	// 부서별 인원현황 차트
	public List<Map<String, Object>> chartData() {
		String sql = "select emp.department_id, dept.department_name, count(1) cnt "
				+ "   from employees emp "
				+ "   join departments dept "
				+ "   on   emp.department_id = dept.department_id "
				+ "   group by emp.department_id, dept.department_name";
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		try {
			psmt = getConnect().prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("dept_name", rs.getString(2));
				map.put("dept_count", rs.getInt(3));
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	// 페이징을 위한 boardNo에 따른 댓글의 갯수 계산
	public int replyCount(int boardNo) {
		String sql = "select count(1) from tbl_reply where board_no = ?";
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setInt(1, boardNo);
			rs = psmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return 0;
	}
	
	// 목록
	public List<ReplyVO> replyList(int boardNo, int page){
		List<ReplyVO> list = new ArrayList<>();
		
		String sql = "select tbl_a.* "
				+ "from (select /*+ INDEX_DESC (r pk_reply) */ "
				+ "             rownum rn, reply_no, reply, replyer, board_no, reply_date "
				+ "      from tbl_reply r "
				+ "      where board_no = ?) tbl_a "
				+ "where tbl_a.rn > (? - 1) * 5 "
				+ "and   tbl_a.rn <= ? * 5";
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setInt(1, boardNo);
			psmt.setInt(2, page);
			psmt.setInt(3, page);
			rs = psmt.executeQuery(); // 쿼리한 결과를 담아두기 위한 Set 컬렉션
			
			while (rs.next()) { // 조회된 결과가 있으면 
				ReplyVO rvo = new ReplyVO();
				rvo.setBoardNo(rs.getInt("board_no"));
				rvo.setReplyNo(rs.getInt("reply_no"));
				rvo.setReply(rs.getString("reply"));
				rvo.setReplyer(rs.getString("replyer"));
				rvo.setReplyDate(rs.getDate("reply_date"));
				
				list.add(rvo); // 건수만큼 list추가
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return list;
	}
	
	// 상세조회
	public ReplyVO selectReply(int replyNo) {
		ReplyVO rvo = new ReplyVO();
		String sql = "select reply_no "
				+ "      ,reply "
				+ "      ,replyer "
				+ "      ,reply_date "
				+ "from tbl_reply "
				+ "where reply_no = ?";
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setInt(1, replyNo);
			rs = psmt.executeQuery(); // 쿼리한 결과를 담아두기 위한 Set 컬렉션
			
			if (rs.next()) { // 조회된 결과가 있으면 
				rvo.setReplyNo(rs.getInt("reply_no"));
				rvo.setReply(rs.getString("reply"));
				rvo.setReplyer(rs.getString("replyer"));
				rvo.setReplyDate(rs.getDate("reply_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return rvo;
	}
	
	// 등록
	public boolean insertReply(ReplyVO reply) {
		String sql1 = "select reply_seq.nextval from dual";
		String sql = "insert into tbl_reply (reply_no, reply, replyer, board_no) ";
		sql += " values(?, ?, ?, ?) ";
		try {
			psmt = getConnect().prepareStatement(sql1);
			rs = psmt.executeQuery();
			if(rs.next()) {
				reply.setReplyNo(rs.getInt(1)); // 첫번째 컬럼
			}
			
			
			psmt = getConnect().prepareStatement(sql);
			psmt.setInt(1, reply.getReplyNo());
			psmt.setString(2, reply.getReply());
			psmt.setString(3, reply.getReplyer());
			psmt.setInt(4, reply.getBoardNo());
			
			int r = psmt.executeUpdate();
			
			if (r == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 삭제
	public boolean deleteReply(int replyNo) {
		String sql = "delete from tbl_reply ";
		sql += "where reply_no = ?";
		
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setInt(1, replyNo);
			int r = psmt.executeUpdate();

			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
