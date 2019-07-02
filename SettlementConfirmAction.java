package com.internousdev.red.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.red.dao.CartInfoDAO;
import com.internousdev.red.dao.DestinationInfoDAO;
import com.internousdev.red.dto.CartInfoDTO;
import com.internousdev.red.dto.DestinationInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class SettlementConfirmAction extends ActionSupport implements SessionAware{

	public Map<String, Object> session;
	private ArrayList<DestinationInfoDTO> destinationInfoList;
	private List<CartInfoDTO> cartInfoDTOList;

	public String execute(){

		//ログイン状況の確認。
		if(!(Objects.isNull(session.get("logined"))) && Integer.parseInt(String.valueOf(session.get("logined"))) == 1){

			String userId = String.valueOf(session.get("userId"));
			CartInfoDAO cartInfoDAO = new CartInfoDAO();
			DestinationInfoDAO destinationInfoDAO = new DestinationInfoDAO();

			//カート情報を取得・リストに格納し、セッションに入れる。
			cartInfoDTOList = cartInfoDAO.getCartInfoDTOList(userId);
			session.put("cartInfoDTOList", cartInfoDTOList);
			destinationInfoList = destinationInfoDAO.selectDestinationInfo(userId);

			return SUCCESS;

		}
		else{
			session.put("logined", 0);
			session.put("cartFlag", "cartFlag");
			return "login";
		}
	}

	@Override
	public void setSession(Map<String, Object> session){
		this.session = session;
	}
	public ArrayList<DestinationInfoDTO> getDestinationInfoList(){
		return this.destinationInfoList;
	}
	public List<CartInfoDTO> getCartInfoDTOList(){
		return this.cartInfoDTOList;
	}
	public void setCartInfoDTOList(List<CartInfoDTO> cartInfoDTOList){
		this.cartInfoDTOList = cartInfoDTOList;
	}

}
