package com.yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.vo.BoardVO;

/*
 추가, 조회, 수정, 삭제
 CRUD
 Create, Read, Update, Delete
 */
public class BoardDAO extends DAO {
	// 글 조회수 증가
	public void updateCount(int boardNo) {
		String sql = "update tbl_board " + "   set    view_cnt = view_cnt + 1 " + "   where board_no = ?";
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setInt(1, boardNo);
			psmt.executeUpdate(); // 쿼리문 실행

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 상세조회 글번호(매개값)-> 전체정보 반환
	public BoardVO getboard(int boardNo) {
		String sql = "select board_no" //
				+ "         ,title" //
				+ "         ,content" //
				+ "         ,writer" //
				+ "         ,write_date" //
				+ "         ,view_cnt" //
				+ "   from tbl_board" //
				+ "   where board_no =?";
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setInt(1, boardNo);
			rs = psmt.executeQuery();
			// 조회결과가 존재하면
			if (rs.next()) {
				BoardVO board = new BoardVO();
				board.setBoardNo(rs.getInt("board_no"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setWriter(rs.getString("writer"));
				board.setWriteDate(rs.getDate("write_date"));
				board.setViewCnt(rs.getInt("view_cnt"));

				// 결과반환
				return board;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // 조회결과없음
	}

	// 조회
	public List<BoardVO> selectBoard() {
		List<BoardVO> boardList = new ArrayList<>();
		String qry = "select * from tbl_board order by board_no";

		try {
			psmt = getConnect().prepareStatement(qry);
			rs = psmt.executeQuery();

			while (rs.next()) {
				BoardVO boardl = new BoardVO();
				boardl.setBoardNo(rs.getInt("board_no"));
				boardl.setTitle(rs.getString("title"));
				boardl.setContent(rs.getString("content"));
				boardl.setWriter(rs.getString("writer"));
				boardl.setWriteDate(rs.getDate("write_date"));
				boardl.setViewCnt(rs.getInt("view_cnt"));

				boardList.add(boardl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boardList;
	} // end of selectBoard()

	// 추가
	public boolean insertBoard(BoardVO board) {
		String sql = "insert into tbl_board (board_no, title, content, writer) " + "values(board_seq.nextval,?,?,?)";
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setString(1, board.getTitle());
			psmt.setString(2, board.getContent());
			psmt.setString(3, board.getWriter());

			int r = psmt.executeUpdate(); // insert쿼리 실행
			if (r == 1) {
				return true; // 정상 등록 됨
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; // 등록되지 않음
	}

	// 수정
	public boolean updatdBoard(BoardVO board) {
		String sql = "update tbl_board " //
				+ "set    title = ? " //
				+ "      ,content = ? " //
				+ "where board_no = ?";
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setString(1, board.getTitle());
			psmt.setString(2, board.getContent());
			psmt.setInt(3, board.getBoardNo());
			int r = psmt.executeUpdate(); // 쿼리문 실행

			if (r > 0) {
				return true; // 수정성공
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; // 수정실패
	}

	// 삭제
	public boolean deleteBoard(int boardNo) {
		String qry = "delete from tbl_board ";
		qry += "where board_no = " + boardNo;

		try {
			psmt = getConnect().prepareStatement(qry);
			rs = psmt.executeQuery();
			int r = psmt.executeUpdate(qry);

			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}
}
