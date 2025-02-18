package com.yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yedam.vo.BoardVO;

/*
 추가, 조회, 수정, 삭제
 CRUD
 Create, Read, Update, Delete
 */
public class BoardDAO extends DAO {
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

		return false;
	}

	// 수정
	public boolean updatdBoard(BoardVO board) {

		return false;
	}

	// 삭제
	public boolean deleteBoard(int boardNo) {
		String qry = "delete from tbl_board ";
		qry += "where board_no =" + boardNo;

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
